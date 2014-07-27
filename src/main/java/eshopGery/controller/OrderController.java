package eshopGery.controller;

import eshopGery.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {

    @RequestMapping(value="/orderForm")
    public ModelAndView showOrderForm(HttpSession session) {
        Order order = (Order) session.getAttribute("OrderObj");
        ModelAndView model = new ModelAndView("orderPage");
        model.addObject("order",order);
        return model;
    }

	@RequestMapping(value="/continueToUserData")
	public ModelAndView showAddUserDataForm(HttpSession  session) {

        ModelAndView model = new ModelAndView("home");
        Order order = (Order) session.getAttribute("OrderObj");
		return model;
	}

    /*@RequestMapping(value="/addToOrder")
    public ModelAndView deleteItemFromOrder(@RequestParam String itemId) {

//        ModelAndView model = new ModelAndView("Items");

        return new ModelAndView("order");
    } */
}
