package xyz.ipiepiepie.test;

public class Main {
    
    /**
     * Some tests for equation
     */
    public static void main(String... args) {
        System.out.printf("%.06f%n", solve(1.2, 3, 5));
        System.out.printf("%.06f%n", solve(10.2, 33.333333, 3));
        System.out.printf("%.06f%n", solve(5, 3, 2));
    }
    
    /**
     * Solves equation.
     * <p>
     * Recursively goes from right to left of equation and multiplies left part to right.
     *
     * @param a some value
     * @param b another value
     * @param n repeats count
     * @return solved equation
     */
    public static double solve(double a, double b, int n) {
        double result = 1;
        
        // calculate equation from right to left
        for (int i = 1; i <= n; i++)
            result = a - (i * b * result);
    
        return result;
    }
    
}
