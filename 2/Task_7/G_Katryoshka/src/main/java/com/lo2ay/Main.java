package com.lo2ay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

            long n = scanner.nextLong();
            long m = scanner.nextLong();
            long k = scanner.nextLong();
            KatryoshkaMaker maker = new KatryoshkaMaker(n, m, k);
            System.out.println(maker.calculateMaxKatryoshkas());
    }

    static class KatryoshkaMaker {
        private long eyes;
        private long mouths;
        private long bodies;
        public KatryoshkaMaker(long eyes, long mouths, long bodies) {
            this.eyes = eyes;
            this.mouths = mouths;
            this.bodies = bodies;
        }

        public long calculateMaxKatryoshkas() {
            long step1 = Math.min(eyes, Math.min(mouths, bodies));
            this.eyes -= step1;
            this.bodies -= step1;
            long step2 = Math.min(this.eyes / 2, this.bodies);
            return step1 + step2;
        }
    }
}