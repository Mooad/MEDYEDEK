package org.sid.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MedyedekConfiguration {

    @Autowired
    private Environment env;

    public String getSmtpPassword() {
        return env.getProperty("mail.smtp.password");

    }
    public String getSmtpHost() {
        return env.getProperty("mail.smtp.host");

    }
    public String getSmtpPort() {
        return env.getProperty("mail.smtp.port");

    }
    public String getSmtpAuth() {
        return env.getProperty("mail.smtp.auth");

    }
    public String getSmtpStarttls() {
        return env.getProperty("mail.smtp.starttls.enable");

    }

    public String getSmtpUser() {
        return env.getProperty("mail.smtp.user");

    }

}
