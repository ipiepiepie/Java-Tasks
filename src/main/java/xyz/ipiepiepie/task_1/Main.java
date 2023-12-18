package xyz.ipiepiepie.task_1;

import java.util.Scanner;

/**
 * {@link Car Cars} meeting time calculation program.
 * <p>
 * Task №3 from <a href="https://edu.vsu.ru/pluginfile.php/6688971/mod_folder/content/0/Task_01.pdf?forcedownload=0">this list</a>
 */
public class Main {
    private static final Scanner input = new Scanner(System.in); // console input
    
    /**
     * Entry point
     */
    public static void main(String[] args) {
        // initialise first car
        System.out.println("== create first car ==");
        Car first = getCarData();
        
        // initialise second car
        System.out.println("== create second car ==");
        Car second = getCarData();
        
        // get distance between car
        System.out.println("== input additional information ==");
        System.out.print("Input distance between cars:\n> ");
        int distance = input.nextInt();
        
        System.out.printf("\nMeeting time is %.2f", calcTime(first, second, distance));
    }
    
    /**
     * Create {@link Car} from {@link #input} data.
     *
     * @return new {@link Car} instance
     */
    private static Car getCarData() {
        int velocity;
        int acceleration;
        
        // get car velocity from input
        System.out.print("Input car velocity:\n> ");
        velocity = input.nextInt();
        
        // get car acceleration from input
        System.out.print("Input car acceleration:\n> ");
        acceleration = input.nextInt();
        
        // log message
        System.out.println("Car successfully initialised!");
        
        return new Car(velocity, acceleration);
    }
    
    /**
     * Calculating convergence time reduces to solving the quadratic equation and find positive value.
     * <p>
     * You can find accelerated uniformly distance (S) by using next formula: <br>
     * <i>{@code S = vt + at^2 / 2}</i>
     * <p>
     * After distances traveled sum will equal starting {@code distance} between {@link Car Cars}, they'll meet.
     * <p>
     * Thus, to find a meet time, you need to write and solve simple quadratic equation. <br>
     * <i>{@code ax^2 + bx + c = 0}</i> <br>
     * in which:
     * <ul>
     *     <li><b>a</b> equals half sum of {@link Car#getAcceleration() accelrations}</li>
     *     <li><b>b</b> equals sum of {@link Car#getVelocity() velocities}</li>
     *     <li><b>c</b> equals negative {@code distance}</li>
     * </ul>
     *
     * @param first first {@link Car}
     * @param second second {@link Car}
     * @param distance distance between {@link Car Cars}
     *
     * @return {@link Car} meeting time
     */
    private static double calcTime(Car first, Car second, int distance) {
        double a = (first.getAcceleration() + second.getAcceleration()) / 2d;
        double b = (first.getVelocity() + second.getVelocity());
        double c = - distance;
        
        // calculate square root of discriminant (b^2 - 4ac)
        double d = Math.sqrt(Math.pow(b, 2) - 4 * a * c);
        
        // solve quadratic equation
        double x1 = (-b + d) / (2 * a);
        double x2 = (-b - d) / (2 * a);
        
        // one of the values will be negative, so we'll return positive one
        return Math.max(x1, x2);
    }
    
}
