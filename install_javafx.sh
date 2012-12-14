#!/bin/bash 

mvn install:install-file -Dfile=$JAVA7_HOME/jre/lib/jfxrt.jar -Dcom.oracle -DartifactId=javafx2 -Dversion=2.0 -Dpackaging=jar -DgeneratePom=true