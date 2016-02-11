package rosshamish;

import java.io.FileNotFoundException;

public interface DataSorter {
    void sort(String inputFilename, String outputFilename, Double failureProb, Integer timeLimit) throws MemoryFailureException, FileNotFoundException;
}
