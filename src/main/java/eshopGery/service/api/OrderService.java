package eshopGery.service.api;

import java.util.List;
import java.util.Map;

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

    public void removeItemFromOrder(Order order, Long itemID, String size);

	public String encodeShoppingItem(Map<ShoppingItem, Integer> items);

    public int calculateTotalPrice(Map<ShoppingItem, Integer> items);

	public Order findItemById(Long id);

	public void createOrder(Order order);

	public List<Order> getAllItems();

	public void sendEmail(String email, Order order);

}
