package com.smtp.mailserver.helper;


import com.smtp.mailserver.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Cookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class OtpSender {
    @Autowired
    private EmailService emailService;
    private final Cookie cookie = new Cookie();

    public boolean sendOtp(String email) throws Exception {
        int min = 199999;
        int max = 999999;
        int a = (int) (Math.random() * (max - min + 1) + min);
        String myOTP = ""+a;
        cookie.setName(myOTP);
        System.out.println("OTP : " + a);
        String setSubject = "OTP Verification!!";
        String setMessage = "<img src='https://imgs.search.brave.com/oyLZ-NkPrjhqJYTArtmTRYX8LacV0cGkhnzh-WKkAxo/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9jZG4t/aWNvbnMtcG5nLmZs/YXRpY29uLmNvbS81/MTIvMTg3LzE4NzY2/Mi5wbmc' alt='Girl in a jacket'><h1 style='color: blue;'>Please verify the OTP</h1> " +
                "<h1><span style='color: green;'>OTP</span> - "+a+"</h1>";

        boolean b = emailService.sendEmailWithHtml(email, setSubject, setMessage);
        if(!b){
            throw new Exception("User Email is not valid!!");
        }
        else{
            return true;
        }
    }

    public boolean verifyOTP(String otp){
        boolean b = false;
        String myOTP = cookie.getName();
        System.out.println("Sent OTP is: " + myOTP);
        if(otp.equals(myOTP)){
            cookie.setName("");
            cookie.setPath("/");
            Duration d = Duration.ofDays(0);
            cookie.setMaxAge(d);
            b = true;
        }
        return  b;
    }

}
