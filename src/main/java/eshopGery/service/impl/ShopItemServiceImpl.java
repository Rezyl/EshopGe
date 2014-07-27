package eshopGery.service.impl;

import eshopGery.dao.ShoppingItemDao;
import eshopGery.model.ShoppingItem;
import eshopGery.service.api.ShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 22.6.14
 */
@Service
public class ShopItemServiceImpl implements ShopItemService {

	@Autowired
	private ShoppingItemDao shoppingItemDao;

	@Override
	public void createItem(ShoppingItem shoppingItem) {
		shoppingItemDao.save(shoppingItem);
	}

	@Override
	public void updateItem(ShoppingItem shoppingItem) {
		shoppingItemDao.update(shoppingItem);
	}

	@Override
	public void deleteItem(ShoppingItem shoppingItem) {
		shoppingItemDao.deleteItem(shoppingItem);
	}

	@Override
	public List<ShoppingItem> getAllItems() {
		return shoppingItemDao.getAllItems();
	}

	@Override
	public ShoppingItem findItemById(Long id) {
		return shoppingItemDao.getById(id);
	}

	@Override
	public void addItemToOrder() {

	}

	@Override
	public void deleteItemFromOrder() {

	}

	@Override
	public List<ShoppingItem> getAllItemsByUserId(String UserId) {
		return null;
	}
}
