package eshopGery.service.api;

import java.io.IOException;

import javax.servlet.ServletContext;

import eshopGery.model.EmailMessage;
import eshopGery.model.Order;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 21.8.14
 */
public interface MailService {

    public void sendEmail(EmailMessage messageDTO);

    public void sendRecapitulation(Order order, ServletContext servletContext);

    public void sendInvitation(String emailAddressTo, ServletContext servletContext) throws IOException;
}
