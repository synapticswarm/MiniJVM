package com.synapticswarm.minijvm.ui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Maps a domain object (a line of opcode in a method) to an equivalent UI display object with displayable properties.
 */
public class MethodEntryDisplayModel {

	private SimpleStringProperty offset = new SimpleStringProperty();
	private SimpleStringProperty opcode = new SimpleStringProperty();
	private SimpleStringProperty arg = new SimpleStringProperty();
	private SimpleStringProperty comment = new SimpleStringProperty();
	public final static ObservableList <String> opCodes = FXCollections.observableArrayList(
			"getstatic", "invokevirtual", "ldc", "imul", "iadd", "iload_0", "iload_1", "iload_2", "return");
	
	public MethodEntryDisplayModel(int offset, String opCode, String arg, String comment){
		this.setOffsetProperty(Integer.toString(offset));
		this.setOpcodeProperty(opCode);
		this.setArgProperty(arg);
		this.setCommentProperty(comment);
	}
	
	public String getOffsetProperty() {
		return offset.get();
	}
	public void setOffsetProperty(String offset) {
		this.offset.set(offset);
	}
	public String getOpcodeProperty() {
		return opcode.get();
	}
	public void setOpcodeProperty(String opcode) {
		this.opcode.set(opcode);
	}
	public String getArgProperty() {
		return arg.get();
	}
	public void setArgProperty(String arg) {
		this.arg.set(arg);
	}
	public String getCommentProperty() {
		return comment.get();
	}
	public void setCommentProperty(String arg) {
		this.comment.set(arg);
	}
	
}
