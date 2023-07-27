package org.sid.services.mailconfirmation;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.sid.services.config.MedyedekConfiguration;
import org.sid.services.setting.MailSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class ResetConfirmation implements MailConfirmation {

    @Autowired
    MedyedekConfiguration medyedekConfiguration;

    private final JavaMailSender mailSender;
    private final MailSetting mailSetting;


    public ResetConfirmation(JavaMailSender mailSender, MailSetting mailSetting) {
        this.mailSender = mailSender;
        this.mailSetting = mailSetting;
    }

    @Override
    public void sendMail(String to, String content, String subject, String tempPassword, String user) {

        //getting user and password of smtp server
        final String username = medyedekConfiguration.getSmtpUser();
        final String password = medyedekConfiguration.getSmtpPassword();

        Properties prop = new Properties();
        prop.put("mail.smtp.host", medyedekConfiguration.getSmtpHost());
        prop.put("mail.smtp.port", medyedekConfiguration.getSmtpPort());
        prop.put("mail.smtp.auth", medyedekConfiguration.getSmtpAuth());
        prop.put("mail.smtp.ssl.enable", "true");
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            MimeMultipart multipart = new MimeMultipart();

            final MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(content, "text/html;charset=iso-8859-1");
            messageBodyPart.setHeader("Content-type", "text/HTML");
            // add it
            multipart.addBodyPart(messageBodyPart);
            // don't forget to add the content to your message.

            HtmlEmail email = new HtmlEmail();
            email.setHostName(medyedekConfiguration.getSmtpHost());
            email.setSmtpPort(Integer.valueOf(medyedekConfiguration.getSmtpPort()));
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            email.setSSLOnConnect(true);
            //Remplacing User Name and token in email body
            content =content.replace("**$$pass$$**", "temporaryPassword="+tempPassword);

            email.setFrom(username);
            email.setSubject(subject);
            //  email.setMsg(Content);
            email.setHtmlMsg(content);
            email.addTo(to);
            email.send();
            // HTML Content


            System.out.println("Reset Password Email is sent");
        } catch (MessagingException | EmailException e) {
            e.printStackTrace();
        }
    }
}
