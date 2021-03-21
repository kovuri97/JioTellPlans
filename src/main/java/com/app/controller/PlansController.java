package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Plans;
import com.app.service.PlansService;

@RestController
@RequestMapping("/JioPlans")
public class PlansController {
	@Autowired
	private PlansService planService;
	@GetMapping("/PlansDetails")
	public ResponseEntity<?> getPlans(){

		List<Plans> plansList = planService.getPlansDetails();
		if(plansList.size()!=0) {
			return ResponseEntity.ok().body(plansList);
		}
		else {
			return ResponseEntity.ok().body("Plans are not available");
		}
	}
	@PostMapping("/addPlan")
	public ResponseEntity<?> addPlan(@RequestBody Plans plan){

		List<Plans> planList = planService.getPlansDetails();
		if(!planList.contains(plan)) {
			Plans plans = planService.addPlanDetails(plan);
			return ResponseEntity.ok().body(plans);
		}
		else {
			return ResponseEntity.ok().body("Adding plan is not successfull");
		}
	}
	
	@PutMapping("/updatePlan")
	public ResponseEntity<?> updatePlan(@RequestBody Plans plan){

		List<Plans> planList = planService.getPlansDetails();
		if(planList.contains(plan)) {
			Plans plans = planService.updatePlanDetails(plan);
			return ResponseEntity.ok().body(plans);
		}
		else {
			return ResponseEntity.ok().body("Updating plan is not successfull");
		}
	}
	
	@DeleteMapping("/deletePlan/{id}")
	public ResponseEntity<?> deletePlan(@PathVariable int id){

		List<Plans> planList = planService.getPlansDetails();
		int check=0;
		for(Plans plan:planList) {
			if(plan.getPlanId()==id) {
				check=1;
			}
		}
		if(check==1) {
			planService.deletPlan(id);
			return ResponseEntity.ok().body("Plan Details are successfully Deleted");
		}
		else {
			return ResponseEntity.ok().body("Deletion of plan is unSuccessfull");
		}
	}
}
