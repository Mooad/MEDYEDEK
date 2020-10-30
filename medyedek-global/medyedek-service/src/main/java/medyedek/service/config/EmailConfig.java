package medyedek.service.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import medyedek.service.constantes.EmailConstant;




@Configuration
public class EmailConfig {

    private final medyedek.service.setting.MailSetting mailSetting;

    @Autowired
    public EmailConfig(medyedek.service.setting.MailSetting mailSetting) {
        this.mailSetting =mailSetting;
    }

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailSetting.getHost());
        mailSender.setPassword(mailSetting.getPassword());
        mailSender.setUsername(mailSetting.getUsername());
        mailSender.setPort(mailSetting.getPort());
        mailSender.setProtocol(mailSetting.getProtocol());
        mailSender.setJavaMailProperties(getMailProperties());
        return mailSender;
    }

    private Properties getMailProperties() {
        Properties props = new Properties();
        props.put(EmailConstant.SMTP_AUTH, mailSetting.getAuth());
        props.put(EmailConstant.SMTP_STARTLS, mailSetting.getStarttlsEnable());
        return props;
    }
}
