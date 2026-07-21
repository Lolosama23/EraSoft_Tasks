package com.lo2ay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            LuckyNumberChecker checker = new LuckyNumberChecker(n);
            if (checker.isLucky())
                System.out.println("YES");
            else
                System.out.println("NO");
    }

    static class LuckyNumberChecker {
        private int number;
        public LuckyNumberChecker(int number) {
            this.number = number;
        }

        public boolean isLucky() {
            int tens = this.number / 10;
            int units = this.number % 10;
            if (units % tens == 0) {
                return true;
            }
            if (units != 0 && tens % units == 0) {
                return true;
            }
            return false;
        }
    }
}