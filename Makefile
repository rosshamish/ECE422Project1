
SHELL := /bin/bash

export CLASSPATH=$CLASSPATH:./vendor/junit-4.12.jar:./vendor/hamcrest-core-1.3.jar

.PHONY: build
build:
	@mkdir -p build
	@find src -name '*.java' > build/sourceFiles.txt
	javac -d build @build/sourceFiles.txt
	javah -jni -d build -classpath build rosshamish.DataSorterBackup
	

clean:
	rm -rf build

run:
	@java -cp build Main
