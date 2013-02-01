package com.synapticswarm.minijvm.model;

import com.synapticswarm.minijvm.opcode.IOpCode;

/**
 * Represents a single line in a method's bytecode.
 *
 */
public class MiniMethodEntry {
	private IOpCode opCode;
	private int arg;
	private int offset;
	
	public MiniMethodEntry(){}
	
	public MiniMethodEntry(IOpCode opCode, int arg, int offset){
		this.opCode = opCode;
		this.arg = arg;
		this.offset = offset;
	}
	
	public IOpCode getOpCode() {
		return opCode;
	}

	public void setOpCode(IOpCode opCode) {
		this.opCode = opCode;
	}

	public int getArg() {
		return arg;
	}

	public void setArg(int arg) {
		this.arg = arg;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		String opcodeStr = opCode == null ? "null" : opCode.getClass().getName();
		return "MyMethodEntry[offset="+ offset + ",opCode=" + opcodeStr + ",arg=" + arg + "]\n";
	}
	
}
