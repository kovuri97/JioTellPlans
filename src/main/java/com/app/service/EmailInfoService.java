package com.app.service;

import java.util.List;

import com.app.model.EmailsInfo;

public interface EmailInfoService {
	
	public void addEmailInfo(EmailsInfo emailsInfo);
	public List<EmailsInfo> getEmailInfo(int id);
	
}
