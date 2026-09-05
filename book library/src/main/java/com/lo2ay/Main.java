package com.lo2ay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Book book1 = new Book("data structure", 1000, "Loai");
        book1.displayInfo();
        book1.borrow();
        book1.borrow();
        book1.returnBook();
        System.out.println("******************************");
        EBook ebook1 = new EBook("algorithm", 2000, "Osama", "epub");
        ebook1.displayInfo();
    }

}