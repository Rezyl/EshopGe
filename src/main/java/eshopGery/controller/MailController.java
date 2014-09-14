package eshopGery.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
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
		if (StringUtils.isEmpty(emailAddress)) {
			mav.addObject("validationError", "Nesmí být prázdné");
			mav.setViewName("onas");
			return mav;
		}

        try {
            service.sendInvitation(emailAddress, session.getServletContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/sendContact")
	public String sendContactMessage(@ModelAttribute("contactMessage") @Valid EmailMessage message, BindingResult result) {

		if (result.hasErrors()) {
			return "kontakt";
		}

		message.setSubject(message.getTo()+"-"+message.getSubject());
        message.setTo("l.rezner@gmail.com");
        //TODO ceske znaky
        service.sendEmail(message);
        return "redirect:kontakt";
    }

}
