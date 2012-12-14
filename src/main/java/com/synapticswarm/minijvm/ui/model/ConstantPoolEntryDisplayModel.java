package com.synapticswarm.minijvm.ui.model;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConstantPoolEntryDisplayModel {

	private SimpleStringProperty indexProperty = new SimpleStringProperty();
	private SimpleStringProperty typeProperty = new SimpleStringProperty();
	private SimpleStringProperty valueProperty = new SimpleStringProperty();
	private SimpleStringProperty commentProperty = new SimpleStringProperty();
	public final static ObservableList <String> constantPoolTypes;
	
	static {
		constantPoolTypes = FXCollections.observableArrayList("Class","Fieldref", "Integer", "Methodref","NameAndType","String",  "Utf8");
	}
	
	public ConstantPoolEntryDisplayModel(String index, String type, String value, String comment){
		this.setIndexProperty(index);
		this.setTypeProperty(type);
		this.setValueProperty(value);
		this.setCommentProperty(comment);
	}

	@Override
	public String toString() {
		return "ConstantPoolEntryDisplayModel[" + this.getIndexProperty() +","+ this.getTypeProperty() +","+  this.getValueProperty() +
				","+ this.getCommentProperty() + "]";
	}

	public void setIndexProperty(String index) {
		this.indexProperty.set(index);
	}
	public String getIndexProperty() {
		return indexProperty.get();
	}

	public String getTypeProperty() {
		return typeProperty.get();
	}

	public void setTypeProperty(String typeProperty) {
		this.typeProperty.set(typeProperty);
	}

	public String getValueProperty() {
		return valueProperty.get();
	}

	public void setValueProperty(String valueProperty) {
		this.valueProperty.set(valueProperty);
	}

	public String getCommentProperty() {
		return commentProperty.get();
	}

	public void setCommentProperty(String commentProperty) {
		this.commentProperty.set(commentProperty);
	}
}
