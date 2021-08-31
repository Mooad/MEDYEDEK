package org.sid.mailconfirmation;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.sid.config.MedyedekConfiguration;
import org.sid.setting.MailSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.EnumSet;
import java.util.Properties;


@Service
public class RegistrationConfirmation implements MailConfirmation {

    /**
     *
     */
    private static final long serialVersionUID = -3627432171893621308L;

    @Autowired
    MedyedekConfiguration medyedekConfiguration;

    private final JavaMailSender mailSender;
    private final MailSetting mailSetting;


    public RegistrationConfirmation(JavaMailSender mailSender, MailSetting mailSetting) {
        this.mailSender = mailSender;
        this.mailSetting = mailSetting;
    }

    @Override
    public void sendMail(String to, String content, String subject,String user , String token) {
        {

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

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("username"));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse("moad52@hotmail.fr")
                );
                message.setSubject("Welcome TO MEDYEDEK");

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
                content =content.replace("token", "token="+token);
                content =  content.replace("{user}", user);

                email.setFrom(username);
                email.setSubject(subject);
                //  email.setMsg(Content);
                email.setHtmlMsg(content);
                email.addTo(to);
                email.send();
                // HTML Content

                message.setContent(multipart);
                //  Transport.send(message);

                System.out.println("Done");
            } catch (MessagingException | EmailException e) {
                e.printStackTrace();
            }
        }
    }
}