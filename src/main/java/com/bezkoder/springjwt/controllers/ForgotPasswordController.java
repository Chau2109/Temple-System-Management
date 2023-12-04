package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.Utility.Utility;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jshell.execution.Util;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import net.bytebuddy.utility.*;

import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
    @Autowired
    private UserService userService;
    private JavaMailSender javaMailSender;
    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(Model model){
        model.addAttribute("pageTitle","Forgot Password" );
        return "forgot_password_form";

    }
    @PostMapping("/forgot_password")
    public String processForgetPasswordForm(HttpServletRequest request, Model model){
        String email = request.getParameter("email");
        String token = RandomString.make(45);
        try{
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            //send email to the customer
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "The link is sent to reset your pw");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
        }
        return "forgot_password_form";
    }

    private void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("contact@phattu.com", "Support");
        helper.setTo(email);
        String subject = "<p>Here's the link to reset the password<p>";
        String content = "<p>You have requested to reset your password<p>" + "<p>Click the link below to change your password<p>"
                + "<a href = \""+ resetPasswordLink + "\">Change my password</a>";
        helper.setSubject(subject); //set the email subject
        helper.setText(content, true);
        javaMailSender.send(message);
    }
    //handle the request when customer click the link
    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model){
        //check if the token is valid
        User user = userService.getUserByResetPasswordToken(token);
        if(user == null){
            model.addAttribute("title", "Reset your password");
            model.addAttribute("message", "Invalid token");
            return "message";
        }
        model.addAttribute("token", token);
        model.addAttribute("title", "reset your password");
        return "reset_password_form";
    }
    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model){
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        User user = userService.getUserByResetPasswordToken(token);
        if(user == null){
            model.addAttribute("title", "Reset your password");
            model.addAttribute("message", "Invalid token");
            return "message";
        }else{
            userService.updatePassword(user, password);
            model.addAttribute("message", "You changed the password successfully");
        }
        return "message";
    }
}
