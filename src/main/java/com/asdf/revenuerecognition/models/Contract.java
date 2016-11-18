package com.asdf.revenuerecognition.models;

import com.asdf.revenuerecognition.util.Money;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class Contract extends AbstractModel {
	
	private Product product;
	private Money revenue;
	private GregorianCalendar whenSigned;
	//private Long id;
	
	private List<RevenueRecognition> recognitions = new ArrayList<>();
	
	public Contract(Product product, Money revenue, GregorianCalendar date){
		this.product = product;
		this.revenue = revenue;
		this.whenSigned = date;
	}
	
	public Money recognizedRevenue(GregorianCalendar date){
		if(recognitions.size()==0) calculateRecognitions();
		Money result = Money.dollars(0);
		Iterator<RevenueRecognition> it = recognitions.iterator();
		while(it.hasNext()){
			RevenueRecognition r = (RevenueRecognition) it.next();
			//System.out.println("In Conract.RecognizedRevenue amount: "+ r.getAmount().getAmount()+
			//		r.getDate().getTime());
			//System.out.println(r.isRecognizableBy(date));
			if(r.isRecognizableBy(date)){
				result = result.add(r.getAmount());
			}
		}
		return result;
	}

	

	public void addRevenueRecognition(RevenueRecognition revenueRecognition) {
		//System.out.println("In Contract.addRevenueRecognition amount: " + revenueRecognition.getAmount().getAmount()+"date:"+ revenueRecognition.getDate().getTime());
		recognitions.add(revenueRecognition);
		/*Iterator<RevenueRecognition> it = recognitions.iterator();
		while(it.hasNext()){
			RevenueRecognition r = (RevenueRecognition) it.next();
			System.out.println("In Conract.addRevenueRecognition amount: "+ r.getAmount().getAmount()+
					r.getDate().getTime());
			//System.out.println(r.isRecognizableBy(date));
		}*/
		
		
	}
	
	public void calculateRecognitions(){
		product.calculateRevenueRecognition(this);
	}

	public Money getRevenue() {
		// TODO Auto-generated method stub
		return revenue;
	}
	
	public GregorianCalendar getWhenSigned() {
		// TODO Auto-generated method stub
		return whenSigned;
	}
	
}
