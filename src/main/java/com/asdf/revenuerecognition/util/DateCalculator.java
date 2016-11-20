package com.asdf.revenuerecognition.util;

import java.util.GregorianCalendar;


public class DateCalculator {
	
	public static java.util.Date addDay(java.util.Date date, int n){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DATE, n);
		return calendar.getTime();
		
	}
	
	public static GregorianCalendar addDay(GregorianCalendar date, int n){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date.getTime());
		calendar.add(GregorianCalendar.DATE, n);
		return calendar;
		
	}
	
	public static java.sql.Date dateTransform(java.util.Date utilDate ){
		return  new java.sql.Date(utilDate.getTime());
	}
	
	public void dateTransform() {
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    System.out.println("utilDate:" + utilDate);
	    System.out.println("sqlDate:" + sqlDate);
	}

}
