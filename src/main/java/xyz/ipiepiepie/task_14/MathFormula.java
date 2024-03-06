package xyz.ipiepiepie.task_14;

import java.util.*;

/**
 *
 * @see <a href="https://en.wikipedia.org/wiki/Shunting_yard_algorithm">Shunting Yard Algorithm</a>
 * @see <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">Reverse Polish Notation</a>
 */
public class MathFormula {
    private String expression;
    // variables //
    private final List<String> variables;
    
    
    public MathFormula(String expression) {
        this.expression = expression;
        this.variables = findVariables();
        
    }
    
    /// VARIABLES ///
    
    private List<String> findVariables() {
        List<String> variables = new ArrayList<>();
        
        // find variable tokens
        for (String token : tokenize(expression))
            if (isVariable(token) && !variables.contains(token)) variables.add(token);
        
        return variables;
    }
    
    /// EXECUTION ///
    
    public double execute(double... mappings) throws IllegalArgumentException {
        if (mappings.length != variables.size())
            throw new IllegalArgumentException("Mappings length doesn't equals variables length!");
        
        int index = 0;
        for (String variable : variables)
            expression = expression.replaceAll(variable, String.valueOf(mappings[index++]));
        
        return solve();
    }
    
    
    private double solve() throws IllegalArgumentException {
        Stack<String> data = parse();
        Stack<Double> calculations = new Stack<>();
        
        // reverse for loop
        Collections.reverse(data);
        
        // solve given expression
        while (!data.empty()) {
            String token = data.pop();
            
            // push numbers to calculations stack
            if (isNumber(token)) {
                calculations.push(Double.parseDouble(token));
            // calculate expression with given operand
            } else {
                double y = calculations.pop();
                double x = calculations.pop();
                
                calculations.push(solve(x, y, token));
            }
        }
        
        // if there is more than one element after calculations, then expression is incorrect.
        if (calculations.size() != 1)
            throw new IllegalArgumentException("Can't solve given expression!");
        
        return calculations.pop();
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
    
    /**
     * Parse expression to
     *
     * @return
     */
    public Stack<String> parse() {
        Stack<String> operations = new Stack<>();
        Stack<String> result = new Stack<>();
        
        // split expression to tokens
        String[] tokens = tokenize(expression);
        
        // loop for handling each token - shunting-yard algorithm
        for (String token : tokens) {
            // handle brackets open
            if (isOpenBracket(token)) {
                operations.push(token);
            // handle brackets close
            } else if (isCloseBracket(token)) {
                // move operations to result stack until we reach opening bracket
                while (!operations.empty() && !isOpenBracket(operations.lastElement()))
                    result.push(operations.pop());
                
                // remove opening bracket from operations stack
                operations.pop();
            // handle numbers
            } else if (isNumber(token)) {
                result.push(token);
            // handle operators
            } else if (isOperator(token)) {
                // push more precedence operators to result stack
                while (!operations.empty() && isOperator(operations.lastElement()) && getPrecedence(token) <= getPrecedence(operations.lastElement()))
                    result.push(operations.pop());
                
                // push current operation to operations stack
                operations.push(token);
            // handle variables
            } else {
                result.push(token);
            }
        }
        
        // push left operations to result stack
        while (!operations.empty())
            result.push(operations.pop());
        
        return result;
    }
    
    /**
     * Format and split expression to tokens.
     *
     * @param expression given expression.
     * @return array of tokens.
     */
    private String[] tokenize(String expression) {
        // format expression
        expression = expression
                .replace(" ", "") // remove spaces
                .replace("(-", "(0-"); // handle negative numbers at start of brackets
        
        // handle negative numbers at start of expression.
        if (expression.charAt(0) == '-')
            expression = "0" + expression;
        
        // split expression to tokens
        return expression.split("(?<=[-+*/()])|(?=[-+*/()])");
    }
    
    /// CHECKS ///
    
    /**
     * Check if {@link String} token is {@link Double}.
     *
     * @param token token to check
     * @return {@code true} if token is {@link Double}, otherwise {@code false}
     */
    private boolean isNumber(String token) {
        try { // fixme it's better using regex here
            Double.parseDouble(token);
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Check if {@link String} is opening bracket ['('].
     *
     * @param token token to check.
     * @return {@code true} if token is opening bracket ['('], otherwise {@code false}.
     */
    private boolean isOpenBracket(String token) {
        return token.equals("(");
    }
    
    /**
     * Check if {@link String} is closing bracket [')'].
     *
     * @param token token to check.
     * @return {@code true} if token is closing bracket [')'], otherwise {@code false}.
     */
    private boolean isCloseBracket(String token) {
        return token.equals(")");
    }
    
    /**
     * Check if {@link String} is mathematical operator (-, +, *, /).
     *
     * @param token token to check.
     * @return {@code true} if token is mathematical operator, otherwise {@code false}.
     */
    private boolean isOperator(String token) {
        return "-+*/".contains(token);
    }
    
    /**
     * Check if {@link String} is some variable.
     *
     * @param token token to check.
     * @return {@code true} if token is variable, otherwise {@code false}.
     */
    private boolean isVariable(String token) {
        return !isOpenBracket(token) && !isCloseBracket(token) && !isNumber(token) && !isOperator(token);
    }
    
    /**
     * Get precedence of mathematical operator.
     *
     * @param token mathematical operator.
     *
     * @return {@code 1} if token is plus or minus operator, otherwise {@code 2}.
     */
    private byte getPrecedence(String token) {
        if (token.equals("+") || token.equals("-"))
            return 1;
        
        return 2;
    }
    
}
