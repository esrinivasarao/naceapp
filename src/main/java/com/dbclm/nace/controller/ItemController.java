package com.dbclm.nace.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dbclm.nace.entity.Item;
import com.dbclm.nace.service.ItemService;
import com.dbclm.nace.utility.CsvParser;


/**
 * @author Srinivas
 *
 */
@RestController
@RequestMapping(value = "/nace")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private 	CsvParser  csvParser; 
	
	/**
	 * This method will take csv file and insert data into databse table
	 * @return Response Entity with Message if data inserted successfully
	 */
	@PostMapping("/putNaceDetails")
    public ResponseEntity<String> uploadCSVFile() {
        try {
            	      
            List<Item> items = csvParser.parseCsvFile(".\\src\\main\\resources\\DataFile1.csv");
            itemService.saveItems(items);
            return ResponseEntity.ok("Data Saved successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading the file");
        }
        
	}
	/**
	 * This method will accept itemDto and save itemdetails 
	 * @param itemDto
	 * @return Response Entity object 
	 */
	@PostMapping("/putNaceDetail")
    public ResponseEntity<Item> putNaceDetails(@RequestBody @Validated Item itemDto) {
        Item item = itemService.save(itemDto);
        return ResponseEntity.ok(item);
    }
	
	/**
	 * This method will return item details for particular order id 
	 * @param id
	 * @return Response Entity with Item object if found else will return notfound 
	 */
	@GetMapping("/getNaceDetails/{orderId}")
    public ResponseEntity<Item> getNaceDetails(@PathVariable("orderId") String id) {
        Item item = itemService.findByOrderId(id);
        if(item!=null) {
        	return ResponseEntity.ok(item);
        }else {
        	return ResponseEntity.notFound().build();
        }
    }
	
	/**
	 * This method will return list of items present in the database 
	 * @return List of items present 
	 */
	@GetMapping("/getNaceDetails")
	public ResponseEntity<?> list() {
		List<Item> itemList = itemService.findAll();
		if (itemList.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(itemList);
	}
}
