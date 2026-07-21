package com.lo2ay;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            long a = scanner.nextLong();
            long b = scanner.nextLong();

            PeterAdder adder = new PeterAdder(a, b);

            System.out.println(adder.add());

        }
    static class PeterAdder {
        private long num1;
        private long num2;

        public PeterAdder(long num1, long num2) {
            this.num1 = num1;
            this.num2 = num2;
        }
        public long add() {
            return this.num1 ^ this.num2;
        }
    }
}
