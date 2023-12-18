package xyz.ipiepiepie.task_10;

/**
 * Represents candy in task.
 */
public class Candy {
    private final String name;
    private final int cost;
    
    /**
     * Constructor for new instances.
     */
    public Candy(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }
    
    /// NAME ///
    
    /**
     * Get name of current candy.
     *
     * @return candy name
     */
    public String getName() {
        return name;
    }
    
    /// COST ///
    
    /**
     * Get cost of current candy.
     *
     * @return candy cost
     */
    public int getCost() {
        return cost;
    }
    
}
