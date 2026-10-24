package com.lo2ay.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {
    private String userName;
    private String password;
    private Double balance;
    private String phoneNumber;
    private Float age;

    private boolean isAdmin = false;
    private boolean isActive = true;

    private List<Transaction> transactionHistory = new ArrayList<>();

    public Account() {
        this.balance = 0.0;
    }

    public Account(String userName, String password, boolean isAdmin) {
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isActive = true;
        this.balance = 0.0;
    }

    public Account(String userName, Float age, String phoneNumber, String password) {
        setUserName(userName);
        setAge(age);
        setPhoneNumber(phoneNumber);
        setPassword(password);
        this.balance = 0.0;
    }

    public Account(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }

    //  لإنشاء كائن Transaction جديد
    public void addTransaction(String details) {
        transactionHistory.add(new Transaction(details));
    }

    //  إرجاع List من نوع Transaction
    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        int attempts = 0;
        String currentInput = userName;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                if (currentInput == null || currentInput.trim().length() < 3) {
                    throw new IllegalArgumentException("Username must be at least 3 characters long.");
                }
                this.userName = currentInput;
                break;
            } catch (IllegalArgumentException e) {
                attempts++;
                if (attempts >= 3) {
                    System.out.println("❌ استنفذت 3 محاولات خاطئة. جاري إغلاق التطبيق...");
                    System.exit(0);
                }
                System.out.println("❌ Error: " + e.getMessage());
                System.out.print("Try again (Username): ");
                currentInput = scanner.next();
            }
        }
    }

    public Float getAge() {
        return age;
    }

    public void setAge(Float age) {
        int attempts = 0;
        Float currentInput = age;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                if (currentInput == null || currentInput < 16) {
                    throw new IllegalArgumentException("Age must be 16 or older to create a wallet.");
                }
                this.age = currentInput;
                break;
            } catch (IllegalArgumentException e) {
                attempts++;
                if (attempts >= 3) {
                    System.out.println("❌ استنفذت 3 محاولات خاطئة. جاري إغلاق التطبيق...");
                    System.exit(0);
                }
                System.out.println("❌ Error: " + e.getMessage());
                System.out.print("Try again (Age): ");

                while (!scanner.hasNextFloat()) {
                    scanner.next();
                    attempts++;
                    if (attempts >= 3) {
                        System.out.println("❌ استنفذت 3 محاولات خاطئة. جاري إغلاق التطبيق...");
                        System.exit(0);
                    }
                    System.out.println("❌ يجب إدخال أرقام فقط!");
                    System.out.print("Try again (Age): ");
                }
                currentInput = scanner.nextFloat();
            }
        }
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        int attempts = 0;
        Double currentInput = balance;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                if (currentInput == null || currentInput < 0) {
                    throw new IllegalArgumentException("Balance cannot be negative.");
                }
                this.balance = currentInput;
                break;
            } catch (IllegalArgumentException e) {
                attempts++;
                if (attempts >= 3) {
                    System.out.println("❌ استنفذت 3 محاولات خاطئة. جاري إغلاق التطبيق...");
                    System.exit(0);
                }
                System.out.println("❌ Error: " + e.getMessage());
                System.out.print("Try again (Balance): ");

                while (!scanner.hasNextDouble()) {
                    scanner.next();
                    attempts++;
                    if (attempts >= 3) {
                        System.out.println("❌ استنفذت 3 محاولات خاطئة. جاري إغلاق التطبيق...");
                        System.exit(0);
                    }
                    System.out.println("❌ يجب إدخال أرقام فقط!");
                    System.out.print("Try again (Balance): ");
                }
                currentInput = scanner.nextDouble();
            }
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        int attempts = 0;
        String currentInput = phoneNumber;
        Scanner scanner = new Scanner(System.in);
        String phoneRegex = "^01[0125][0-9]{8}$";

        while (true) {
            try {
                if (currentInput == null || !currentInput.matches(phoneRegex)) {
                    throw new IllegalArgumentException("Invalid phone number. Must be a valid 11-digit number starting with 01.");
                }
                this.phoneNumber = currentInput;
                break;
            } catch (IllegalArgumentException e) {
                attempts++;
                if (attempts >= 3) {
                    System.out.println("❌ استنفذت 3 محاولات خاطئة. جاري إغلاق التطبيق...");
                    System.exit(0);
                }
                System.out.println("❌ Error: " + e.getMessage());
                System.out.print("Try again (Phone Number): ");
                currentInput = scanner.next();
            }
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        int attempts = 0;
        String currentInput = password;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                if (currentInput == null || currentInput.length() < 6) {
                    throw new IllegalArgumentException("Password must be at least 6 characters long.");
                }
                this.password = currentInput;
                break;
            } catch (IllegalArgumentException e) {
                attempts++;
                if (attempts >= 3) {
                    System.out.println("❌ استنفذت 3 محاولات خاطئة. جاري إغلاق التطبيق...");
                    System.exit(0);
                }
                System.out.println("❌ Error: " + e.getMessage());
                System.out.print("Try again (Password): ");
                currentInput = scanner.next();
            }
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", balance=" + balance +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", isAdmin=" + isAdmin +
                ", isActive=" + isActive +
                '}';
    }
}