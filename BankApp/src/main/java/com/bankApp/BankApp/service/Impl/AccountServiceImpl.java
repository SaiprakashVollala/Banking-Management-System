package com.bankApp.BankApp.service.Impl;

import com.bankApp.BankApp.Entity.Account;
import com.bankApp.BankApp.dto.AccountDto;
import com.bankApp.BankApp.mapper.AccountMapper;
import com.bankApp.BankApp.respository.AccountRepository;
import com.bankApp.BankApp.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }
    public AccountDto createAccount(AccountDto accountdto){
        Account account = AccountMapper.maptoAccount(accountdto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }
    public AccountDto getAccount(Long id){
      Account account =  accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't exist"));
      return AccountMapper.maptoAccountDto(account);
    }
    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account>  ls =accountRepository.findAll();
        return ls.stream().map((account)-> AccountMapper.maptoAccountDto(account)).collect(Collectors.toList());
    }
    public AccountDto deposit(Long id,double amount){
       Account account =  accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account doesn't found"));
       double bal = account.getBalance()+amount;
       account.setBalance(bal);
       accountRepository.save(account);
       return AccountMapper.maptoAccountDto(account);
    }
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesn't found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance. Cannot withdraw ₹" + amount);
        }

        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        accountRepository.save(account);
        return AccountMapper.maptoAccountDto(account);
    }


    @Override
    public AccountDto deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found"));
        accountRepository.deleteById(id);
        return null;
    }
    public AccountDto deleteAllAccounts(){
        accountRepository.deleteAll();
        return null;
    }
}
