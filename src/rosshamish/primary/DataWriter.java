package rosshamish.primary;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class DataWriter {
    public static void writeIntegers(String outputFilename, List<Integer> integers) throws FileNotFoundException {
        PrintWriter writer;
        try {
            writer = new PrintWriter(outputFilename, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }

        writer.println(integers.size());
        for (int i=0; i < integers.size(); i++) {
            if (i > 0) {
                writer.print(" ");
            }
            writer.print(integers.get(i));
        }
        writer.close();
    }
}
