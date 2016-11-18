package com.asdf.revenuerecognition.models;


import com.asdf.revenuerecognition.strategies.CompleteRecognitionStategy;
import com.asdf.revenuerecognition.strategies.RecoginitionStrategy;
import com.asdf.revenuerecognition.strategies.ThreeWayRecognitionStategy;

public class Product extends AbstractModel {
	private String name;
	private RecoginitionStrategy recognitionStrategy;
	
	public Product(String name, RecoginitionStrategy strategy){
		this.setName(name);
		this.recognitionStrategy = strategy;
	}
	
	public static Product newWordProcessor(String name){
		return new Product(name, new CompleteRecognitionStategy());
	}
	
	public static Product newSpreadSheet(String name){
		return new Product(name, new ThreeWayRecognitionStategy(60, 90));
	}
	
	public static Product newDatabase(String name){
		return new Product(name, new ThreeWayRecognitionStategy(30, 60));
	}

	public void calculateRevenueRecognition(Contract contract) {
		recognitionStrategy.calculateRevenueRecognitions(contract);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
