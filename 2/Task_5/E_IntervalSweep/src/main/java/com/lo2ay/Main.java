package com.lo2ay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            IntervalChecker checker = new IntervalChecker(a, b);

            if (checker.isValid()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        scanner.close();
    }
    
    static class IntervalChecker {
        private int oddCount;
        private int evenCount;

        public IntervalChecker(int oddCount, int evenCount) {
            this.oddCount = oddCount;
            this.evenCount = evenCount;
        }

        public boolean isValid() {
            if (this.oddCount == 0 && this.evenCount == 0) {
                return false;
            }

            int difference = Math.abs(this.oddCount - this.evenCount);

            if (difference <= 1) {
                return true;
            } else {
                return false;
            }
        }
    }
}