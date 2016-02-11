import rosshamish.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("ECE 422, Reliable & Secure Systems Design");
        System.out.println("Project #1: Fault Tolerant Systems");
        System.out.println("Ross Anderson (rhanders / 1363708)");
        System.out.println("-----------------------------------------");

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