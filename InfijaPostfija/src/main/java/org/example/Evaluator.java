package org.example;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Stack;

public class Evaluator {

    public Double evaluate() {
        System.out.print("Introduzca la expresión en notación postfija: ");
        return evaluate(System.in);
    }

    public Double evaluate(InputStream in) {
        Scanner inputScanner = new Scanner(in).useDelimiter("\\n");
        String line = inputScanner.nextLine(); // si usan nextLine() no poner \\r
        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");

        if (!lineScanner.hasNext())
            throw new RuntimeException("Nothing to evaluate\n");

        Stack<Double> stack = new Stack<>();

        while (lineScanner.hasNext()) {
            String token = lineScanner.next();
            if (isNumber(token))
                stack.push(Double.parseDouble(token));
            else {
                if (!isOperator(token))
                    throw new IllegalArgumentException("Expresión con caracteres no aceptados");
                if (stack.size() < 2)
                    throw new IllegalArgumentException("Expresión mal formada");
                double n2 = stack.pop(), n1 = stack.pop();
                stack.push(evaluateOperator(n1, n2, token));
            }
        }
        if (stack.size() != 1)
            throw new IllegalArgumentException("La expresión esta mal formada");
        return stack.pop();
    }

    private static double evaluateOperator(double n1, double n2, String operator) {
        double ans = 0;
        switch (operator) {
            case "+" -> ans = n1 + n2;
            case "-" -> ans = n1 - n2;
            case "/" -> {
                if (n2 == 0)
                    throw new IllegalStateException("Can't divide by zero");
                ans = n1 / n2;
            }
            case "*" -> ans = n1 * n2;
            case "^" -> ans = Math.pow(n1, n2);
        }
        return ans;
    }

    public static boolean isOperator(String token) {
        return token.matches("[+-/*^]");
    }

    public static boolean isNumber(String token) {
        return token.matches("-?[0-9]+(.[0-9]+)?");
    }

    public static void main(String[] args) {
        Double rta = new Evaluator().evaluate();
        System.out.println(rta);
    }
}
