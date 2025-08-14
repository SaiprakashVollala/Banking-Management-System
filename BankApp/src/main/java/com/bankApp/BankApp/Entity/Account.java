package com.bankApp.BankApp.Entity;

import jakarta.persistence.*;
@Table(name="accounts")
@Entity
public class Account  {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="account_holder_name")
    private String accountHolderName;
    @Column(name="balance")
    private double balance;
    public Account(){

    }
    public Account(Long id, String accountHolderName, double balance) {
        this.id = id;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }
}
