package xyz.ipiepiepie.test_2;

/**
 * Represents shop product.
 */
public class Product {
    private final String name;
    private final int cost;
    
    /**
     * Constructor for new instances
     *
     * @param name name of product
     * @param cost cost of product
     */
    public Product(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }
    
    /// GETTERS ///
    
    /**
     * Get {@code name} of current product.
     *
     * @return product name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get {@code cost} of current product.
     *
     * @return product cost
     */
    public int getCost() {
        return cost;
    }
    
}
