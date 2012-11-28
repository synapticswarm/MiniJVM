package com.synapticswarm.minijvm.model;

public class CPInteger extends AbstractConstantPoolType {
	private Integer value;

	public CPInteger(String val) {
		this.value = Integer.parseInt(val);
	}

	public Integer getArg() {
		return value;
	}

	public String getName() {
		return "Integer";
	}

	@Override
	public String toString() {
		return getName() + ":" + value;
	}
}