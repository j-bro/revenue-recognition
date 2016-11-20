package com.asdf.revenuerecognition.beans;

import com.asdf.revenuerecognition.util.Money;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class RevenueRecognitionBean extends AbstractBean {
	
	private Money amount;
	private GregorianCalendar date;
    private Long contractId;

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * No-arg constructor.
     */
	public RevenueRecognitionBean() {}

    /**
     *
     * @param amount
     * @param date
     */
	public RevenueRecognitionBean(Money amount, GregorianCalendar date){
		this.amount = amount;
		this.date = date; 
	}

    /**
     *
     * @return
     */
	public Money getAmount(){
		return amount;
	}

    /**
     *
     * @param amount
     */
    public void setAmount(Money amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     */
	public GregorianCalendar getDate(){
		return date;
	}

    /**
     *
     * @param date
     */
    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public String getDateString() {
        return format.format(this.date.getTime());
    }

    /**
     *
     * @return
     */
    public Long getContractId() {
        return contractId;
    }

    /**
     *
     * @param contractId
     */
    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    /**
     *
     * @param date
     * @return
     */
	boolean isRecognizableBy(GregorianCalendar date){
		return date.after(this.date) || this.date.equals(date);
	}

}
