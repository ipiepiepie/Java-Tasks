package xyz.ipiepiepie.test_1;

import java.util.*;

public class Main {
    
    /**
     * Entry point.
     */
    public static void main(String[] args) {
        // points //
        List<Point> points = new ArrayList<>();
        /* TODO add more points here... */
        points.add(new Point(1, 3));
        points.add(new Point(-4, 2));
        // distance //
        double distance = 2.5;
        // result //
        Map<Point, Integer> results = new HashMap<>();
        
        // calculate number of neighbors for each Point
        for (Point point : points) {
            // find number of neighbors in selected distance
            int numberOfNeighbors = (int) points
                    .stream()
                    .filter(p -> p.getX() != point.getX() && p.getY() != point.getY())
                    .filter(p -> p.distance(point) < distance)
                    .count();
            
            // add calculations result
            results.put(point, numberOfNeighbors);
        }
        
        // print the best result
        results.entrySet().stream().max(Map.Entry.comparingByValue()).ifPresent(e -> System.out.println(e.getKey()));
    }
    
}
