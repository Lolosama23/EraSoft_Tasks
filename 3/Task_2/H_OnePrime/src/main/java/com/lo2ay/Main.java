package com.lo2ay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


            int x = scanner.nextInt();
            PrimeChecker checker = new PrimeChecker(x);
            if (checker.isPrime()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

    }
    static class PrimeChecker {
        private int number;
        public PrimeChecker(int number) {
            this.number = number;
        }
        public boolean isPrime() {
            if (this.number <= 1) {
                return false;
            }
            for (int i = 2; i * i <= this.number; i++) {
                if (this.number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}