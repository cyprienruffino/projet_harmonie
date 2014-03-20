all: Harmonie.jar

Harmonie.jar: class
	jar cvfe Harmonie.jar main.Main */*.class
class: 
	javac -d . src/accords/*.java src/main/*.java src/writer/*.java
clean:
	-rm -rf accords main writer
distclean: clean
	-rm Harmonie.jar