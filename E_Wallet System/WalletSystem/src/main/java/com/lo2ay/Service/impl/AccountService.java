package com.lo2ay.Service.impl;

import com.lo2ay.Model.Account;

public interface AccountService {

    Account createAccount( Account account);
    Account getAccountByUserNameAndPassword(Account account);
    void deposit(Account account, Double amount);
    boolean withdraw(Account account, Double amount);
    boolean transfer(Account senderAccount, String receiverUsername, Double amount);
    boolean changePassword(Account account, String oldPassword, String newPassword);



}
