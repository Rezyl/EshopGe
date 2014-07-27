package eshopGery.service.api;

import eshopGery.model.ShoppingItem;

import java.util.List;

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

    public ShoppingItem findItemById(Long id);

    public void addItemToOrder();

    public void deleteItemFromOrder();

    public List<ShoppingItem> getAllItemsByUserId(String UserId);










}
