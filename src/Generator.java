import rosshamish.DataGenerator;
import rosshamish.Strings;

import java.io.FileNotFoundException;

public class Generator {
    public static void main(String[] args) {
        System.out.println(Strings.Header);
        System.out.println("Component 1: Data Generator");

        if (args.length != 2) {
            System.err.println("2 arguments required: outputFilename, numIntegersToGenerate");
            System.exit(1);
        }

        String outputFilename = args[0];
        Integer numIntegersToGenerate = Integer.valueOf(args[1]);

        System.out.println(String.format("Using options:\n" +
                "\toutputFilename=%s\n" +
                "\tnumIntegersToGenerate=%d", outputFilename, numIntegersToGenerate));

        DataGenerator generator = new DataGenerator();
        try {
            generator.generateData(outputFilename, numIntegersToGenerate);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("...done");
    }

}