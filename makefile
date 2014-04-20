all: Harmonie.jar

Harmonie.jar: class
	jar cfe Harmonie.jar main.Main */*.class src
class: 
	javac -d . src/main/*.java src/io/*.java src/monteur/*.java
clean:
	-rm -rf main io monteur
distclean: clean
	-rm Harmonie.jar
