package rosshamish;

import java.nio.file.Path;
import java.util.List;

public abstract class DataSorter {
    protected final Double HAZARD;

    public DataSorter(Double HAZARD) {
        if (HAZARD < 0 || HAZARD > 0.5) {
            throw new IllegalArgumentException("HAZARD must be in the range [0,0.5]");
        }
        this.HAZARD = HAZARD;
    }

    public void sort(Path filePath) throws MemoryFailureException {
        List<Integer> integers = DataReader.read(filePath);
        integers = this.sort(integers);
        DataWriter.write(DataWriter.testPath, integers);
    }

    public static Double Hazard(Double v) {
        return v;
    }

    public abstract List<Integer> sort(List<Integer> integers) throws MemoryFailureException;
}
