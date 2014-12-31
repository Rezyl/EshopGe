package eshopGery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import eshopGery.model.Order;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 4.11.14
 */
@Transactional(readOnly = true)
public interface OrderRepository extends JpaRepository<Order, Long> {

	public final static String FIND_ORDERS_BY_FILTER_QUERY = "SELECT o FROM Order o WHERE o.complete= :complete AND o.paid= :paid";

	@Query(FIND_ORDERS_BY_FILTER_QUERY)
	public List<Order> findByFilter(@Param("complete") boolean complete, @Param("paid") boolean paid);
}
