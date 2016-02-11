import rosshamish.DataSorter;
import rosshamish.DataSorterBackup;
import rosshamish.DataSorterPrimary;
import rosshamish.MemoryFailureException;

public class Sorter {
    public static void main(String[] args) {
        System.out.println(Strings.Header);
        System.out.println("Component 2: Data Sorter");

        if (args.length != 5) {
            System.err.println("5 arguments required: inputFilename, outputFilename, primaryHazard, backupHazard, timeLimit");
            System.exit(1);
        }

        String inputFilename = args[0];
        String outputFilename = args[1];
        Double primaryHazard = Double.valueOf(args[2]);
        Double backupHazard = Double.valueOf(args[3]);
        Integer timeLimit = Integer.valueOf(args[4]);

        System.out.println(String.format("Using options:\n" +
                "\tinputFilename=%s\n" +
                "\toutputFilename=%s\n" +
                "\tprimaryHazard=%.3f\n" +
                "\tbackupHazard=%.3f\n" +
                "\ttimeLimit=%d", inputFilename, outputFilename, primaryHazard, backupHazard, timeLimit));

        DataSorter sorter = new DataSorterPrimary();
        try {
            sorter.sort(inputFilename, outputFilename, primaryHazard, timeLimit);
        } catch (MemoryFailureException e) {
            e.printStackTrace(); // TODO remove
            // TODO do something?
            sorter = new DataSorterBackup();
            try {
                sorter.sort(inputFilename, outputFilename, backupHazard, timeLimit);
            } catch (MemoryFailureException e1) {
                e1.printStackTrace(); // TODO remove
                // TODO do something?
            }
        }

        System.out.println("...done");
    }
}
