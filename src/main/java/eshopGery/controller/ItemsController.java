package eshopGery.controller;

import eshopGery.model.Category;
import eshopGery.model.Order;
import eshopGery.model.ShoppingItem;
import eshopGery.service.api.ShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("OrderObj")
public class ItemsController {

    @Autowired
    private ShopItemService service;

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

    @RequestMapping(value = "/saveShoppingItemForm")
    public ModelAndView newItemForm() {
        ModelAndView mav = new ModelAndView("/admin/newShoppingItem");
        ShoppingItem item = new ShoppingItem();
        Map<Category, String> categoryValues = new HashMap<Category,String>();
		for (Category category : Category.values()) {
            categoryValues.put(category, category.getDisplayName());
		}
        mav.getModelMap().put("categoryValues", categoryValues);
        mav.getModelMap().put("shoppingItem", item);
        return mav;
    }

    @RequestMapping(value = "/saveShoppingItem", method = RequestMethod.POST)
    public String create(@ModelAttribute("shoppingItem") ShoppingItem item,
                         BindingResult result, SessionStatus status) {

        if (result.hasErrors()) {
            return "saveShoppingItem";
        }
        service.createItem(item);
        status.setComplete();
        return "redirect:adminPart";
    }

    @RequestMapping("/viewAllItems")
    public ModelAndView getAllItems() {
        ModelAndView mav = new ModelAndView("/admin/showShoppingItems");
        List<ShoppingItem> items = service.getAllItems();
        mav.addObject("SEARCH_ITEM_RESULTS_KEY", items);
        return mav;
    }

    @RequestMapping("/deleteItem")
    public String delete(@RequestParam("itemId") Long IDshoppingItem) {
        ShoppingItem item = service.findItemById(IDshoppingItem);
        service.deleteItem(item);
        return "redirect:viewAllItems";
    }

}

//    @RequestMapping(value = "updateCompetitor", method = RequestMethod.GET)
//    public ModelAndView edit(@RequestParam("IDcompetitor") Long IDcompetitor) {
//        ModelAndView mav = new ModelAndView("editCompetitor");
//        Competitor competitor = service.getByID(IDcompetitor);
//        mav.addObject("editCompetitor", competitor);
//        return mav;
//    }
//
//    @RequestMapping(value = "updateCompetitor", method = RequestMethod.POST)
//    public String update(@ModelAttribute("editCompetitor") @Valid Competitor competitor,
//                         BindingResult result, SessionStatus status) {
//        if (result.hasErrors()) {
//            return "editCompetitor";
//        }
//        service.edit(competitor);
//        status.setComplete();
//        return "redirect:viewAllCompetitors.do";
//    }
//
//    
//
//
//
//    @RequestMapping("/deleteCompetitor")
//    public String delete(@RequestParam("IDcompetitor") Long IDcompetitor) {
//        service.delete(IDcompetitor);
//        return "redirect:viewAllCompetitors.do";
//    }
//
//}
//
