package com.graduation.furniture.service;

import com.graduation.furniture.dto.EmailDetails;

public interface EmailService {
	String sendMailSignUpSuccess(EmailDetails details);

	String sendMailPaymentSuccess(EmailDetails details);

	String sendMailForgotPassword(String email, String url);
}
