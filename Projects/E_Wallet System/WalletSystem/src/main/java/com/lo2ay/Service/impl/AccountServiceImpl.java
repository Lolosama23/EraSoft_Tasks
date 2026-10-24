package com.lo2ay.Service.impl;

import com.lo2ay.Model.Account;
import com.lo2ay.Model.Wallet;
import com.lo2ay.enums.ChangePasswordStatus;
import com.lo2ay.enums.DepositStatus;
import com.lo2ay.enums.TransferStatus;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private Wallet walletSystem = new Wallet();

    private Optional<Account> findAccountByUsername(String username) {
        return walletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(username))
                .findFirst();
    }

    @Override
    public Account createAccount(Account account) {
        boolean isAccountExistWithSameUserName = findAccountByUsername(account.getUserName()).isPresent();

        if (isAccountExistWithSameUserName) {
            return null;
        }

        account.addTransaction("Signup Successful.");
        walletSystem.getAccounts().add(account);
        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Optional<Account> existedAccount = findAccountByUsername(account.getUserName())
                .filter(acc -> acc.getPassword().equals(account.getPassword()));

        if (existedAccount.isPresent()) {
            Account acc = existedAccount.get();
            if (!acc.isActive()) {
                throw new RuntimeException("ACCOUNT_INACTIVE"); // سيتم معالجتها في الـ Login
            }
            acc.addTransaction("Login Successful.");
            return acc;
        }
        return null;
    }

    @Override
    public DepositStatus deposit(Account account, double amount) {
        Optional<Account> accountOptional = findAccountByUsername(account.getUserName());

        if (accountOptional.isEmpty()) return DepositStatus.ACCOUNT_NOT_EXIST;
        if (amount < 100) return DepositStatus.AMOUNT_LESS_THAN_MINIMUM;
        if (amount > 12000) return DepositStatus.AMOUNT_GREATER_THAN_MAXIMUM;

        Account acc = accountOptional.get();
        acc.setBalance(acc.getBalance() + amount);
        acc.addTransaction("Deposit: +" + amount);
        return DepositStatus.SUCCESS;
    }

    @Override
    public Double withdraw(Account account, double amount) {
        Optional<Account> accountOptional = findAccountByUsername(account.getUserName());

        if (accountOptional.isEmpty()) return -1.0;
        if (amount < 100) return -2.0;
        if (amount > 12000) return -3.0;

        Account acc = accountOptional.get();
        if (acc.getBalance() < amount) return -4.0;

        acc.setBalance(acc.getBalance() - amount);
        acc.addTransaction("Withdraw: -" + amount);
        return acc.getBalance();
    }

    @Override
    public Double getAccountBalance(Account account) {
        Optional<Account> accountOptional = findAccountByUsername(account.getUserName());
        if (accountOptional.isEmpty()) throw new RuntimeException("account not exist");
        return accountOptional.get().getBalance();
    }

    @Override
    public TransferStatus transfer(Account sourceAccount, String destinationUsername, double amount) {
        try {
            if (sourceAccount.getUserName().equals(destinationUsername)) return TransferStatus.TRANSFER_TO_SELF_NOT_ALLOWED;
            if (amount <= 0) return TransferStatus.INVALID_AMOUNT;

            Optional<Account> sourceOpt = findAccountByUsername(sourceAccount.getUserName());
            if (sourceOpt.isEmpty()) return TransferStatus.SOURCE_ACCOUNT_NOT_FOUND;

            Optional<Account> destOpt = findAccountByUsername(destinationUsername);
            if (destOpt.isEmpty()) return TransferStatus.DESTINATION_ACCOUNT_NOT_FOUND;

            Account source = sourceOpt.get();
            Account dest = destOpt.get();

            if (source.getBalance() < amount) return TransferStatus.INSUFFICIENT_BALANCE;

            source.setBalance(source.getBalance() - amount);
            dest.setBalance(dest.getBalance() + amount);

            source.addTransaction("Transfer Out: -" + amount + " to " + destinationUsername);
            dest.addTransaction("Transfer In: +" + amount + " from " + source.getUserName());

            return TransferStatus.SUCCESS;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ChangePasswordStatus changePassword(Account account, String oldPassword, String newPassword) {
        Optional<Account> accountOpt = findAccountByUsername(account.getUserName());
        if (accountOpt.isEmpty()) return ChangePasswordStatus.ACCOUNT_NOT_FOUND;

        Account currentUser = accountOpt.get();
        if (!currentUser.getPassword().equals(oldPassword)) return ChangePasswordStatus.OLD_PASSWORD_INCORRECT;
        if (oldPassword.equals(newPassword)) return ChangePasswordStatus.SAME_AS_OLD_PASSWORD;
        if (newPassword == null || newPassword.trim().length() < 6) return ChangePasswordStatus.INVALID_PASSWORD_FORMAT;

        currentUser.setPassword(newPassword);
        currentUser.addTransaction("Password Changed.");
        return ChangePasswordStatus.SUCCESS;
    }

    @Override
    public Account getAccountDetails(Account account) {
        return findAccountByUsername(account.getUserName()).orElse(null);
    }

    @Override
    public boolean logout() {
        return true;
    }

    // دوال الأدمن
    @Override
    public List<Account> getAllAccounts() {
        return walletSystem.getAccounts();
    }

    @Override
    public boolean deleteAccount(String username) {
        return walletSystem.getAccounts().removeIf(acc -> acc.getUserName().equals(username) && !acc.isAdmin());
    }

    @Override
    public boolean toggleAccountStatus(String username) {
        Optional<Account> accOpt = findAccountByUsername(username);
        if (accOpt.isPresent() && !accOpt.get().isAdmin()) {
            Account acc = accOpt.get();
            acc.setActive(!acc.isActive());
            return true;
        }
        return false;
    }
}