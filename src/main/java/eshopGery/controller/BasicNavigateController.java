package eshopGery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import eshopGery.model.EmailMessage;

@Controller
public class BasicNavigateController {

	@RequestMapping(value = "/")
	public ModelAndView test() {
		return new ModelAndView("onas");
	}

	@RequestMapping(value = "/onas")
	public ModelAndView getFrameONas() {
		return new ModelAndView("onas");
	}

	@RequestMapping(value = "/obchod")
	public ModelAndView getFrameObchod() {
		return new ModelAndView("obchod");
	}

	@RequestMapping(value = "/top")
	public ModelAndView getFrameTop() {
		return new ModelAndView("top");
	}

	@RequestMapping(value = "/kontakt")
	public ModelAndView getFrameKontakt() {
		return new ModelAndView("kontakt", "contactMessage", new EmailMessage());
	}

	@RequestMapping(value = "/terms")
	public ModelAndView getTerms() {
		return new ModelAndView("obchodnipodminky");
	}

    @RequestMapping(value = "/administration")
    public String goToAdminitration() {
        return "redirect:admin/adminPartShoppingItems";
    }
}
