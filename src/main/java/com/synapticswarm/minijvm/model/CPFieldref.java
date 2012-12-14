package com.synapticswarm.minijvm.model;

import com.synapticswarm.minijvm.opcode.Helper;

public class CPFieldref extends AbstractConstantPoolType{
	public int classIndex = -1;
	public int nameAndTypeIndex = -1;
	
	public CPFieldref (int classIndex, int nameAndTypeIndex){
		this.classIndex = classIndex;
		this.nameAndTypeIndex = nameAndTypeIndex;
	}
	
	public CPFieldref (String val) throws Exception{
		int [] vals = Helper.split (val, '.');
		this.classIndex = vals[0];
		this.nameAndTypeIndex = vals [1];
	}
	
	public String getName(){
		return "NameAndType";
	}

	@Override
	public String toString() {
		return getName() + ":" + classIndex + "," + nameAndTypeIndex;
	}
	
}