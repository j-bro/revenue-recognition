package com.asdf.revenuerecognition.strategies;

import soen387.common.DateCalculator;
import soen387.common.Money;
import soen387.domainlogic.domainmodel.domainmodel.Contract;
import soen387.domainlogic.domainmodel.domainmodel.RevenueRecognition;

public class ThreeWayRecognitionStategy extends RecoginitionStrategy {
	
	private int firstRecognitionOffset;
	private int secondRecognitionOffset;
	

	public ThreeWayRecognitionStategy(int i, int j) {
		firstRecognitionOffset = i;
		secondRecognitionOffset = j;
	}

	@Override
	public void calculateRevenueRecognitions(Contract contract) {
		Money[] allocation = contract.getRevenue().allocation(3);
		contract.addRevenueRecognition(new RevenueRecognition(allocation[0], contract.getWhenSigned()));
		contract.addRevenueRecognition(new RevenueRecognition(allocation[1], DateCalculator.addDay(contract.getWhenSigned(), firstRecognitionOffset)));
		contract.addRevenueRecognition(new RevenueRecognition(allocation[2], DateCalculator.addDay(contract.getWhenSigned(), secondRecognitionOffset)));
		
	}

}
