all: Harmonie.jar

Harmonie.jar: class
	jar cfe Harmonie.jar main.Main */*.class src
class: 
	javac -d . src/accords/*.java src/main/*.java src/io/*.java
clean:
	-rm -rf accords main writer
	-rm Main.class
distclean: clean
	-rm Harmonie.jar