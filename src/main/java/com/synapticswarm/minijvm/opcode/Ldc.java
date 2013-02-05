package com.synapticswarm.minijvm.opcode;

import com.synapticswarm.minijvm.MiniStack;
import com.synapticswarm.minijvm.JVM.MethodContext;
import com.synapticswarm.minijvm.model.ConstantPoolEntry;
import com.synapticswarm.minijvm.model.MiniConstantPool;

// push a constant #index from a constant pool (String, int or float) onto
// the stack
public class Ldc extends BaseOpCode {

    @Override
    public int getExpectedOffSetSize() {
        return 2;
    }

    @Override
    public String getDisplayName() {
        return "ldc";
    }

    @Override
    public void checkAndSetArguments(int givenOffSetSize, int offSetPosition, String arg, String comment) throws Exception {
        Helper.checkArgHasOneIndex(arg, getDisplayName());
        setRawArgString(arg);
        setArgInt(Integer.parseInt(Helper.stripLeadingHash(arg)));
        super.checkOffSetAsExpected(givenOffSetSize, getExpectedOffSetSize());
        setOffSetPosition(offSetPosition);
        setComment(comment);
    }

    public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
        ConstantPoolEntry constantPoolEntry = constantPool.get(this.getArgInt());

        //we've delegated the responsibility for getting the actual value to the CPType class itself
        stack.push(constantPoolEntry.lookupValue(constantPool));
    }
}