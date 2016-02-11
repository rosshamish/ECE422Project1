package test.rosshamish;

import org.junit.Test;
import rosshamish.DataGenerator;
import rosshamish.DataReader;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DataReaderTest {

    @Test
    public void testRead() throws Exception {
        DataGenerator dg = new DataGenerator();
        dg.generateData(DataGenerator.testPath, 100);

        List<Integer> integers = DataReader.read(DataGenerator.testPath);
        assertEquals("list must be of the correct length", integers.size(), 100);
    }
}