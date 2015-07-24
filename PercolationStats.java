/**
 * Auto Generated Java Class.
 */
public class PercolationStats {
    
    private double[] thresholds;
    private int T;
    
    
    
    public PercolationStats(int N, int T) throws java.lang.IllegalArgumentException {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException(" N and T should be greater than 0");
        }
        
        thresholds = new double[T];
        int noThresholds = 0;
        this.T = T;
        
        for (int i = 1; i <= T; i++) {
            int isOpened = 0;
             Percolation perc = new Percolation (N);
            while (true) {
                 int row = StdRandom.uniform (1, N+1); 
                int col = StdRandom.uniform (1, N+1);
                
               
                    if (!perc.isOpen(row,col)) {
                         perc.open(row,col);
                         isOpened++;
                         
                         
                    }
                    if (perc.percolates()) {
                     thresholds[noThresholds] = isOpened/(double)(N*N);
                     noThresholds++;
                    
                       
                    
                    
                    break;
                    }
                    
                    }
            }
       
        }
    
    public double mean() {
        double threshold = 0;
        for (int i = 0; i < T; i++) {
        threshold += thresholds[i];
        }
        
        return threshold/T;
    }
    public double stddev() {
        double mean = mean();
        double std = 0;
        for(int i = 0; i < T; i++) {
        std += Math.pow(thresholds[i] - mean, 2);
        }
        return Math.sqrt(std/(T-1));
        
    }
    public double confidenceLo() {
        return mean() - ((1.96*stddev())/Math.sqrt(T));
    }
    public double confidenceHi() {
         return mean() + ((1.96*stddev())/Math.sqrt(T));
    }
    private void print() {
        StdOut.println("mean           = " + mean());
        StdOut.println("stddev         = " + stddev());
        StdOut.println("95% confidence interval = " + confidenceLo() + " , " + confidenceHi());
    }
    
    public static void main(String[] args) {
        new PercolationStats (Integer.parseInt(args[0]), Integer.parseInt(args[1])).print();
        
       
    }
}
