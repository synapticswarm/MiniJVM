#!/bin/bash 
echo Using jre at $JAVA7_HOME
$JAVA7_HOME/bin/java -cp "$JAVA7_HOME/jre/lib/jfxrt.jar;./target/classes/" com.synapticswarm.minijvm.ui.AppMain
