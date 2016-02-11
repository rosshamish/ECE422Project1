package rosshamish;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    public static final String testPath = "GeneratedData_test.txt";
    public static final String prodPath = "GeneratedData.txt";

    public void generateData(String outputFilename, Integer numIntegersToGenerate) throws FileNotFoundException {
        List<Integer> integers = new ArrayList<>();
        Random rand = new Random(System.currentTimeMillis());
        for (int i=0; i < numIntegersToGenerate; i++) {
            integers.add(rand.nextInt());
        }
        DataWriter.writeIntegers(outputFilename,integers);
    }
}
