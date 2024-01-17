package xyz.ipiepiepie.task_12;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private final String node;
    // children //
    private final List<TreeNode> children = new ArrayList<>();

    /**
     * Single node constructor.
     *
     * @param node node contents
     */
    public TreeNode(String node) {
        this.node = node;
    }

    /// SIZE ///

    /**
     * Returns whole {@link #size(boolean)} of current Node.
     *
     * @return whole size of Node
     */
    public int size() {
        return size(true);
    }

    /**
     * Get size of current Node with its children.
     *
     * @param recursive should size be calculated recursively
     *
     * @return size of node
     */
    public int size(boolean recursive) {
        int size = 0;

        // return node size if there is no children
        if (children.isEmpty()) return node.length();

        // calculate children size
        for (TreeNode node : children)
            size += recursive ? node.size() : node.getNode().length();

        // count in spacers
        size += children.size() - 1;

        return Math.max(size, node.length());
    }

    public int optimisedSize() {
        int size = size();


        // return node size if there is no children
        if (children.isEmpty()) return node.length();

        // calculate children size
        for (int i = 0; i < children.size(); i++) {
            TreeNode node = children.get(i);
            // make additional size of current node
            int additional = node.size();

            // center first and last nodes
            if (i == 0 || i == children.size() - 1)
                additional = (additional + 1) / 2;
                //additional = (additional - 1) / 3; // math ceil for int

            // add current node additional size to optimal size
            size += additional;
        }

        // count in spacers
        size += children.size() - 1;

        return size;
    }

    /// GETTERS ///

    /**
     * Get current Node contents.
     *
     * @return contents
     */
    public String getNode() {
        return node;
    }

    /**
     * Add Node as children
     *
     * @param node children Node
     */
    public void addChildren(TreeNode node) {
        if (node == this) return;

        children.add(node);
    }

    /**
     * Get children Nodes.
     *
     * @return {@link List} of children Nodes
     */
    public List<TreeNode> getChildren() {
        return children;
    }

}
