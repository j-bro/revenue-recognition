package com.asdf.revenuerecognition.models;

import com.asdf.revenuerecognition.util.Money;

import java.util.GregorianCalendar;

public class RevenueRecognition {
	
	private Money amount;
	private GregorianCalendar date;
	
	public RevenueRecognition(Money amount, GregorianCalendar date){
		this.amount = amount;
		this.date = date; 
	}
	
	public Money getAmount(){
		return amount;
	}
	
	public GregorianCalendar getDate(){
		return date;
	}
	
	boolean isRecognizableBy(GregorianCalendar date){
		return date.after(this.date) || this.date.equals(date);
	}

}
