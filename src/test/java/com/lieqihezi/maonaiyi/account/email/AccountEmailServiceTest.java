package com.lieqihezi.maonaiyi.account.email;

import static junit.framework.Assert.assertEquals;

import javax.mail.Message;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;

public class AccountEmailServiceTest {
   
	private GreenMail greenMail;
	private ApplicationContext ctx;
	
	@Before
	public void startMailServer() throws Exception {
		greenMail = new GreenMail(ServerSetup.SMTP);
		greenMail.setUser("lieqihezi@163.com", "cbt403857346");
		greenMail.start();
	}
	
	@Test
	public void testSendMail()
		throws Exception
	{
		ctx = new ClassPathXmlApplicationContext("account-email.xml");
		AccountEmailService accountEmailService = (AccountEmailService) ctx.getBean("accountEmailService");
		
		String subject = "Test Subject";
		String htmlText = "<h3>Test </h3>";
		accountEmailService.sendMail("cbtpro@163.com", subject, htmlText);
		
		greenMail.waitForIncomingEmail(10000, 1);
		
		Message[] msgs = greenMail.getReceivedMessages();
		assertEquals(1, msgs.length);
		assertEquals(subject, msgs[0].getSubject());
		assertEquals(htmlText, GreenMailUtil.getBody(msgs[0]).trim());
	}
	
	@After
	public void stopMailServer()
		throws Exception
	{
		greenMail.stop();
	}
}
