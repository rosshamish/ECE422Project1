package rosshamish.primary;

import ishikawa.HeapSorter;
import rosshamish.DataSorter;
import rosshamish.exceptions.IllegalIntegersFileException;
import rosshamish.exceptions.MemoryFailureException;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class DataSorterPrimary implements DataSorter, Runnable {
    private String inputFilename;
    private String outputFilename;
    private Double failureProb;

    private Boolean status = FAILURE;

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

    @Override
    public Boolean getStatus() {
        return status;
    }

    private List<Integer> sort(List<Integer> integers, Double failureProb) throws MemoryFailureException {
        int[] ints = new int[integers.size()];
        for (int i=0; i < integers.size(); i++) {
            ints[i] = integers.get(i);
        }

        HeapSorter heapSorter = new HeapSorter();
        int memoryAccesses = heapSorter.sort(ints);

        Double HAZARD = memoryAccesses * failureProb;
        Random rand = new Random(System.currentTimeMillis());
        Double randDouble = rand.nextDouble();
        if (randDouble > 0.5 && randDouble < (0.5+HAZARD)) {
            status = FAILURE;
            throw new MemoryFailureException("Primary sorter failed");
        } else {
            status = SUCCESS;
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
            status = DataSorter.FAILURE;
        }
    }
}
