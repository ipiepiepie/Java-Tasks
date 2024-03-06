package xyz.ipiepiepie.task_14;

import java.util.*;

/**
 *
 */
public class Formula {
    private String formula;
    // variables //
    private final List<String> variables = new ArrayList<>();
    private final Stack<String> chunks = new Stack<>();


    public void prepare(String expression) {
        System.out.println(Arrays.toString(expression.split("")));
        formula = expression;

        findVariables(expression);

        System.out.println(variables);

        //System.out.println(solve(expression, "*"));
        //System.out.println(solve(expression, "+"));
    }

    private void findVariables(String expression) {
        StringBuilder chunk = new StringBuilder();

        expression += " "; // add trailing operand

        for (String symbol : expression.split("")) {
            if (isVariable(symbol)) {
                chunk.append(symbol);
                continue;
            }

            if (!chunk.isEmpty() && !variables.contains(chunk.toString()))
                variables.add(chunk.toString());

            chunk = new StringBuilder();
        }
    }

    public double execute(double... mappings) {
        if (mappings.length != variables.size())
            return 0;

        int index = 0;
        for (String variable : variables)
            formula = formula.replaceAll(variable, String.valueOf(mappings[index++]));

        solve(formula, "*");
        solve(formula, "/");
        solve(formula, "+");
        solve(formula, "-");

        return Double.parseDouble(formula);
    }

    private List<String> solve(String expression, String operand) {
        List<String> chunks = new ArrayList<>();

        expression += "+"; // add trailing operand

        // chunk values
        StringBuilder left = new StringBuilder();
        String currentOperand = null;
        StringBuilder right = new StringBuilder();
        // iterate over symbols in given expression string
        for (String symbol : expression.split("")) {
            // if symbol is value or variable, then add it to left or right part of chunk
            if (isValue(symbol) || isVariable(symbol)) {
                (currentOperand == null ? left : right).append(symbol);
                continue;
            }

            // if there is no operand, add current symbol as operand
            if (currentOperand == null) {
                currentOperand = symbol;
                continue;
            }

            // save chunk if it has searching operand
            if (currentOperand.equals(operand)) {
                double solved = solve(Double.parseDouble(left.toString()), Double.parseDouble(right.toString()), currentOperand);

                formula = formula.replace(left + currentOperand + right, String.valueOf(solved));

                left = new StringBuilder(String.valueOf(solved));
                //chunks.add(left + currentOperand + right);
            } else {
                left = right;
            }

            // move cursor to the next chunk
            currentOperand = symbol;
            right = new StringBuilder();
        }

        return chunks;
    }

    private double solve(double x, double y, String operand) {
        return switch (operand) {
            case "+" -> x + y;
            case "-" -> x - y;
            case "*" -> x * y;
            case "/" -> {
                if (y == 0)
                    throw new IllegalArgumentException("Can't divide by 0");
                yield x / y;
            }
            default -> throw new IllegalArgumentException("Unknown operator %s".formatted(operand));
        };
    }

    private boolean isValue(String symbol) {
        return symbol.matches("[0-9.]");
    }

    private boolean isVariable(String symbol) {
        return !isValue(symbol) && !isOperand(symbol);
    }

    private boolean isOperand(String symbol) {
        return symbol.matches("[*/+-]");
    }
}
