package com.synapticswarm.minijvm.model;

public abstract class AbstractConstantPoolType {
	public abstract String getName();

	/**
	 * ConstantPoolTypes which hold a value which can be pushed onto the stack (e.g. by opcode 'ldc') should
	 * override this method.
	 * 
	 * @return
	 */
	public Object lookupValue(MiniConstantPool mcp){
		throw new RuntimeException("Cannot push this value onto the stack");
	}
	
}

