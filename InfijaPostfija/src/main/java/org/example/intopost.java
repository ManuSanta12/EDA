package org.example;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class intopost {

    private final Map<String, Double> variables;

    public intopost(Map<String, Double> variables) {
        this.variables = variables;
    }

    public intopost() {
        variables = null;
    }

    private static final Map<String, Integer> mapping = new HashMap<>() {
        {
            put("+", 0);
            put("-",0 );
            put("*", 2);
            put("/", 2);
            put("^", 3);
            put("(", 5);
            put(")", 6);
        }
    };

    private static final boolean[][] precedenceMatriz =
            {
                    {true, true, false, false, false, false, true},
                    {true, true, false, false, false, false, true},
                    {true, true, true, true, false, false, true},
                    {true, true, true, true, false, false, true},
                    {true, true, true, true, false, false, true},
                    {false, false, false, false, false, false, false}
            };

    /*
     * ---------------------------------------------------------
     * Completar el pasaje de infija a posfija de manera grafica
     * ---------------------------------------------------------
     * los números van directo a la "salida"
     * izquierda: tope de la pila
     * derecha: elemento siendo analizado (current)
     * false ==> se pushea current
     * true ==> se popea la pila y se ubica en la salida, se
     * vuelve a analizar el current con el tope de la pila.
     */

    private static final Map<String, Boolean> precendeceMap = new HashMap<>() {
        {
            put("+_+", true);
            put("+_-", true);
            put("+_*", false);
            put("+_/", false);
            put("+_^", false);
            put("+_(", false);
            put("+_)", true);
            put("-_+", true);
            put("-_-", true);
            put("-_*", false);
            put("-_/", false);
            put("-_^", false);
            put("-_(", false);
            put("-_)", true);
            put("*_+", true);
            put("*_-", true);
            put("*_*", true);
            put("*_/", true);
            put("*_^", false);
            put("*_(", false);
            put("*_)", true);
            put("/_+", true);
            put("/_-", true);
            put("/_*", true);
            put("/_/", true);
            put("/_^", false);
            put("/_(", false);
            put("/_)", true);
            put("^_+", true);
            put("^_-", true);
            put("^_*", true);
            put("^_/", true);
            put("^_^", false);
            put("^_(", false);
            put("^_)", true);
            put("(_+", false);
            put("(_-", false);
            put("(_*", false);
            put("(_/", false);
            put("(_^", false);
            put("(_(", false);
            put("(_)", false);
        }
    };

    private static boolean getPrecedence(String tope, String current) {
        Integer topeIndex, currentIndex;
        if ((topeIndex = mapping.get(tope)) == null)
            throw new RuntimeException(String.format("tope operator %s not found", tope));
        if ((currentIndex = mapping.get(current)) == null)
            throw new RuntimeException(String.format("current operator %s not found", current));
        return precedenceMatriz[topeIndex][currentIndex];
    }

    private String parse() {
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresión en notación infija: ");
        String line = inputScanner.nextLine(); // si usan nextLine() no poner \\r
        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");

        if (!lineScanner.hasNext())
            throw new RuntimeException("Nothing to evaluate\n");

        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        while (lineScanner.hasNext()) {
            String token = lineScanner.next();
            if (Evaluator.isNumber(token))
                sb.append(token).append(" ");
            else if (variables != null && variables.containsKey(token))
                sb.append(variables.get(token).toString()).append(" ");
            else {
                while (!stack.isEmpty() && getPrecedence(stack.peek(), token))
                    sb.append(stack.pop()).append(" ");
                if (token.matches("\\)")) {
                    if (stack.isEmpty() || stack.peek().compareTo("(") != 0) // si está vacío o no hay un ( en el tope
                        throw new IllegalStateException("Didn't open the brackets");
                    stack.pop();
                } else
                    stack.push(token);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek().compareTo("(") == 0)
                throw new IllegalStateException("Didn't close all open brackets");
            sb.append(stack.pop()).append(" ");
        }
        return sb.toString();
    }

    public Double evaluate() {
        String posfija = parse();
        System.out.println(posfija);
        return new Evaluator().evaluate(new ByteArrayInputStream(posfija.getBytes(StandardCharsets.UTF_8)));
    }

    public static void main(String[] args) {
        Map<String, Double> vars = new HashMap<>();
        vars.put("uno", 1.0);
        vars.put("dos", 2.0);
        vars.put("tres", 3.0);
        vars.put("cuatro", 4.0);
        vars.put("cinco", 5.0);
        intopost e = new intopost(vars);
        System.out.println(e.evaluate());
    }
}
