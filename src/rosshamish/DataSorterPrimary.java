package rosshamish;

import ishikawa.HeapSort;

import java.util.List;

public class DataSorterPrimary implements DataSorter {

    @Override
    public void sort(String inputFilename, String outputFilename, Double hazard, Integer timeLimit) throws MemoryFailureException {
        List<Integer> integers = DataReader.read(inputFilename);
        integers = this.sort(integers, hazard, timeLimit);
        // TODO write sorted output to file in this class
    }

    private List<Integer> sort(List<Integer> integers, Double hazard, Integer timeLimit) throws MemoryFailureException {
        int[] ints = new int[integers.size()];
        for (int i=0; i < integers.size(); i++) {
            ints[i] = integers.get(i);
        }

        HeapSort.sort(ints); // todo pass hazard, timeLimit

        for (int i=0; i < integers.size(); i++) {
            integers.set(i, ints[i]);
        }
        return integers;
    }
}
