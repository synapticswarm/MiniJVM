package com.synapticswarm.minijvm.model;

import java.util.ArrayList;
import java.util.List;

public class MiniMethod {
	private List <MiniMethodEntry> entries = new ArrayList <MiniMethodEntry>();
	
	public List<MiniMethodEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<MiniMethodEntry> entries) {
		this.entries = entries;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("method start>>");
		
		for (MiniMethodEntry mme : entries){
			sb.append(mme.toString());
		}
		
		sb.append("<<method end");
		return sb.toString();
	}
	
}
