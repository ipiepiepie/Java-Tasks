package xyz.ipiepiepie.test_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    
    public static void main(String... args) {
        // input //
        List<Integer> numbers = new ArrayList<>();
        // populate numbers list
        numbers.add(3);
        numbers.add(7);
        numbers.add(3);
        numbers.add(3);
        numbers.add(5);
        // TODO ...
        // output //
        Map<Integer, List<Integer>> result = new HashMap<>();
        
        // iterate over numbers indexes
        for (int i = 0; i < numbers.size(); i++) {
            int number = numbers.get(i);
            // get or create neighbours list
            List<Integer> neighbours = result.getOrDefault(number, new ArrayList<>());
            
            // add left neighbor if number isn't first
            if (i != 0)
                neighbours.add(numbers.get(i - 1));
            
            // add right neighbor if number isn't last
            if (i != numbers.size() - 1)
                neighbours.add(numbers.get(i + 1));
            
            // put current neighbours
            result.put(number, neighbours);
        }
        
        // print result
        for (Map.Entry<Integer, List<Integer>> entry : result.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            
            System.out.println(key + " " + value);
        }
    }
    
}
