package com.dbclm.nace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dbclm.nace.entity.Item;


/**
 * @author Srinivas
 *
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

	Item findByOrderId(String orderId);
}
