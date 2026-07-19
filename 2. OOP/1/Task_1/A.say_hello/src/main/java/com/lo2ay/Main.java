package com.lo2ay;

import java.util.Scanner;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name=input.next();
        Hello hello = new Hello();
     System.out.println(name);

    }
    static  class Hello{
        public Hello()
        {
            System.out.print("Hello, ");
        }
    }
}
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name=input.next();
        Hello hello = new Hello();
        System.out.println(name);

    }


    static class Hello{
        public Hello()
        {
            System.out.print("Hello, ");
        }

    }
}