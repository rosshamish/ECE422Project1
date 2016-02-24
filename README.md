ECE 422, Reliable & Secure Systems Design
Project #1: Fault Tolerant Systems

Fault-tolerant sorting using Recovery Blocks.

```
Executive: runs the variants, checks results using adjudicator, enforces watchdog, class SorterDriver
Variant 1 (Primary): Heapsort in Java, package rosshamish.primary
Variant 2 (Backup): Insertion sort in C, package rosshamish.backup
Adjudicator: reads output file, asserts file is sorted and well-formed, class rosshamish.Adjudicator
Watchdog Timer: kills variants if they run longer than `timeout` seconds, class rosshamish.WatchdogTimer
```

The Executive is the main entry point. It does something like this:
```
variants = [primary, backup]
for v in variants:
    output = v.sort()
    if output.success():
        print('file sorted')
        return
    else:
        print('variant failed, trying another')
```

A variant fails if any one of the following is true:

- output does not pass acceptance test from Adjudicator
- variant is still running after the allotted timeout. The Watchdog Timer will kill it.
- memory access failure occurs. This is simulated by dice roll, with configurable failure probability.

> Author: Ross Anderson

---

### Usage

Works on Mac OS El Capitan and on the version of Linux in ETLC E5-005.

##### On Linux

```
# Build
make clean      # cleans the build directory
make build      # builds .java source
make jni        # (Linux) compiles and links Java/C interop using JNI

# Run with Default Arguments
make generator  # runs the Data Generator with default args
make sorter     # runs the Data Sorter with default args

# Build and Run with Default Arguments
make            # alias for clean+build+jni+generator+sorter
```

Run the Data Generator with Custom Arguments:
```
$ java -cp build Generator <generated-ints-filename> <num-integers-to-generate>
(e.g. $ java -cp build Generator generated_ints.out 10)
```

Run the Data Sorter with Custom Arguments:
```
$ java -cp build -Djava.library.path=build SorterDriver <generated-ints-filename> <sorted-output-filename> <primary-prob> <backup-prob> <watchdog-timeout>
(e.g. $ java -cp build -Djava.library.path=build SorterDriver generate_test.out sorted_test.out 0.5 0.2 10)
```

##### On Mac

Same instructions, except use `make mac` instead of `make`, and `make jni-mac` instead of `make jni`.

