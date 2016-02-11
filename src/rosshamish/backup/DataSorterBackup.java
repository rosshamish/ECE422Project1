package rosshamish.backup;

import rosshamish.DataSorter;
import rosshamish.exceptions.MemoryFailureException;

public class DataSorterBackup implements DataSorter, Runnable {
    private String inputFilename;
    private String outputFilename;
    private Double failureProb;

    private Boolean status = FAILURE;

    public DataSorterBackup(String inputFilename, String outputFilename, Double failureProb) {
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
        this.failureProb = failureProb;
    }

    private native int sortData_C(String inputFilename, String outputFilename, double failureProb);

    static {
        System.loadLibrary("DataSorterBackup");
    }

    @Override
    public void sort() throws MemoryFailureException {
        int returnCode = this.sortData_C(inputFilename, outputFilename, failureProb);
        if (returnCode == 0) {
            status = SUCCESS;
        } else {
            status = FAILURE;
            throw new MemoryFailureException("Backup sorter failed");
        }
    }

    @Override
    public Boolean getStatus() {
        return status;
    }

    @Override
    public void run() {
        try {
            this.sort();
        } catch (MemoryFailureException e) {
            e.printStackTrace();
        }
    }
}
