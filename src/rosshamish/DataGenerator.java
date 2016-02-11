package rosshamish;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class DataGenerator {
    public static final String testPath = "GeneratedData_test.txt";
    public static final String prodPath = "GeneratedData.txt";

    public void generateData(String outputFilename, Integer numIntegersToGenerate) throws FileNotFoundException {
        PrintWriter writer;
        try {
            writer = new PrintWriter(outputFilename, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }

        writer.println(numIntegersToGenerate);
        Random rand = new Random(System.currentTimeMillis());
        for (int i=0; i < numIntegersToGenerate; i++) {
            if (i > 0) {
                writer.print(" ");
            }
            writer.print(rand.nextInt());
        }
        writer.close();
    }
}
