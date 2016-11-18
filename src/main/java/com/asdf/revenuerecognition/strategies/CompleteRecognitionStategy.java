package com.asdf.revenuerecognition.strategies;


import com.asdf.revenuerecognition.beans.ContractBean;
import com.asdf.revenuerecognition.beans.RevenueRecognition;

public class CompleteRecognitionStategy extends RecoginitionStrategy {

	@Override
	public void calculateRevenueRecognitions(ContractBean contract) {
		contract.addRevenueRecognition(new RevenueRecognition(contract.getRevenue(), contract.getWhenSigned()));
		
	}

}
