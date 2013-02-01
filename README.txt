1) Set environmental variable for $JAVA7_HOME. If you already have $JAVA_HOME pointing to Java 7 then alternatively you can edit the .sh scripts to replace 'JAVA7_HOME' with 'JAVA_HOME'.
2) Import the JavaFX jars into your local repository by running import_javafx.sh
3) To run the app run run_minijvm.sh

Download javafx 2.2.4 (for jdk 6)
Import with process described here http://jeff.langcode.com/archives/27
copy the JavaFx 2.2 SDK/rt/bin folder into the maven repository