package com.lo2ay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String equation = input.next();

        int opIndex = -1;
        char op = ' ';

        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                opIndex = i;
                op = c;
                break;
            }
        }

        int num1 = Integer.parseInt(equation.substring(0, opIndex));
        int num2 = Integer.parseInt(equation.substring(opIndex + 1));

        Calc calc = new Calc(num1, op, num2);
        System.out.println(calc.compute());

        input.close();
    }

    static class Calc {
        private int num1;
        private int num2;
        private char operator;

        public Calc(int num1, char op, int num2) {
            this.num1 = num1;
            this.operator = op;
            this.num2 = num2;
        }

        public int compute() {
            switch (operator) {
                case '+':
                    return num1 + num2;
                case '-':
                    return num1 - num2;
                case '*':
                    return num1 * num2;
                case '/':
                    return num1 / num2;
                default:
                    return 0;
            }
        }
    }
}