package com.lo2ay;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        long days= scanner.nextLong();
AgeCalc ageCalc= new AgeCalc(days);
ageCalc.printAge();
    }
    static public class AgeCalc
    {
        private long totaldays;
        public AgeCalc (long total_days)
        {
            this.totaldays=total_days;
        }
        public void printAge()
        {
            System.out.println(totaldays/365+" "+"years");
            totaldays%=365;
            System.out.println(totaldays/30+" "+"months");
            totaldays%=30;
            System.out.println(totaldays+" "+"days");
        }
    }

}