package rosshamish;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DataGenerator {
    public static final Path testPath = Paths.get("GeneratedData_test.txt");
    public static final Path prodPath = Paths.get("GeneratedData.txt");

    public void generateData() {
        this.generateData(prodPath);
    }

    public void generateData(Path outfilePath) {

    }
}
