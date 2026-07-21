package com.lo2ay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


            long a = input.nextLong();
            long b = input.nextLong();
            long c = input.nextLong();
            long d = input.nextLong();

            PowerComparer comparer = new PowerComparer(a, b, c, d);

            if (comparer.isFirstGreater()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }


        input.close();
    }

    static class PowerComparer {
        private long base1;
        private long power1;
        private long base2;
        private long power2;

        public PowerComparer(long base1, long power1, long base2, long power2) {
            this.base1 = base1;
            this.power1 = power1;
            this.base2 = base2;
            this.power2 = power2;
        }

        public boolean isFirstGreater() {
            return (power1 * Math.log(base1)) > (power2 * Math.log(base2));
        }
    }
}