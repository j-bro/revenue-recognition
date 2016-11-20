package com.asdf.revenuerecognition.strategies;


import com.asdf.revenuerecognition.beans.RevenueRecognitionBean;
import com.asdf.revenuerecognition.util.Money;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CompleteRecognitionStategy extends RecoginitionStrategy {

	private static final String STRATEGY_NAME = "Complete Recognition Strategy";

	@Override
	public String getName() {
		return STRATEGY_NAME;
	}

	@Override
	public List<RevenueRecognitionBean> calculateRevenueRecognitions(Money revenue, GregorianCalendar whenSigned) {
        List<RevenueRecognitionBean> revenueRecognitionList = new ArrayList<>();
		revenueRecognitionList.add(new RevenueRecognitionBean(revenue, whenSigned));
        return revenueRecognitionList;
	}

}
