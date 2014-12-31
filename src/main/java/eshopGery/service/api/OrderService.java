package eshopGery.service.api;

import java.util.List;
import java.util.Map;

import eshopGery.model.Order;
import eshopGery.model.ShoppingItem;
import eshopGery.model.TypePayment;

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

    public Map<ShoppingItem, Integer> decodeShoppingItem(String code);

	public int calculatePriceOfItems(Map<ShoppingItem, Integer> items);

	public int calculateTotalPrice(Map<ShoppingItem, Integer> items, TypePayment typePayment);

	public Order findItemById(Long id);

	public void createOrder(Order order);

    public void updateOrder(Order order);

	public List<Order> getAllItems();

    public List<Order> applyFilter(boolean complete, boolean paid);

    public void removePiecesOfShoppingItems(Map<ShoppingItem, Integer> items);
}
