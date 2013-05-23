package com.synapticswarm.minijvm.ui;

import com.synapticswarm.minijvm.JVM;
import com.synapticswarm.minijvm.ObservableStack;
import com.synapticswarm.minijvm.examples.AdditionExample;
import com.synapticswarm.minijvm.examples.HelloWorldExample;
import com.synapticswarm.minijvm.model.MiniClassFile;
import com.synapticswarm.minijvm.ui.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class UIController {

    @FXML
    TableView tableViewStack;
    @FXML
    TableColumn stackValueColumn;
    @FXML
    TableColumn stackTypeColumn;

    @FXML
    TableView tableViewLocalVariables;
    @FXML
    TableColumn localVariableValueColumn;
    @FXML
    TableColumn localVariableTypeColumn;
    @FXML
    TableColumn localVariableIndexColumn;

    @FXML
    TableView tableViewMethodDisplay;
    @FXML
    TableColumn methodDisplayOffsetColumn;
    @FXML
    TableColumn methodDisplayOpcodeColumn;
    @FXML
    TableColumn methodDisplayArgColumn;
    @FXML
    TableColumn methodDisplayCommentColumn;

    @FXML
    TableView<ConstantPoolEntryDisplayModel> tableViewConstantPool;
    @FXML
    TableColumn constantPoolIndexColumn;
    @FXML
    TableColumn constantPoolTypeColumn;
    @FXML
    TableColumn constantPoolValueColumn;
    @FXML
    TableColumn constantPoolCommentColumn;


    @FXML
    TextArea systemOutTextArea;

    @FXML
    ComboBox<String> choiceBox_examples;

    private AtomicInteger count = new AtomicInteger(0);

    private ObservableList<ConstantPoolEntryDisplayModel> constantPoolEntries;
    private ObservableList<MethodEntryDisplayModel> methodEntries;
    private ObservableList<StackEntryDisplayModel> stackEntries = FXCollections.observableArrayList();
    private ObservableList<LocalVariableEntryDisplayModel> localVariableEntries = FXCollections.observableArrayList();

    //TODO must be a better way of capturing rows
    private List<TableRow> methodRows = new ArrayList<TableRow>();

    @FXML
    @SuppressWarnings("unchecked")
    public void initialize() {

        tableViewStack
                .setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewConstantPool
                .setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewConstantPool.setEditable(true);
        tableViewMethodDisplay
                .setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewLocalVariables
                .setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //HelloWorldExample.Data data = new HelloWorldExample.Data();

        //constant pool
        initTextFieldColumn(this.constantPoolIndexColumn, "indexProperty");
        initChoiceBoxColumn(this.constantPoolTypeColumn, ConstantPoolEntryDisplayModel.constantPoolTypes, "typeProperty");
        initTextFieldColumn(this.constantPoolValueColumn, "valueProperty");
        initTextFieldColumn(this.constantPoolCommentColumn, "commentProperty");

        //method display
        tableViewMethodDisplay.setItems(methodEntries);
        initTextFieldColumn(this.methodDisplayOffsetColumn, "offsetProperty");
        initChoiceBoxColumn(this.methodDisplayOpcodeColumn, MethodEntryDisplayModel.opCodes, "opcodeProperty");
        initTextFieldColumn(this.methodDisplayArgColumn, "argProperty");
        initTextFieldColumn(this.methodDisplayCommentColumn, "commentProperty");

        //stack display
        tableViewStack.setItems(stackEntries);
        stackValueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        stackValueColumn.setCellValueFactory(new PropertyValueFactory("valueProperty"));
        stackTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        stackTypeColumn.setCellValueFactory(new PropertyValueFactory("typeProperty"));

        //local variable display
        tableViewLocalVariables.setItems(localVariableEntries);
        localVariableIndexColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        localVariableIndexColumn.setCellValueFactory(new PropertyValueFactory("indexProperty"));
        localVariableValueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        localVariableValueColumn.setCellValueFactory(new PropertyValueFactory("valueProperty"));
        localVariableTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        localVariableTypeColumn.setCellValueFactory(new PropertyValueFactory("typeProperty"));

        //Capture System.out to the mini-console
        SystemOutCapturePrintStream stream = new SystemOutCapturePrintStream(this.systemOutTextArea, System.out);
        System.setOut(stream);

        //Set up our examples
        choiceBox_examples.getItems().add("None");
        choiceBox_examples.getItems().add("Hello World");
        choiceBox_examples.getItems().add("Addition");
        choiceBox_examples.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        loadExampleFired(choiceBox_examples.getSelectionModel().getSelectedItem());
                    }
                }
        );
        choiceBox_examples.getSelectionModel().select("Hello World");
    }

    //I cannot believe I have to write this method. why do the properties not magically update?!
    public static void setPropertyValue(Object target, String propertyName, String value) {
        try {
            String name = propertyName.replaceFirst(propertyName.substring(0, 1), propertyName.substring(0, 1).toUpperCase());
            Method method = target.getClass().getMethod("set" + name, String.class);
            method.invoke(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private void initTextFieldColumn(TableColumn column, final String propertyName) {
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setCellValueFactory(new PropertyValueFactory(propertyName));

        column.setOnEditCommit(
                new EventHandler<CellEditEvent<? extends Object, String>>() {
                    @Override
                    public void handle(CellEditEvent<? extends Object, String> t) {
                        setPropertyValue(t.getRowValue(), propertyName, t.getNewValue());
                    }
                }
        );
    }

    @SuppressWarnings("unchecked")
    private void initChoiceBoxColumn(TableColumn column, ObservableList<String> values, final String selectedValuePropertyName) {
        column.setCellFactory(ChoiceBoxTableCell.forTableColumn(values));
        column.setCellValueFactory(new PropertyValueFactory(selectedValuePropertyName));

        column.setOnEditCommit(
                new EventHandler() {
                    @Override
                    public void handle(Event t) {
                        CellEditEvent cee = (CellEditEvent) t;
                        setPropertyValue(cee.getRowValue(), selectedValuePropertyName, (String) cee.getNewValue());
                    }
                }
        );
    }

    private void startJVM() {
        MiniClassFile myClassFile = null;

        try{
            myClassFile = ClassFileFactory.parseClassFile(constantPoolEntries, methodEntries);
            jvm = new JVM(new ObservableStack(this.stackEntries), this.localVariableEntries, myClassFile);
            this.maxStepCount = myClassFile.getMainMethod().getEntries().size();
        }
        catch (Exception ex){
            this.systemOutTextArea.appendText(ex.getMessage() + "\n");
        }
    }

    private JVM jvm = null;
    private int stepCount = 0;
    private int maxStepCount = -1;

    private void highlightRow() {
        if (stepCount == this.maxStepCount) {
            return;
        }

        if (stepCount > 0) {
            //de-highlight previous row
            deHighlightRow(methodRows.get(stepCount - 1));
        }

        this.methodRows.get(stepCount).setStyle("-fx-background-color:yellow");
    }

    private void highlightFinalRow(){
        this.methodRows.get(this.maxStepCount - 1).setStyle("-fx-background-color:yellow");
    }

    private void deHighlightRow(TableRow row){
        row.setStyle("");
    }

    //pretty lame to have to do this... find all the rows and store them for highlighting/de-highlighting later.
    //TODO this doesnt work if done at end of init method.. where it would look neater.
    private void captureRowsForHighlighting() {
        //Find all the Row objects for manipulation later
        Set<Node> nodes = this.tableViewMethodDisplay.lookupAll("TableRow");
        Iterator<Node> nodesItr = nodes.iterator();

        while (nodesItr.hasNext()) {
            TableRow row = (TableRow) nodesItr.next();
            this.methodRows.add(row);
        }
    }

    private void loadExampleFired(String example){
        if("Hello World".equals(example)){
            this.constantPoolEntries = HelloWorldExample.Data.constantPoolEntries;
            this.methodEntries = HelloWorldExample.Data.methodEntries;
        }
        else if("Addition".equals(example)){
            this.constantPoolEntries = AdditionExample.Data.constantPoolEntries;
            this.methodEntries = AdditionExample.Data.methodEntries;
        }

        tableViewConstantPool.setItems(constantPoolEntries);
        tableViewMethodDisplay.setItems(methodEntries);

        resetAllViewItems();
    }

    public void resetFired(ActionEvent event) {
        resetAllViewItems();
    }

    private void resetAllViewItems(){
        this.stepCount = 0;
        this.maxStepCount = -1;
        this.stackEntries.clear();
        this.localVariableEntries.clear();
        this.systemOutTextArea.clear();

        //de-highlight rows
        for (TableRow row : this.methodRows) {
            deHighlightRow(row);
        }
        this.methodRows.clear();
    }

    public void stepFired(ActionEvent event) {
        if (stepCount == 0) {
            captureRowsForHighlighting();
            startJVM();
        }

        if (stepCount == maxStepCount) {
            return;
        }

        jvm.executeOpCode();
        highlightRow();
        stepCount++;
    }

    public void runFired(ActionEvent event) {
        resetAllViewItems();
        captureRowsForHighlighting();//needed as we will be highlighting the final row
        startJVM();
        jvm.executeAll();
        highlightFinalRow();
    }

}




