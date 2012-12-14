package com.synapticswarm.minijvm.ui.model;

import javafx.beans.property.SimpleStringProperty;

public class StackEntryDisplayModel {
	private SimpleStringProperty value = new SimpleStringProperty();
	private SimpleStringProperty type = new SimpleStringProperty();
	
	public StackEntryDisplayModel(String value, String type){
		setValueProperty(value);
		setTypeProperty(type);
	}
	
	public String getValueProperty() {
		return value.get();
	}
	public void setValueProperty(String offset) {
		this.value.set(offset);
	}
	public String getTypeProperty() {
		return type.get();
	}
	public void setTypeProperty(String opcode) {
		this.type.set(opcode);
	}
}
