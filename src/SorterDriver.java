import rosshamish.Adjudicator;
import rosshamish.DataSorter;
import rosshamish.WatchdogTimer;
import rosshamish.backup.DataSorterBackup;
import rosshamish.exceptions.IllegalIntegersFileException;
import rosshamish.primary.DataSorterPrimary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SorterDriver {
    public static void main(String[] args) throws FileNotFoundException, IllegalIntegersFileException, InterruptedException {
        System.out.println("\nComponent 2: Data Sorter");

        if (args.length != 5) {
            System.err.println("5 arguments required: inputFilename, outputFilename, primaryFailureProb, backupFailureProb, timeLimit");
            System.exit(1);
        }

        String inputFilename = args[0];
        String outputFilename = args[1];
        Double primaryFailureProb = Double.valueOf(args[2]);
        Double backupFailureProb = Double.valueOf(args[3]);
        Integer timeLimit = Integer.valueOf(args[4]);

        System.out.println(String.format("Using options:\n" +
                "\tinputFilename=%s\n" +
                "\toutputFilename=%s\n" +
                "\tprimaryFailureProb=%.3f\n" +
                "\tbackupFailureProb=%.3f\n" +
                "\ttimeLimit=%d", inputFilename, outputFilename, primaryFailureProb, backupFailureProb, timeLimit));

        WatchdogTimer watchdog = null;
        Adjudicator adjudicator = new Adjudicator();
        Thread sorterThread = null;

        List<DataSorter> sorters = new ArrayList<>();
        sorters.add(new DataSorterPrimary(inputFilename, outputFilename, primaryFailureProb));
        sorters.add(new DataSorterBackup(inputFilename, outputFilename, backupFailureProb));
        for (DataSorter sorter: sorters) {
            sorterThread = new Thread(sorter);
            watchdog = new WatchdogTimer(sorterThread, timeLimit);

            sorterThread.start();
            watchdog.start();

            sorterThread.join();
            watchdog.cancel();

            try {
                if (adjudicator.passesAcceptanceTest(outputFilename)) {
                    break;
                }
            } catch (IOException e) {
                System.err.println(String.format("Sorter %s failed acceptance test: '%s'", sorter.toString(), e.getMessage()));
            }
        }

        System.out.println("...done");
    }
}
