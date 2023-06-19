package com.graduation.furniture.service;

import com.graduation.furniture.dto.EmailDetails;
import com.graduation.furniture.dto.EmailPaymentSuccess;

public interface EmailService {
	String sendMailSignUpSuccess(EmailDetails details);

	String sendMailPaymentSuccess(EmailPaymentSuccess details);

	String sendMailForgotPassword(String email, String url);
	String sendOTPSignUp(EmailDetails details);

}
