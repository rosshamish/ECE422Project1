package rosshamish.primary;

import ishikawa.HeapSorter;
import rosshamish.DataSorter;
import rosshamish.exceptions.IllegalIntegersFileException;
import rosshamish.exceptions.MemoryFailureException;

import java.io.FileNotFoundException;
import java.util.List;

public class DataSorterPrimary implements DataSorter, Runnable {

    @Override
    public void sort(String inputFilename, String outputFilename, Double failureProb, Integer timeLimit) throws MemoryFailureException, FileNotFoundException, IllegalIntegersFileException {
        List<Integer> integers = DataReader.read(inputFilename);
        integers = this.sort(integers, failureProb, timeLimit);
        DataWriter.writeIntegers(outputFilename, integers);
    }

    private List<Integer> sort(List<Integer> integers, Double failureProb, Integer timeLimit) throws MemoryFailureException {
        int[] ints = new int[integers.size()];
        for (int i=0; i < integers.size(); i++) {
            ints[i] = integers.get(i);
        }

        HeapSorter heapSorter = new HeapSorter();
        boolean success = heapSorter.sort(ints, failureProb, timeLimit);
        if (!success) {
            throw new MemoryFailureException("Primary sorter failed");
        }

        for (int i=0; i < integers.size(); i++) {
            integers.set(i, ints[i]);
        }
        return integers;
    }

    @Override
    public void run() {

    }
}
