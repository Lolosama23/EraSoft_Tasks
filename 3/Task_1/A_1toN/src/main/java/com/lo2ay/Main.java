package com.lo2ay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


            int n = scanner.nextInt();

            NumberPrinter printer = new NumberPrinter(n);
            printer.printNumbers();

    }

    static class NumberPrinter {
        private int limit;
        public NumberPrinter(int limit) {
            this.limit = limit;
        }

        public void printNumbers() {
            for (int i = 1; i <= this.limit; i++) {
                System.out.println(i);
            }
        }
    }
}