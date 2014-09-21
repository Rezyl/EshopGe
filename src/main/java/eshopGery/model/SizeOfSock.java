package eshopGery.model;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 10.8.14
 */
public enum SizeOfSock {

	SIZE_34_41("34-41"), SIZE_35_40("35-40"), SIZE_34_39("34-39"), SIZE_39_46("39-46"), SIZE_35_42("35-42"), SIZE_34_42("34-42");

	private String displayName;

	SizeOfSock(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
