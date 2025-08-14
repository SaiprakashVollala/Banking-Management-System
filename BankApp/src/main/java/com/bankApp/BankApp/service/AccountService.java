package com.bankApp.BankApp.service;

import com.bankApp.BankApp.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountdto);
    AccountDto getAccount(Long id);
    List<AccountDto> getAllAccounts();
    AccountDto deposit(Long id,double amount);
    AccountDto withdraw(Long id,double amount);
    AccountDto deleteAccount(Long id);
    AccountDto deleteAllAccounts();
}
