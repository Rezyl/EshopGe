package eshopGery.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eshopGery.model.Order;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 26.7.14
 */
@Repository
@Transactional
public class OrderDao extends AbstractDao<Order> {
	@Override
	public Class<Order> getClazz() {
		return Order.class;
	}

	@Override
	public List<Order> searchByName(String name) {
		return null;
	}
}
