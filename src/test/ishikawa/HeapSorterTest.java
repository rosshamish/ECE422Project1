package test.ishikawa;

import ishikawa.HeapSorter;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HeapSorterTest {
    private HeapSorter sorter;

    @Before
    public void createSorter() {
        this.sorter = new HeapSorter();
    }
    
    @Test
    public void testEmpty() {
        int[] elements = {};
        this.sorter.sort(elements, 0.0);
        assertEquals(0, elements.length);
    }

    @Test
    public void test_single_element() {
        int[] elements = {123};
        this.sorter.sort(elements, 0.0);
        assertEquals(1, elements.length);
        assertEquals(123, elements[0]);
    }

    @Test
    public void test_sorted_array() {
        int[] elements = {1, 2, 3};
        this.sorter.sort(elements, 0.0);
        assertEquals(3, elements.length);
        assertTrue(Arrays.equals(elements, new int[]{1, 2, 3}));
    }

    @Test
    public void test_simple() {
        int[] elements = {123, 20, 55, 40, 0};
        this.sorter.sort(elements, 0.0);
        assertEquals(5, elements.length);
        assertTrue(Arrays.equals(elements, new int[]{0, 20, 40, 55, 123}));
    }

    @Test
    public void test_same_elements() {
        int[] elements = {0,0,0,0,0};
        this.sorter.sort(elements, 0.0);
        assertEquals(5, elements.length);
        assertTrue(Arrays.equals(elements, new int[]{0,0,0,0,0}));
    }

}
