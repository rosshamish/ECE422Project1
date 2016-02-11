package rosshamish.primary;

import ishikawa.HeapSorter;
import rosshamish.DataSorter;
import rosshamish.exceptions.IllegalIntegersFileException;
import rosshamish.exceptions.MemoryFailureException;

import java.io.IOException;
import java.util.List;

public class DataSorterPrimary implements DataSorter, Runnable {
    private String inputFilename;
    private String outputFilename;
    private Double failureProb;

    public DataSorterPrimary(String inputFilename, String outputFilename, Double failureProb) {
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
        this.failureProb = failureProb;
    }

    @Override
    public void sort() throws MemoryFailureException, IOException, IllegalIntegersFileException {
        List<Integer> integers = DataReader.read(inputFilename);
        integers = this.sort(integers, failureProb);
        DataWriter.writeIntegers(outputFilename, integers);
    }

    private List<Integer> sort(List<Integer> integers, Double failureProb) throws MemoryFailureException {
        int[] ints = new int[integers.size()];
        for (int i=0; i < integers.size(); i++) {
            ints[i] = integers.get(i);
        }

        HeapSorter heapSorter = new HeapSorter();
        boolean success = heapSorter.sort(ints, failureProb);
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
        try {
            this.sort();
        } catch (MemoryFailureException | IOException | IllegalIntegersFileException e) {
            e.printStackTrace();
        }
    }
}
