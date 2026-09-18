package com.lo2ay.Model;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    public final static String name = " EraaSoft's Wallet";
    private static List<Account>accounts= new ArrayList();

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
