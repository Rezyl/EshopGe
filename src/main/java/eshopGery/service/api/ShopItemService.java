package eshopGery.service.api;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import eshopGery.model.Category;
import eshopGery.model.ShoppingItem;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 22.6.14
 */
public interface ShopItemService {

	public void createItem(ShoppingItem shoppingItem);

	public void updateItem(ShoppingItem shoppingItem);

	public void deleteItem(ShoppingItem shoppingItem);

	public List<ShoppingItem> getAllItems();

	public List<ShoppingItem> getAllByCategory(Category category);

	public ShoppingItem findItemById(Long id);

	public String uploadImage(String pathToDirectory, MultipartFile file);

    public String decodeImagesPath(List<String> imagesPath);

    public List<String> encodeImagesPath(String decodePath);

	public boolean deleteImage(String fullPath);

	public void addItemToOrder();

	public void deleteItemFromOrder();

	public List<ShoppingItem> getAllItemsByUserId(String UserId);

}
