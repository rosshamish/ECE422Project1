package rosshamish;

import rosshamish.exceptions.IllegalIntegersFileException;
import rosshamish.exceptions.MemoryFailureException;

import java.io.FileNotFoundException;

public interface DataSorter extends Runnable {
    void sort() throws MemoryFailureException, FileNotFoundException, IllegalIntegersFileException;
}
