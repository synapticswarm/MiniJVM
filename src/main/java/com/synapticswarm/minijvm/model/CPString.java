package com.synapticswarm.minijvm.model;

import com.synapticswarm.minijvm.opcode.Helper;

public class CPString extends AbstractConstantPoolType{
	private int utf8Index = -1;
	
	public CPString (String value){
		this.utf8Index = Integer.parseInt(Helper.stripLeadingHash(value));
	}

	@Override
	public String getName() {
		return "String";
	}

	@Override
	public String toString() {
		return getName() + ":" + this.utf8Index;
	}

	@Override
	public String lookupValue(MiniConstantPool mcp) {
		CPUtf8 c = (CPUtf8) mcp.getEntries()[this.utf8Index];
		return c.getValue();
	}
	
}