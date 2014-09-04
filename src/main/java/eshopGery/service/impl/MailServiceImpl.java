package eshopGery.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
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
        mailSender.send(message);
    }

    @Override
    public void sendInvitation(MimeMessage message) {
        mailSender.send(message);
    }

    @Override
    public MimeMessage prepareInvitation(String emailAddressTo, ServletContext servletContext) throws IOException {
        EmailMessage messageDTO = createEmailMessageDTO(emailAddressTo, servletContext);

        MimeMessage message = mailSender.createMimeMessage();
        try {
            buildHtmlMessageContent(message, messageDTO);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(messageDTO.getTo()));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
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

    private EmailMessage createEmailMessageDTO(String emailAddressTo, ServletContext servletContext) {
        EmailMessage message = new EmailMessage();

        message.setTo(emailAddressTo);
        message.setSubject("Pozvánka");

        String[] attachmentNames = {"photo1.png", "photo2.jpg", "photo3.jpg", "photo4.jpg", "logo.png"};
        String basicPath = servletContext.getRealPath("resources")+"/other/invitation_email/";
        List<File> attachments = new ArrayList<File>();
        Map<String, String> dataTemplate = new HashMap<String, String>();
        for (String attachmentName : attachmentNames) {
            File f = new File(basicPath+ attachmentName);
            attachments.add(f);
            String baseName = FilenameUtils.getBaseName(f.getName());

            dataTemplate.put(baseName, f.getName());
        }
        message.setFiles(attachments);

        String htmlContent = processTemplate(dataTemplate);
        message.setMessage(htmlContent);
        return message;
    }

    private String processTemplate(Map<String, String> dataTemplate) {
        Configuration cfg = freeMarkerConfigurer.getConfiguration();
        Writer out = new StringWriter();
        try {
            Template template = cfg.getTemplate(EMAIL_INVITATION_TEMPLATE_NAME);
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
