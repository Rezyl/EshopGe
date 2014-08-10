package eshopGery.service.api;

import java.util.List;

import eshopGery.model.Order;
import eshopGery.model.ShoppingItem;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 22.6.14
 */
public interface OrderService {

	public static final String ORDER_SESSION_OBJECT = "OrderObj";

	public void addItemToOrder(Order order, ShoppingItem item);

	public String encodeShoppingItem(List<ShoppingItem> items);

	public Order findItemById(Long id);

	public void createOrder(Order order);

	public List<Order> getAllItems();

	public void sendEmail(String email, Order order);

}
