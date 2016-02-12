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

Currently only works on Mac OS

TODO get compilation and linking working on Linux

```
make clean
make build
make jni-mac
make generator
make sorter
```

... or just `make`


