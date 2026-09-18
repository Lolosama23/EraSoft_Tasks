package com.lo2ay.Model;

public class Account {
    private String userName;
    private String password;
    private Double balance;
    private String phoneNumber;
    private Float age;

    public Account() {
    }

    public Account(String userName, Float age, String phoneNumber, String password) {
        this.userName = userName;
        this.age = age;
        this.balance=0.0;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName == null || userName.trim().length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters long.");
        }
        this.userName = userName;
    }

    public Float getAge() {
        return age;
    }

    public void setAge(Float age) {
        if (age == null || age < 16) {
            throw new IllegalArgumentException("Age must be 16 or older to create a wallet.");
        }
        this.age = age;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        if (balance == null || balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.balance = balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        // التحقق: رقم هاتف مصري صحيح (11 رقم، يبدأ بـ 010 أو 011 أو 012 أو 015)
        String phoneRegex = "^01[0125][0-9]{8}$";
        if (phoneNumber == null || !phoneNumber.matches(phoneRegex)) {
            throw new IllegalArgumentException("Invalid phone number. Must be a valid 11-digit number starting with 01.");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        }
        this.password = password;
    }
    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                '}';
    }
}
