package ishikawa;

/**
 * Attribution
 *  Author: Takanori Ishikawa
 *  URL: https://gist.github.com/ishikawa/9839
 *  Published: Sep 10, 2008
 *  Accessed: February 10, 2016
 */
public class HeapSort {
    public static void sort(int[] a) {
        for (int i = 1; i < a.length; i++) shiftup(a, i);
        for (int i = a.length - 1; i > 0; i--) {
            swap(a, 0, i);
            shiftdown(a, i - 1);
        }
    }

    private static void swap(int[] elements, int i, int j) {
        int tmp = elements[j];
        elements[j] = elements[i];
        elements[i] = tmp;
    }

    private static void shiftup(int[] elements, int n) {
        int i = n;
        while (i > 0) {
            final int p = (i+1)/2-1;
            if (elements[i] < elements[p]) break;
            swap(elements, i, p);
            i = p;
        }
    }

    private static void shiftdown(int[] elements, int n) {
        int i = 0;
        while(i <= n) {
            int c = (i+1)*2-1; // left child
            if (c > n) break;
            if ((c+1) <= n && elements[c+1] > elements[c]) c++;
            if (elements[i] >= elements[c]) break;
            swap(elements, i, c);
            i = c;
        }
    }
}
