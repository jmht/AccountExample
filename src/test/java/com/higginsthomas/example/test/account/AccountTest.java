package com.higginsthomas.example.test.account;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.higginsthomas.example.account.Account;
import com.higginsthomas.example.account.Money;

public class AccountTest {

    @Test
    public void testTransferTo() {
        Account account1 = new Account("1", new Money(10));
        Account account2 = new Account("2", new Money(10));
        
        account1.transferTo(account2, new Money(5));
        
        assertThat(account1.getBalance(), equalTo(new Money(5)));
        assertThat(account2.getBalance(), equalTo(new Money(15)));
    }

    @Test
    public void testTransferFrom() {
        Account account1 = new Account("1", new Money(10));
        Account account2 = new Account("2", new Money(10));
        
        account1.transferFrom(account2, new Money(5));
        
        assertThat(account1.getBalance(), equalTo(new Money(15)));
        assertThat(account2.getBalance(), equalTo(new Money(5)));
    }

    @Test
    public void testTransferTo_NegativeAmount() {
        Account account1 = new Account("1", new Money(10));
        Account account2 = new Account("2", new Money(10));
        
        account1.transferTo(account2, new Money(-5));
        
        assertThat(account1.getBalance(), equalTo(new Money(15)));
        assertThat(account2.getBalance(), equalTo(new Money(5)));
    }

    @Test
    public void testTransferFrom_NegativeAmount() {
        Account account1 = new Account("1", new Money(10));
        Account account2 = new Account("2", new Money(10));
        
        account1.transferFrom(account2, new Money(-5));
        
        assertThat(account1.getBalance(), equalTo(new Money(5)));
        assertThat(account2.getBalance(), equalTo(new Money(15)));
    }
}
