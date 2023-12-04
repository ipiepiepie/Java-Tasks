package xyz.ipiepiepie.arrays;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Represents subsequence of some array.
 */
public class Subsequence {
    private int[] numbers = new int[0];
    private final int startIndex;
    
    /**
     * Constructor for new instance.
     */
    public Subsequence(int startIndex) {
        this.startIndex = startIndex;
    }
    
    /**
     * Constructor for Subsequence of another Subsequence.
     */
    public Subsequence(int startIndex, int lastNumber, Subsequence old) {
        // add numbers from old Subsequence if can
        if (old.contains(lastNumber)) {
            this.numbers = Arrays.copyOfRange(old.numbers, old.indexOf(lastNumber) + 1, old.getSize());
            
            // shift start index to the index of element in old array
            startIndex -= numbers.length;
        }
        
        // set start index
        this.startIndex = startIndex;
    }
    
    /*================================* SUBSEQUENCE METHODS *===============================*/
    
    /**
     * Add number to current Subsequence.
     *
     * @param number number to add
     */
    public void add(int number) {
        int[] old = this.numbers;
        this.numbers = Arrays.copyOf(old, old.length + 1);
        
        // add number to array
        this.numbers[old.length] = number;
    }
    
    /**
     * Check if current Subsequence contains some {@code number}.
     *
     * @param number number to check in Subsequence.
     * @return {@code true} if contains, otherwise {@code false}.
     */
    public boolean contains(int number) {
        return Arrays.stream(this.numbers).anyMatch(x -> x == number);
    }
    
    /**
     * Get index of {@code number} in current Subsequence.
     *
     * @param number number to find index of.
     *
     * @return index of number if exists, otherwise {@code -1}.
     */
    public int indexOf(int number) {
        return IntStream.range(0, numbers.length).filter(i -> numbers[i] == number).findFirst().orElse(-1);
    }
    
    /*==================================* GETTER METHODS *==================================*/
    
    /// SIZE ///
    
    /**
     * Get current Subsequence size.
     *
     * @return subsequence size.
     */
    public int getSize() {
        return numbers.length;
    }
    
    /// START INDEX ///
    
    /**
     * Get start index.
     *
     * @return start index.
     */
    public int getStartIndex() {
        return startIndex;
    }
    
}
