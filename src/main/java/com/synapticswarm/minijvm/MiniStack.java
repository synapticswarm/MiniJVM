package com.synapticswarm.minijvm;

/**
 * The most simple definition of a Stack that we can get our miniJVM to work with.
 */
public interface MiniStack {
	void push(Object o);
	Object pop();
}
