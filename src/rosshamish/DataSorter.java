package rosshamish;

public interface DataSorter {
    void sort(String inputFilename, String outputFilename, Double hazard, Integer timeLimit) throws MemoryFailureException;
}
