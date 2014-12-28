package eshopGery.config;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;

import eshopGery.service.api.EshopConstants;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 28.12.14
 */
public class Initialization {

	public Initialization(ServletContext servletContext) {
		try {
			copyImages(EshopConstants.getRootDirToSaveImages(), EshopConstants.getResourcesDirToSaveImages(servletContext));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void copyImages(String rootDirToSaveImages, String targetDir) throws IOException {
		File sourceDirectory = new File(rootDirToSaveImages);
		File targetDirectory = new File(targetDir);
		if (!targetDirectory.exists()) {
			targetDirectory.mkdir();
		}

		if (sourceDirectory.isDirectory()) {
			String[] fileNames = sourceDirectory.list();
			for (String fileName : fileNames) {
				File source = new File(rootDirToSaveImages + fileName);
				File target = new File(targetDir + fileName);
				FileUtils.copyFile(source, target);
			}
		}
	}
}
