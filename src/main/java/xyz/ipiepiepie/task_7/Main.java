package xyz.ipiepiepie.task_7;

import java.util.*;

/**
 * Longest {@link Subsequence} calculation.
 * <p>
 * Task №26 from <a href="https://edu.vsu.ru/pluginfile.php/6688971/mod_folder/content/0/Task_07.pdf?forcedownload=0">this list</a>
 *
 * @author ipiepiepie
 */
public class Main {
    
    /**
     * Entry point
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter numbers, divided by spaces:\n> ");

        String inputLine = input.nextLine();

        // FIXME 2 7 8 11 9 11 19 7 19 15 19 12 14 9 13

        // test for random values if test flag exists
        if (inputLine.contains("test")) {
            String[] arguments = inputLine.split(" ");

            // test X times if selected, otherwise 10 times
            test(arguments.length >= 2 ? Integer.parseInt(arguments[1]) : 10);

            return;
        }
        
        // parse numbers array from input
        int[] numbers = Arrays.stream(inputLine.split(" ")).mapToInt(Integer::valueOf).toArray();
        
        // find the longest subsequence
        Subsequence subsequence = findLongest(numbers);
        
        System.out.printf("The longest subsequence has size of %d and starts at index %d", subsequence.getSize(), subsequence.getStartIndex());
    }

    /**
     * Tests program {@code X times}.
     *
     * @param count count of tests
     */
    private static void test(int count) {
        int min = 1;  // minimum array size and contents value
        int max = 20; // maximum array size and contents value

        // test x times
        for (int i = 0; i <= count - 1; i++) {
            int[] numbers = new int[new Random().nextInt(min, max)];

            // fill test numbers with random values
            for (int k = 0; k < numbers.length; k++)
                numbers[k] = new Random().nextInt(min, max);

            System.out.printf("(Test #%d) Array elements are %s\n", i + 1, Arrays.toString(numbers));

            // find the longest subsequence
            Subsequence subsequence = findLongest(numbers);
            System.out.printf("(Test #%d) The longest subsequence has size of %d and starts at index %d\n", i + 1, subsequence.getSize(), subsequence.getStartIndex());
            System.out.println();
        }
    }
    
    /**
     * Find the longest {@link Subsequence} of some numbers array.
     *
     * @param numbers numbers array
     * @return The longest {@link Subsequence}
     */
    private static Subsequence findLongest(int[] numbers) {
        // longest //
        Subsequence longest = new Subsequence(0);
        // current //
        Subsequence current = new Subsequence(0);
        
        // iterate over numbers in array
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            
            // count in last number if we reached end, and it isn't duplicated
            if (i == (numbers.length - 1) && !current.contains(number))
                current.add(number);
            
            // calc results if we have duplicate number or reached the end
            if (current.contains(number) || i == (numbers.length - 1)) {
                // check if current subsequence is the biggest one
                if (current.getSize() >= longest.getSize())
                    longest = current;
                
                // reset currents
                Subsequence old = current;
                current = new Subsequence(i, numbers[i], old);
            }
            
            current.add(number);
        }
        
        // return longest subsequence
        return longest;
    }
    
}
