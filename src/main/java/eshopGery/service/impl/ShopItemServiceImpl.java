package eshopGery.service.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import eshopGery.dao.ShoppingItemDao;
import eshopGery.model.Category;
import eshopGery.model.ShoppingItem;
import eshopGery.service.api.ShopItemService;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 22.6.14
 */
@Service
public class ShopItemServiceImpl implements ShopItemService {

	@Autowired
	private ShoppingItemDao shoppingItemDao;

	@Override
	public void createItem(ShoppingItem shoppingItem) {
		shoppingItemDao.save(shoppingItem);
	}

	@Override
	public void updateItem(ShoppingItem shoppingItem) {
		shoppingItemDao.update(shoppingItem);
	}

	@Override
	public void deleteItem(ShoppingItem shoppingItem) {
		shoppingItemDao.deleteItem(shoppingItem);
	}

	@Override
	public List<ShoppingItem> getAllItems() {
		return shoppingItemDao.getAllItems();
	}

	@Override
	public ShoppingItem findItemById(Long id) {
		return shoppingItemDao.getById(id);
	}

	@Override
	public List<ShoppingItem> getAllByCategory(Category category) {
		List<ShoppingItem> result = new ArrayList<ShoppingItem>();
		for (ShoppingItem shoppingItem : shoppingItemDao.getAllItems()) {
			if (category.equals(shoppingItem.getCategory())) {
				result.add(shoppingItem);
			}
		}
		return result;
	}

	@Override
	public String uploadImage(String pathToDirectory, MultipartFile file) {
		File rootDir = new File(pathToDirectory);
		// if the directory does not exist, create it
		if (!rootDir.exists()) {
			rootDir.mkdirs();
		}
		String fullPath = pathToDirectory + file.getOriginalFilename();
		try {
			writeFileAsBytes(fullPath, file.getBytes());
			int splitIndex = fullPath.indexOf("resources");
			return fullPath.substring(splitIndex);
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public boolean deleteImage(String fullPath) {
		File file = new File(fullPath);
		return file.delete();
	}

	private void writeFileAsBytes(String fullPath, byte[] bytes) throws IOException {
		OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fullPath));
		InputStream inputStream = new ByteArrayInputStream(bytes);
		int token = -1;

		while ((token = inputStream.read()) != -1) {
			bufferedOutputStream.write(token);
		}
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
		inputStream.close();
	}

	@Override
	public void addItemToOrder() {

	}

	@Override
	public void deleteItemFromOrder() {

	}

	@Override
	public List<ShoppingItem> getAllItemsByUserId(String UserId) {
		return null;
	}
}
