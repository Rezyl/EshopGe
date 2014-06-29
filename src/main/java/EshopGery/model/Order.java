package EshopGery.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 22.6.14
 */
public class Order {

    private User user;
    private List<ShoppingItem> shoppingItems;
    private TypePayment typeOfPayment;
    private BigDecimal totalPrice;
    private DateTime dateCreate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public TypePayment getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(TypePayment typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public DateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(DateTime dateCreate) {
        this.dateCreate = dateCreate;
    }
}
