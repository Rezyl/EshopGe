package eshopGery.model;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 3.8.14
 */
public enum Category {

	LONG_SOCKS("Dlouhé ponožky"), SHORT_SOCKS("Krátké ponožky"), WEED_SOCKS("Weed ponožky"), STRIPES_SOCKS("Pruhované ponožky"), CUBE_SOCKS("Kostičkované"), MEGALOMAN(
			"Megalomanské"), CREATIVE("Kreativní"), RANDOM("Náhodné");

	String displayName;

	Category(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
