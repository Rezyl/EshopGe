package eshopGery.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import eshopGery.model.Order;
import eshopGery.model.ShoppingItem;
import eshopGery.model.TypePayment;
import eshopGery.service.api.OrderService;
import eshopGery.service.api.ShopItemService;

@Controller
@SessionAttributes(OrderService.ORDER_SESSION_OBJECT)
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ShopItemService shoppingItemService;

    @RequestMapping(value="/orderForm")
    public ModelAndView showOrderForm(HttpSession session) {
		ModelAndView model = new ModelAndView("kosik");
		Order order = (Order) session.getAttribute("OrderObj");
		if (order != null) {
			model.addObject("SEARCH_ITEM_RESULTS_KEY", order.getShoppingItems());
			model.addObject("order", order);
		} else {
			model.addObject("SEARCH_ITEM_RESULTS_KEY", new ArrayList<String>());
			model.addObject("order", new Order());
		}
		return model;
  }

    @RequestMapping(value="/addItemsToOrder", method = RequestMethod.GET)
	public ModelAndView addItemToOrder(@RequestParam("size") String size, @RequestParam("itemID") Long itemID, HttpServletRequest request) {
		// reference on actual page
		String previousPage = request.getHeader("Referer");
		ModelAndView model = new ModelAndView("redirect:" + previousPage);
		Order order = (Order) request.getSession().getAttribute("OrderObj");
		if (order == null) {
			order = new Order();
		}
		ShoppingItem item = shoppingItemService.findItemById(itemID);
		item.setSize(size);
		orderService.addItemToOrder(order, item);
		model.addObject("OrderObj", order);
		return model;
	}

	@RequestMapping("/deleteItemFromOrder")
	public String delete(@RequestParam("itemId") Long IDshoppingItem, HttpSession session) {
		// Order order = (Order) session.getAttribute("OrderObj");
		// List<ShoppingItem> items = order.getShoppingItems();
		// for (ShoppingItem item : items) {
		// //
		// }
		// ShoppingItem item = service.findItemById(IDshoppingItem);
		// service.deleteImage(item.getImageFilePath());
		// service.deleteItem(item);
		return "redirect:viewAllItems";
	}

	@RequestMapping(value = "/continueToUserData")
	public ModelAndView showAddUserDataForm(HttpSession session) {

        ModelAndView model = new ModelAndView("orderData");
        Order order = (Order) session.getAttribute("OrderObj");

		Map<TypePayment, String> typePaymentList = new HashMap<TypePayment, String>();
		for (TypePayment typePayment : TypePayment.values()) {
			typePaymentList.put(typePayment, typePayment.getDisplayName());
		}
		model.addObject("typePaymentList", typePaymentList);
		model.addObject("order", order);
		return model;
	}

	@RequestMapping(value = "/completeOrder")
	public ModelAndView completeOrder(@ModelAttribute("order") Order order, HttpSession session) {
		ModelAndView mav = new ModelAndView("successfulOrder");

        order.setComplete(false);
		order.setPaid(false);
		DateTime now = new DateTime();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		order.setDateCreate(df.format(now));
		order.setShoppingItemCodes(orderService.encodeShoppingItem(order.getShoppingItems()));
		orderService.createOrder(order);
		session.removeAttribute(OrderService.ORDER_SESSION_OBJECT);
		// TODO send email
		return mav;

	}

	@RequestMapping("/adminPartOrders")
	public ModelAndView getAllItems(WebRequest webRequest) {
		ModelAndView mav = new ModelAndView("/admin/administration");
		List<Order> orders = orderService.getAllItems();
		mav.addObject("SEARCH_ITEM_RESULTS_KEY", orders);
        //message
		String message = webRequest.getParameter("message");
		if (message != null) {
			mav.addObject("message", message);
		}
		mav.addObject("typeOfPage", "order");
		return mav;
	}

}
