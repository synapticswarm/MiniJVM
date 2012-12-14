package com.synapticswarm.minijvm.ui;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import com.synapticswarm.minijvm.JVM;
import com.synapticswarm.minijvm.examples.HelloWorldExample;
import com.synapticswarm.minijvm.model.MiniClassFile;
import com.synapticswarm.minijvm.ui.model.ClassFileFactory;
import com.synapticswarm.minijvm.ui.model.ConstantPoolEntryDisplayModel;
import com.synapticswarm.minijvm.ui.model.MethodEntryDisplayModel;
import com.synapticswarm.minijvm.ui.model.StackEntryDisplayModel;

public class UIController {

	@FXML
	TableView tableViewStack;
	@FXML
	TableColumn stackValueColumn;
	@FXML
	TableColumn stackTypeColumn;

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
	TableView <ConstantPoolEntryDisplayModel> tableViewConstantPool;
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
	ChoiceBox choiceBox;
	
	private AtomicInteger count = new AtomicInteger(0);
	
	private ObservableList <ConstantPoolEntryDisplayModel> constantPoolEntries;
	private ObservableList <MethodEntryDisplayModel> methodEntries;
	private ObservableList <StackEntryDisplayModel> stackEntries = FXCollections.observableArrayList();
	
	//TODO must be a better way of capturing rows
	private List <TableRow> methodRows = new ArrayList <>();

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

		HelloWorldExample.Data data = new HelloWorldExample.Data();
		
		//constant pool
		constantPoolEntries = data.constantPoolEntries;
		
		tableViewConstantPool.setItems(constantPoolEntries);
		initTextFieldColumn(this.constantPoolIndexColumn, "indexProperty");
		initChoiceBoxColumn(this.constantPoolTypeColumn, ConstantPoolEntryDisplayModel.constantPoolTypes, "typeProperty");
		initTextFieldColumn(this.constantPoolValueColumn, "valueProperty");
		initTextFieldColumn(this.constantPoolCommentColumn, "commentProperty");
		
		//method display
		methodEntries = data.methodEntries;
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
		
		//Capture System.out to the mini-console
		SystemOutCapturePrintStream stream = new SystemOutCapturePrintStream(this.systemOutTextArea, System.out);
		System.setOut(stream);
	}
	
	//I cannot believe I have to write this method. why do the properties not magically update?!
	public static void setPropertyValue(Object target, String propertyName, String value){
		try{
			String name = propertyName.replaceFirst(propertyName.substring(0, 1), propertyName.substring(0, 1).toUpperCase());
			Method method = target.getClass().getMethod("set" + name, String.class);
			method.invoke(target, value);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	private  void initTextFieldColumn(TableColumn column,final  String propertyName){
		column.setCellFactory(TextFieldTableCell.forTableColumn());
		column.setCellValueFactory(new PropertyValueFactory(propertyName));
		
		column.setOnEditCommit(
			    new EventHandler<CellEditEvent<? extends Object, String>>() {
			        @Override
			        public void handle(CellEditEvent<? extends Object, String> t) {
			        	//ConstantPoolEntryDisplayModel cpedm = t.getRowValue();//getTableView().getItems().get(t.getTablePosition().getRow());
			        	setPropertyValue(t.getRowValue(), propertyName, t.getNewValue());
			        }
			    }
			);
	}
	
	@SuppressWarnings("unchecked")
	private  void initChoiceBoxColumn(TableColumn column, ObservableList <String> values, final String selectedValuePropertyName){
		column.setCellFactory(ChoiceBoxTableCell.forTableColumn(values));
		column.setCellValueFactory(new PropertyValueFactory(selectedValuePropertyName));
		
		column.setOnEditCommit(
		    new EventHandler () {
		        @Override
		        public void handle(Event t) {
		        	CellEditEvent cee = (CellEditEvent)t;
		        	//ConstantPoolEntryDisplayModel cpedm = (ConstantPoolEntryDisplayModel) cee.getRowValue();
		        	setPropertyValue(cee.getRowValue(), selectedValuePropertyName, (String)cee.getNewValue());
		        }
		    }
		);
	}
	
	//TODO duplication
	private JVM jvm = null;
	private int stepCount = 0;
	private int maxStepCount = -1;
	
	//TODO this probably breaks the threading model
	public void stepFired(ActionEvent event){		
//		if (stepCount == 0){
//			//Parse constant pool and method display models into real objects.
//			MiniClassFile myClassFile = ClassFileFactory.parseClassFile(constantPoolEntries, methodEntries);
//			jvm = new JVM(this.stackEntries, myClassFile);
//			this.maxStepCount = myClassFile.entryPoint.entries.size();
//		}
//
//		if(stepCount == maxStepCount){
//			resetFired(null);
//			return;
//		}
//
//		jvm.execute();
//
//		highlightRow();
	}
	
	private void highlightRow(){
		System.out.println(this.tableViewMethodDisplay.getStyle());
		
		if (stepCount == 0){
		    Set <Node> nodes =  this.tableViewMethodDisplay.lookupAll("TableRow");
			Iterator <Node> nodesItr = nodes.iterator();
			
			while (nodesItr.hasNext()){
				TableRow row = (TableRow) nodesItr.next();
				this.methodRows.add(row);
			}
		}
		
		if (stepCount != 0){
			//de-highlight previous row
			this.methodRows.get(stepCount-1).setStyle("-fx-background-color:grey");
		}
		
		this.methodRows.get(stepCount).setStyle("-fx-background-color:yellow");
		stepCount++;
	}
	
	public void resetFired(ActionEvent event){
//		this.stepCount = 0;
//		this.stackEntries.clear();
//		this.jvm = new JVM(this.stackEntries, ClassFileFactory.parseClassFile(constantPoolEntries, methodEntries));
//
//		//reset row styles
//		for (TableRow row : this.methodRows){
//			row.setStyle("");//back to de-highlighted style
//		}
	}

}




