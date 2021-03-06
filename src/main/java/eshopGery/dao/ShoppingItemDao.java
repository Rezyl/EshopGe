package eshopGery.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eshopGery.model.ShoppingItem;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 26.7.14
 */
@Repository
@Transactional
public class ShoppingItemDao extends AbstractDao<ShoppingItem> {
	@Override
	public Class<ShoppingItem> getClazz() {
		return ShoppingItem.class;
	}

	@Override
	public List<ShoppingItem> searchByName(String name) {
		return null;
	}
}
