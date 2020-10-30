package medyedek.service.mailconfirmation;

import java.io.Serializable;
import java.util.List;

public interface MailConfirmation extends Serializable {


	void sendMail(String from, String to, String Content, String subject);

}
