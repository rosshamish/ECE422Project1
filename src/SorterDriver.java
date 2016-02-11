import rosshamish.Adjudicator;
import rosshamish.DataSorter;
import rosshamish.WatchdogTimer;
import rosshamish.backup.DataSorterBackup;
import rosshamish.exceptions.IllegalIntegersFileException;
import rosshamish.primary.DataSorterPrimary;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SorterDriver {
    public static void main(String[] args) throws FileNotFoundException, IllegalIntegersFileException, InterruptedException {
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

        Adjudicator adjudicator = new Adjudicator();
        Thread sorterThread;
        Thread watchdogThread = null;

        List<DataSorter> sorters = new ArrayList<>();
        sorters.add(new DataSorterPrimary(inputFilename, outputFilename, primaryHazard));
        sorters.add(new DataSorterBackup(inputFilename, outputFilename, backupHazard));
        for (DataSorter sorter: sorters) {
            sorterThread = new Thread(sorter);
            watchdogThread = new Thread(new WatchdogTimer(sorterThread, timeLimit));
            sorterThread.start();
            watchdogThread.start();

            sorterThread.join();
            watchdogThread.join();
            if (adjudicator.passesAcceptanceTest(outputFilename)) {
                break;
            }
        }

        if (watchdogThread != null) {
            watchdogThread.join();
        }
        System.out.println("...done");
    }
}
