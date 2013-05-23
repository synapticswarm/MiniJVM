package com.synapticswarm.minijvm;

import com.synapticswarm.minijvm.model.MiniClassFile;
import com.synapticswarm.minijvm.model.MiniMethodEntry;
import com.synapticswarm.minijvm.ui.model.LocalVariableEntryDisplayModel;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JVM {


    private MiniStack stack;
    private MiniClassFile classFile;
    private int stepCount = 0;
    private Iterator <MiniMethodEntry> methodEntriesIterator = null;
    private MethodContext methodContext = null;

    public JVM(MiniStack stack, ObservableList <LocalVariableEntryDisplayModel> localVariableEntriesObservableList, MiniClassFile classFile) {
        this.stack = stack;
        this.classFile = classFile;
        this.methodEntriesIterator = this.classFile.getMainMethod().getEntries().iterator();
        this.methodContext = new MethodContext(localVariableEntriesObservableList);
    }

    public void executeOpCode() {
        if(this.methodEntriesIterator.hasNext()){
            MiniMethodEntry entry = this.methodEntriesIterator.next();
            entry.getOpCode().execute(stack, this.classFile.getConstantPool(), this.methodContext);
        }
    }

    public void executeAll(){
        while(this.methodEntriesIterator.hasNext()){
            MiniMethodEntry entry = this.methodEntriesIterator.next();
            entry.getOpCode().execute(stack, this.classFile.getConstantPool(), this.methodContext);
        }
    }


}
