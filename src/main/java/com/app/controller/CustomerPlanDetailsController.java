package com.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CustomerPlanDetails;
import com.app.model.CutomerPlanFraming;
import com.app.service.CustomerPlanDetailsService;

import java.time.temporal.ChronoUnit;


@RestController
@RequestMapping("/Plans")
public class CustomerPlanDetailsController {
	@Autowired
	private CustomerPlanDetailsService customerPlanDetailsService;

	@PostMapping("/ActivatePlan")
	public ResponseEntity<?> activePlan(@RequestBody CutomerPlanFraming cutomerPlanFraming){
		//int bookedPlansCount =1;
		LocalDate date = LocalDate.now();
		CustomerPlanDetails customerPlanDetails = new CustomerPlanDetails();
		customerPlanDetails.setCustomerId(cutomerPlanFraming.getCustomerId());
		customerPlanDetails.setPlanId(cutomerPlanFraming.getPlanId());
		customerPlanDetails.setPlanCategory(cutomerPlanFraming.getPlanCategory());
		customerPlanDetails.setPrice(cutomerPlanFraming.getPrice());
		customerPlanDetails.setPlanValidity(cutomerPlanFraming.getPlanValidity());
		customerPlanDetails.setPlanData(cutomerPlanFraming.getPlanData());
		customerPlanDetails.setDataSpeed(cutomerPlanFraming.getDataSpeed());
		customerPlanDetails.setVoice(cutomerPlanFraming.getVoice());
		customerPlanDetails.setSms(cutomerPlanFraming.getSms());
		customerPlanDetails.setOtherSubscription(cutomerPlanFraming.getOtherSubscription());
		String[] validity = cutomerPlanFraming.getPlanValidity().split(" ");
		customerPlanDetails.setPlanAvailableDays(Long.parseLong(validity[0]));
		customerPlanDetails.setStatus("Active");
		customerPlanDetails.setPlanCount(1);
		customerPlanDetails.setDate(date.toString());
		List<CustomerPlanDetails> customerPlanDetailsList = customerPlanDetailsService.getCustomePlanDetails(); 
		if(customerPlanDetailsList.size()!=0) {
			for(CustomerPlanDetails values: customerPlanDetailsList) {
				if(values.getCustomerId()!=cutomerPlanFraming.getCustomerId()) {
					customerPlanDetailsService.activatePlanForCustomer(customerPlanDetails);
					return ResponseEntity.ok().body(customerPlanDetails);
				} else {
					return ResponseEntity.ok().body("Selected customer already have the active plan");
				}
			}
			return ResponseEntity.ok().body("Customer not existed");
		}
		else {
			customerPlanDetailsService.activatePlanForCustomer(customerPlanDetails);
			return ResponseEntity.ok().body(customerPlanDetails);
			}
	}

	@GetMapping("/getCustomerPlanDetails/{id}")
	public ResponseEntity<?> getDetails(@PathVariable int id){
		CustomerPlanDetails customerPlanDetails1 = new CustomerPlanDetails();
		List<CustomerPlanDetails> customerPlanDetailsList = customerPlanDetailsService.getCustomePlanDetails();
		if(customerPlanDetailsList.size()!=0) {
			for(CustomerPlanDetails values: customerPlanDetailsList) {
				if(values.getCustomerId()==id) { 
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");;
					LocalDate rechargeDate = LocalDate.parse(values.getDate(), formatter);
					LocalDate todaysDate = LocalDate.now();
					long noOfDaysBetween = ChronoUnit.DAYS.between(rechargeDate, todaysDate);
					String[] validity = values.getPlanValidity().split(" ");
					long difference = Long.parseLong(validity[0])-noOfDaysBetween ;
					if(difference >= 0) {
						customerPlanDetails1.setCustomerPlanId(values.getCustomerPlanId());
						customerPlanDetails1.setCustomerId(values.getCustomerId());
						customerPlanDetails1.setPlanId(values.getPlanId());
						customerPlanDetails1.setPlanCategory(values.getPlanCategory());
						customerPlanDetails1.setPrice(values.getPrice());
						customerPlanDetails1.setPlanValidity(values.getPlanValidity());
						customerPlanDetails1.setPlanData(values.getPlanData());
						customerPlanDetails1.setDataSpeed(values.getDataSpeed());
						customerPlanDetails1.setVoice(values.getVoice());
						customerPlanDetails1.setSms(values.getSms());
						customerPlanDetails1.setOtherSubscription(values.getOtherSubscription());
						customerPlanDetails1.setStatus(values.getStatus());
						customerPlanDetails1.setPlanCount(values.getPlanCount());
						customerPlanDetails1.setDate(values.getDate());
						customerPlanDetails1.setPlanAvailableDays(difference);
						customerPlanDetailsService.updatetePlanForCustomer(customerPlanDetails1);
						return ResponseEntity.ok().body(customerPlanDetails1);
					}
					else {
						customerPlanDetailsService.removeActivePlan(values.getCustomerPlanId());
						return ResponseEntity.ok().body("No active plans");
					}
				}
				else {
					return ResponseEntity.ok().body("No active plans");	
				}
			}
			return ResponseEntity.ok().body(customerPlanDetails1);
		}
		else {
			return ResponseEntity.ok().body("No active plans");	
		}
	}
}
