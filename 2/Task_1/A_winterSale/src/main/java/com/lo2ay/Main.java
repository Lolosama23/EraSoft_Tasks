package com.lo2ay;



import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int x=input.nextInt();
        int y=input.nextInt();
        BeforeDisc beforeDisc = new BeforeDisc(x,y);
        beforeDisc.price();

    }
    static class BeforeDisc
    {
        private double Precentage;
        private double postdisc;
        public BeforeDisc(double a, double b)
        {
            this.Precentage=a;
            this.postdisc=b;
        }
        public void price()
        {
           double res= (1-Precentage/100);
           double price=postdisc/res;
           System.out.printf("%.2f\n", price);
        }
    }
}