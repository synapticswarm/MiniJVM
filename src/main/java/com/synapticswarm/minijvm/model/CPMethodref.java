package com.synapticswarm.minijvm.model;

import com.synapticswarm.minijvm.opcode.Helper;

public class CPMethodref extends AbstractConstantPoolType{
	public int classIndex = -1;
	public int nameAndTypeIndex = -1;

	public CPMethodref(int classIndex, int nameAndTypeIndex){
		this.classIndex = classIndex;
		this.nameAndTypeIndex = nameAndTypeIndex;
	}
	
	public CPMethodref (String val) {
		int [] vals = Helper.split (val, '.');
		this.classIndex = vals[0];
		this.nameAndTypeIndex = vals [1];
	}
	
	public String getName(){
		return "Methodref";
	}
	@Override
	public String toString() {
		return getName() + ":" + classIndex + "," + nameAndTypeIndex;
	}
}