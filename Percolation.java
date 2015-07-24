/**
 * Auto Generated Java Class.
 */

public class Percolation {
    private WeightedQuickUnionUF site;
    private boolean[] isOpened;
    private int size;
    
    public Percolation(int N) throws java.lang.IllegalArgumentException {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be greater than 0");
        }
        site = new WeightedQuickUnionUF((N*N) + 2);
        size = N*N;
        isOpened = new boolean[N*N];
        for (int i = 0; i < size; i++) {
        isOpened[i] = false;
        }
    }
    public void open(int i, int j) throws java.lang.IndexOutOfBoundsException {
        validate(i);
       validate(j);
        int no = xyTo1D(i, j);
        int k = (getRowSize() + 1) -i;
        
        isOpened[no] = true;
        int three = no + getRowSize();
        int four = no - getRowSize();
        if (no >= 0 && no < getRowSize()) {
            site.union(no, size);
        }
        if (no >= (size - getRowSize())  && no < size) {
            site.union(no, size + 1);
        }
        if (no != (getRowSize()*k) - 1) {
            if (isOpened[no + 1]) {
            site.union(no, no + 1);
        }
        }
        if (no != (getRowSize()*(k - 1))) {
            if (isOpened[no - 1]) {
            site.union(no, no - 1 );
        }
        }
                
        if (three < size) {
            if (isOpened[three]) {
                site.union(no, three);
            }
        }
        if (four >= 0) {
            if (isOpened[four]) {
                site.union(no, four);
            }
        }
        
    }
    private int getRowSize() {
        return (int) Math.sqrt(size);
    }
   
    private int xyTo1D(int i, int j) {
       int col = j;
       int row = (getRowSize() + 1) - i;
       return ((col - 1) + (getRowSize() * (row - 1)));
    }
    private void validate(int p) {
        int N = getRowSize();
        if (p <= 0 || p > N) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + N);
        }
    }
    public boolean isOpen(int i, int j) throws java.lang.IndexOutOfBoundsException {
        validate(i);
        validate(j);
        return isOpened[xyTo1D(i, j)];
    }
    public boolean isFull(int i, int j) {
       validate(i);
       validate(j);
        int no = xyTo1D(i, j);
        
        return site.connected(no, size + 1);
    }   
    public boolean percolates() {
        return site.connected(size, size + 1);
    }
     public static void main(String[] args) {
         
        
    }
    
   
        
    
}
