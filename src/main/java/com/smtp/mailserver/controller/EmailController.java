package com.smtp.mailserver.controller;

import com.smtp.mailserver.entity.Otp;
import com.smtp.mailserver.helper.OtpSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private OtpSender otpSender;

    @PostMapping("/sendOtp/{email}")
    public boolean sendMail(@PathVariable String email) throws Exception {
        return otpSender.sendOtp(email);
    }

    @PostMapping("/verifyOtp")
    public boolean verifyOtp(@RequestBody Otp otp){
        System.out.println(otp.getOtp());
        return otpSender.verifyOTP(otp.getOtp());
    }

}
