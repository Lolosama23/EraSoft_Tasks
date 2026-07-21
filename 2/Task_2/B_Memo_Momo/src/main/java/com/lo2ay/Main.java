package com.lo2ay;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long a = input.nextLong();
        long b = input.nextLong();
        long c = input.nextLong();
        Game game = new Game(a,b,c);
        game.getWinner();

    }
    static class Game
    {
        private long a,b,k;


    public Game(long num1,long num2 ,long num3)

    {
        this.a=num1;
        this.b=num2;
        this.k=num3;

    }
    public void getWinner()
    {
        if(a%k==0&&b%k==0)
            System.out.println("Both");
        else if (a%k==0)
            System.out.println("Memo");
        else if (b%k==0)
            System.out.println("Momo");
        else
        System.out.println("No One");
    }
}}
