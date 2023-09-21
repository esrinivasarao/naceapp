package com.dbclm.nace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dbclm.nace.entity.Item;
import com.dbclm.nace.repository.ItemRepository;


/**
 * @author Srinivas
 *
 */
@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	public Item save(Item item) {
		return itemRepository.save(item);
		
	}
	
	public List<Item> saveItems(List<Item> items) {
		return itemRepository.saveAll(items);
		
	}
	
	public Item findByOrderId(String orderId) {
		return itemRepository.findByOrderId(orderId);
	}
	
	public List<Item> findAll(){
		return itemRepository.findAll();
	}
}
