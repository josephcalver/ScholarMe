package com.josephcalver.service;

import java.util.Date;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${mail.enable}")
	private Boolean enable;

	private void send(MimeMessagePreparator preparator) {
		
		if (enable) {
			mailSender.send(preparator);
		}
		
	}

	public void sendVerificationEmail(String emailAddress, String token) {

		// Implement Thymeleaf email template here, and remember to add "token"
		// to the model so that I can embed in email

		StringBuilder sb = new StringBuilder();

		sb.append("<HTML>");
		sb.append("<p>Hello there, this is <strong>HTML</strong></p>");
		sb.append("<HTML>");

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

				message.setTo(emailAddress);
				message.setFrom(new InternetAddress("no-reply@josephcalver.com"));
				message.setSubject("Please verify your email address");
				message.setSentDate(new Date());

				message.setText(sb.toString(), true);
			}

		};

		send(preparator);
	}

}
