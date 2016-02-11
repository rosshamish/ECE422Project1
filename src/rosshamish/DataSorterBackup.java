package rosshamish;

public class DataSorterBackup implements DataSorter {
    private native void sortData_C(String inputFilename, String outputFilename, double hazard, int timeLimit);

    static {
        System.loadLibrary("DataSorterBackup");
    }

    @Override
    public void sort(String inputFilename, String outputFilename, Double hazard, Integer timeLimit) throws MemoryFailureException {
        this.sortData_C(inputFilename, outputFilename, hazard, timeLimit);
    }
}
