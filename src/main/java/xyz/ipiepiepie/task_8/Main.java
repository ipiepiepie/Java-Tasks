package xyz.ipiepiepie.task_8;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import xyz.ipiepiepie.task_8.gui.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Multidimensional array task.
 * <p>
 * Task №13 from <a href="https://edu.vsu.ru/pluginfile.php/6688971/mod_folder/content/0/Task_08.pdf?forcedownload=0">this list</a>
 *
 * @author ipiepiepie
 */
public class Main {
    
    /**
     * Entry point.
     */
    public static void main(String[] args) {
        Options options = new Options();
        // register options
        options.addOption("d", "debug", false, "Run program in debug mode");
        options.addOption("g", "gui", false, "Run program in GUI mode");
        
        // parse command
        parseArgs(options, args);
    }
    
    /// COMMAND FUNCTIONS ///
    
    /**
     * Parse jar args.
     *
     * @param options registered arguments.
     * @param args received arguments.
     */
    private static void parseArgs(Options options, String[] args) {
        try {
            CommandLine cmd = new DefaultParser().parse(options, args);
            
            if (cmd.hasOption("d")) {
                String path = "src/main/resources/task_8/debug_%d.txt";
                
                // iterate over tests
                for (int i = 0; i < 6; i++) {
                    System.out.printf("======== Testing debug_%d.txt ========%n", i);
                    int[][] array = read(new File(String.format(path, i)));
                    
                    // skip array if it's empty
                    if (array == null) continue;
                    
                    // print 2-D array
                    System.out.println("Input array:");
                    print(array);
                    // print result
                    System.out.println("Test result:");
                    System.out.printf("- Array is%s%n", isOrdered(array) ? " ordered (true)" : "n't ordered (false)");
                }
            } else if (cmd.hasOption("g")) {
                new Menu();
            } else {
                String path = cmd.getArgs()[0];
                System.out.printf("Array is%s%n", isOrdered(new File(path)) ? " ordered (true)" : "n't ordered (false)");
            }
        } catch (ParseException e) {
            System.err.println("Unexpected exception:" + e.getMessage());
        }
    }
    
    /// ORDER FUNCTIONS ///
    
    /**
     * Check if numbers array inside {@link File} is ordered.
     *
     * @return {@code true} if {@link File} exists and array inside {@link File} is ordered, otherwise {@code false}
     */
    public static boolean isOrdered(File file) {
        int[][] array = read(file);
        
        // return if file is an array and array ordered
        return array != null && isOrdered(array);
    }
    
    /**
     * Check if 2-D array is ordered.
     *
     * @return {@code true} if array is ordered, otherwise {@code false}
     */
    private static boolean isOrdered(int[][] numbers) {
        int previous = Integer.MIN_VALUE;
        
        // iterate over width (columns)
        for (int width = 0; width < numbers.length; width++) {
            // iterate over height (rows)
            for (int h = 0; h < numbers[0].length; h++) {
                // calculate height iteration order based on width
                int height = (width % 2 != 0) ? h : (numbers[0].length - 1) - h;
                
                // return false if next number less than previous
                if (numbers[width][height] < previous)
                    return false;
                
                // update previous for next iteration
                previous = numbers[width][height];
            }
        }
        
        return true;
    }
    
    /// I/O FUNCTIONS ///
    
    /**
     * Read 2-D array from {@link File}.
     *
     * @param file {@link File} to read 2-D array.
     *
     * @return 2-D array or {@code null} if array can't be read
     */
    private static int[][] read(File file) {
        try {
            Scanner input = new Scanner(file);
            
            // pre-read in the number of rows/columns
            int height = 0; // rows
            int width = 0; // columns
            while (input.hasNextLine()) {
                String line = input.nextLine();
                
                // skip if line is blank
                if (line.isBlank()) continue;
                
                // calculate height and width
                height++;
                width = Math.max(width, line.split(" ").length);
            }
            
            // init array
            int[][] array = new int[height][width];
            
            input.close();
            
            // read in the data
            input = new Scanner(file);
            
            // read data
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++)
                    if (input.hasNextInt())
                        array[j][i] = input.nextInt();
            
            return array;
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("File not found: " + e);
        }
        
        return null;
    }
    
    /**
     * Print 2-D array in convenient format.
     *
     * @param array 2-D array to print
     */
    private static void print(int[][] array) {
        for (int height = 0; height < array[0].length; height++) {
            StringBuilder line = new StringBuilder();
            
            // read line contents
            for (int[] width : array)
                line.append(width[height]).append(" ");
            
            // print part of 2-D array
            System.out.println("  " + line.substring(0, line.length() - 1));
        }
    }
    
}
