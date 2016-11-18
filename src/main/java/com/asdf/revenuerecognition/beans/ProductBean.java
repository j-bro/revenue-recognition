package com.asdf.revenuerecognition.beans;

import com.asdf.revenuerecognition.strategies.RecoginitionStrategy;

public class ProductBean extends AbstractBean {

	private String name;

	private RecoginitionStrategy recognitionStrategy;

	/**
	 * No-arg constructor.
	 */
	public ProductBean() {}

	/**
	 *
	 * @param name
	 * @param strategy
	 */
	public ProductBean(String name, RecoginitionStrategy strategy){
		this.setName(name);
		this.recognitionStrategy = strategy;
	}

	/**
	 *
	 * @param contract
	 */
	public void calculateRevenueRecognition(ContractBean contract) {
		recognitionStrategy.calculateRevenueRecognitions(contract);
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
