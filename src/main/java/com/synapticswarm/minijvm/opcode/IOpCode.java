package com.synapticswarm.minijvm.opcode;


import com.synapticswarm.minijvm.JVM;
import com.synapticswarm.minijvm.MiniStack;
import com.synapticswarm.minijvm.model.MiniConstantPool;

public interface IOpCode {
    void execute (MiniStack stack, MiniConstantPool constantPool, JVM.MethodContext ctx);
    void checkAndSetArguments(int offSet, String arg, String comment) throws Exception;
    String getDisplayName();
    int getOffSet();
    String getArg();
    String getComment();
}
