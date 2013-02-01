package com.synapticswarm.minijvm.opcode;

import com.synapticswarm.minijvm.MiniStack;
import com.synapticswarm.minijvm.JVM.MethodContext;
import com.synapticswarm.minijvm.model.AbstractConstantPoolType;
import com.synapticswarm.minijvm.model.MiniConstantPool;

// push a constant #index from a constant pool (String, int or float) onto
// the stack
public class Ldc extends BaseOpCode {
    private int arg;

    @Override
    public int getOffSet() {
        return 2;
    }

    @Override
    public String getDisplayName() {
        return "ldc";
    }

    @Override
    public void checkAndSetArguments(int offSet, String arg, String comment) throws Exception {
        checkArgumentHasValue(arg);
        setArg(arg);
        checkOffSet(offSet);
        setOffSet(offSet);
    }

    public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
        AbstractConstantPoolType cpType = constantPool.get(arg);

        //we've delegated the responsibility for getting the actual value to the CPType class itself
        stack.push(cpType.lookupValue(constantPool));
    }
}