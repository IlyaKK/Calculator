package com.elijahcorp.calculator;

import java.util.Stack;

public class Calculation {
    private String stringExpression;

    public Calculation(String stringExpression) {
        this.stringExpression = stringExpression;
    }

    public String getStringExpression() {
        return stringExpression;
    }

    public void setStringExpression(String stringExpression) {
        this.stringExpression = stringExpression;
    }

    public double calculate() {
        StringBuilder postfixStringExpression = getExpression(stringExpression);
        return counting(postfixStringExpression.toString());
    }

    private StringBuilder getExpression(String stringExpression) {
        StringBuilder postfixStringExpression = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();
        for (int i = 0; i < stringExpression.length(); i++) {
            if (isDelimiter(stringExpression.charAt(i))) {
                continue;
            }

            if (Character.isDigit(stringExpression.charAt(i)) || stringExpression.charAt(i) == '.') {
                while (!isDelimiter(stringExpression.charAt(i)) && !isOperator(stringExpression.charAt(i))) {
                    postfixStringExpression.append(stringExpression.charAt(i));
                    i++;
                    if (i == stringExpression.length()) break;
                }
                postfixStringExpression.append(" ");
                i--;
            }

            if (isOperator(stringExpression.charAt(i))) {
                if (!operatorStack.empty()) {
                    if (getPriority(stringExpression.charAt(i)) <= getPriority(operatorStack.peek())) {
                        postfixStringExpression.append(operatorStack.pop()).append(" ");
                    }
                }
                operatorStack.push(stringExpression.charAt(i));
            }
        }
        while (!operatorStack.isEmpty()) {
            postfixStringExpression.append(operatorStack.pop()).append(" ");
        }

        return postfixStringExpression;
    }

    private double counting(String input) {
        double result;
        Stack<Double> solveStack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.') {
                StringBuilder a = new StringBuilder();

                while (!isDelimiter(input.charAt(i)) && !isOperator(input.charAt(i))) {
                    a.append(input.charAt(i));
                    i++;
                    if (i == input.length()) break;
                }
                solveStack.push(Double.valueOf(a.toString()));
                i--;
            } else if (isOperator(input.charAt(i))) {
                double a = solveStack.pop();
                double b = solveStack.pop();
                result = operateCalculate(input.charAt(i), a, b);
                solveStack.push(result);
            }
        }
        return solveStack.peek();
    }

    private double operateCalculate(char operand, double a, double b) {
        switch (operand) {
            case '+':
                return b + a;
            case '-':
                return b - a;
            case '*':
                return b * a;
            case '/':
                return b / a;
            default:
                return 0;
        }
    }

    private boolean isDelimiter(char c) {
        return " ".contains(String.valueOf(c));
    }

    private boolean isOperator(char c) {
        return "+-/*".contains(String.valueOf(c));
    }

    private byte getPriority(char s) {
        switch (s) {
            case '+':
                return 0;
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 3;
        }
    }
}
