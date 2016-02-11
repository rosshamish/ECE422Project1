package rosshamish;

import rosshamish.exceptions.IllegalIntegersFileException;
import rosshamish.exceptions.MemoryFailureException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataSorter extends Runnable {
    void sort() throws MemoryFailureException, FileNotFoundException, IllegalIntegersFileException, IOException;
}
