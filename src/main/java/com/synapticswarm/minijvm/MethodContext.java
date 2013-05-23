package com.synapticswarm.minijvm;


import com.synapticswarm.minijvm.ui.model.LocalVariableEntryDisplayModel;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the context of a currently executing method, which is
 * primarily a set of method-scoped variables.
 * NB: at a later stage method parameters would be on this class
 */
public class MethodContext {
    private Map<Integer, Object> variables = new HashMap<Integer, Object>();
    private ObservableList <LocalVariableEntryDisplayModel> observableList;

    public MethodContext(ObservableList <LocalVariableEntryDisplayModel> observableList){
        this.observableList = observableList;
    }

    public void putVariable(Integer index, Object variable){
        variables.put(index, variable);
        observableList.add(new LocalVariableEntryDisplayModel(index, variable.toString(), variable.getClass().getSimpleName().toString()));
    }

    public Object getVariable(Integer index){
        return variables.get(index);
        //don't bother taking out of observable list.. it's not really a stack
    }
}
