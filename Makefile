agent: Run.class
	
Run.class: Run.java
	JAVA_HOME=/usr/usc/jdk/1.6.0_23 PATH=/usr/usc/jdk/1.6.0_23/bin:${PATH} javac Run.java

run: Run.class
	JAVA_HOME=/usr/usc/jdk/1.6.0_23/bin/ PATH=/usr/usc/jdk/1.6.0_23/bin:${PATH} java Run
