package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.EmailsInfo;
import com.app.repo.EmailInfoRepo;
@Service
public class EmailInforServiceImpl implements EmailInfoService {
	@Autowired
	private EmailInfoRepo emailInfoRepo;

	@Override
	public void addEmailInfo(EmailsInfo emailsInfo) {
		// TODO Auto-generated method stub
		emailInfoRepo.save(emailsInfo);
	}

	@Override
	public List<EmailsInfo> getEmailInfo(int id) {
		// TODO Auto-generated method stub
		return emailInfoRepo.findAll();
	}

}
