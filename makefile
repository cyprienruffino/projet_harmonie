all: Harmonie.jar

Harmonie.jar: class
	jar cfe Harmonie.jar main.Main */*.class
class: 
	javac -d . src/accords/*.java src/main/*.java src/writer/*.java
clean:
	-rm -rf accords main writer
	-rm Main.class
distclean: clean
	-rm Harmonie.jar