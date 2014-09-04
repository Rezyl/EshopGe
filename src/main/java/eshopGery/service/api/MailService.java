package eshopGery.service.api;

import java.io.IOException;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import eshopGery.model.EmailMessage;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 21.8.14
 */
public interface MailService {

    public void sendEmail(EmailMessage messageDTO);

    public void sendInvitation(MimeMessage message);

    public MimeMessage prepareInvitation(String emailAddressTo, ServletContext servletContext) throws IOException;
}
