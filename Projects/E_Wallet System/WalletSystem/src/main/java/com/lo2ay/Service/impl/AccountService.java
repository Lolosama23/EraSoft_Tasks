package com.lo2ay.Service.impl;
import com.lo2ay.Model.Account;
import com.lo2ay.enums.ChangePasswordStatus;
import com.lo2ay.enums.DepositStatus;
import com.lo2ay.enums.TransferStatus;
import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    Account getAccountByUsernameAndPassword(Account account);
    DepositStatus deposit(Account account, double amount);
    Double withdraw(Account account, double amount);
    Double getAccountBalance(Account account);
    TransferStatus transfer(Account sourceAccount, String destinationUsername, double amount);
    ChangePasswordStatus changePassword(Account account, String oldPassword, String newPassword);
    Account getAccountDetails(Account account);
    boolean logout();

    // دوال الأدمن الإضافية
    List<Account> getAllAccounts();
    boolean deleteAccount(String username);
    boolean toggleAccountStatus(String username);
}