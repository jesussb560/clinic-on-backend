package com.app.clinicon.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.app.clinicon.activationtoken.ActivationToken;
import com.app.clinicon.configuration.MailPropertiesConfig;
import com.app.clinicon.user.User;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class TemplateMailSender {
    
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final MailPropertiesConfig mailPropertiesConfig;
    
    @SneakyThrows
    public void sendAccountActivationMail(User user, ActivationToken activationToken){

        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("url", mailPropertiesConfig.getAccountActivationUrl() + activationToken.getToken());
        
        String process = templateEngine.process("mail/account-activation" , context);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper( mimeMessage );

        mimeMessageHelper.setSubject("Account activation");
        mimeMessageHelper.setText(process, true);
        mimeMessageHelper.setTo(user.getEmailAddress());

        javaMailSender.send(mimeMessage);

    }

    @SneakyThrows
    private void sendChangePasswordMail(User user){

        Context context = new Context();
        context.setVariable("user", user);
        
        String process = templateEngine.process("mail/change-password" , context);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setSubject("Change password");
        mimeMessageHelper.setText(process, true);
        mimeMessageHelper.setTo(user.getEmailAddress());

        javaMailSender.send(mimeMessage);

    }

}
