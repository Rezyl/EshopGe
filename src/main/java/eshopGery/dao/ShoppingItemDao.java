package eshopGery.dao;

import eshopGery.model.ShoppingItem;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 26.7.14
 */
@Component
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
