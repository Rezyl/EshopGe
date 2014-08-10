package eshopGery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eshopGery.dao.OrderDao;
import eshopGery.model.Order;
import eshopGery.model.ShoppingItem;
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
		order.getShoppingItems().add(item);
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
	public void sendEmail(String email, Order order) {
		// TODO implement sending emails
	}

	@Override
	public String encodeShoppingItem(List<ShoppingItem> items) {
		StringBuilder result = new StringBuilder();
		for (ShoppingItem item : items) {
			result.append(item.getItemId()).append("|");
		}
		return result.toString();
    }
}
