package eshopGery.model;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 22.6.14
 */
public enum TypePayment {

	CASH("Dobírka, Česká pošta (+89Kč)"), CREDIT_CARD("Převodem na účet, Česká pošta (+49Kč)");

	private String displayName;

	TypePayment(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
