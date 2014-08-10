package eshopGery.model;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 22.6.14
 */
public enum TypePayment {

	CASH("Hotovost"), CREDIT_CARD("Převodem");

	private String displayName;

	TypePayment(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
