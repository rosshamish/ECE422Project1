package ishikawa;

/**
 * Attribution
 *  Author: Takanori Ishikawa
 *  URL: https://gist.github.com/ishikawa/9839
 *  Published: Sep 10, 2008
 *  Accessed: February 10, 2016
 */
public class HeapSorter {
    private int numMemoryAccesses = 0;
    public int getNumMemoryAccesses() {
        return numMemoryAccesses;
    }
    private void incrementMemoryAccesses(int by) {
        this.numMemoryAccesses += by;
    }
    private void clearMemoryAccesses() {
        this.numMemoryAccesses = 0;
    }

    public int sort(int[] a) {
        this.clearMemoryAccesses();
        for (int i = 1; i < a.length; i++) shiftup(a, i);
        for (int i = a.length - 1; i > 0; i--) {
            swap(a, 0, i);
            shiftdown(a, i - 1);
        }
        return getNumMemoryAccesses();
    }

    private void swap(int[] elements, int i, int j) {
        int tmp = elements[j];
        elements[j] = elements[i];
        elements[i] = tmp;
        this.incrementMemoryAccesses(2);
    }

    private void shiftup(int[] elements, int n) {
        int i = n;
        boolean condition;
        while (i > 0) {
            final int p = (i+1)/2-1;
            condition = elements[i] < elements[p];
            this.incrementMemoryAccesses(2);
            if (condition) break;
            swap(elements, i, p);
            i = p;
        }
    }

    private void shiftdown(int[] elements, int n) {
        int i = 0;
        boolean condition;
        while(i <= n) {
            int c = (i+1)*2-1; // left child
            if (c > n) {
                break;
            }
            condition = (c+1) <= n && elements[c+1] > elements[c];
            this.incrementMemoryAccesses(2);
            if (condition) {
                c += 1;
            }
            condition = elements[i] >= elements[c];
            this.incrementMemoryAccesses(2);
            if (condition) {
                break;
            }
            swap(elements, i, c);
            i = c;
        }
    }
}
