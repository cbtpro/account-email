package com.lieqihezi.maonaiyi.account.email.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.lieqihezi.maonaiyi.account.email.AccountEmailException;
import com.lieqihezi.maonaiyi.account.email.AccountEmailService;

public class AccountEmailServiceImpl implements AccountEmailService {

	private JavaMailSender javaMailSender;
	
	private String systemEmail;
	
	public void sendMail(String to, String subject, String htmlText) throws AccountEmailException {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage);
			
			msgHelper.setFrom(systemEmail);
			msgHelper.setTo(to);
			msgHelper.setSubject(subject);
			msgHelper.setText(htmlText, true);
			
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new AccountEmailException("Faild to send mail. ", e);
		}
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public String getSystemEmail() {
		return systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}
	
}
