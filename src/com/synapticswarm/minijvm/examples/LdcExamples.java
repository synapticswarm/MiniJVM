package com.synapticswarm.minijvm.examples;

//class just exists to javap against and check the bytecode!
public class LdcExamples {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("hello");
		Integer i = 300;
		System.out.println(i);
		System.out.println(10f);
	}

}
