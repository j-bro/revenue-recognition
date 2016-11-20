package com.asdf.revenuerecognition.beans;

import com.asdf.revenuerecognition.strategies.RecoginitionStrategy;
import com.asdf.revenuerecognition.util.Money;

import java.util.GregorianCalendar;
import java.util.List;

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
	 * @param revenue
	 * @param whenSigned
	 * @return
	 */
	public List<RevenueRecognitionBean> calculateRevenueRecognition(Money revenue, GregorianCalendar whenSigned) {
		return recognitionStrategy.calculateRevenueRecognitions(revenue, whenSigned);
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

    /**
     *
     * @return
     */
    public RecoginitionStrategy getRecognitionStrategy() {
        return recognitionStrategy;
    }

    /**
     *
     * @param recognitionStrategy
     */
    public void setRecognitionStrategy(RecoginitionStrategy recognitionStrategy) {
        this.recognitionStrategy = recognitionStrategy;
    }
}
