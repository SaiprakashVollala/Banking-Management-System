package com.bankApp.BankApp.mapper;

import com.bankApp.BankApp.Entity.Account;
import com.bankApp.BankApp.dto.AccountDto;


public class AccountMapper {
    public static AccountDto maptoAccountDto(Account account){
        return  new AccountDto(account.getId(), account.getAccountHolderName(),account.getBalance());
    }
    public static Account maptoAccount(AccountDto accountdto) {

        return new Account(accountdto.getId(), accountdto.getAccountHolderName(), accountdto.getBalance());
    }
}
