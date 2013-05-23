package com.synapticswarm.minijvm.ui.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Maps a domain object (a method-scoped variable) to an equivalent UI display object with displayable properties.
 */
public class LocalVariableEntryDisplayModel {
    private SimpleStringProperty index = new SimpleStringProperty();
    private SimpleStringProperty value = new SimpleStringProperty();
    private SimpleStringProperty type = new SimpleStringProperty();

    public LocalVariableEntryDisplayModel(Integer index, String value, String type){
        setIndexProperty(index.toString());
        setValueProperty(value);
        setTypeProperty(type);
    }

    public String getValueProperty() {
        return value.get();
    }

    public void setValueProperty(String value) {
        this.value.set(value);
    }

    public String getTypeProperty() {
        return type.get();
    }
    public void setTypeProperty(String type) {
        this.type.set(type);
    }

    public String getIndexProperty() {
        return index.get();
    }

    public void setIndexProperty(String index) {
        this.index.set(index);
    }
}
