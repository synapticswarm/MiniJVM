package com.synapticswarm.minijvm.opcode;


import com.synapticswarm.minijvm.JVM;
import com.synapticswarm.minijvm.MiniStack;
import com.synapticswarm.minijvm.model.MiniConstantPool;

public interface OpCode {
    void execute (MiniStack stack, MiniConstantPool constantPool, JVM.MethodContext ctx);
    void checkAndSetArguments(int givenOffSetSize, int offSetPosition, String arg, String comment) throws Exception;
    String getDisplayName();
    int getExpectedOffSetSize();
    int getOffSetPosition();
    String getRawArgString();
    String getComment();
}
