package test.rosshamish;

import org.junit.Test;
import rosshamish.DataGenerator;
import rosshamish.DataSorter;
import rosshamish.DataSorterBackup;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class DataSorterBackupTest {

    @Test
    public void testSortData() throws Exception {
        DataGenerator dg = new DataGenerator();
        dg.generateData(DataGenerator.testPath, 100);

        DataSorter sorter = new DataSorterBackup();
        List<Integer> integers = new ArrayList<Integer>() {{
            add(0);
            add(1);
            add(9);
            add(-1);
            add(0);
            add(100);
            add(-100);
        }};
        fail("Test case not yet implemented");
//        List<Integer> sorted = sorter.sort(integers);
//        Integer latest = sorted.get(0);
//        for (Integer i: sorted) {
//            assertTrue(i >= latest);
//            latest = i;
//        }
    }
}