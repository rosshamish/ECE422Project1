import rosshamish.DataSorter;
import rosshamish.backup.DataSorterBackup;
import rosshamish.exceptions.IllegalIntegersFileException;
import rosshamish.exceptions.MemoryFailureException;
import rosshamish.primary.DataSorterPrimary;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Sorter {
    public static void main(String[] args) throws FileNotFoundException, IllegalIntegersFileException {
        System.out.println("\nComponent 2: Data Sorter");

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

        List<DataSorter> sorters = new ArrayList<>();
        sorters.add(new DataSorterPrimary());
        sorters.add(new DataSorterBackup());
        for (DataSorter sorter: sorters) {
            try {
                sorter.sort(inputFilename, outputFilename, primaryHazard, timeLimit);
            } catch (FileNotFoundException e) {
                throw e;
            } catch (MemoryFailureException e) {
                continue;
            } catch (IllegalIntegersFileException e) {
                throw e;
            }
            break;
        }

        System.out.println("...done");
    }
}
