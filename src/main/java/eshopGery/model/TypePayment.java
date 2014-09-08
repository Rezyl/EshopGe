package eshopGery.model;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 22.6.14
 */
public enum TypePayment {

	CASH("Dobírka, Česká pošta (+89Kč)", 89), CREDIT_CARD("Převodem na účet, Česká pošta (+49Kč)", 49),
    PERSONAL("Osobní odběr v Hradci Králové (0Kč)", 0);

	private String displayName;
    private Integer pricePayment;

	TypePayment(String displayName, Integer pricePayment) {
		this.displayName = displayName;
        this.pricePayment = pricePayment;
	}

	public String getDisplayName() {
		return displayName;
	}

    public Integer getPricePayment() {
        return pricePayment;
    }
}
