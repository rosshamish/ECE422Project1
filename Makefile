
SHELL := /bin/bash

export CLASSPATH=$CLASSPATH:./vendor/junit-4.12.jar:./vendor/hamcrest-core-1.3.jar

all: clean build jni generator sorter

mac: clean build jni-mac generator sorter

.PHONY: build
build:
	@mkdir -p build
	@find src -name '*.java' > build/sourceFiles.txt
	javac -d build @build/sourceFiles.txt
	javah -jni -d src/rosshamish/backup -classpath build rosshamish.backup.DataSorterBackup

jni:
	gcc -I$JAVA_HOME/include -I$JAVA_HOME/include/linux -shared -fpic -o build/libDataSorterBackup.so \
		src/rosshamish/backup/DataSorterBackupImpl.c
    export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.

jni-mac:
	#cp /Library/Java/JavaVirtualMachines/jdk1.8.0_60.jdk/Contents/Home/include/darwin/* \
		/Library/Java/JavaVirtualMachines/jdk1.8.0_60.jdk/Contents/Home/include
	cc -c -I/Library/Java/JavaVirtualMachines/jdk1.8.0_60.jdk/Contents/Home/include \
		-o build/libDataSorterBackup.o \
		src/rosshamish/backup/DataSorterBackupImpl.c
	cc -dynamiclib -o build/libDataSorterBackup.jnilib build/libDataSorterBackup.o -framework JavaVM

clean:
	rm -rf build
	-rm src/rosshamish/backup/*.h
	-rm *_test.out

generator:
	@java -cp build Generator generate_test.out 5

sorter:
	@java -cp build -Djava.library.path=build SorterDriver generate_test.out sorted_test.out 0.5 0.2 10
