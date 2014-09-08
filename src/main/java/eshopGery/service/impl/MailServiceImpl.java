package eshopGery.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import eshopGery.model.EmailMessage;
import eshopGery.model.Order;
import eshopGery.model.ShoppingItem;
import eshopGery.service.api.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 21.8.14
 */
@Service
public class MailServiceImpl implements MailService {

    private static final String EMAIL_INVITATION_TEMPLATE_NAME = "emailText.ftl";

    private static final String REC_CASH_TEMPLATE_NAME = "rekplatbadobirkou.ftl";
    private static final String REC_CREDIT_CARD_TEMPLATE_NAME = "rekplatbaprevodem.ftl";
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public void sendEmail(EmailMessage messageDTO) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(messageDTO.getFrom());
        message.setTo(messageDTO.getTo());
        message.setSubject(messageDTO.getSubject());
        message.setText(messageDTO.getMessage());

        System.setProperty("mail.mime.charset", "utf8");

        mailSender.send(message);
    }

    @Override
    public void sendRecapitulation(Order order, ServletContext servletContext) {
        String[] attachmentNames = {"logo.png"};
        EmailMessage messageDTO = createEmailMessageDTO(order.getEmail(), "TODO", attachmentNames, servletContext);

        messageDTO.addTemplateItem("order", order);
        messageDTO.addTemplateItem("shoppingItems", new ArrayList<Map.Entry<ShoppingItem, Integer>>(order.getShoppingItems().entrySet()));


        String htmlContent = null;
        switch (order.getTypeOfPayment()) {
            case CASH:
                htmlContent = processTemplate(messageDTO.getTemplateCnx(),REC_CASH_TEMPLATE_NAME);
                break;
            case CREDIT_CARD:
                htmlContent = processTemplate(messageDTO.getTemplateCnx(),REC_CREDIT_CARD_TEMPLATE_NAME);
                break;
            case PERSONAL:
                //TODO personal template
                //htmlContent = processTemplate(messageDTO.getTemplateCnx(),REC_CREDIT_CARD_TEMPLATE_NAME);
        }
        messageDTO.setMessage(htmlContent);

        MimeMessage message = mailSender.createMimeMessage();

        try {
            buildHtmlMessageContent(message, messageDTO);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(messageDTO.getTo()));
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mailSender.send(message);


    }

    @Override
    public void sendInvitation(String emailAddressTo, ServletContext servletContext) throws IOException {
        String[] attachmentNames = {"photo1.png", "photo2.jpg", "photo3.jpg", "photo4.jpg", "logo.png"};
        EmailMessage messageDTO = createEmailMessageDTO(emailAddressTo, "Pozvánka", attachmentNames, servletContext);

        String htmlContent = processTemplate(messageDTO.getTemplateCnx(), EMAIL_INVITATION_TEMPLATE_NAME);
        messageDTO.setMessage(htmlContent);

        MimeMessage message = mailSender.createMimeMessage();
        try {
            buildHtmlMessageContent(message, messageDTO);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(messageDTO.getTo()));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);

    }

    private EmailMessage createEmailMessageDTO(String emailAddressTo, String subject, String[] attachmentNames, ServletContext servletContext) {
        EmailMessage message = new EmailMessage();

        message.setTo(emailAddressTo);
        message.setSubject(subject);

        String basicPath = servletContext.getRealPath("resources")+"/other/email_images/";

        List<File> attachments = new ArrayList<File>();
        for (String attachmentName : attachmentNames) {
            File f = new File(basicPath+ attachmentName);
            attachments.add(f);
            String baseName = FilenameUtils.getBaseName(f.getName());

            //set to template context -> later build template html result
            message.addTemplateItem(baseName, f.getName());
        }
        message.setFiles(attachments);
        return message;
    }

    private void buildHtmlMessageContent(MimeMessage message, EmailMessage messageDTO) throws MessagingException, IOException {
        MimeMultipart content = new MimeMultipart("related");

        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(messageDTO.getMessage(), "UTF-8", "html");
        content.addBodyPart(textPart);

        // Image part
		for (File image : messageDTO.getFiles()) {
            MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.attachFile(image);
            imagePart.setContentID("<" + image.getName() + ">");
            imagePart.setDisposition(MimeBodyPart.INLINE);
            content.addBodyPart(imagePart);
		}

        message.setContent(content);
        message.setSubject(messageDTO.getSubject());
    }

    private String processTemplate(Map<String, Object> dataTemplate, String templateName) {
        Configuration cfg = freeMarkerConfigurer.getConfiguration();
        Writer out = new StringWriter();
        try {
            Template template = cfg.getTemplate(templateName);
            template.process(dataTemplate, out);
            out.flush();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
