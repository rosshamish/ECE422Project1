
SHELL := /bin/bash

export CLASSPATH=$CLASSPATH:./vendor/junit-4.12.jar:./vendor/hamcrest-core-1.3.jar

all: build jni run

jni: jni-mac

.PHONY: build
build:
	@mkdir -p build
	@find src -name '*.java' > build/sourceFiles.txt
	javac -d build @build/sourceFiles.txt
	javah -jni -d src/rosshamish -classpath build rosshamish.DataSorterBackup

jni-mac:
	#cp /Library/Java/JavaVirtualMachines/jdk1.8.0_60.jdk/Contents/Home/include/darwin/* \
		/Library/Java/JavaVirtualMachines/jdk1.8.0_60.jdk/Contents/Home/include
	cc -c -I/Library/Java/JavaVirtualMachines/jdk1.8.0_60.jdk/Contents/Home/include \
		-o build/libDataSorterBackup.o \
		src/rosshamish/DataSorterBackupImpl.c
	cc -dynamiclib -o build/libDataSorterBackup.jnilib build/libDataSorterBackup.o -framework JavaVM

clean:
	rm -rf build
	-rm src/rosshamish/*.h

run:
	@java -cp build -Djava.library.path=build Main 0.2
