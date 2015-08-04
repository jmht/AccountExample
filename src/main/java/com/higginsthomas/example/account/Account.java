/**
 * © Copyright 2015. All rights reserved.
 */
package com.higginsthomas.example.account;


/**
 * Entity class representing a financial account.  The account is identified
 * by an Account Number (String) and contains a Balance (Money).
 * 
 * @author James M. Higgins-Thomas
 * @version 1.0
 * @since 1.0
 */
public class Account {
    private final String accountNumber;
    private Money balance;

    /**
     * Constructor.
     * 
     * @param accountNumber     The account number (identity) for the account.
     * @param initialBalance    The initial balance for the account.
     */
    public Account(String accountNumber,
            Money initialBalance)
    {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    /**
     * @return the account number (identity) for the account.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @return the current balance in the account.
     */
    public Money getBalance() {
        return balance;
    }

    /**
     * Transfer money from this account to another.
     * 
     * This is a transactional action (will succeed or fail as a whole).
     * This operation satisfies the double-entry accounting requirement,
     * sometimes refered to as "conservation of money", by making the
     * debit (to {@code this} account) and credit (to the target account)
     * together.
     * 
     * @param target    the account receiving the funds.
     * @param amount    the amount to be transfered.
     * 
     * @return  {@code this}
     */
    // @Transactional
    public Account transferTo(Account target, Money amount) {
        if ( amount.isNegative() ) {
            // Transfer of negative amount from this to target
            // is equivalent to transfer of the positive amount
            // from target to this.
            return transferFrom(target, amount.negate());    
        }

        // Carry out the offsetting instructions.
        debit(amount);
        target.credit(amount);
        
        return this;        // allows for chaining
    }

    /**
     * Transfer money to this account from another.
     * 
     * This is a transactional action (will succeed or fail as a whole).
     * This operation satisfies the double-entry accounting requirement,
     * sometimes refered to as "conservation of money". In particular, the
     * debit and offsetting credit are carried out together.
     * 
     * @param target    the account sourcing the funds.
     * @param amount    the amount to be transfered.
     * 
     * @return  {@code this}
     */
    public Account transferFrom(Account target, Money amount) {
        // Transfer from target to this is equivalent to transfer to this from target.
        target.transferTo(this, amount);
        
        return this;        // allows for chaining
    }

    /*
     * Debit the account by a specified amount.
     * 
     * This operation is private, reserved for internal use only.
     * Note: the amount should be non-negative.
     * 
     * @param amount    the amount to debit.
     * 
     * @return  {@code this}
     */
    private void debit(Money amount) {
        assert !amount.isNegative();
        balance = balance.subtract(amount);
    }

    /*
     * Credit the account by a specified amount.
     * 
     * This operation is private, reserved for internal use only.
     * Note: the amount should be non-negative.
     * 
     * @param amount    the amount to credit.
     * 
     * @return  {@code this}
     */
    private void credit(Money amount) {
        assert !amount.isNegative();
        balance = balance.add(amount);
    }
}
