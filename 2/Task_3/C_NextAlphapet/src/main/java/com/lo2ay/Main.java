package com.lo2ay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char s = scanner.next().charAt(0);
        Alphapet alphapet = new Alphapet(s);
        alphapet.nextAlpapet();
    }
    static class Alphapet
    {
       private char s;
        public Alphapet (char s)
        {
                this.s=s;
        }
        public void nextAlpapet()
        {
//            int x= (int) s;
            if(s=='z')
                System.out.println("a");
            else
            System.out.println(++s);

        }
    }

}