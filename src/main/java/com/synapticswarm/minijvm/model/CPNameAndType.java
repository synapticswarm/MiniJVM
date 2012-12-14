package com.synapticswarm.minijvm.model;

import com.synapticswarm.minijvm.opcode.Helper;

public class CPNameAndType extends AbstractConstantPoolType{
	public int methodNameIndex = -1;
	public int methodTypeDescriptorIndex = -1;
	
	public CPNameAndType(int methodNameIndex, int methodTypeDescriptorIndex){
		this.methodNameIndex = methodNameIndex;
		this.methodTypeDescriptorIndex = methodTypeDescriptorIndex;
	}
	
	public CPNameAndType (String val) {
		int [] vals = Helper.split (val, ':');
		this.methodNameIndex = vals[0];
		this.methodTypeDescriptorIndex = vals[1];
	}
	
	public String getName(){
		return "NameAndType";
	}
	
	@Override
	public String toString() {
		return getName() + ":" + methodNameIndex + "," + methodTypeDescriptorIndex;
	}
}