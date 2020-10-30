package medyedek.service.mailconfirmation;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import medyedek.service.setting.MailSetting;



@Service
public class RegistrationConfirmation implements  MailConfirmation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3627432171893621308L;

	private final JavaMailSender mailSender;
	private final MailSetting mailSetting;
	

	 
	public RegistrationConfirmation( JavaMailSender mailSender, MailSetting mailSetting) {
	        this.mailSender = mailSender;
	        this.mailSetting = mailSetting;
	    }

	
	@Override
	public void sendMail(String from, String to, String Content,String subject) {

        final String username = "fajrimoad@gmail.com";
        final String password = "moad199619951990wwwa";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        
		 Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });
	        try {
	             Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("username"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("moad52@hotmail.fr")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

	            Transport.send(message);

	            System.out.println("Done");
	        }
	        catch (MessagingException e) {
	            e.printStackTrace();
	        }
	}  
}