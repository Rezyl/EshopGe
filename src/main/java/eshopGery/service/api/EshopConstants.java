package eshopGery.service.api;

import javax.servlet.ServletContext;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 31.8.14
 */
public class EshopConstants {

	public static final String ADMIN_PART_PREFIX = "admin/";

	public static final int PAGE_SIZE = 6;

	public static final String HOME_DIRECTORY = System.getProperty("user.home");
	public static final String NAME_OF_DIR_TO_SAVE_IMAGES = "socksImages";

	public static String getRootDirToSaveImages() {
		String fileSeparator = System.getProperty("file.separator");
		return EshopConstants.HOME_DIRECTORY + fileSeparator + NAME_OF_DIR_TO_SAVE_IMAGES + fileSeparator;
	}

	public static String getResourcesDirToSaveImages(ServletContext servletContext) {
		String fileSeparator = System.getProperty("file.separator");
		return servletContext.getRealPath("resources") + fileSeparator + NAME_OF_DIR_TO_SAVE_IMAGES + fileSeparator;
	}
}
