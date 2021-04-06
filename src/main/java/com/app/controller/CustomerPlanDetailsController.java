package com.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.model.Customer;
import com.app.model.CustomerPlanDetails;
import com.app.model.CutomerPlanFraming;
import com.app.model.EmailsInfo;
import com.app.service.CustomerPlanDetailsService;
import com.app.service.EmailInfoService;
import com.google.gson.Gson;

import java.time.temporal.ChronoUnit;


@RestController
@RequestMapping("/Plans")
public class CustomerPlanDetailsController {

	@Autowired
	private CustomerPlanDetailsService customerPlanDetailsService;
	
	@Autowired
	private EmailInfoService emailInfoService;

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
				if(values.getCustomerId()==cutomerPlanFraming.getCustomerId()) {
					//Should write  validations only inside for loop
					return ResponseEntity.ok().body("Selected customer already have the active plan");
				} 
			}
			customerPlanDetailsService.activatePlanForCustomer(customerPlanDetails);
			sendEmailOnRecharge(customerPlanDetails);
			return ResponseEntity.ok().body(customerPlanDetails);
		}
		else {
			customerPlanDetailsService.activatePlanForCustomer(customerPlanDetails);
			sendEmailOnRecharge(customerPlanDetails);
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

						if(difference == 0) {
							sendEmail(customerPlanDetails1);
						}

						return ResponseEntity.ok().body(customerPlanDetails1);
					}
					else {
						customerPlanDetailsService.removeActivePlan(values.getCustomerPlanId());
						sendEmailOnExpired(customerPlanDetails1);
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
	public void sendEmail(CustomerPlanDetails customerPlanDetails) {
		String receiverEmailId = setEmailInfo(customerPlanDetails, "active");
		
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost("smtp.gmail.com");
		javaMailSenderImpl.setPort(587);
		Properties props = javaMailSenderImpl.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("neelimaTesting1234@gmail.com", "Testing@123");
			}
		});

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(receiverEmailId);//sai10cp246//prashanthyenugula1993
		msg.setSubject("Jio Plan Alert");
		msg.setText("Dear Customer,\n\nYour Active Jio Plan will expired tomorrow.\nPlan details: \n"+(new Gson().toJson(customerPlanDetails))+"\n\n If you want to continue your Jio benefits, please recharge immediatly.\n\n Thanks & Regards,\n Jio Team.");
		javaMailSenderImpl.setSession(session);
		javaMailSenderImpl.send(msg);
	}
	public void sendEmailOnExpired(CustomerPlanDetails customerPlanDetails) {
		String receiverEmailId = setEmailInfo(customerPlanDetails, "Expired");
		
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost("smtp.gmail.com");
		javaMailSenderImpl.setPort(587);
		Properties props = javaMailSenderImpl.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("neelimaTesting1234@gmail.com", "Testing@123");
			}
		});

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(receiverEmailId);//sai10cp246//prashanthyenugula1993
		msg.setSubject("Jio Plan Alert");
		msg.setText("Dear Customer,\n\nYour Active Jio Plan is expired.\nPlan details: \n"+(new Gson().toJson(customerPlanDetails))+"\n\n If you want to continue your Jio benefits, please recharge immediatly.\n\n Thanks & Regards,\n Jio Team.");
		javaMailSenderImpl.setSession(session);
		javaMailSenderImpl.send(msg);
	}

	public void sendEmailOnRecharge(CustomerPlanDetails customerPlanDetails) {
		String receiverEmailId = setEmailInfo(customerPlanDetails, "Active");
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost("smtp.gmail.com");
		javaMailSenderImpl.setPort(587);
		Properties props = javaMailSenderImpl.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("neelimaTesting1234@gmail.com", "Testing@123");
			}
		});

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(receiverEmailId);//sai10cp246//prashanthyenugula1993
		msg.setSubject("Jio Plan Alert");
		msg.setText("Dear Customer,\n\nYou are successfully recharged for below Jio plan.\nPlan details: \n"+(new Gson().toJson(customerPlanDetails))+"\n\n Enjoy your Jio Plans.\n\n Thanks & Regards,\n Jio Team.");
		javaMailSenderImpl.setSession(session);
		javaMailSenderImpl.send(msg);
	}
	
	public String setEmailInfo(CustomerPlanDetails customerPlanDetails, String status) {
		String baseUrl = "http://localhost:5003/customer/getProfile/"+customerPlanDetails.getCustomerId();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<?> response=restTemplate.exchange(baseUrl,HttpMethod.GET,entity,String.class);
		Gson gson1 = new Gson();
		Customer customer  = gson1.fromJson((String) response.getBody(),
				Customer.class); //= (Customer) response.getBody();
		EmailsInfo emailsInfo = new EmailsInfo();
		emailsInfo.setCustomerId(customerPlanDetails.getCustomerId());
		emailsInfo.setPlanId(customerPlanDetails.getPlanId());
		emailsInfo.setDate(LocalDate.now().toString());
		emailsInfo.setEmailId(customer.getEmailId());
		emailsInfo.setFullname(customer.getFullName());
		emailsInfo.setPlanStatus(status);
		emailInfoService.addEmailInfo(emailsInfo);
		return customer.getEmailId();
	}

} 
