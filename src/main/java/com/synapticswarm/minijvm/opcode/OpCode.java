package com.synapticswarm.minijvm.opcode;

import com.synapticswarm.minijvm.MiniStack;
import com.synapticswarm.minijvm.JVM.MethodContext;
import com.synapticswarm.minijvm.model.MiniConstantPool;

public interface OpCode {
	void execute (MiniStack stack, MiniConstantPool constantPool, MethodContext ctx);
}
