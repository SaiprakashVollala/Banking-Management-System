package com.bankApp.BankApp.controller;
import com.bankApp.BankApp.dto.AccountDto;
import com.bankApp.BankApp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:5173")

public class AccountController {
    private AccountService accountService;
    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }
 @PostMapping
 public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountdto){

     return new ResponseEntity<>(accountService.createAccount(accountdto), HttpStatus.CREATED);
 }
 @GetMapping("/{id}")
 public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccount(id);
   return ResponseEntity.ok(accountDto);
 }
 @GetMapping
 public ResponseEntity<List<AccountDto>> getAllAccounts(){
     List<AccountDto> accounts =  accountService.getAllAccounts();
     return ResponseEntity.ok(accounts);
 }
 @PutMapping("/{id}/{amount}/{operation}")
 public ResponseEntity<AccountDto> BankOperations (@PathVariable Long id,@PathVariable double amount,@PathVariable String operation){
     AccountDto accountDto;
        if(operation.equalsIgnoreCase("deposit")) {
             accountDto = accountService.deposit(id, amount);
             return ResponseEntity.ok(accountDto);
        }
        else if(operation.equalsIgnoreCase("withdraw")) {
            accountDto = accountService.withdraw(id, amount);
            return ResponseEntity.ok(accountDto);
        }
        else{
            return (ResponseEntity<AccountDto>) ResponseEntity.badRequest();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable Long id){
        AccountDto accountDto = accountService.deleteAccount(id);
        return ResponseEntity.ok(accountDto);
    }
    @DeleteMapping
    public ResponseEntity<AccountDto> deleteAllAccounts(){
        AccountDto accountDto = accountService.deleteAllAccounts();
        return ResponseEntity.ok(accountDto);
    }
}
