package com.asdf.revenuerecognition.strategies;


import com.asdf.revenuerecognition.beans.ContractBean;
import com.asdf.revenuerecognition.beans.RevenueRecognition;
import com.asdf.revenuerecognition.util.DateCalculator;
import com.asdf.revenuerecognition.util.Money;

public class ThreeWayRecognitionStategy extends RecoginitionStrategy {
	
	private int firstRecognitionOffset;
	private int secondRecognitionOffset;
	

	public ThreeWayRecognitionStategy(int i, int j) {
		firstRecognitionOffset = i;
		secondRecognitionOffset = j;
	}

	@Override
	public void calculateRevenueRecognitions(ContractBean contract) {
		Money[] allocation = contract.getRevenue().allocation(3);
		contract.addRevenueRecognition(new RevenueRecognition(allocation[0], contract.getWhenSigned()));
		contract.addRevenueRecognition(new RevenueRecognition(allocation[1],
				DateCalculator.addDay(contract.getWhenSigned(), firstRecognitionOffset)));
		contract.addRevenueRecognition(new RevenueRecognition(allocation[2],
				DateCalculator.addDay(contract.getWhenSigned(), secondRecognitionOffset)));
		
	}

}
