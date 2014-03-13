package org.ednovo.gooru.mail.handler;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.ednovo.gooru.mail.domain.MailDO;

public interface MailHandler {
	Object sendMail(MailDO mail, Long expires) throws MessagingException,
			UnsupportedEncodingException;
}
