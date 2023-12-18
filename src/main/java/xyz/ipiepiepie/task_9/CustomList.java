package xyz.ipiepiepie.task_9;

import java.util.List;

/**
 * Represents list with self coded basic functional.
 */
public class CustomList {
    private final List<Integer> list;
    
    /**
     * Constructor for new instances.
     */
    public CustomList(List<Integer> list) {
        this.list = list;
    }
    
    /// MIN/MAX ///
    
    /**
     * Find {@link Math#max maximum} element in list.
     *
     * @return {@link Math#max maximum} element in list.
     *
     * @see Math#max(int, int)
     */
    public int max() {
        int max = Integer.MIN_VALUE;
        
        // find max element
        for (int number : list)
            max = Math.max(max, number);
        
        return max;
    }
    
    /**
     * Find {@link Math#min minimum} element in list.
     *
     * @return {@link Math#min minimum} element in list.
     *
     * @see Math#min(int, int)
     */
    public int min() {
        int min = Integer.MAX_VALUE;
        
        // find max element
        for (int number : list)
            min = Math.min(min, number);
        
        return min;
    }
    
    /// INDEX ///
    
    /**
     * Get element at the specified position.
     *
     * @param index position of element
     * @return the element at the specified position if exists.
     *
     * @see List#get(int)
     */
    public int get(int index) {
        return list.get(index);
    }
    
    /**
     * Set number to selected index.
     *
     * @param index index of future number
     * @param number number to set
     */
    public void set(int index, int number) {
        list.set(index, number);
    }
    
    /**
     * Get the first index of selected {@code number}.
     *
     * @param number number to get first index for
     * @return first index of number if exists, otherwise {@code -1}
     */
    public int firstIndexOf(int number) {
        // find the first existing index
        for (int i = 0; i < list.size(); i++)
            if (get(i) == number)
                return i;
        
        // return -1 if not found
        return -1;
    }
    
    /**
     * Get last index of selected {@code number}.
     *
     * @param number number to get last index for
     * @return last index of number if exists, otherwise {@code -1}
     */
    public int lastIndexOf(int number) {
        int latest = -1;
        
        // find latest index
        for (int i = 0; i < list.size(); i++)
            if (get(i) == number)
                latest = i;
        
        return latest;
    }
    
    /// TO STRING ///
    
    @Override
    public String toString() {
        return list.toString();
    }
    
}
