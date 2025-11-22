package com.srap.Shree.Radhe.Agro.Foods.service;

import com.srap.Shree.Radhe.Agro.Foods.entity.PasswordResetToken;
import com.srap.Shree.Radhe.Agro.Foods.entity.User;
import com.srap.Shree.Radhe.Agro.Foods.repository.PasswordResetTokenRepo;
import com.srap.Shree.Radhe.Agro.Foods.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private PasswordResetTokenRepo resetTokenRepo;
    @Autowired
    private UserRepo userRepo;

//    Generating reset token for particular user
    public String generateToken(String email){
        User user = userRepo.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User Not Found With Email : " + email));
        String token = UUID.randomUUID().toString();
        PasswordResetToken existingToken = resetTokenRepo.findByUser(user);
        if(existingToken != null){
            existingToken.setToken(token);
            existingToken.setUser(user);
            existingToken.setExpiryTime(LocalDateTime.now().plusMinutes(10));
            resetTokenRepo.save(existingToken);
        }else{
            PasswordResetToken passwordResetToken = new PasswordResetToken();
            passwordResetToken.setUser(user);
            passwordResetToken.setExpiryTime(LocalDateTime.now().plusMinutes(10));
            passwordResetToken.setToken(token);
            resetTokenRepo.save(passwordResetToken);
        }
        return sendMailMessage(email,token);
    }
//    Sending mail to user's mail
    @Async
    private String sendMailMessage(String email, String token){
        String resetLink = "http://localhost:80/auth/resetpassword?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Reset Password Link");
        message.setText("Dear User,\n" +
                "\n" +
                "A password reset request was made for your account at Shri Radhe Agro Foods.\n" +
                "\n" +
                "Please click the link below to reset your password:\n" +
                resetLink + "\n" +
                "\n" +
                "For your security, this link is valid for a limited time.  \n" +
                "If you did not initiate this request, please contact our support team or ignore this email.\n" +
                "\n" +
                "Warm regards,\n" +
                "Shri Radhe Agro Foods Team\n");

        mailSender.send(message);
        return "Password Reset Mail Sended";
    }
}
