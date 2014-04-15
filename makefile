all: Harmonie.jar

Harmonie.jar: class
	jar cfe Harmonie.jar main.Main */*.class src
class: 
	javac -d . src/main/*.java src/io/*.java
clean:
	-rm -rf main io
distclean: clean
	-rm Harmonie.jar