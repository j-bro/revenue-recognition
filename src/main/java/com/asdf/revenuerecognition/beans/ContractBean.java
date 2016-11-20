package com.asdf.revenuerecognition.beans;

import com.asdf.revenuerecognition.util.Money;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ContractBean extends AbstractBean {
	
	private ProductBean product;
	private Money revenue;
	private GregorianCalendar whenSigned;

	private List<RevenueRecognitionBean> recognitions = new ArrayList<>();

    /**
     * No-arg constructor.
     */
    public ContractBean() {}

	public ContractBean(ProductBean product, Money revenue, GregorianCalendar date) {
		this.product = product;
		this.revenue = revenue;
		this.whenSigned = date;
	}
	
	public Money getRecognizedRevenue(GregorianCalendar date) {
		if(recognitions.isEmpty()) {
            calculateRecognitions();
        }

        Money result = Money.dollars(0);
        for (RevenueRecognitionBean r : recognitions) {
			if (r.isRecognizableBy(date)) {
				result = result.add(r.getAmount());
			}
		}
		return result;
	}

	public void addRevenueRecognition(RevenueRecognitionBean revenueRecognition) {
		recognitions.add(revenueRecognition);
	}

	public void calculateRecognitions() {
        List<RevenueRecognitionBean> revenueRecognitions =
                product.calculateRevenueRecognition(this.getRevenue(), this.getWhenSigned());
		revenueRecognitions.forEach(r -> r.setContractId(this.getId()));
		revenueRecognitions.forEach(this::addRevenueRecognition);
	}

	public Money getRevenue() {
		return revenue;
	}

	public void setRevenue(Money revenue) {
		this.revenue = revenue;
	}

	public GregorianCalendar getWhenSigned() {
		return whenSigned;
	}

	public void setWhenSigned(GregorianCalendar whenSigned) {
		this.whenSigned = whenSigned;
	}

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public List<RevenueRecognitionBean> getRecognitions() {
        return recognitions;
    }

    public void setRecognitions(List<RevenueRecognitionBean> recognitions) {
        this.recognitions = recognitions;
    }
}
