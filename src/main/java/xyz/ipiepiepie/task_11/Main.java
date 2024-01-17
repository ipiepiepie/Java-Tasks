package xyz.ipiepiepie.task_11;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import xyz.ipiepiepie.task_11.gui.Menu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * Finds {@link Date Dates} in text
 * <p>
 * Task №6 from <a href="https://edu.vsu.ru/pluginfile.php/6688971/mod_folder/content/0/Task_11.doc?forcedownload=1">this list</a>
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
                String path = "src/main/resources/task_11/debug_%d.txt";

                // iterate over tests
                for (int i = 0; i < 5; i++) {
                    System.out.printf("======== Testing debug_%d.txt ========%n", i);
                    String text = read(Path.of(String.format(path, i)));

                    // skip text if it's empty
                    if (text == null || text.isEmpty()) continue;

                    // print given text
                    System.out.println("Current text:");
                    System.out.println(text);
                    // print fetched Dates
                    System.out.println("Fetched dates:");
                    System.out.println(findDates(text));
                }
            } else if (cmd.hasOption("g")) {
                new Menu();
            } else {
                String path = cmd.getArgs()[0];
                String text = read(Path.of(path));

                // skip text if it's empty
                if (text == null || text.isEmpty()) {
                    System.err.println("Can't read text from selected file");
                    return;
                }

                // print given text
                System.out.println("Current text:");
                System.out.println(text);
                // print fetched Dates
                System.out.println("Fetched dates:");
                System.out.println(findDates(text));
            }
        } catch (ParseException e) {
            System.err.println("Unexpected exception:" + e.getMessage());
        }
    }

    /// DATE PARSERS ///

    /**
     * Fetch {@link Date Dates} from text.
     *
     * @param text processing text
     * @return {@link List} of {@link Date Dates}
     */
    public static List<String> findDates(String text) {
        List<String> dates = new ArrayList<>();

        // skip null or empty text
        if (text == null || text.isBlank()) return dates;

        // parse DD.MM.YYYY
        dates.addAll(findDates(text, DatePattern.getDateMonthNumberYearPattern()));
        // parse DD Month YYYY
        dates.addAll(findDates(text, DatePattern.getDateMonthNameYearPattern()));
        // parse DD Month
        dates.addAll(findDates(text, DatePattern.getDateMonthNamePattern()));

        return dates;
    }

    /**
     * Find {@link Date Dates}, matching selected {@link Pattern}.
     *
     * @param text text to look for {@link Date Dates}.
     * @param pattern {@link Date} {@link Pattern}.
     *
     * @return {@link List} of {@link Date Dates} from text
     */
    private static List<String> findDates(String text, Pattern pattern) {
        return pattern.matcher(text.toLowerCase())
                .results()
                .map(MatchResult::group)
                .map(result -> result.replaceAll("\\n", ""))
                .toList();
    }

    /// I/O FUNCTIONS ///

    /**
     * Read {@code text} from {@link File}.
     *
     * @param path {@link Path} to {@code text}.
     *
     * @return {@code text} if exists, otherwise {@code null}
     */
    public static String read(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            System.out.println("File not found: " + e);
        }

        return null;
    }

}
