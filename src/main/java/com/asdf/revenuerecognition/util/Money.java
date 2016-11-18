package com.asdf.revenuerecognition.util;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class Money {
	
	private long amount;
	private Currency currency;
	
	private static final int[] cents = new int[]{1, 10, 100, 1000};

	/**
	 *
	 * @param amount
	 * @param currency
	 */
	public Money(double amount, Currency currency) {
		this.currency = currency;
		this.amount = Math.round(amount * centFactor());
	}

	/**
	 *
	 * @param amount
	 * @param currency
	 */
	public Money(long amount, Currency currency) {
		this.currency = currency;
		this.amount = amount * centFactor();
	}

	/**
	 * No-arg constructor.
	 */
	public Money() {
		
	}

	/**
	 *
	 * @param amount
	 * @param currency
	 * @param roundingMode
	 */
	public Money(BigDecimal amount, Currency currency, int roundingMode) {
		amount = amount.multiply(new BigDecimal(cents[currency.getDefaultFractionDigits()]));
		this.amount = amount.longValue();
		this.currency = currency;
	}

	/**
	 *
	 * @return
	 */
	private int centFactor() {
		return cents[currency.getDefaultFractionDigits()];
	}

	/**
	 *
	 * @return
	 */
	public Currency currency(){
		return currency;
	}

	/**
	 *
	 * @param amount
	 * @return
	 */
	public static Money dollars(double amount) {
		return new Money(amount, Currency.getInstance(Locale.CANADA_FRENCH));
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal amount(){
		return BigDecimal.valueOf(amount, currency.getDefaultFractionDigits());
	}


	/**
	 * make it as a bean
	 */
	public BigDecimal getAmount(){
		return BigDecimal.valueOf(amount, currency.getDefaultFractionDigits());
	}

	/**
	 *
	 * @param arg
	 * @return
	 */
	private boolean isSameCurrencyAs(Money arg) {
		return currency.equals(arg.currency);
	}

	/**
	 *
	 * @param other
	 * @return
	 */
	public Money add(Money other) {
		isSameCurrencyAs(other);
		return newMoney(amount + other.amount);
	}

	/**
	 *
	 * @param other
	 * @return
	 */
	public Money subtract(Money other) {
		isSameCurrencyAs(other);
		return newMoney(amount - other.amount);
	}

	/**
	 *
	 * @param amount
	 * @return
	 */
	public Money multiply(double amount) {
		return multiply(new BigDecimal(amount));
	}

	/**
	 *
	 * @param amount
	 * @return
	 */
	private Money multiply(BigDecimal amount) {
		return multiply(amount, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 *
	 * @param amount
	 * @param roundingMode
	 * @return
	 */
	private Money multiply(BigDecimal amount, int roundingMode) {
		return new Money(amount().multiply(amount), currency, roundingMode);
	}

	/**
	 *
	 * @param other
	 * @return
	 */
	public int compareTo(Object other) {
		return compareTo((Money)other);
	}

	/**
	 *
	 * @param other
	 * @return
	 */
	public int compareTo(Money other) {
		isSameCurrencyAs(other);
		if (amount < other.amount) return -1;
		else if (amount == other.amount) return 0;
		else return 1;
	}

	/**
	 *
	 * @param other
	 * @return
	 */
	public boolean equals(Object other){
		return (other instanceof Money) && (equals((Money)other));
	}

	/**
	 *
	 * @param other
	 * @return
	 */
	public boolean equals(Money other){
		return currency.equals(other.currency) && (amount == other.amount);
	}


	/**
	 *
	 * @return
	 */
	public int hashCode() {
		return (int) (amount ^ (amount >>> 32));
	}

	/**
	 *
	 * @param n
	 * @return
	 */
	public Money[] allocation(int n) {
		Money lowResult = newMoney(amount/n);
		Money highResult = newMoney(lowResult.amount +1);
		Money[] results = new Money[n];
		int remainder = (int) amount %n;
		for(int i=0; i <remainder; i++) results[i]= highResult;
		for(int i = remainder; i <n; i++) results[i] = lowResult;
		return results;
	}

	/**
	 *
	 * @param amount
	 * @return
	 */
	private Money newMoney(long amount){
		Money money = new Money();
		money.currency = this.currency;
		money.amount = amount;
		return money;
	}

}
