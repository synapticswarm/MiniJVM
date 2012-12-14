package com.synapticswarm.minijvm.model;

public class CPUtf8 extends AbstractConstantPoolType{
	public String value = null;
	
	public CPUtf8 (String val){
		this.value = val;
	}
	
	public String getName(){
		return "Utf8";
	}

	@Override
	public String toString() {
		return getName() + ":" + value;
	}
}