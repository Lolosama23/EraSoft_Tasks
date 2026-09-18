package com.lo2ay.Service.impl;

import com.lo2ay.Model.Account;
import com.lo2ay.Model.Wallet;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class WalletApplicationServiceImpl implements ApplicationService {
    static Scanner scanner = new Scanner(System.in);
    private static AccountService accountService = new AccountServiceImpl();

    @Override
    public void start() {
        System.out.println("------->Welcome to " + Wallet.name + "------->");

        int count = 0;


        while (true) {
            System.out.println("please choose......");
            System.out.println("1.login    2.signup     3.Exit");

            int choose;

            try {
                choose = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("plz enter numbers only");

                scanner.nextLine(); // remove the invalid input
                count++;

                if (count == 4) {
                    System.out.println("pls contact with Admin :(");
                    break;
                }

                continue; // don't execute the switch
            }

            boolean isExit = false;

            switch (choose) {
                case 1:
                    logIn();
                    break;

                case 2:
                    signUp();
                    break;

                case 3:
                    System.out.println("have a nice day :)");
                    isExit = true;
                    break;

                default:
                    System.out.println("invalid choose :(");
                    count++;
            }

            if (isExit) {
                break;
            }

            if (count == 4) {
                System.out.println("pls contact with Admin :(");
                break;
            }
        }
    }

    private void logIn() {
        System.out.print("plz enter Username: ");
        String userName = scanner.next();

        System.out.print("plz enter Password: ");
        String password = scanner.next();

        Account existAccount = new Account(userName, password);
        existAccount = accountService.getAccountByUserNameAndPassword(existAccount);

        if (Objects.isNull(existAccount)) System.out.println("Account not exist plz check UserName &Password ....");
        else {
            System.out.println("success Login");
            mainProfile(existAccount);
        }
    }

    private void signUp() {
        System.out.println("Please enter all data:");

        System.out.print("Username: ");
        String userName = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.next();
        System.out.print("Age: ");
        Float age = scanner.nextFloat();

        Account accountcreated = new Account(userName, age, phoneNumber, password);
        accountcreated = accountService.createAccount(accountcreated);

        if (Objects.isNull(accountcreated)) System.out.println("can't create account bc it is already exist....");
        else {
            System.out.println("Account created successfully");
            mainProfile(accountcreated);
        }
    }

    private static void mainProfile(Account account) {
        boolean isLogout = false;
        while (!isLogout)
        {
        System.out.println(
                "1.Deposit  " +
                        "2.Withdraw  " +
                        "3.Transfer  " +
                        "4.Show Balance  " +
                        "5.Show Details  " +
                        "6.Change Password  " +
                        "7.Logout  ");

            int choose;
            try {
                 choose = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("plz enter numbers only");
                scanner.nextLine();
                continue;
            }

            switch (choose)
            {
                case 1:
                    deposite(account);
                    break;
                case 2:
                    withdraw(account);
                    break;
                case 3:
                    transfer(account);
                    break;
                case 4:
                    System.out.println("Your Current Balance is: " + account.getBalance());
                    break;
                case 5:
                    System.out.println(account);
                    break;
                case 6:
                    changePassword(account);
                    break;
                case 7:
                    System.out.println("Logging out...");
                    isLogout = true;
                    break;
                default:
                    System.out.println("invalid choice");
                    break;
            }

        }
    }

    private static void changePassword(Account account) {
        System.out.print("Enter current password: ");
        String oldPass = scanner.next();
        System.out.print("Enter new password: ");
        String newPass = scanner.next();

        boolean isChanged = accountService.changePassword(account, oldPass, newPass);
        if (isChanged) {
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Failed! Incorrect current password.");
        }
    }

    private static void transfer(Account account) {
        System.out.print("Enter receiver username: ");
        String receiver = scanner.next();
        System.out.print("Enter amount to transfer: ");
        double transferAmount = scanner.nextDouble();

        boolean isTransferred = accountService.transfer(account, receiver, transferAmount);
        if (isTransferred) {
            System.out.println("Transfer successful. Current Balance: " + account.getBalance());
        } else {
            System.out.println("Failed! Check receiver username or your balance.");
        }
        return;
    }

    private static void withdraw(Account account) {
        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();
        boolean isWithdrawn = accountService.withdraw(account, withdrawAmount);
        if (isWithdrawn) {
            System.out.println("Withdraw successful. Current Balance: " + account.getBalance());
        } else {
            System.out.println("Failed! Insufficient balance or invalid amount.");
        }
    }

    private static void deposite(Account account) {
        System.out.print("Enter amount to deposit: ");
        double depositAmount = scanner.nextDouble();
        accountService.deposit(account, depositAmount);
        System.out.println("Deposit successful. Current Balance: " + account.getBalance());
        return;
    }
}

