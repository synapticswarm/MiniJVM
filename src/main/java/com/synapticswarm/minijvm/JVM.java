package com.synapticswarm.minijvm;

import com.synapticswarm.minijvm.model.MiniClassFile;
import com.synapticswarm.minijvm.model.MiniMethodEntry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JVM {

    /**
     * Represents the context of a currently executing method, which is
     * primarily a set of method-scoped variables.
     * NB: at a later stage method parameters would be on this class
     */
    public class MethodContext {
        private Map<Integer, Object> variables = new HashMap<Integer, Object>();

        public Map<Integer, Object> getVariables() {
            return variables;
        }
    }

    private MiniStack stack;
    private MiniClassFile classFile;
    private int stepCount = 0;
    private Iterator <MiniMethodEntry> methodEntriesIterator = null;
    private MethodContext methodContext = null;

    public JVM(MiniStack stack, MiniClassFile classFile) {
        this.stack = stack;
        this.classFile = classFile;
        this.methodEntriesIterator = this.classFile.getMainMethod().getEntries().iterator();
        this.methodContext = new MethodContext();
    }

    public void executeOpCode() {
        if(this.methodEntriesIterator.hasNext()){
            MiniMethodEntry entry = this.methodEntriesIterator.next();
            entry.getOpCode().execute(stack, this.classFile.getConstantPool(), this.methodContext);
        }
    }


}
