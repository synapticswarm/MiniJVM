package com.synapticswarm.minijvm.examples;

import com.synapticswarm.minijvm.ui.model.ConstantPoolEntryDisplayModel;
import com.synapticswarm.minijvm.ui.model.MethodEntryDisplayModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdditionExample {
	public static void main (String [] args){
		int a = 1;
		int b = 2;
		int c = a + b;
		System.out.println(c);		
	}

    public static class Data {
        public static final ObservableList<ConstantPoolEntryDisplayModel> constantPoolEntries = FXCollections
                .observableArrayList(
                        new ConstantPoolEntryDisplayModel("1", "Fieldref", "#3.#4", "//  java/lang/System.out:Ljava/io/PrintStream;"),
                        new ConstantPoolEntryDisplayModel("2", "Methodref", "#5.#6", "//  java/io/PrintStream.println:(I)V"),
                        new ConstantPoolEntryDisplayModel("3", "Class", "#7", "//  java/lang/System"),
                        new ConstantPoolEntryDisplayModel("4", "NameAndType", "#8:#9", "//  out:Ljava/io/PrintStream;"),
                        new ConstantPoolEntryDisplayModel("5", "Class", "#10", "//  java/io/PrintStream"),
                        new ConstantPoolEntryDisplayModel("6", "NameAndType", "#11:#12", "//  println:(I)V"),
                        new ConstantPoolEntryDisplayModel("7", "Utf8", "java/lang/System", null),
                        new ConstantPoolEntryDisplayModel("8", "Utf8", "out", null),
                        new ConstantPoolEntryDisplayModel("9", "Utf8", "Ljava/io/PrintStream;", null),
                        new ConstantPoolEntryDisplayModel("10", "Utf8", "java/io/PrintStream", null),
                        new ConstantPoolEntryDisplayModel("11", "Utf8", "println", null),
                        new ConstantPoolEntryDisplayModel("12", "Utf8", "(I)V", null)
                );

        public static final ObservableList <MethodEntryDisplayModel> methodEntries =  FXCollections.observableArrayList(
                new MethodEntryDisplayModel(0, "iconst_1", null, null),
                new MethodEntryDisplayModel(1, "istore_1", null, null),
                new MethodEntryDisplayModel(2, "iconst_2", null, null),
                new MethodEntryDisplayModel(3, "istore_2", null, null),
                new MethodEntryDisplayModel(4, "iload_1", null, null),
                new MethodEntryDisplayModel(5, "iload_2", null, null),
                new MethodEntryDisplayModel(6, "iadd", null, null),
                new MethodEntryDisplayModel(7, "istore_3", null, null),
                new MethodEntryDisplayModel(8, "getstatic", "#2", "// Field java/lang/System.out:Ljava/io/PrintStream;"),
                new MethodEntryDisplayModel(11, "iload_3", null, null),
                new MethodEntryDisplayModel(12, "invokevirtual", "#3", "// Method java/io/PrintStream.println:(I)V"),
                new MethodEntryDisplayModel(15, "return", null, null));

    }
}
