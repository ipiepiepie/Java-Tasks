package xyz.ipiepiepie.task_14;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import xyz.ipiepiepie.task_14.gui.Menu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

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
                String path = "src/main/resources/task_14/debug_%d.txt";
                
                // iterate over tests
                for (int i = 0; i < 5; i++) {
                    System.out.printf("======== Testing debug_%d.txt ========%n", i);
                    Path currentPath = Path.of(String.format(path, i));
                    String expression = readExpression(currentPath);
                    double[] values = readValues(currentPath);
                    
                    // skip expression if it's empty
                    if (expression == null || expression.isEmpty()) continue;
                    
                    // print given expression
                    System.out.println("Input expression:");
                    System.out.println(expression);
                    // print given values
                    System.out.println("Input variables:");
                    System.out.println(Arrays.toString(values));
                    // print rendered tree
                    System.out.println("Calculation result:");
                    System.out.println(new MathFormula(expression).execute(values));
                    System.out.println(); // insert new line for future tests
                }
            } else if (cmd.hasOption("g")) {
                new Menu();
            } else {
                Path path = Path.of(cmd.getArgs()[0]);
                String expression = readExpression(path);
                double[] values = readValues(path);
                
                // skip expression if it's empty
                if (expression == null || expression.isEmpty()) {
                    System.err.println("Incorrect expression!");
                    return;
                }
                
                // print given expression
                System.out.println("Input expression:");
                System.out.println(expression);
                // print given values
                System.out.println("Input variables:");
                System.out.println(Arrays.toString(values));
                // print rendered tree
                System.out.println("Calculation result:");
                System.out.println(new MathFormula(expression).execute(values));
            }
        } catch (ParseException e) {
            System.err.println("Unexpected exception:" + e.getMessage());
        }
    }
    
    /// I/O METHODS ///
    
    /**
     * Read {@code expression} from {@link File}.
     *
     * @param path {@link Path} to {@code expression}.
     *
     * @return {@code expression} if exists, otherwise {@code null}
     */
    public static String readExpression(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            return lines.findFirst().orElse(null);
        } catch (IOException | SecurityException e) {
            System.out.println("File not found: " + e);
        }
        
        return null;
    }
    
    /**
     * Read {@code values} from {@link File}.
     *
     * @param path {@link Path} to {@code values}.
     *
     * @return {@code values} if exists, otherwise {@code null}
     */
    public static double[] readValues(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            String unformattedValues = lines.toList().get(1);
            
            return Arrays.stream(unformattedValues.split(", ")).mapToDouble(Double::valueOf).toArray();
        } catch (IOException | SecurityException e) {
            System.out.println("File not found: " + e);
        }
        
        return new double[0];
    }
    
}
