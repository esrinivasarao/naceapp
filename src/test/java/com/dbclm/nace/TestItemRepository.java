package com.dbclm.nace;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbclm.nace.entity.Item;



/**
 * @author errab
 *
 */
public interface TestItemRepository extends JpaRepository<Item,Long>{

}
