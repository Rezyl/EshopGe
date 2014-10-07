package eshopGery.model;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 3.8.14
 */
public enum Category {
//
//    ALTERNATIVE("Alternativní"),
//    SHORT_SOCKS("Krátké"),
//    MEGALOMANIA("Megalomanské"),
//    STOCKINGS("Podkolenky"),
//    SPORT_SOCKS("Sportovní"),
//    STRIPES_SOCKS("Proužkované"),
//    DOT_SOCKS("Puntíkované"),
//    STYLE_SOCKS("Stylové"),
//    WEED_SOCKS("Weed time");

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
