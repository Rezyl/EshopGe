package eshopGery.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 22.6.14
 */
@Entity
@Table(name = "OrderItem")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderID;

    @Transient
    private Map<ShoppingItem, Integer> shoppingItems = new HashMap<ShoppingItem, Integer>();
    @Column
	private String shoppingItemCodes;
	@Column
	@Enumerated(EnumType.STRING)
	private TypePayment typeOfPayment;
    @Column
	private Integer totalPrice;
    @Column
	private String dateCreate;
	@Column
	private Boolean complete;
	@Column
	private Boolean paid;
	// User data
	@Column
	private String name;
	@Column
	private String surname;
	@Column
	private String street;
	@Column
	private String city;
	@Column
	private String psc;
	@Column
	private String email;
	@Column
	private String mobile;
	@Column
	private String notes;

	public Long getOrderID() {
        return orderID;
    }

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

    public Map<ShoppingItem, Integer> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(Map<ShoppingItem, Integer> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public TypePayment getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(TypePayment typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
    }

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public String getShoppingItemCodes() {
		return shoppingItemCodes;
	}

	public void setShoppingItemCodes(String shoppingItemCodes) {
		this.shoppingItemCodes = shoppingItemCodes;
	}

	// User data

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPsc() {
		return psc;
	}

	public void setPsc(String psc) {
		this.psc = psc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
