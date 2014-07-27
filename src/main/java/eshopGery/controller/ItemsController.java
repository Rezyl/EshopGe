package eshopGery.controller;

import eshopGery.model.Order;
import eshopGery.model.ShoppingItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("OrderObj")
public class ItemsController {

    @RequestMapping(value="/itemsForm")
    public ModelAndView showItems() {
        return new ModelAndView("items","shoppingItem", new ShoppingItem());
    }

	@RequestMapping(value="/addToOrder")
	public ModelAndView addItemToOrder(@ModelAttribute ShoppingItem shoppingItem,HttpSession  session) {

        ModelAndView model = new ModelAndView("home");
        Order order = (Order) session.getAttribute("OrderObj");
        if (session.getAttribute("OrderObj") == null) {
            order = new Order();
        }
        order.getShoppingItems().add(shoppingItem);
        model.addObject("OrderObj",order);
		return model;
	}
}
