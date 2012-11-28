package com.synapticswarm.minijvm.opcode;

import com.synapticswarm.minijvm.MiniStack;
import com.synapticswarm.minijvm.JVM.MethodContext;
import com.synapticswarm.minijvm.model.AbstractConstantPoolType;
import com.synapticswarm.minijvm.model.CPString;
import com.synapticswarm.minijvm.model.CPUtf8;
import com.synapticswarm.minijvm.model.MiniConstantPool;
import com.synapticswarm.minijvm.model.MiniMethodEntry;

// push a constant #index from a constant pool (String, int or float) onto
// the stack
public class Ldc implements OpCode {
	private int arg;
	
	public Ldc(int arg){
		this.arg = arg;
	}
	
	public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
		AbstractConstantPoolType cpType = constantPool.get(arg);
		
		//we've delegated the responsibility for getting the actual value to the CPType class itself
		stack.push(cpType.lookupValue(constantPool));
	}
}