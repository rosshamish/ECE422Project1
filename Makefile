
SHELL := /bin/bash

export CLASSPATH=$CLASSPATH:./vendor/junit-4.12.jar:./vendor/hamcrest-core-1.3.jar

all: build jni generator sorter

jni: jni-mac

.PHONY: build
build:
	@mkdir -p build
	@find src -name '*.java' > build/sourceFiles.txt
	javac -d build @build/sourceFiles.txt
	javah -jni -d src/rosshamish/backup -classpath build rosshamish.backup.DataSorterBackup

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

generator:
	@java -cp build Generator generate_test.out 5

sorter:
	@java -cp build -Djava.library.path=build Sorter generate_test.out sorted_test.out 1.0 0.0 100
