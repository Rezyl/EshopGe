package EshopGery.controller;

import EshopGery.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("OrderObj")
public class OrderController {

    @RequestMapping(value="/orderForm")
    public ModelAndView showOrder() {
        return new ModelAndView("orderPage","order", new Order());
    }

	/*@RequestMapping(value="/addToOrder")
	public ModelAndView addItemToOrder(@ModelAttribute ShoppingItem shoppingItem) {

//        ModelAndView model = new ModelAndView("Items");

		return new ModelAndView("order");
	}

    @RequestMapping(value="/addToOrder")
    public ModelAndView deleteItemFromOrder(@RequestParam String itemId) {

//        ModelAndView model = new ModelAndView("Items");

        return new ModelAndView("order");
    } */
}
