#!/bin/bash 

mvn install:install-file -DgroupId=com.oracle -DartifactId=javafx2 -Dversion=2.0 -Dfile=$JAVA7_HOME/jre/lib/jfxrt.jar -Dpackaging=jar -DgeneratePom=true