package com.synapticswarm.minijvm.examples;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.synapticswarm.minijvm.ui.model.ConstantPoolEntryDisplayModel;
import com.synapticswarm.minijvm.ui.model.MethodEntryDisplayModel;

public class HelloWorldExample {
	public static void main(String [] args){
		System.out.println("Hello world!");
	}
	
	public static class Data{
		public static final ObservableList <ConstantPoolEntryDisplayModel> constantPoolEntries = FXCollections
				.observableArrayList(
						new ConstantPoolEntryDisplayModel("1", "Fieldref", "#2.#4", "//  java/lang/System.out:Ljava/io/PrintStream;"),
						new ConstantPoolEntryDisplayModel("2", "Class", "#3", ""),
						new ConstantPoolEntryDisplayModel("3", "Utf8", "java/lang/System", "//  java/lang/System"),
						new ConstantPoolEntryDisplayModel("4", "NameAndType", "#5:#6", "//  out:Ljava/io/PrintStream;"),
						new ConstantPoolEntryDisplayModel("5", "Utf8", "out", ""),
						new ConstantPoolEntryDisplayModel("6", "Utf8", "Ljava/io/PrintStream;", ""),
						new ConstantPoolEntryDisplayModel("7", "String", "#8", "//  Hello world!"),
						new ConstantPoolEntryDisplayModel("8", "Utf8", "Hello world!", "//a comment"),
						new ConstantPoolEntryDisplayModel("9", "Methodref", "#10.#12", "//  java/io/PrintStream.println:(Ljava/lang/String;)V"),
						new ConstantPoolEntryDisplayModel("10", "Class", "#6", "//  java/io/PrintStream"),
						new ConstantPoolEntryDisplayModel("11", "Utf8", "java/io/PrintStream", "//a comment"),
						new ConstantPoolEntryDisplayModel("12", "NameAndType", "#13:#14", "//  println:(Ljava/lang/String;)V"),
						new ConstantPoolEntryDisplayModel("13", "Utf8", "println", "//a comment"),
						new ConstantPoolEntryDisplayModel("14", "Utf8", "(Ljava/lang/String;)V", "//a comment")
				);
		
		public static final ObservableList <MethodEntryDisplayModel> methodEntries =  FXCollections.observableArrayList(
				new MethodEntryDisplayModel(0, "getstatic", "#1", "// Field java/lang/System.out:Ljava/io/PrintStream;"),
				new MethodEntryDisplayModel(3, "ldc", "#7", "// String Hello world!"),
				new MethodEntryDisplayModel(5, "invokevirtual", "#9", "// Method java/io/PrintStream.println:(Ljava/lang/String;)V"),
				new MethodEntryDisplayModel(8, "return", "", "//a comment"));
	}
}
