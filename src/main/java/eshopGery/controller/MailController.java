package eshopGery.controller;

import java.io.IOException;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import eshopGery.model.EmailMessage;
import eshopGery.service.api.MailService;

@Controller
public class MailController {

    @Autowired
    private MailService service;

    @RequestMapping(value = "/sendInvitation")
    public ModelAndView sendInvitation(@RequestParam("emailAddress")String emailAddress, HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:onas");
        MimeMessage message = null;
        try {
            message = service.prepareInvitation(emailAddress, session.getServletContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        service.sendInvitation(message);
        return mav;
    }

    @RequestMapping(value = "/sendContact")
    public ModelAndView sendContactMessage(@ModelAttribute("contactMessage")EmailMessage message) {
        ModelAndView mav = new ModelAndView("redirect:kontakt");
        message.setSubject(message.getTo()+"-"+message.getSubject());
        message.setTo("l.rezner@gmail.com");
        //TODO ceske znaky
        service.sendEmail(message);
        return mav;
    }

}
