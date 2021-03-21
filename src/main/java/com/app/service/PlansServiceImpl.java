package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Plans;
import com.app.repo.PlansRepository;
@Service
public class PlansServiceImpl implements PlansService{
	@Autowired
	private PlansRepository plansRepo;

	@Override
	public List<Plans> getPlansDetails() {
		// TODO Auto-generated method stub
		return plansRepo.findAll();
	}

	@Override
	public Plans addPlanDetails(Plans plan) {
		// TODO Auto-generated method stub
		return plansRepo.save(plan);
	}

	@Override
	public Plans updatePlanDetails(Plans plan) {
		// TODO Auto-generated method stub
		return plansRepo.save(plan);
	}

	@Override
	public void deletPlan(int id) {
		// TODO Auto-generated method stub
		plansRepo.deleteById(id);
	}
	

}
