package test.rosshamish;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

import rosshamish.DataGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class DataGeneratorTest {

    @AfterClass
    public static void deleteTestFile() {
        try {
            if (Files.exists(DataGenerator.testPath)) {
                Files.delete(DataGenerator.testPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGenerateData() throws Exception {
        DataGenerator dg = new DataGenerator();
        dg.generateData(DataGenerator.testPath);

        assertTrue("file must exist", Files.exists(DataGenerator.testPath));

        List<String> lines = Files.readAllLines(DataGenerator.testPath);
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