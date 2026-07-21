package com.lo2ay;
import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        long l1 = input.nextLong();
        long r1 = input.nextLong();
        long l2 = input.nextLong();
        long r2 = input.nextLong();
        Interval interval = new Interval(l1,r1,l2,r2);
        interval.getInterval();
    }
    static class Interval
    {
        private long l1, r1,l2,r2;
        public Interval(long l1,long r1,long l2,long r2)
        {
            this.l1=l1;
            this.r1=r1;
            this.l2=l2;
            this.r2=r2;
        }
        public void getInterval()
        {
            long start=Math.max(l1,l2);
            long end=Math.min(r1,r2);
 
            if (start>end)
 
                System.out.println("-1");
            else
                System.out.println(start + " " + end);
        }
    }
}

