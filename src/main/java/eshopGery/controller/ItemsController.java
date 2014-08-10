package eshopGery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import eshopGery.model.Category;
import eshopGery.model.ShoppingItem;
import eshopGery.model.SizeOfSock;
import eshopGery.service.api.ShopItemService;

@Controller
@SessionAttributes("OrderObj")
public class ItemsController {

	private static final String NAME_OF_DIR_TO_SAVE_IMAGES = "socksImages";
    @Autowired
    private ShopItemService service;

    @RequestMapping(value = "/saveShoppingItemForm")
    public ModelAndView newItemForm() {
        ModelAndView mav = new ModelAndView("/admin/newShoppingItem");
        ShoppingItem item = new ShoppingItem();
		Map<Category, String> categoryValues = getAllcategorieMap();
     mav.getModelMap().put("categoryValues", categoryValues);
        mav.getModelMap().put("shoppingItem", item);
        return mav;
    }

	private Map<Category, String> getAllcategorieMap() {
		Map<Category, String> categoryValues = new HashMap<Category, String>();
		for (Category category : Category.values()) {
			categoryValues.put(category, category.getDisplayName());
		}
		return categoryValues;
	}

	@RequestMapping(value = "/saveShoppingItem", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("shoppingItem") ShoppingItem item, BindingResult result, @RequestParam("file") MultipartFile file,
			HttpSession session) {
		String message;
		// TODO valid correct file type
		// if (!file.isEmpty() && file.getContentType().equals(XLS_FORMAT) || file.getContentType().equals(XLSX_FORMAT)) {
		String imagePath = service.uploadImage(getRootDirToSaveImages(session), file);
		if (imagePath != null) {
			message = "Success!";

			item.setImageFilePath(imagePath);
        } else {
			message = "Nahravan spatny soubor";
		}
		if (result.hasErrors()) {
			return new ModelAndView("redirect:saveShoppingItem");
        }
        service.createItem(item);
		return new ModelAndView("redirect:adminPart", "message", message);
    }

	@RequestMapping(value = "updateShoppingItemForm", method = RequestMethod.GET)
	public ModelAndView editForm(@RequestParam("itemId") Long itemId) {
		ModelAndView mav = new ModelAndView("/admin/editShoppingItem");
		ShoppingItem item = service.findItemById(itemId);
		Map<Category, String> categoryValues = getAllcategorieMap();
		mav.getModelMap().put("categoryValues", categoryValues);
		mav.addObject("updateShoppingItem", item);
		return mav;
	}

	@RequestMapping(value = "updateShoppingItem", method = RequestMethod.POST)
	public String edit(@ModelAttribute("updateShoppingItem") ShoppingItem shoppingItem, BindingResult result, @RequestParam("file") MultipartFile file,
			HttpSession session) {
		String message;
		// TODO valid correct file type
		// if (!file.isEmpty() && file.getContentType().equals(XLS_FORMAT) || file.getContentType().equals(XLSX_FORMAT)) {
		String fullImagePath = service.uploadImage(getRootDirToSaveImages(session), file);
		if (fullImagePath != null) {
			message = "Success!";
			shoppingItem.setImageFilePath(fullImagePath);
		} else {
			message = "Nahravan spatny soubor";
		}
		if (result.hasErrors()) {
			return "/admin/editShoppingItem";
		}
		service.updateItem(shoppingItem);
		return "redirect:viewAllItems";
	}

	@RequestMapping("/adminPartShoppingItems")
	public ModelAndView getAllItems(WebRequest webRequest) {
		ModelAndView mav = new ModelAndView("/admin/administration");
		List<ShoppingItem> items = service.getAllItems();
		mav.addObject("SEARCH_ITEM_RESULTS_KEY", items);
		// message
        String message= webRequest.getParameter("message");
        if (message != null) {
            mav.addObject("message", message);
        }
        mav.addObject("typeOfPage", "item");
        return mav;
    }

    @RequestMapping("/showByCategory")
	public ModelAndView getAllItemsByCetegory(@RequestParam("category") String category) {
		ModelAndView mav = new ModelAndView("obchod");
		Category categoryEnum = Category.valueOf(category);
		List<ShoppingItem> items = service.getAllByCategory(categoryEnum);
		mav.addObject("SEARCH_ITEM_RESULTS_KEY", items);
		mav.addObject("sizeList", SizeOfSock.values());
		return mav;
	}

	@RequestMapping("/deleteItem")
	public String delete(@RequestParam("itemId") Long IDshoppingItem) {
		ShoppingItem item = service.findItemById(IDshoppingItem);
		service.deleteImage(item.getImageFilePath());
		service.deleteItem(item);
		return "redirect:viewAllItems";
	}

	private String getRootDirToSaveImages(HttpSession session) {
		String fileSeparator = System.getProperty("file.separator");
		ServletContext sc = session.getServletContext();
		return sc.getRealPath("resources") + fileSeparator + NAME_OF_DIR_TO_SAVE_IMAGES + fileSeparator;
	}

}
