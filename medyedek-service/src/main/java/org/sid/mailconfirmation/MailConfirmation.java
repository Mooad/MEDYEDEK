package org.sid.mailconfirmation;

import java.io.Serializable;
import java.util.List;

public interface MailConfirmation extends Serializable {



	void sendMail(String from, String to, String content, String subject, String token, String user);
}
