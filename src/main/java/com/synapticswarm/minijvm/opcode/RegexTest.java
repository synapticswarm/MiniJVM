package com.synapticswarm.minijvm.opcode;

public class RegexTest {

    public static void main (String [] args){

        String str = "";

        System.out.println("#2".matches("#[0-9]{1,2}"));
        System.out.println("#23".matches("#[0-9]{1,2}"));
        System.out.println("#1 ".matches("#[0-9]{1,2}"));
        System.out.println("".matches("#[0-9]{1,2}"));
        System.out.println("# 1".matches("#[0-9]{1,2}"));
        System.out.println("#a".matches("#[0-9]{1,2}"));
        System.out.println("#a1".matches("#[0-9]{1,2}"));
    }


}
