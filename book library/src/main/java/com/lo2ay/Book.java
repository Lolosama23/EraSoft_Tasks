package com.lo2ay;

public class Book {
    public Book() {
    }

    private  String title, author;
    private int isbn;
    boolean IsAvailable = true;

    public Book(String title, int isbn, String author) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title !=null&& !(title.trim().isEmpty()))
        this.title = title;
        else
        {
            System.out.println("Plz don't leave tittle  empty");
        }
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        if (isbn>0) {
            this.isbn = isbn;
        }
        else System.out.println("isbn should be from 1 to 1000");
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author !=null&& !(author.trim().isEmpty()))
            this.author = author;
        else
        {
            System.out.println("Plz don't leave Author  empty");
        }
    }
    public void borrow()
    {
        if (IsAvailable)
        {
            System.out.println("the book borrowed successfuly"+this.title);
            IsAvailable=false;
        }
        else
        {
            System.out.println("the book is borrowed noe");
        }
    }
    public void returnBook()
    {
        IsAvailable=true;
        System.out.println("book returned back you can borrow it now");
    }
    public void displayInfo()
    {
        System.out.println("the book name is "+ this.title);
        System.out.println("the author name is "+ this.author);
        System.out.println("the isbn is "+ this.getIsbn());
        if (IsAvailable) System.out.println("available");
        else System.out.println(" borrowed");
       // System.out.println(" condition"+ ((IsAvailable)? " available": "borrowed"));
    }


}
