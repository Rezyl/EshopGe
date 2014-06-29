package EshopGery.service.api;

import EshopGery.model.ShoppingItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 22.6.14
 */
public interface ShopItemService {

    public void addItemToOrder();

    public void deleteItemFromOrder();

    public List<ShoppingItem> getAllItemsByUserId(String UserId);










}
