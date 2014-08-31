package eshopGery.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import eshopGery.model.Order;
import eshopGery.model.ShoppingItem;
import eshopGery.model.TypePayment;
import eshopGery.service.api.EshopConstants;
import eshopGery.service.api.OrderService;
import eshopGery.service.api.ShopItemService;

@Controller
@SessionAttributes(OrderService.ORDER_SESSION_OBJECT)
public class OrderController {

    @Autowired
	private OrderService orderService;

	@Autowired
	private ShopItemService shoppingItemService;

	@RequestMapping(value = "/orderForm")
	public ModelAndView showOrderForm(HttpSession session) {
		ModelAndView model = new ModelAndView("kosik");
		Order order = (Order) session.getAttribute(OrderService.ORDER_SESSION_OBJECT);
		if (order != null) {
			model.addObject("SEARCH_ITEM_RESULTS_KEY", order.getShoppingItems());
			int price = orderService.calculateTotalPrice(order.getShoppingItems());
			order.setTotalPrice(price);
			model.addObject(OrderService.ORDER_SESSION_OBJECT, order);
		} else {
			model.addObject("SEARCH_ITEM_RESULTS_KEY", new ArrayList<String>());
			model.addObject(OrderService.ORDER_SESSION_OBJECT, new Order());
		}
		return model;
	}

    @RequestMapping(value="/addItemsToOrder", method = RequestMethod.GET)
	public ModelAndView addItemToOrder(@RequestParam("size") String size, @RequestParam("itemID") Long itemID, HttpServletRequest request) {
		// reference on actual page
		String previousPage = request.getHeader("Referer");
		ModelAndView model = new ModelAndView("redirect:" + previousPage);
		Order order = (Order) request.getSession().getAttribute(OrderService.ORDER_SESSION_OBJECT);
		if (order == null) {
			order = new Order();
		}
		ShoppingItem item = shoppingItemService.findItemById(itemID);
		item.setSize(size);
		orderService.addItemToOrder(order, item);
		model.addObject(OrderService.ORDER_SESSION_OBJECT, order);
		return model;
	}

	@RequestMapping(value="/deleteItemFromOrder")
	public ModelAndView deleteFromOrder(@RequestParam("itemID") Long itemID, @RequestParam("size") String size, HttpSession session) {
		ModelAndView model = new ModelAndView("redirect:orderForm");
		Order order = (Order) session.getAttribute(OrderService.ORDER_SESSION_OBJECT);
		orderService.removeItemFromOrder(order, itemID, size);
		model.addObject(OrderService.ORDER_SESSION_OBJECT, order);
		return model;
	}

	@RequestMapping(value = "/continueToUserData")
	public ModelAndView showAddUserDataForm(HttpSession session) {

        ModelAndView model = new ModelAndView("orderData");
        Order order = (Order) session.getAttribute(OrderService.ORDER_SESSION_OBJECT);

		Map<TypePayment, String> typePaymentList = new HashMap<TypePayment, String>();
		for (TypePayment typePayment : TypePayment.values()) {
			typePaymentList.put(typePayment, typePayment.getDisplayName());
		}
		model.addObject("typePaymentList", typePaymentList);
		model.addObject(OrderService.ORDER_SESSION_OBJECT, order);
		return model;
	}

	@RequestMapping(value = "/completeOrder")
	public ModelAndView completeOrder(@ModelAttribute(OrderService.ORDER_SESSION_OBJECT) Order order, HttpSession session, SessionStatus status) {
		ModelAndView mav = new ModelAndView("successfulOrder");

        order.setComplete(false);
		order.setPaid(false);
		DateTime now = DateTime.now();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        String parseDateTime = formatter.print(now);
		order.setDateCreate(parseDateTime);
		order.setShoppingItemCodes(orderService.encodeShoppingItem(order.getShoppingItems()));
		orderService.createOrder(order);
        status.setComplete();
		session.removeAttribute(OrderService.ORDER_SESSION_OBJECT);
		// TODO send email
		return mav;

	}

    /**
     * ADMIN PART
     */

	@RequestMapping(EshopConstants.ADMIN_PART_PREFIX + "adminPartOrders")
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
