package com.graduation.furniture.controller;

import com.graduation.furniture.dto.EmailDetails;
import com.graduation.furniture.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/payment-success")
    public String sendMail(@RequestBody EmailDetails details) {
        return emailService.sendMailPaymentSuccess(details);
    }

    @PostMapping("/sign-up-success")
    public String sendMailSignUpSuccess(@RequestBody EmailDetails details) {
        return emailService.sendMailSignUpSuccess(details);
    }

}
