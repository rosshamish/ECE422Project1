package rosshamish.primary;

import rosshamish.exceptions.IllegalIntegersFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static List<Integer> read(String filename) throws IllegalIntegersFileException, IOException {
        ArrayList<Integer> integers = new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            throw new IOException(String.format("file with name [%s] not found", filename));
        }

        if (lines.size() != 2) {
            throw new IllegalIntegersFileException("File to read must be exactly 2 lines");
        }

        String[] strIntegers = lines.get(1).split("\\s+");
        Integer fileNumberOfIntegers;
        try {
            fileNumberOfIntegers = Integer.valueOf(lines.get(0));
        } catch (NumberFormatException e) {
            throw new IllegalIntegersFileException("First value in file (number of integers) must be an integer");
        }

        if (fileNumberOfIntegers != strIntegers.length) {
            throw new IllegalIntegersFileException("File header (length) does not match number of integers in file");
        }

        for (String s: strIntegers) {
            try {
                integers.add(Integer.valueOf(s));
            } catch (NumberFormatException e) {
                throw new IllegalIntegersFileException("All values in file must be integers");
            }
        }

        return integers;
    }
}
