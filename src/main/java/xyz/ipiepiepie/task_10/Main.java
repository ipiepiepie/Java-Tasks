package xyz.ipiepiepie.task_10;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import xyz.ipiepiepie.task_10.gui.Form;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

/**
 * {@link Candy} task.
 * <p>
 * Task №14 from <a href="https://edu.vsu.ru/pluginfile.php/6688971/mod_folder/content/0/Task_10.pdf?forcedownload=0">this list</a>
 *
 * @author ipiepiepie
 */
public class Main {
    private final static int DEFAULT_BALANCE = 100;
    
    /**
     * Entry point.
     */
    public static void main(String[] args) {
        Options options = new Options();
        // register options
        options.addOption("d", "debug", false, "Run program in debug mode");
        options.addOption("g", "gui", false, "Run program in GUI mode");
        options.addOption("m", "money", true, "Specify money amount");
        
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
            
            // parse money balance
            int money = readBalance(cmd.getOptionValue("m"));
            
            System.out.println("Initial balance:");
            System.out.println("  " + money);
            
            if (cmd.hasOption("d")) {
                String path = "src/main/resources/task_10/debug_%d.txt";
                
                // iterate over tests
                for (int i = 0; i < 5; i++) {
                    System.out.printf("======== Testing debug_%d.txt ========%n", i);
                    List<Candy> list = read(new File(String.format(path, i)));
                    List<Candy> result;
                    
                    // skip list if it's empty
                    if (list == null || list.isEmpty()) continue;
                    
                    result = findBest(list, money);
                    
                    // print candies
                    System.out.println("Candies list:");
                    print(list);
                    // print bought candies
                    System.out.println("Buy result:");
                    print(result);
                    // print money left
                    System.out.println("Money left:");
                    System.out.println("  " + (money - result.stream().mapToInt(Candy::getCost).sum()));
                }
            } else if (cmd.hasOption("g")) {
                new Form();
            } else {
                String path = cmd.getArgs()[0];
                List<Candy> list = read(new File(path));
                List<Candy> result;
                
                // skip list if it's empty
                if (list == null || list.isEmpty()) {
                    System.err.println("Can't load list!");
                    return;
                }
                
                // find best result
                result = findBest(list, money);
                
                // print candies
                System.out.println("Candies list:");
                print(list);
                // print bought candies
                System.out.println("Buy result:");
                print(result);
                // print money left
                System.out.println("Money left:");
                System.out.println("  " + (money - result.stream().mapToInt(Candy::getCost).sum()));
            }
        } catch (ParseException e) {
            System.err.println("Unexpected exception:" + e.getMessage());
        }
    }
    
    /// PROCESS ///
    
    /**
     * Find best {@link Candy Candies} list.
     *
     * @param list list of {@link Candy Candies}.
     * @param money available money to buy {@link Candy Candies}.
     *
     * @return best shopping result
     *
     * @see Candy
     */
    public static List<Candy> findBest(List<Candy> list, int money) {
        List<Candy> candies = list.stream().sorted(Comparator.comparing(Candy::getCost).reversed()).toList();
        List<Candy> result = new ArrayList<>();
        
        // buy the best candies
        for (Candy candy : candies) {
            if (candy.getCost() <= money) {
                // move candy from store to result
                result.add(candy);
                
                // withdraw cost
                money -= candy.getCost();
            }
        }
        
        return result;
    }
    
    /// I/O FUNCTIONS ///
    
    // READ //
    
    /**
     * Read {@link Candy} {@link List} from {@link File}.
     *
     * @param file {@link File} to read {@link Candy} {@link List}.
     *
     * @return {@link List} of {@link Candy Candies}
     *
     * @see Candy
     */
    public static List<Candy> read(File file) {
        try {
            Scanner input = new Scanner(file);
            List<Candy> list = new ArrayList<>();
            
            // read data from file
            while (input.hasNextLine()) {
                String[] data = input.nextLine().split(" ");
                Candy candy = readCandy(data);
                
                // add candy in list if it loaded successfully
                if (candy != null) list.add(candy);
            }
            
            // close input stream
            input.close();
            
            return list;
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("File not found: " + e);
        }
        
        return null;
    }
    
    /**
     * Read {@link Candy} from given {@code data}.
     *
     * @param data {@link Candy} data
     * @return {@link Candy} if can be read, or {@code null}
     */
    public static Candy readCandy(String[] data) {
        // print error if length isn't 2
        if (data.length != 2) {
            System.err.println("Incorrect data array: " + Arrays.toString(data));
            return null;
        }
        
        // try to load candy data
        try {
            return new Candy(data[0], Integer.parseInt(data[1]));
        } catch (NumberFormatException e) {
            System.err.printf("Can't load candy '%s'%n", data[0]);
        }
        
        return null;
    }
    
    /**
     * Read {@code initial balance} from {@link String}.
     *
     * @param balance {@link String}, representing balance
     *
     * @return parsed {@code balance} or {@code 100} if balance can't be parsed.
     */
    public static int readBalance(String balance) {
        // return default balance if it's null
        if (balance == null) return DEFAULT_BALANCE;
        
        // try to parse balance
        try {
            return Integer.parseInt(balance);
        } catch (NumberFormatException ignore) {
            return DEFAULT_BALANCE;
        }
    }
    
    // PRINT //
    
    /**
     * Print {@link Candy Candies} {@link List} in convenient format.
     *
     * @param candies {@link Candy Candies} {@link List}
     */
    private static void print(List<Candy> candies) {
        // print candies list contents
        for (Candy candy : candies)
            System.out.printf("  - %s: %d%n", candy.getName(), candy.getCost());
    }
    
}
