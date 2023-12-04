package xyz.ipiepiepie.lists;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import xyz.ipiepiepie.lists.gui.Menu;

import java.util.List;

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
                String path = "src/main/resources/lists/debug_%d.txt";
                
                // iterate over tests
                for (int i = 0; i < 6; i++) {
                    System.out.printf("======== Testing debug_%d.txt ========%n", i);
                    
                }
            } else if (cmd.hasOption("g")) {
                new Menu();
            } else {
            
            }
        } catch (ParseException e) {
            System.err.println("Unexpected exception:" + e.getMessage());
        }
    }
    
    private static void process(List<Integer> list) {
        
    
    }
    
}
