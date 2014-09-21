package eshopGery.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eshopGery.dao.OrderDao;
import eshopGery.model.Order;
import eshopGery.model.ShoppingItem;
import eshopGery.model.TypePayment;
import eshopGery.service.api.OrderService;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 22.6.14
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public void addItemToOrder(Order order, ShoppingItem item) {
		Map<ShoppingItem, Integer> map = order.getShoppingItems();
		if (map.containsKey(item)) {
			Integer oldQuantity = map.get(item);
			map.put(item, oldQuantity + 1);
		} else {
			map.put(item, 1);
		}
	}

	@Override
	public void removeItemFromOrder(Order order, Long itemID, String size) {
		Map<ShoppingItem, Integer> map = order.getShoppingItems();
		List<ShoppingItem> candidateForRemove = findItemsFromOrder(order, itemID);

		for (ShoppingItem shoppingItem : candidateForRemove) {
			if (size.equals(shoppingItem.getSize())) {
				if (map.get(shoppingItem) > 1) {
					Integer oldQuantity = map.get(shoppingItem);
					map.put(shoppingItem, oldQuantity - 1);
				} else {
					map.remove(shoppingItem);
				}
			}
		}
	}

	private List<ShoppingItem> findItemsFromOrder(Order order, Long itemID) {
		List<ShoppingItem> result = new ArrayList<ShoppingItem>();
		for (ShoppingItem shoppingItem : order.getShoppingItems().keySet()) {
			if (itemID.equals(shoppingItem.getItemId())) {
				result.add(shoppingItem);
			}
		}
		return result;
	}

	@Override
	public Order findItemById(Long id) {
		return orderDao.getById(id);
	}

	@Override
	public void createOrder(Order order) {
		orderDao.save(order);
	}

	@Override
	public List<Order> getAllItems() {
		return orderDao.getAllItems();
	}

	@Override
	public String encodeShoppingItem(Map<ShoppingItem, Integer> items) {
		StringBuilder result = new StringBuilder();
		for (Map.Entry<ShoppingItem, Integer> entry : items.entrySet()) {
			for (int i = 0; i < entry.getValue(); i++) {
				result.append(entry.getKey().getItemId()).append("|");
			}
		}
		return result.toString();
	}

	@Override
	public int calculatePriceOfItems(Map<ShoppingItem, Integer> items) {
		int price = 0;
		for (Map.Entry<ShoppingItem, Integer> shoppingItemIntegerEntry : items.entrySet()) {
			ShoppingItem shoppingItem = shoppingItemIntegerEntry.getKey();
			int quantity = shoppingItemIntegerEntry.getValue();
			price += shoppingItem.getPrice() * quantity;
		}
		return price;
	}

	@Override
	public int calculateTotalPrice(Map<ShoppingItem, Integer> items, TypePayment typePayment) {
		return calculatePriceOfItems(items) + typePayment.getPricePayment();
	}
}
