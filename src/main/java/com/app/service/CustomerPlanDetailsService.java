package com.app.service;

import java.util.List;

import com.app.model.CustomerPlanDetails;

public interface CustomerPlanDetailsService {

	public CustomerPlanDetails activatePlanForCustomer(CustomerPlanDetails customerPlanDetails);
	public CustomerPlanDetails updatetePlanForCustomer(CustomerPlanDetails customerPlanDetails);
	public void removeActivePlan(long cutomerPlanId);
	public List<CustomerPlanDetails> getCustomePlanDetails();

}
