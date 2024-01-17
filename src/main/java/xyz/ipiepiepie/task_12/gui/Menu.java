package xyz.ipiepiepie.task_12.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import xyz.ipiepiepie.task_12.Main;
import xyz.ipiepiepie.task_12.TreeNode;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.List;

public class Menu extends JFrame {
    private JPanel panel;
    // input //
    private JTextField input;
    // files //
    private JPanel files;
    private final JFileChooser fileChooser;
    private JButton attach;
    // submit //
    private JButton submit;
    // output //
    private JScrollPane result;
    private JTextPane output;

    /**
     * Menu constructor.
     */
    public Menu() {
        createListeners();

        this.setTitle("Task 12");
        this.setContentPane(panel);
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // set up file chooser
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text file", "txt", "md"));

        // configure result
        output.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    }

    /**
     * Create button listeners
     */
    private void createListeners() {
        // attach file button
        attach.addActionListener(action -> {
            int status = fileChooser.showOpenDialog(panel);
            File file = fileChooser.getSelectedFile();

            // skip if file not read
            if (status != JFileChooser.APPROVE_OPTION || file == null) return;

            // set file text to input text box
            input.setText(Main.read(file.toPath()));
        });

        // calculate button
        submit.addActionListener(action -> {
            StringBuilder result = new StringBuilder();
            TreeNode root = Main.read(input.getText());

            // read drawn tree to result
            for (String line : Main.draw(root, false))
                result.append(line).append("\n");

            // print result to output
            output.setText(result.toString());
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(7, 1, new Insets(10, 10, 10, 10), -1, -1));
        input = new JTextField();
        panel.add(input, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Дерево");
        panel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        files = new JPanel();
        files.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(files, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        attach = new JButton();
        attach.setText("Загрузить из файла");
        files.add(attach, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        files.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        result = new JScrollPane();
        panel.add(result, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        output = new JTextPane();
        output.setEditable(false);
        result.setViewportView(output);
        final JLabel label2 = new JLabel();
        label2.setText("Результат");
        panel.add(label2, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        submit = new JButton();
        submit.setText("Построить дерево");
        panel.add(submit, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel.add(separator1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
