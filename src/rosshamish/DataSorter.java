package rosshamish;

import rosshamish.exceptions.IllegalIntegersFileException;
import rosshamish.exceptions.MemoryFailureException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataSorter extends Runnable {
    Boolean FAILURE = Boolean.FALSE;
    Boolean SUCCESS = Boolean.TRUE;

    void sort() throws MemoryFailureException, FileNotFoundException, IllegalIntegersFileException, IOException;

    Boolean getStatus();
}
