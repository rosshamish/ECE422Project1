package test.rosshamish;

import org.junit.After;
import org.junit.Test;
import rosshamish.DataGenerator;
import rosshamish.DataReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DataReaderTest {

    @After
    public void deleteTestFile() {
        try {
            if (Files.exists(Paths.get(DataGenerator.testPath))) {
                Files.delete(Paths.get(DataGenerator.testPath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead() throws Exception {
        DataGenerator dg = new DataGenerator();
        dg.generateData(DataGenerator.testPath, 100);

        List<Integer> integers = DataReader.read(DataGenerator.testPath);
        assertEquals("list must be of the correct length", integers.size(), 100);
    }
}