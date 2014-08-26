package eshopGery.service.api;

import java.io.IOException;

import eshopGery.model.EmailMessage;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 21.8.14
 */
public interface MailService {

    public void sendEmail(EmailMessage messageDTO);

    public void sendInvitation(EmailMessage messageDTO);

    public EmailMessage prepareInvitation(String emailAddressTo) throws IOException;
}
