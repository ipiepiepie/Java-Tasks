package xyz.ipiepiepie.task_12;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import xyz.ipiepiepie.task_12.gui.Menu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Recursive tree builder task.
 * <p>
 * Task №12 from <a href="https://edu.vsu.ru/pluginfile.php/6688971/mod_folder/content/0/Task_12.doc?forcedownload=1">this list</a>
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
                String path = "src/main/resources/task_12/debug_%d.txt";

                // iterate over tests
                for (int i = 0; i < 5; i++) {
                    System.out.printf("======== Testing debug_%d.txt ========%n", i);
                    String tree = read(Path.of(String.format(path, i)));

                    // skip text if it's empty
                    if (tree == null || tree.isEmpty()) continue;

                    // print given tree
                    System.out.println("Input tree:");
                    System.out.println(tree);
                    // print rendered tree
                    System.out.println("Draw result:");
                    System.out.println();
                    for (String line : draw(read(tree), false))
                        System.out.println(line);
                    System.out.println(); // insert new line for future tests
                }
            } else if (cmd.hasOption("g")) {
                new Menu();
            } else {
                String path = cmd.getArgs()[0];
                String tree = read(Path.of(path));

                // skip tree if it's empty
                if (tree == null || tree.isEmpty()) {
                    System.err.println("Can't read tree from selected file");
                    return;
                }

                // print given tree
                System.out.println("Input tree:");
                System.out.println(tree);
                // print rendered tree
                System.out.println("Draw result:");
                for (String line : draw(read(tree), false))
                    System.out.println(line);
            }
        } catch (ParseException e) {
            System.err.println("Unexpected exception:" + e.getMessage());
        }
    }

    /// DRAW FUNCTIONS ///

    public static List<String> draw(TreeNode node, boolean hasParent) {
        List<String> lines = new ArrayList<>();

        // add branch if it's a child
        if (hasParent) lines.add(shift("|", node.size()));

        // add current node with shift
        lines.add(shift(node.getNode(), node.size()));

        // add child branches if current node has any child
        if (!node.getChildren().isEmpty()) {
            lines.add(shift("|", node.size()));
            lines.add(divider(node));

            // children nodes
            List<String> children = new ArrayList<>();

            List<TreeNode> previous = new ArrayList<>();
            // horizontally write children nodes to array
            for (TreeNode child : node.getChildren()) {
                horizontalAdd(children, child, previous);
                previous.add(child);
            }

            // add children nodes
            lines.addAll(children);
        }

        return lines;
    }

    private static void horizontalAdd(List<String> target, TreeNode child, List<TreeNode> previous) {
        List<String> additional = draw(child, true);

        for (int i = 0; i < 13; i++) {
            StringBuilder line = new StringBuilder(i < additional.size() ? additional.get(i) : "");

            // horizontally add line if its existing
            if (i < additional.size()) {
                // handle new nodes on existing line (e.g. second node in row)
                if (i < target.size()) {
                    target.set(i, target.get(i) + " " + line);
                // handle new level nodes (e.g. node in center)
                } else {
                    // add empty lines to fit node
                    for (TreeNode node : previous)
                        line.insert(0, " ".repeat(node.size() + 1));

                    target.add(line.toString());
                }
            // add empty space if node ended, but tree still go on
            } else if (i < target.size()) {
                target.set(i, target.get(i) + " ".repeat(child.size() + 1));
            }
        }
    }

    /**
     * Center provided word in selected size.
     *
     * @param word word to center
     * @param size size of block for centering
     * @return word centered with spaces
     */
    private static String shift(String word, int size) {
        String leftSpacer = " ".repeat((size - word.length() + 1) / 2);
        String rightSpacer = " ".repeat((size - word.length()) / 2);

        return leftSpacer + word + rightSpacer;
    }

    /**
     * Draw divider line for {@link TreeNode node}.
     *
     * @param node @link TreeNode node} to draw divider for.
     *
     * @return divider line
     */
    private static String divider(TreeNode node) {
        // return empty divider if node doesn't have any child
        if (node.getChildren().isEmpty()) return "";
        // children //
        TreeNode first = node.getChildren().get(0);
        TreeNode last = node.getChildren().get(node.getChildren().size() - 1);
        // check if node size is even
        boolean even = first.size() % 2 == 0 || last.size() % 2 == 0;

        // calculate spacers based on first and last children sizes
        String leftSpacer = " ".repeat(first.size() / 2);
        String rightSpacer = " ".repeat((last.size() / 2) - (even ? 1 : 0));

        int size;
        // change node size if it has more than one child
        if (first != last) {
            // set size to node size
            size = node.size();

            // remove half of first and last node sizes
            size -= first.size() / 2;
            size -= last.size() / 2;

            // adjust divider size if node is even
            if (even) size++;
        } else {
            size = 1;
        }

        return leftSpacer + "-".repeat(size) + rightSpacer;
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

    /**
     * Read tree from {@link String}.
     *
     * @param tree tree as {@link String}
     *
     * @return {@link List} of {@link TreeNode Tree Nodes}
     */
    public static TreeNode read(String tree) {
        TreeNode parent = null;

        // it's single node, return node
        if (!tree.contains("(")) return new TreeNode(tree);

        // cut off braces
        tree = tree.substring(1, tree.length() - 1);

        // iterate over tree nodes
        for (String contents : optimise(tree.split(", "))) {
            TreeNode node = read(contents); // load node

            // set parent node if its first node
            if (parent == null)
                parent = node;
            // otherwise add it as children
            else
                parent.addChildren(node);
        }

        return parent;
    }

    /**
     * Optimise nodes array.
     * <p>
     * Concatenates nodes under braces.
     *
     * @param nodes nodes array
     * @return optimised array.
     */
    private static List<String> optimise(String[] nodes) {
        List<String> result = new ArrayList<>();

        int level = 0;
        StringBuilder finalNode = new StringBuilder();
        // iterate over nodes
        for (String node : nodes) {
            // check braces if we are dealing with them
            if (level != 0 || node.contains("(") || node.contains(")")) {
                // go level up if we have opening brace
                if (node.contains("(")) level += count(node, '(');
                // go level down if we have opening brace
                if (node.contains(")")) level -= count(node, ')');

                // append current node to final
                finalNode.append(node);

                // add final node if all braces closed
                if (level == 0) {
                    // add concatenated node
                    result.add(finalNode.toString());
                    // reset final node
                    finalNode = new StringBuilder();
                } else {
                    finalNode.append(", ");
                }
            // otherwise add single node
            } else {
                result.add(node);
            }
        }

        return result;
    }

    /// UTIL ///

    /**
     * Count the number of occurrences of a char in a {@link String}.
     *
     * @param str {@link String} to find char.
     * @param symbol symbol to find
     *
     * @return number of occurrences of a char in a {@link String}
     */
    private static int count(String str, char symbol) {
        return (int) str.chars().filter(ch -> ch == symbol).count();
    }

}
