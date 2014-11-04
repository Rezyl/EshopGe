package eshopGery.service.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import eshopGery.dao.ShoppingItemDao;
import eshopGery.model.Category;
import eshopGery.model.ShoppingItem;
import eshopGery.repository.ShoppingItemRepository;
import eshopGery.service.api.EshopConstants;
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

	@Autowired
	private ShoppingItemRepository shoppingItemRepository;

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
	public Page<ShoppingItem> getItemsToPage(int pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, EshopConstants.PAGE_SIZE, Sort.Direction.DESC);
		return shoppingItemRepository.findAll(request);
	}

	@Override
	public Page<ShoppingItem> getItemsToPageByCategory(int pageNumber, Category category) {
		PageRequest request = new PageRequest(pageNumber - 1, EshopConstants.PAGE_SIZE);
		return shoppingItemRepository.findByCategory(category, request);
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

	@Override
	public String decodeImagesPath(List<String> imagesPath) {
		StringBuilder result = new StringBuilder();
		for (String path : imagesPath) {
			result.append(path).append("|");
		}
		return result.toString();
	}

	@Override
	public List<String> encodeImagesPath(String decodePath) {
		String[] imagesPath = decodePath.split("\\|");
		return Arrays.asList(imagesPath);
	}
}
