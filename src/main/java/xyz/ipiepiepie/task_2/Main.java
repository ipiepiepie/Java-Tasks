package xyz.ipiepiepie.task_2;

import java.util.Scanner;

/**
 * Number min and max digits
 * <p>
 * Task №19 in <a href="https://edu.vsu.ru/pluginfile.php/6688971/mod_folder/content/0/Task_02.pdf?forcedownload=1">this list</a>
 */
public class Main {
    
    /**
     * Entry point
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // get number
        System.out.print("Specify number\n> ");
        int number = input.nextInt();
        
        // send output
        System.out.printf("Min digit equals '%d'\nMax digit equals '%d'",
                getMinDigit(String.valueOf(number)),
                getMaxDigit(String.valueOf(number))
        );
    }
    
    /**
     * Finds minimal digit in number.
     *
     * @param number number to find min digit
     * @return minimal digit in number
     */
    private static int getMinDigit(String number) {
        int min = Integer.MAX_VALUE; // specify the highest value
        
        // iterate over digits and find minimal digit in number
        for (char digit : number.toCharArray())
            min = Math.min(Character.getNumericValue(digit), min);
        
        return min;
    }
    
    /**
     * Finds maximal digit in number.
     *
     * @param number number to find max digit
     * @return maximal digit in number
     */
    private static int getMaxDigit(String number) {
        int max = Integer.MIN_VALUE; // specify the lowest value
        
        // iterate over digits and find maximal digit in number
        for (char digit : number.toCharArray())
            max = Math.max(Character.getNumericValue(digit), max);
        
        return max;
    }
    
}
