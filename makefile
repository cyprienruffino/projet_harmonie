all: projet_s3_ruffino_ben-salem_herve.jar

Harmonie.jar: `ls *.class` ./MANIFEST.MF
	jar cvmf ./MANIFEST.MF projet_s3_ruffino-bensalem_herve.jar *.class
$(list).class:
	javac ./src/*.java
	mv ./src/*.class ./
MANIFEST.MF:
	echo "Main-Class: Main\n">MANIFEST.MF
clean:
	-rm *.class
	-rm MANIFEST.MF

distclean: clean
	-rm projet_s3_ruffino-bensalem_herve.jar