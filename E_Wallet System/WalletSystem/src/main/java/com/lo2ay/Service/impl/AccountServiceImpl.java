package com.lo2ay.Service.impl;

import com.lo2ay.Model.Account;
import com.lo2ay.Model.Wallet;

import java.util.Optional;
import java.util.Scanner;

public class AccountServiceImpl implements AccountService {
    private static Wallet wallet = new Wallet();
    static Scanner scanner = new Scanner(System.in);
    Account account =new Account();

    @Override
    public Account createAccount(Account account) {
        boolean IsAccountExistWithSameUserName = wallet.getAccounts().stream()
                .anyMatch(acc -> acc.getUserName()
                        .equals(account.getUserName()));
        if (IsAccountExistWithSameUserName)
            return null;
        else {
            wallet.getAccounts().add(account);
            return account;
        }
    }
    public Account getAccountByUserNameAndPassword(Account account)
    {
       Optional<Account>existedAccount= wallet.getAccounts().stream()
                .filter(ac->ac.getUserName().equals(account.getUserName())&&ac.getPassword()
                        .equals(account.getPassword())).findFirst();
       if (existedAccount.isPresent()) return existedAccount.get();
       else return null;
    }

    @Override
    public void deposit(Account account, Double amount) {
        if (amount>0)
        {
            account.setBalance(account.getBalance()+amount);
        }
    }

    @Override
    public boolean withdraw(Account account, Double amount) {
        if (amount>0&& account.getBalance()>=amount)
        {
            account.setBalance(account.getBalance()-amount);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean transfer(Account senderAccount, String receiverUsername, Double amount) {
        Optional<Account> receiverOpt = wallet.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(receiverUsername))
                .findFirst();
        if (receiverOpt.isPresent() && amount > 0 && senderAccount.getBalance() >= amount)
        {
            Account receiverAccount = receiverOpt.get();
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            receiverAccount.setBalance(receiverAccount.getBalance() + amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean changePassword(Account account, String oldPassword, String newPassword)
    {
        if (account.getPassword().equals(oldPassword)) {
            account.setPassword(newPassword);
            return true;
        }
        return false;
    }

}
