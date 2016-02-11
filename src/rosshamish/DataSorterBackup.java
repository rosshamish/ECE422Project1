package rosshamish;

public class DataSorterBackup implements DataSorter {
    private native int sortData_C(String inputFilename, String outputFilename, double failureProb, int timeLimit);

    static {
        System.loadLibrary("DataSorterBackup");
    }

    @Override
    public void sort(String inputFilename, String outputFilename, Double failureProb, Integer timeLimit) throws MemoryFailureException {
        int returnCode = this.sortData_C(inputFilename, outputFilename, failureProb, timeLimit);
        if (returnCode != 0) {
            throw new MemoryFailureException("Backup sorter failed");
        }
    }
}
