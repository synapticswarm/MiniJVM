To build and run miniJVM do this:

1) Install git.
2) Clone the repository.
3) Install Java 7 and set an environmental variable JAVA7_HOME. If you don't want to do this because your JAVA_HOME already points at Java 7 then edit the run_minijvm.sh and replace 'JAVA7_HOME' with 'JAVA_HOME'.
4) Install Apache Maven and add the path to <maven install dir>\bin onto your system PATH. As part of the Maven install define the system variable M2_HOME.
5) The Maven build needs the Java Fx 2 runtime so you need to install this into your maven repository. To do this run the script install_javafx.sh
6) Build the app with maven by executing 'mvn compile'.
7) To run the app execute run_minijvm.sh or open the project in intellij and run 'AppMain'.
