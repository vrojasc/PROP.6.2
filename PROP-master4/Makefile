JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC)	$(JFLAGS) 	$*.java
	
CLASSES = \
	src\capaDatos\*.java
	src\capaDominio\*.java
	src\generadorhorario\*.java
	
default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
