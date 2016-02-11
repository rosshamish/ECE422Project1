package rosshamish;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static List<Integer> read(String filename) {
        ArrayList<Integer> integers = new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found");
        }

        if (lines.size() != 2) {
            throw new IllegalArgumentException("File to read must be exactly 2 lines");
        }

        String[] strIntegers = lines.get(1).split("\\s+");
        if (Integer.valueOf(lines.get(0)) != strIntegers.length) {
            throw new IllegalArgumentException("File header (length) does not match number of integers in file");
        }

        for (String s: strIntegers) {
            integers.add(Integer.valueOf(s));
        }

        return integers;
    }
}
