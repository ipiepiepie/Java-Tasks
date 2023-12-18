package xyz.ipiepiepie.task_9;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import xyz.ipiepiepie.task_9.gui.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Wrap list task.
 * <p>
 * Task №14 from <a href="https://edu.vsu.ru/pluginfile.php/6688971/mod_folder/content/0/Task_09.pdf?forcedownload=0">this list</a>
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
     * Parses jar args.
     *
     * @param options registered arguments.
     * @param args received arguments.
     */
    private static void parseArgs(Options options, String[] args) {
        try {
            CommandLine cmd = new DefaultParser().parse(options, args);
            
            if (cmd.hasOption("d")) {
                String path = "src/main/resources/task_9/debug_%d.txt";
                
                // iterate over tests
                for (int i = 0; i < 6; i++) {
                    System.out.printf("======== Testing debug_%d.txt ========%n", i);
                    CustomList list = read(new File(String.format(path, i)));
                    
                    // skip list if it's empty
                    if (list == null) continue;
                    
                    // print initial list
                    System.out.println("Input list:");
                    System.out.println("  " + list);
                    // print processed list
                    System.out.println("Sorting result:");
                    System.out.println("  " + process(list));
                }
            } else if (cmd.hasOption("g")) {
                new Menu();
            } else {
                String path = cmd.getArgs()[0];
                CustomList list = read(new File(path));
                
                // skip list if it's empty
                if (list == null) {
                    System.err.println("Can't load list!");
                    return;
                }
                
                // print initial list
                System.out.println("Input list:");
                System.out.println("  " + list);
                // print processed list
                System.out.println("Sorting result:");
                System.out.println("  " + process(list));
            }
        } catch (ParseException e) {
            System.err.println("Unexpected exception:" + e.getMessage());
        }
    }
    
    /// PROCESS ///
    
    /**
     * Sort list with given in task rules.
     *
     * @param list list to sort
     * @return sorted list.
     */
    public static CustomList process(CustomList list) {
        int maxIndex = list.firstIndexOf(list.max());
        int minIndex = list.lastIndexOf(list.min());
        // calculate gap between indexes
        int gap = minIndex - maxIndex;
        
        // gap between indexes is negative, we can't do anything with this array
        if (gap <= 0) return list;
        
        // swap numbers from left to right
        for (int i = 1; i <= gap / 2; i++) {
            int first = list.get(maxIndex + i);
            int second = list.get(minIndex - i);
            
            // replace selected in current cycle numbers
            list.set(maxIndex + i, second);
            list.set(minIndex - i, first);
        }
        
        return list;
    }
    
    /// I/O FUNCTIONS ///
    
    /**
     * Read {@link CustomList List} from {@link File}.
     *
     * @param file {@link File} to read {@link CustomList List}.
     *
     * @return {@link CustomList List} or {@code null} if {@link CustomList List} can't be read
     */
    public static CustomList read(File file) {
        try {
            Scanner input = new Scanner(file);
            List<Integer> list = new ArrayList<>();
            
            // read data
            while (input.hasNextInt())
                list.add(input.nextInt());
            
            // close input stream
            input.close();
            
            return new CustomList(list);
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("File not found: " + e);
        }
        
        return null;
    }
    
}
