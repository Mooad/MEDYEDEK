package org.sid.services.config;

import java.util.Properties;

import org.sid.services.setting.MailSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class EmailConfig {

    private final MailSetting mailSetting;

    @Autowired
    public EmailConfig(MailSetting mailSetting) {
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
        return props;
    }
}
