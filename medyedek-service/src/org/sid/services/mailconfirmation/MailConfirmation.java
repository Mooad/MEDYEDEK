package org.sid.services.mailconfirmation;

import java.io.Serializable;
import java.util.List;

public interface MailConfirmation extends Serializable {
	void sendMail(String to, String content, String subject, String token, String user);
}
