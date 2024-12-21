package Programs.Program1;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;

    public MinPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1]; // modified to start at index 1
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Key key) {// changed to add element to mbinbary heap minpq and resize accordingly
        if (n == pq.length - 1)
            resize(2 * pq.length);
        pq[++n] = key;
        swim(n);
    }

    public Key delMin() {// modified to delete minimum item and restore the heap to a complete bt
        Key min = pq[1];
        exch(1, n--);
        sink(1); // moves new root down, restores the heap
        pq[n + 1] = null;
        if (n > 0 && n == (pq.length - 1) / 4)
            resize(pq.length / 2);
        return min;
    }

    private void swim(int k) {// repeatadly checks element with parent to move it up the tree
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {// repeatadly checks element with children to move it down the tree
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1))
                j++;
            if (!greater(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) { // added to simplify compare logic
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {// added to simplify exchanging of keys in sink/swim
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }
}
