package eshopGery.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import eshopGery.model.Category;
import eshopGery.model.ShoppingItem;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 4.11.14
 */
@Transactional(readOnly = true)
public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {

	public final static String FIND_BY_CATEGORY_QUERY = "SELECT i " + "FROM ShoppingItem i WHERE i.category= :category";

	@Query(FIND_BY_CATEGORY_QUERY)
	public Page<ShoppingItem> findByCategory(@Param("category") Category category, Pageable page);
}
