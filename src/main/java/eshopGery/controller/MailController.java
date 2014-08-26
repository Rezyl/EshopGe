package eshopGery.controller;

import java.io.IOException;

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

    @RequestMapping(value = "/sendEmail")
    public ModelAndView sendEmail(@RequestParam("emailAddress")String emailAddress) {
        ModelAndView mav = new ModelAndView("redirect:onas");
        EmailMessage message = null;
        try {
            message = service.prepareInvitation(emailAddress);
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
        message.setTo("m.gerstberger91@gmail.com");
        //TODO ceske znaky
        service.sendEmail(message);
        return mav;
    }

}
