package rosshamish;

import java.util.List;

public class DataSorterBackup extends DataSorter {

    public DataSorterBackup(Double HAZARD) {
        super(HAZARD);
    }

    private native void sortData_C(double HAZARD);

    static {
        System.loadLibrary("DataSorterBackup");
    }

    @Override
    public List<Integer> sort(List<Integer> integers) throws MemoryFailureException {
        this.sortData_C(this.HAZARD);
        return null;
    }
}
