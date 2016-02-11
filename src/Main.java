import rosshamish.*;

public class Main {
    public static void main(String[] args) {
        Double HAZARD = 0.0; // TODO take hazard from cli args

        DataGenerator generator = new DataGenerator();
        generator.generateData(DataGenerator.prodPath);

        DataSorter sorter = new DataSorterPrimary(HAZARD);
        try {
            sorter.sort(DataGenerator.prodPath);
        } catch (MemoryFailureException e) {
            e.printStackTrace(); // TODO remove
            // TODO do something?
            sorter = new DataSorterBackup(HAZARD);
            try {
                sorter.sort(DataGenerator.prodPath);
            } catch (MemoryFailureException e1) {
                e1.printStackTrace(); // TODO remove
                // TODO do something?
            }
        }
    }
}