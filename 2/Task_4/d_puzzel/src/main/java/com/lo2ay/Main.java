package com.lo2ay;


import java.util.Scanner;

public class Main {
   static class AliBabaPuzzle {
        private long a;
        private long b;
        private long c;
        private long d;

        public AliBabaPuzzle(long a, long b, long c, long d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        public boolean canSolve() {
            if (a + b - c == d) return true;
            if (a + b * c == d) return true;
            if (a - b + c == d) return true;
            if (a - b * c == d) return true;
            if (a * b + c == d) return true;
            if (a * b - c == d) return true;
            return false;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        long c = scanner.nextLong();
        long d = scanner.nextLong();
        AliBabaPuzzle puzzle = new AliBabaPuzzle(a, b, c, d);

        if (puzzle.canSolve())
            System.out.println("YES");
         else
            System.out.println("NO");

    }
    }
