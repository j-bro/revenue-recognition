package com.asdf.revenuerecognition.strategies;


import com.asdf.revenuerecognition.models.Contract;
import com.asdf.revenuerecognition.models.RevenueRecognition;

public class CompleteRecognitionStategy extends RecoginitionStrategy {

	@Override
	public void calculateRevenueRecognitions(Contract contract) {
		contract.addRevenueRecognition(new RevenueRecognition(contract.getRevenue(), contract.getWhenSigned()));
		
	}

}
