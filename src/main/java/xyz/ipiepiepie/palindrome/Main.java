package xyz.ipiepiepie.palindrome;

import java.util.Scanner;

/**
 * Finds {@code N} <a href="https://en.wikipedia.org/wiki/Palindrome">palindrome</a>
 * <p>
 * Task №26 from <a href="https://edu.vsu.ru/pluginfile.php/6688971/mod_folder/content/0/Task_04.doc?forcedownload=1">this list</a>
 */
public class Main {
    
    /**
     * Entry point
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter palindrome position:\n> ");
        int n = input.nextInt();
        
        int counter = 0;  // palindromes counter
        int number = 0;  // current number
        
        // search palindrome until we reach selected position
        while (counter < n) {
            if (isPalindrome(number++))
                counter++;
        }
        
        System.out.printf("%dth palindrome is %d", n, number - 1);
    }
    
    /**
     * Check if number is palindrome.
     *
     * @param number number to check
     * @return {@code true} or {@code false}.
     */
    private static boolean isPalindrome(int number) {
        if (length(number) == 1) return true; // any one-digit number will be palindrome
        
        // if number equals its reversed representation, then number is a palindrome
        return number == mirror(number);
    }
    
    /**
     * Get mirrored number.
     * <p>
     * E.x. 574 will return 475
     *
     * @param number number to reverse.
     * @return reversed number.
     */
    private static int mirror(int number) {
        // get initial number place
        int place = (int) Math.pow(10, length(number) - 1);
        
        // reversed number
        int result = 0;
        while (number > 0) {
            result += place * (number % 10); // add number with reversed place
            
            place /= 10; // go to previous place
            number /= 10; // go to next digit
        }
        
        return result;
    }
    
    /**
     * Get length of number without using {@link String#length()}.
     *
     * @param number number to get length for
     * @return length of number
     */
    private static int length(int number) {
        int length = 0;
        
        // zero is an exception
        if (number == 0) return 1;
        
        // count digits until number will reach zero
        while (number > 0) {
            length++;
            number /= 10;
        }
        
        return length;
    }
}
