all: Harmonie.jar

Harmonie.jar: class
	jar cfe Harmonie.jar src.main.Main -C src_protoype/*/*.class 
class: 
	javac src/accords/*.java src/main/*.java src/writer/*.java
clean:
	-rm src/*/*.class

distclean: clean
	-rm Harmonie.jar