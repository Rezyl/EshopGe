package eshopGery.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import eshopGery.model.EmailMessage;
import eshopGery.service.api.MailService;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 21.8.14
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

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
    public void sendInvitation(EmailMessage messageDTO) {

        MimeMessage message = mailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(messageDTO.getFrom());
            helper.setTo(messageDTO.getTo());
            helper.setSubject(messageDTO.getSubject());


            for (File file : messageDTO.getFiles()) {
//                helper.addAttachment(file.getName(), file);
//                String type = Files.probeContentType(file.toPath());
                helper.addInline(file.getName(), file);
            }

            helper.setText(messageDTO.getMessage(), true);

        }catch (MessagingException e) {
            throw new MailParseException(e);
        }
        mailSender.send(message);
    }

    @Override
    public EmailMessage prepareInvitation(String emailAddressTo) throws IOException {
        EmailMessage message = new EmailMessage();

        message.setTo(emailAddressTo);
        message.setFrom("l.rezner@gmail.com");
        message.setSubject("Pozvánka");

        String[] attachmentNames = {"photo1.png", "photo2.jpg", "photo3.jpg", "photo4.jpg", "logo.png"};
        String basicPath = "/other/invitation_email/";
        final Context ctx = new Context(Locale.getDefault());
        List<File> attachments = new ArrayList<File>();
        for (String attachmentName : attachmentNames) {
            URL fileUrl = getClass().getClassLoader().getResource(basicPath + attachmentName);
            assert fileUrl != null;
            File f = new File(fileUrl.getFile());

            ctx.setVariable(FilenameUtils.getBaseName(f.getName()), f.getName());
            attachments.add(f);
		}
        message.setFiles(attachments);

        final String htmlContent = templateEngine.process("emailText.htm", ctx);
        message.setMessage(htmlContent);
        return message;
    }
}
