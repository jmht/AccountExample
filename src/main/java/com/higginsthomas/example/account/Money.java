/**
 * © Copyright 2015. All rights reserved.
 */
package com.higginsthomas.example.account;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class Money {
    private final MathContext context = new MathContext(2, RoundingMode.DOWN);
    private BigDecimal amount;
    
    
    public Money(BigDecimal amount) {
        amount = amount.round(context);
        this.amount = amount;
    }
    
    public Money(Money money) {
        this.amount = money.getAmount();
    }
    
    public Money(double amount) {
        this(new BigDecimal(amount));
    }
    
    public Money(long amount) {
        this(new BigDecimal(amount));
    }

    public BigDecimal getAmount() { return amount; }

    public boolean isNegative() {
        return amount.compareTo(BigDecimal.ZERO) < 0;
    }

    public Money negate() {
        return new Money(amount.negate());
    }

    public Money add(Money amount) {
        return new Money(this.getAmount().add(amount.getAmount()));
    }

    public Money subtract(Money amount) {
        return this.add(new Money(amount.getAmount().negate()));
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getAmount().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Money other = (Money) obj;
        return getAmount().equals(other.getAmount());
    }
}
