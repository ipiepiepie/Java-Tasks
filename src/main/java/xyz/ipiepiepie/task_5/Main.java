package xyz.ipiepiepie.task_5;

import java.util.Scanner;

/**
 * Draws triangle of a certain height.
 * <p>
 * Task №8 from <a href="https://edu.vsu.ru/pluginfile.php/6688971/mod_folder/content/0/Task_05.doc?forcedownload=1">this list</a>
 */
public class Main {
    
    /**
     * Entry point
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // get number
        System.out.print("Specify triangle height\n> ");
        int height = input.nextInt();
        
        // call draw method
        draw(height);
    }
    
    /**
     * Draw centered triangle to console.
     * <p>
     * Look of this triangle determined by task.
     *
     * @param rows count of rows in triangle <i>(height)</i>
     */
    private static void draw(int rows) {
        int radius = rows / 2;
        
        // draw triangle lines one by one.
        for (int i = rows; i > 0; i--) {
            int step = Math.min(rows - i, i - 1);  // max number in current line
            int length = 2 * i - 1;  // length of current triangle line
            
            // add spaces to make triangle centered
            for (int space = 1; space <= rows - i; ++space)
                System.out.print(" ");
            
            StringBuilder line = new StringBuilder();  // final result line
            StringBuilder pattern = new StringBuilder();  // current line pattern
            // create line pattern (e.x. for step '2' it'll be '012')
            for (int x = 0; x <= step; x++)
                pattern.append(x);
            
            // append pattern to line
            line.append(pattern);
            
            // fill remaining space with current step number
            line.append(String.valueOf(step).repeat(Math.max(length - (line.length() * 2), 0)));
            
            // if we reached triangle center, remove last symbol (otherwise it'll be duplicated on reversed pattern appending)
            if (rows - i >= radius)
                pattern.deleteCharAt(pattern.length() - 1);
            
            // append reversed pattern
            line.append(pattern.reverse());
            
            // print line
            System.out.println(line);
        }
    }
    
}
