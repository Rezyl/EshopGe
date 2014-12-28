package eshopGery.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import eshopGery.model.Category;
import eshopGery.model.ShoppingItem;
import eshopGery.model.SizeOfSock;
import eshopGery.service.api.EshopConstants;
import eshopGery.service.api.ShopItemService;

@Controller
@SessionAttributes({ "OrderObj", "updateItem" })
public class ItemsController {

	@Autowired
	private ShopItemService service;

	/**
	 * ADMIN PART
	 */
	@RequestMapping(EshopConstants.ADMIN_PART_PREFIX + "adminPartShoppingItems")
	public ModelAndView getAllItems(WebRequest webRequest) {
		ModelAndView mav = new ModelAndView(EshopConstants.ADMIN_PART_PREFIX + "administration");
		List<ShoppingItem> items = service.getAllItems();
		mav.addObject("SEARCH_ITEM_RESULTS_KEY", items);
		// message
		String message = webRequest.getParameter("message");
		if (message != null) {
			mav.addObject("message", message);
		}
		mav.addObject("typeOfPage", "item");
		return mav;
	}

	@RequestMapping(EshopConstants.ADMIN_PART_PREFIX + "saveShoppingItemForm")
	public ModelAndView createForm() {
		ModelAndView mav = new ModelAndView(EshopConstants.ADMIN_PART_PREFIX + "newShoppingItem");
		ShoppingItem item = new ShoppingItem();
		mav.getModelMap().put("categoryValues", getAllCategoryMap());
		mav.getModelMap().put("sizesValues", getAllSizesMap());
		mav.getModelMap().put("shoppingItem", item);
		return mav;
	}

	@RequestMapping(value = EshopConstants.ADMIN_PART_PREFIX + "updateShoppingItemForm", method = RequestMethod.GET)
	public ModelAndView editForm(@RequestParam("itemId") Long itemId) {
		ModelAndView mav = new ModelAndView(EshopConstants.ADMIN_PART_PREFIX + "editShoppingItem");
		ShoppingItem item = service.findItemById(itemId);
		mav.addObject("updateItem", item);
		mav.getModelMap().put("categoryValues", getAllCategoryMap());
		mav.getModelMap().put("sizesValues", getAllSizesMap());
		return mav;
	}

	@RequestMapping(value = EshopConstants.ADMIN_PART_PREFIX + "saveShoppingItem", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("shoppingItem") ShoppingItem item, BindingResult result, @RequestParam("file") MultipartFile file,
			@RequestParam("filesForGallery") MultipartFile[] filesForGallery, HttpSession session) {
		if (result.hasErrors()) {
			return new ModelAndView("redirect:saveShoppingItem");
		}
		String message = uploadAllImages(item, file, filesForGallery, session);
		service.createItem(item);
		return new ModelAndView("redirect:adminPartShoppingItems", "message", message);
	}

	@RequestMapping(value = EshopConstants.ADMIN_PART_PREFIX + "updateShoppingItem", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute("updateItem") ShoppingItem shoppingItem, BindingResult result, @RequestParam("file") MultipartFile file,
			@RequestParam("filesForGallery") MultipartFile[] filesForGallery, HttpSession session, SessionStatus status) {
		if (result.hasErrors()) {
			return new ModelAndView("redirect:updateShoppingItem");
		}
		String message = uploadAllImages(shoppingItem, file, filesForGallery, session);
		service.updateItem(shoppingItem);
		status.setComplete();
		return new ModelAndView("redirect:adminPartShoppingItems", "message", message);
	}

	@RequestMapping(EshopConstants.ADMIN_PART_PREFIX + "deleteItem")
	public String delete(@RequestParam("itemId") Long IDshoppingItem) {
		ShoppingItem item = service.findItemById(IDshoppingItem);
		service.deleteImage(item.getImageFilePath());
		service.deleteItem(item);
		return "redirect:adminPartShoppingItems";
	}

	/**
	 * Upload image of item and images to gallery
	 * 
	 * @return message of result
	 */
	private String uploadAllImages(ShoppingItem item, MultipartFile file, MultipartFile[] filesForGallery, HttpSession session) {
		String message;
		// TODO valid correct file type
		// if (!file.isEmpty() && file.getContentType().equals(XLS_FORMAT) || file.getContentType().equals(XLSX_FORMAT)) {
		// save to storage
		service.uploadImage(EshopConstants.getRootDirToSaveImages(), file, false);
		// save to resources
		String imagePath = service.uploadImage(EshopConstants.getResourcesDirToSaveImages(session.getServletContext()), file, true);
		if (imagePath != null) {
			message = "Success!";
			item.setImageFilePath(imagePath);
		} else {
			message = "Nahravan spatny soubor";
		}
		// IMAGE GALLERY
		List<String> imagesForGallery = new ArrayList<String>();
		for (MultipartFile multipartFile : filesForGallery) {
			// save to storage
			service.uploadImage(EshopConstants.getRootDirToSaveImages(), file, false);
			// save to resources
			String imageGallery = service.uploadImage(EshopConstants.getResourcesDirToSaveImages(session.getServletContext()), file, true);
			imagesForGallery.add(imageGallery);
		}
		item.setImageForGallery(service.decodeImagesPath(imagesForGallery));
		return message;
	}

	private Map<Category, String> getAllCategoryMap() {
		Map<Category, String> categoryValues = new HashMap<Category, String>();
		for (Category category : Category.values()) {
			categoryValues.put(category, category.getDisplayName());
		}
		return categoryValues;
	}

	private Map<SizeOfSock, String> getAllSizesMap() {
		Map<SizeOfSock, String> sizeValues = new HashMap<SizeOfSock, String>();
		for (SizeOfSock sizeOfSock : SizeOfSock.values()) {
			sizeValues.put(sizeOfSock, sizeOfSock.getDisplayName());
		}
		return sizeValues;
	}

	/**
	 * PUBLIC PART
	 */
	@RequestMapping(value = "/showByCategory/pages/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView getAllItemsByCategory(@PathVariable Integer pageNumber, @RequestParam("category") String category) {
		ModelAndView mav = new ModelAndView("obchod");
		Category categoryEnum = Category.valueOf(category);
		Page<ShoppingItem> items = service.getItemsToPageByCategory(pageNumber, categoryEnum);
		mav.addObject("SEARCH_ITEM_RESULTS_KEY", items);
		mav.addObject("allCategories", Arrays.asList(Category.values()));
		mav.addObject("actualCategory", category);

		int current = items.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, items.getTotalPages());

		mav.addObject("beginIndex", begin);
		mav.addObject("endIndex", end);
		mav.addObject("currentIndex", current);
		return mav;
	}

	@RequestMapping(value = "/showGallery", method = RequestMethod.GET)
	public ModelAndView showModalGallery(@RequestParam("itemId") Long itemID) {
		ModelAndView mav = new ModelAndView("imageGallery");
		ShoppingItem shoppingItem = service.findItemById(itemID);
		List<String> images = service.encodeImagesPath(shoppingItem.getImageForGallery());
		mav.addObject("images", images);
		return mav;
	}
}
