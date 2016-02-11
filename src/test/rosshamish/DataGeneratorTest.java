package test.rosshamish;

import org.junit.After;
import org.junit.Test;
import rosshamish.DataGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class DataGeneratorTest {

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
    public void testGenerateData() throws Exception {
        DataGenerator dg = new DataGenerator();
        dg.generateData(DataGenerator.testPath, 100);

        assertTrue("file must exist", Files.exists(Paths.get(DataGenerator.testPath)));

        List<String> lines = Files.readAllLines(Paths.get(DataGenerator.testPath));
        assertEquals("file should be exactly 2 lines long",
                lines.size(), 2);

        Integer N = -1;
        try {
            N = Integer.valueOf(lines.get(0));
        } catch (NumberFormatException e) {
            fail("first line should be exactly one number (the number of integers, N)");
        }

        String[] sNums = lines.get(1).split("\\s+");
        assertEquals("second line should contain exactly N values",
                Integer.valueOf(sNums.length), N);

        for (String num: sNums) {
            try {
                Integer.valueOf(num);
            } catch (NumberFormatException e) {
                fail("each value should be an integer");
            }
        }
    }
}