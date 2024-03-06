package xyz.ipiepiepie.test_2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    
    /**
     * Entry point.
     */
    public static void main(String[] args) {
        // products //
        List<Product> products = new ArrayList<>();
        /* TODO add more products here... */
        products.add(new Product("c", 5));
        products.add(new Product("b", 7));
        products.add(new Product("a", 5));
        // result //
        Product product = findCheapest(products);
        
        // print result
        if (product != null)
            System.out.printf("%s %d", product.getName(), product.getCost());
    }
    
    /**
     * Get the cheapest {@link Product} from {@link List}.
     *
     * @param products {@link Product} {@link List}
     * @return {@link Product} with the lowest cost <i>(first in alphabetical order)</i> or {@code null} if {@link Product} don't exist
     */
    private static Product findCheapest(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(Product::getName))
                .min(Comparator.comparing(Product::getCost))
                .orElse(null);
    }
    
}
