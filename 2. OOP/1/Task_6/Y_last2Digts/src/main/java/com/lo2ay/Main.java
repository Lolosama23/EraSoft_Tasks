package com.lo2ay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        long a = input.nextLong();
        long b = input.nextLong();
        long c = input.nextLong();
        long d = input.nextLong();
        Digts digts =new Digts(a,b,c,d);
        digts.getLast2Digts();

    }
    static class Digts
    {
        private long num1 ,num2,num3,num4;
public Digts(long a ,long b ,long c ,long d)
{

    this.num1=a%100;
    this.num2=b%100;
    this.num3=c%100;
    this.num4=d%100;
}
public void getLast2Digts()
{
    long result=(num1*num2*num3*num4)%100;
    if (result<10)
        System.out.print("0"+result);
    else
        System.out.print(result);

}


    }
}