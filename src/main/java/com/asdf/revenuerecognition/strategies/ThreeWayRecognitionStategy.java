package com.asdf.revenuerecognition.strategies;


import com.asdf.revenuerecognition.beans.RevenueRecognitionBean;
import com.asdf.revenuerecognition.util.DateCalculator;
import com.asdf.revenuerecognition.util.Money;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ThreeWayRecognitionStategy extends RecoginitionStrategy {
	
	private int firstRecognitionOffset;
	private int secondRecognitionOffset;

	private static final String STRATEGY_NAME = "Three Way Recognition Strategy";

	public ThreeWayRecognitionStategy(int i, int j) {
		firstRecognitionOffset = i;
		secondRecognitionOffset = j;
	}

	@Override
	public String getName() {
		return STRATEGY_NAME;
	}

	@Override
	public List<RevenueRecognitionBean> calculateRevenueRecognitions(Money revenue, GregorianCalendar whenSigned) {
        List<RevenueRecognitionBean> revenueRecognitionList = new ArrayList<>();

		Money[] allocation = revenue.allocation(3);
		revenueRecognitionList.add(new RevenueRecognitionBean(allocation[0], whenSigned));
        revenueRecognitionList.add(new RevenueRecognitionBean(allocation[1],
				DateCalculator.addDay(whenSigned, firstRecognitionOffset)));
        revenueRecognitionList.add(new RevenueRecognitionBean(allocation[2],
				DateCalculator.addDay(whenSigned, secondRecognitionOffset)));

        return revenueRecognitionList;
	}

}
