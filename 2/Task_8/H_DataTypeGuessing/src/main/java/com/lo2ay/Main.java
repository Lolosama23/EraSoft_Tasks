package com.lo2ay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            long n = scanner.nextLong();
            long k = scanner.nextLong();
            long a = scanner.nextLong();
            DataTypeIdentifier identifier = new DataTypeIdentifier(n, k, a);
            System.out.println(identifier.identifyType());

    }

    static class DataTypeIdentifier {
        private long n;
        private long k;
        private long a;

        public DataTypeIdentifier(long n, long k, long a) {
            this.n = n;
            this.k = k;
            this.a = a;
        }

        public String identifyType() {
            long product = this.n * this.k;
            if (product % this.a != 0) {
                return "double";
            }
            long result = product / this.a;
            if (result <= 2147483647L) {
                return "int";
            }
            else {
                return "long long";
            }
        }
    }
}