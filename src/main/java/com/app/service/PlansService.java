package com.app.service;

import java.util.List;

import com.app.model.Plans;

public interface PlansService {

	public List<Plans> getPlansDetails();
	public Plans addPlanDetails(Plans plan);
	public Plans updatePlanDetails(Plans plan);
	public void deletPlan(int id);

}
