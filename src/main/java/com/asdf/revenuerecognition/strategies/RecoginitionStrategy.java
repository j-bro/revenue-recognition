package com.asdf.revenuerecognition.strategies;


import com.asdf.revenuerecognition.beans.RevenueRecognitionBean;
import com.asdf.revenuerecognition.util.Money;

import java.util.GregorianCalendar;
import java.util.List;

public abstract class RecoginitionStrategy {

	public abstract String getName();
	
	public abstract List<RevenueRecognitionBean> calculateRevenueRecognitions(Money revenue, GregorianCalendar whenSigned);

}
