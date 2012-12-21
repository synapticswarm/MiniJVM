package com.synapticswarm.minijvm;

import com.synapticswarm.minijvm.model.MiniClassFile;
import com.synapticswarm.minijvm.model.MiniMethodEntry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Semaphore;

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
    private Semaphore semaphore = new Semaphore(1);
    private boolean stepThrough = true;

    public JVM(MiniStack stack, MiniClassFile classFile, boolean stepThrough) {
        this.stack = stack;
        this.classFile = classFile;
        this.stepThrough = stepThrough;
    }

    public void execute() {
        Iterator<MiniMethodEntry> entriesItr = this.classFile.getMainMethod().getEntries().iterator();
        MethodContext ctx = new MethodContext();

        while (entriesItr.hasNext()) {
            MiniMethodEntry entry = entriesItr.next();
            entry.getOpCode().execute(stack, this.classFile.getConstantPool(), ctx);

            if (stepThrough) {
                try {
                    this.semaphore.acquire();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void step() {
        if (stepThrough){
            this.semaphore.release();
        }
    }

    public int getLastExecutedStepIndex() {
        return stepCount;
    }


}
