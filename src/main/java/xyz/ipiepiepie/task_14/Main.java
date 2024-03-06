package xyz.ipiepiepie.task_14;

public class Main {

    public static void main(String... args) {
        Formula formula = new Formula();
        formula.prepare("3*x+y*3");
        formula.execute(1, 2);

        formula.prepare("4*x*5+y*8");
        formula.execute(1, 2);
    }
}
