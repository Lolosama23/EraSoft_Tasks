package com.lo2ay;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        long num1, num2, num3;
        Scanner input = new Scanner(System.in);
        num1 = input.nextLong();
        num2 = input.nextLong();
        num3 = input.nextLong();
        MaxMin maxMin = new MaxMin();
       System.out.print( maxMin.getMin(num1, num2, num3)+" "+maxMin.getMax(num1, num2, num3));


    }

    static class MaxMin {
        public long getMax(long x, long y, long z) {
            if (x >= y && x >= z)
                return x;
            else if (y >= x && y >= z)
                return y;
            else
                return z;
        }

        public long getMin(long x, long y, long z) {
            if (x <= y && x <= z)
             return x;
            else if (y <= x && y <= z)
                return y;
            else
                return z;


        }
    }
}
