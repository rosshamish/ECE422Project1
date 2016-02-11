package test.rosshamish;

import org.junit.Test;
import rosshamish.DataGenerator;
import rosshamish.DataSorter;
import rosshamish.DataSorterPrimary;

import java.util.*;

import static org.junit.Assert.*;

public class DataSorterPrimaryTest {

    @Test
    public void testHazardRange() throws Exception {
        DataSorter sorter;
        Boolean threw;

        threw = Boolean.FALSE;
        try {
            sorter = new DataSorterPrimary(-0.1);
        } catch (IllegalArgumentException e) {
            threw = Boolean.TRUE;
        }
        assertTrue("should throw an exception on HAZARD below range", threw);

        threw = Boolean.FALSE;
        try {
            sorter = new DataSorterPrimary(0.51);
        } catch (IllegalArgumentException e) {
            threw = Boolean.TRUE;
        }
        assertTrue("should throw an exception on HAZARD above range", threw);

        threw = Boolean.FALSE;
        try {
            sorter = new DataSorterPrimary(0.0);
        } catch (IllegalArgumentException e) {
            threw = Boolean.TRUE;
        }
        assertFalse("should NOT throw an exception on HAZARD=0", threw);

        threw = Boolean.FALSE;
        try {
            sorter = new DataSorterPrimary(0.2);
        } catch (IllegalArgumentException e) {
            threw = Boolean.TRUE;
        }
        assertFalse("should NOT throw an exception on HAZARD in range", threw);
    }

    @Test
    public void testSortData() throws Exception {
        DataGenerator dg = new DataGenerator();
        dg.generateData(DataGenerator.testPath);

        DataSorter sorter = new DataSorterPrimary(DataSorter.Hazard(0.0));
        List<Integer> integers = new ArrayList<Integer>() {{
            add(0);
            add(1);
            add(9);
            add(-1);
            add(0);
            add(100);
            add(-100);
        }};
        List<Integer> sorted = sorter.sort(integers);
        Integer latest = sorted.get(0);
        for (Integer i: sorted) {
            assertTrue(i >= latest);
            latest = i;
        }
    }
}