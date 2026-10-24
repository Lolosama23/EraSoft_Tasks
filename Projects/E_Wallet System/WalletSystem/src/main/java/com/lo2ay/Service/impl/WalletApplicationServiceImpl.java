package com.lo2ay.Service.impl;

import com.lo2ay.Model.Account;
import com.lo2ay.Model.Transaction;
import com.lo2ay.Model.Wallet;
import com.lo2ay.enums.ChangePasswordStatus;
import com.lo2ay.enums.DepositStatus;
import com.lo2ay.enums.TransferStatus;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class WalletApplicationServiceImpl implements WalletApplicationService {
    static Scanner scanner = new Scanner(System.in);
    private static AccountService accountService = new AccountServiceImpl();

    @Override
    public void start() {
        System.out.println("-> Welcome to " + Wallet.name);

        // Auto create Admin account if it doesn't exist
        Account adminCheck = new Account("IAM", "IAM123", true);
        if (accountService.getAccountDetails(adminCheck) == null) {
            accountService.createAccount(adminCheck);
        }

        Integer count = 0;

        while (true) {
            System.out.println("\nplease choose......");
            System.out.println("1.login    2.signup     3.Exit");

            int choose = 0;
            try {
                choose = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            Boolean isExit = false;

            switch (choose) {
                case 1:
                    login();
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

            if (isExit) break;
            if (count == 4) {
                System.out.println("pls contact with Admin :(");
                break;
            }
        }
    }

    private void login() {
        System.out.print("Please enter your username: ");
        String userName = scanner.next();
        System.out.print("Please enter your password: ");
        String password = scanner.next();

        Account account = new Account(userName, password);

        try {
            Account existedAccount = accountService.getAccountByUsernameAndPassword(account);
            if (Objects.isNull(existedAccount)) {
                System.out.println("account not exist pls check username and password :(");
            } else {
                System.out.println("✅ success login :)");
                if (existedAccount.isAdmin()) {
                    adminProfile(existedAccount); // توجيه الأدمن
                } else {
                    mainProfile(existedAccount); // توجيه المستخدم العادي
                }
            }
        } catch (RuntimeException e) {
            if (e.getMessage().equals("ACCOUNT_INACTIVE")) {
                System.out.println("❌ Your account is currently inactive. Please contact the Admin.");
            }
        }
    }

    private void signUp() {
        System.out.println("Please enter all data:");
        Account account = new Account();

        System.out.print("Username: ");
        account.setUserName(scanner.next());
        scanner.nextLine();
        System.out.print("Password: ");
        account.setPassword(scanner.next());
        System.out.print("Phone Number: ");
        account.setPhoneNumber(scanner.next());
        System.out.print("Age: ");
        account.setAge(scanner.nextFloat());

        Account accountcreated = accountService.createAccount(account);
        if (Objects.isNull(accountcreated)) {
            System.out.println("can't create account bc it is already exist....");
        } else {
            System.out.println("Account created successfully");
            mainProfile(accountcreated);
        }
    }

    // --- User Profile ---
    private void mainProfile(Account account) {
        Integer count = 0;

        while (true) {
            System.out.println(
                    "\n1.deposit   2.withdraw   3.Transfer   4.show balance   5.show details   6.Change Password   7.Transaction History   8.logout"
            );

            System.out.println("pls enter your choose");
            int choose = 0;
            try {
                choose = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            boolean isExist = false;

            switch (choose) {
                case 1: deposit(account); break;
                case 2: withdraw(account); break;
                case 3: transfer(account); break;
                case 4: showBalance(account); break;
                case 5: showDetails(account); break;
                case 6: changePassword(account); break;
                case 7: showTransactionHistory(account); break; // عرض السجل
                case 8:
                    System.out.println("have a nice day :)");
                    isExist = accountService.logout();
                    break;
                default:
                    count++;
                    System.out.println("invalid choose ...");
            }

            if (isExist) break;
            if (count == 4) {
                System.out.println("pls contact with Admin :(");
                break;
            }
        }
    }

    // --- Admin Profile ---
    private void adminProfile(Account adminAccount) {
        while (true) {
            System.out.println("\n--- 👑 ADMIN PANEL ---");
            System.out.println("1. View All Accounts\n2. Delete Account\n3. Deactivate/Activate Account\n4. Logout");
            System.out.print("Choose action: ");

            int choose = 0;
            try {
                choose = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input.");
                scanner.nextLine();
                continue;
            }

            if (choose == 1) {
                List<Account> accounts = accountService.getAllAccounts();
                for (Account acc : accounts) {
                    System.out.println(acc.toString());
                }
            } else if (choose == 2) {
                System.out.print("Enter username to delete: ");
                String target = scanner.next();
                if (accountService.deleteAccount(target)) {
                    System.out.println("✅ Account deleted successfully.");
                } else {
                    System.out.println("❌ Failed. Account not found or is an Admin.");
                }
            } else if (choose == 3) {
                System.out.print("Enter username to toggle status: ");
                String target = scanner.next();
                if (accountService.toggleAccountStatus(target)) {
                    System.out.println("✅ Account status updated.");
                } else {
                    System.out.println("❌ Failed. Account not found or is an Admin.");
                }
            } else if (choose == 4) {
                System.out.println("Logging out Admin...");
                accountService.logout();
                break;
            }
        }
    }

    // --- Helpers & Features ---
    private void showTransactionHistory(Account account) {
        System.out.println("\n--- Transaction History ---");
        List<Transaction> history = account.getTransactionHistory();
        if (history.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            // 💡 تم التعديل هنا ليعتمد على كلاس Transaction
            for (Transaction record : history) {
                System.out.println(record.toString());
            }
        }
        System.out.println("---------------------------");
    }

    private Double getValidAmount(String prompt) {
        System.out.println(prompt);
        try {
            return scanner.nextDouble();
        } catch (Exception exception) {
            System.out.println("❌ Error: amount must be a number.");
            scanner = new Scanner(System.in);
            return null;
        }
    }

    private void withdraw(Account account) {
        Double amount = getValidAmount("pls enter your amount to withdraw:");
        if (amount == null) return;

        Double balance = accountService.withdraw(account, amount);
        if (balance == -1) System.out.println("Account not exist....");
        else if (balance == -2 || balance == -3) System.out.println("withdraw failed because amount must between 100 and 12000....");
        else if (balance == -4) System.out.println("amount is greater than your balance ....");
        else System.out.println("Success withdraw current balance: " + balance);
    }

    private void deposit(Account account) {
        Double amount = getValidAmount("pls enter your amount to deposit:");
        if (amount == null) return;

        DepositStatus depositStatus = accountService.deposit(account, amount);
        if (DepositStatus.ACCOUNT_NOT_EXIST.equals(depositStatus)) System.out.println("Account not exist....");
        else if (DepositStatus.AMOUNT_LESS_THAN_MINIMUM.equals(depositStatus) || DepositStatus.AMOUNT_GREATER_THAN_MAXIMUM.equals(depositStatus))
            System.out.println("deposit failed because amount must between 100 and 12000....");
        else if (DepositStatus.SUCCESS.equals(depositStatus))
            System.out.println("Success deposit current balance: " + accountService.getAccountBalance(account));
    }

    private void showBalance(Account account) {
        System.out.println("Balance : " + accountService.getAccountBalance(account));
    }

    private void transfer(Account account) {
        System.out.print("Enter destination username: ");
        String destUsername = scanner.next();
        Double amount = getValidAmount("Enter amount to transfer: ");
        if (amount == null) return;

        TransferStatus status = accountService.transfer(account, destUsername, amount);

        if (TransferStatus.TRANSFER_TO_SELF_NOT_ALLOWED.equals(status)) System.out.println("❌ You cannot transfer money to yourself.");
        else if (TransferStatus.INVALID_AMOUNT.equals(status)) System.out.println("❌ Amount must be greater than 0.");
        else if (TransferStatus.SOURCE_ACCOUNT_NOT_FOUND.equals(status) || TransferStatus.DESTINATION_ACCOUNT_NOT_FOUND.equals(status))
            System.out.println("❌ Account not found. Please check the username.");
        else if (TransferStatus.INSUFFICIENT_BALANCE.equals(status)) System.out.println("❌ Insufficient balance.");
        else if (TransferStatus.SUCCESS.equals(status)) System.out.println("✅ Transfer successful! Your new balance is: " + accountService.getAccountBalance(account));
    }

    private void showDetails(Account account) {
        Account details = accountService.getAccountDetails(account);
        if (details != null) {
            System.out.println("\n--- Account Details ---");
            System.out.println("Username: " + details.getUserName());
            if (!details.isAdmin()) {
                System.out.println("Age: " + details.getAge());
                System.out.println("Phone Number: " + details.getPhoneNumber());
                System.out.println("Status: " + (details.isActive() ? "Active" : "Inactive"));
            }
            System.out.println("Balance: " + details.getBalance());
            System.out.println("Password: ***");
            System.out.println("-----------------------\n");
        }
    }

    private void changePassword(Account account) {
        System.out.print("Enter your OLD password: ");
        String oldPassword = scanner.next();
        System.out.print("Enter your NEW password: ");
        String newPassword = scanner.next();

        ChangePasswordStatus status = accountService.changePassword(account, oldPassword, newPassword);

        if (ChangePasswordStatus.ACCOUNT_NOT_FOUND.equals(status)) System.out.println("❌ Error: Account not found.");
        else if (ChangePasswordStatus.OLD_PASSWORD_INCORRECT.equals(status)) System.out.println("❌ Incorrect old password.");
        else if (ChangePasswordStatus.SAME_AS_OLD_PASSWORD.equals(status)) System.out.println("❌ New password cannot be the same as the old password.");
        else if (ChangePasswordStatus.INVALID_PASSWORD_FORMAT.equals(status)) System.out.println("❌ Invalid format. Password must be at least 6 characters.");
        else if (ChangePasswordStatus.SUCCESS.equals(status)) {
            System.out.println("✅ Password changed successfully!");
            account.setPassword(newPassword);
        }
    }
}