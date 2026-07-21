package com.lo2ay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

            int n = scanner.nextInt();
            TrianglePrinter printer = new TrianglePrinter(n);
            printer.printTriangle();

    }
    static class TrianglePrinter {
        private int rows;
        public TrianglePrinter(int rows) {
            this.rows = rows;
        }
        public void printTriangle() {
            for (int i = 1; i <= this.rows; i++) {
                for (int j = 1; j <= i; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }
    }
}