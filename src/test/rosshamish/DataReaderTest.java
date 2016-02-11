package test.rosshamish;

import org.junit.Test;
import rosshamish.DataGenerator;
import rosshamish.DataReader;

import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

public class DataReaderTest {

    @Test
    public void testRead() throws Exception {
        DataGenerator dg = new DataGenerator();
        dg.generateData(DataGenerator.testPath);

        List<Integer> integers = DataReader.read(DataGenerator.testPath);
        assertTrue("list must be of nonzero length", integers.size() > 0);
    }
}