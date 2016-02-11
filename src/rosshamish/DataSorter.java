package rosshamish;

import rosshamish.exceptions.IllegalIntegersFileException;
import rosshamish.exceptions.MemoryFailureException;

import java.io.FileNotFoundException;

public interface DataSorter {
    void sort(String inputFilename, String outputFilename, Double failureProb, Integer timeLimit) throws MemoryFailureException, FileNotFoundException, IllegalIntegersFileException;
}
