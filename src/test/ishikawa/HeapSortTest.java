package test.ishikawa;

import java.util.Arrays;

import ishikawa.HeapSort;
import org.junit.Test;
import static org.junit.Assert.*;

public class HeapSortTest {

    @Test
    public void testEmpty() {
        int[] elements = {};
        HeapSort.sort(elements);
        assertEquals(0, elements.length);
    }

    @Test
    public void test_single_element() {
        int[] elements = {123};
        HeapSort.sort(elements);
        assertEquals(1, elements.length);
        assertEquals(123, elements[0]);
    }

    @Test
    public void test_sorted_array() {
        int[] elements = {1, 2, 3};
        HeapSort.sort(elements);
        assertEquals(3, elements.length);
        assertTrue(Arrays.equals(elements, new int[]{1, 2, 3}));
    }

    @Test
    public void test_simple() {
        int[] elements = {123, 20, 55, 40, 0};
        HeapSort.sort(elements);
        assertEquals(5, elements.length);
        assertTrue(Arrays.equals(elements, new int[]{0, 20, 40, 55, 123}));
    }

    @Test
    public void test_same_elements() {
        int[] elements = {0,0,0,0,0};
        HeapSort.sort(elements);
        assertEquals(5, elements.length);
        assertTrue(Arrays.equals(elements, new int[]{0,0,0,0,0}));
    }

}
