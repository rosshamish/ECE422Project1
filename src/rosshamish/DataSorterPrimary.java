package rosshamish;

import ishikawa.HeapSort;

import java.util.List;

public class DataSorterPrimary extends DataSorter {
    public DataSorterPrimary(Double HAZARD) {
        super(HAZARD);
    }

    @Override
    public List<Integer> sort(List<Integer> integers) throws MemoryFailureException {
        int[] ints = new int[integers.size()];
        for (int i=0; i < integers.size(); i++) {
            ints[i] = integers.get(i);
        }

        HeapSort.sort(ints);

        for (int i=0; i < integers.size(); i++) {
            integers.set(i, ints[i]);
        }
        return integers;
    }
}
