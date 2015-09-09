package com.lieqihezi.maonaiyi.account.email;

import javax.mail.MessagingException;

public class AccountEmailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3657549802365728668L;

	public AccountEmailException(String string, MessagingException e) {
		super(string, e);
	}
	
}
