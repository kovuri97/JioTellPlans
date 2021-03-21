package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.CustomerPlanDetails;
import com.app.repo.CustomePlanDetailsRepo;
@Service
public class CustomerPlanDetailsServiceIml implements CustomerPlanDetailsService{
	
	@Autowired
	private CustomePlanDetailsRepo customerPlanDetailsRepo;

	@Override
	public CustomerPlanDetails activatePlanForCustomer(CustomerPlanDetails customerPlanDetails) {
		// TODO Auto-generated method stub
		return customerPlanDetailsRepo.save(customerPlanDetails);
	}

	@Override
	public void removeActivePlan(long cutomerPlanId) {
		// TODO Auto-generated method stub
		customerPlanDetailsRepo.deleteById(cutomerPlanId);
	}

	@Override
	public List<CustomerPlanDetails> getCustomePlanDetails() {
		// TODO Auto-generated method stub
		return customerPlanDetailsRepo.findAll();
	}

	@Override
	public CustomerPlanDetails updatetePlanForCustomer(CustomerPlanDetails customerPlanDetails) {
		// TODO Auto-generated method stub
		return customerPlanDetailsRepo.save(customerPlanDetails);
	}

}
