package eshopGery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BasicNavigateController {

	@RequestMapping(value="/")
	public ModelAndView test() {
		return new ModelAndView("index");
	}
    @RequestMapping(value="/onas")
    public ModelAndView getFrameONas() {
        return new ModelAndView("onas");
    }
    @RequestMapping(value="/obchod")
    public ModelAndView getFrameObchod() {
        return new ModelAndView("obchod");
    }
    @RequestMapping(value="/top")
    public ModelAndView getFrameTop() {
        return new ModelAndView("top");
    }
    @RequestMapping(value="/kontakt")
    public ModelAndView getFrameKontakt() {
        return new ModelAndView("kontakt");
    }
    @RequestMapping(value="/obchodleva")
    public ModelAndView getObchodLeva() {
        return new ModelAndView("obchodleva");
    }
    @RequestMapping(value="/kosik")
    public ModelAndView getKosik() {
        return new ModelAndView("kosik");
    }
    @RequestMapping(value="/adminPart")
    public ModelAndView getAdminPart() {
        return new ModelAndView("/admin/adminPart");
    }
}
