package eshopGery.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eshopGery.model.Order;
import eshopGery.model.ShoppingItem;
import eshopGery.model.TypePayment;
import eshopGery.service.api.EshopConstants;
import eshopGery.service.api.MailService;
import eshopGery.service.api.OrderService;
import eshopGery.service.api.ShopItemService;

@Controller
@SessionAttributes(OrderService.ORDER_SESSION_OBJECT)
public class OrderController {

    @Autowired
	private OrderService orderService;

	@Autowired
	private ShopItemService shoppingItemService;

    @Autowired
    private MailService mailService;

	@RequestMapping(value = "/orderForm")
	public ModelAndView showOrderForm(HttpSession session) {
		ModelAndView model = new ModelAndView("kosik");
		Order order = (Order) session.getAttribute(OrderService.ORDER_SESSION_OBJECT);
		if (order != null) {
			model.addObject("SEARCH_ITEM_RESULTS_KEY", order.getShoppingItems());
			int price = orderService.calculatePriceOfItems(order.getShoppingItems());
			order.setPriceOfItems(price);
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
        order.setEmptyItems(false);
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
        //change status empty order
        if (order.getShoppingItems().isEmpty()) {
            order.setEmptyItems(true);
        }
		model.addObject(OrderService.ORDER_SESSION_OBJECT, order);
		return model;
	}

	@RequestMapping(value = "/continueToUserData")
	public ModelAndView showAddUserDataForm(Model m, HttpSession session) {

		// if model contains validations error then write on new model
		if (m.asMap().containsKey("result")) {
			m.addAttribute("org.springframework.validation.BindingResult.OrderObj", m.asMap().get("result"));
		}

        ModelAndView model = new ModelAndView("orderData");
		model.addAllObjects(m.asMap());

		Map<TypePayment, String> typePaymentList = new LinkedHashMap<TypePayment, String>();
		for (TypePayment typePayment : TypePayment.values()) {
			typePaymentList.put(typePayment, typePayment.getDisplayName());
		}
		model.addObject("typePaymentList", typePaymentList);

		if (!model.getModel().containsKey(OrderService.ORDER_SESSION_OBJECT)) {
			// set total price
			Order order = (Order) session.getAttribute(OrderService.ORDER_SESSION_OBJECT);
			order.setTotalPrice(orderService.calculateTotalPrice(order.getShoppingItems(), TypePayment.values()[0]));
			model.addObject(OrderService.ORDER_SESSION_OBJECT, order);
		}
		return model;
	}

	@RequestMapping(value = "/completeOrder", method = RequestMethod.POST)
	public String completeOrder(@ModelAttribute(OrderService.ORDER_SESSION_OBJECT) @Valid Order order, BindingResult result,
			RedirectAttributes redirectAttributes, HttpSession session, SessionStatus status) {
		boolean submit = order.isSubmitOrderNow();

		// if form contains errors -> add to redirect
		if (submit && result.hasErrors()) {
			redirectAttributes.addFlashAttribute("result", result);
			return "redirect:continueToUserData";
		}

		if (submit) {
			// if form is complete then save order and send email
			reallyCompleteOrder(order, session, status);
			return "successfulOrder";
		} else {
			order.setTotalPrice(orderService.calculateTotalPrice(order.getShoppingItems(), order.getTypeOfPayment()));
			redirectAttributes.addFlashAttribute(OrderService.ORDER_SESSION_OBJECT, order);
			return "redirect:continueToUserData";
		}

	}

	/**
	 * Complete and save order from session to database and send email about the order.
	 */
	private void reallyCompleteOrder(Order order, HttpSession session, SessionStatus status) {
        order.setComplete(false);
		order.setPaid(false);
		DateTime now = DateTime.now();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        String parseDateTime = formatter.print(now);
		order.setDateCreate(parseDateTime);
		order.setShoppingItemCodes(orderService.encodeShoppingItem(order.getShoppingItems()));
		orderService.createOrder(order);
        //send email recapitulation
        mailService.sendRecapitulation(order, session.getServletContext());
        status.setComplete();
		session.removeAttribute(OrderService.ORDER_SESSION_OBJECT);
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
