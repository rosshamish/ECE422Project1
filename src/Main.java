import rosshamish.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("ECE 422, Reliable & Secure Systems Design");
        System.out.println("Project #1: Fault Tolerant Systems");
        System.out.println("Ross Anderson (rhanders / 1363708)");
        System.out.println("-----------------------------------------");

        Double HAZARD;
        if (args.length > 1) {
            throw new IllegalArgumentException("Too many arguments");
        } else if (args.length == 1) {
            try {
                HAZARD = Double.valueOf(args[0]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("HAZARD must be passed as a double");
            }
        } else {
            HAZARD = 0.0;
        }
        System.out.println(String.format("Using HAZARD=%.3f", HAZARD));

        DataGenerator generator = new DataGenerator();
        generator.generateData(DataGenerator.prodPath);

//        DataSorter sorter = new DataSorterPrimary(HAZARD);
        DataSorter sorter = new DataSorterBackup(HAZARD);
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