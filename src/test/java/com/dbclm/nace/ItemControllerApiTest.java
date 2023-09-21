package com.dbclm.nace;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.dbclm.nace.controller.ItemController;
import com.dbclm.nace.entity.Item;
import com.dbclm.nace.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author errab
 *
 */
@WebMvcTest(ItemController.class)
public class ItemControllerApiTest {

	private static final String END_POINT_PATH = "/nace";

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private ItemService itemService;

	private static Item item;

	@BeforeAll
	public static void init() {
		item = new Item();
		item.setOrderId("1234");
		item.setAlsoIncludes("");
		item.setCode("");
		item.setDescription("");
		item.setExcludes("");
		item.setIncludes("");
		item.setLevel("");
		item.setParent("");
		item.setReference("");
		item.setRulings("");

	}

	@Test
	public void testShouldReturn400BadRequest() throws Exception {
		Item newItem = new Item();
		newItem.setOrderId(null);

		String requestBody = objectMapper.writeValueAsString(newItem);

		mockMvc.perform(post(END_POINT_PATH + "/putNaceDetails").contentType("application/json").content(requestBody))
				.andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void testShouldReturn200Created() throws Exception {

		String requestBody = objectMapper.writeValueAsString(item);

		mockMvc.perform(post(END_POINT_PATH + "/putNaceDetails").contentType("application/json").content(requestBody))
				.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testGetShouldReturn404NotFound() throws Exception {
		String orderId = "1234";
		String requestURI = END_POINT_PATH + "/getNaceDetails/" + orderId;
		
		Mockito.when(itemService.findByOrderId(orderId)).thenReturn(null);
		
		mockMvc.perform(get(requestURI))
			.andExpect(status().isNotFound())
			.andDo(print());
	}
	
	@Test
	public void testGetShouldReturn200OK() throws Exception {
		String orderId = "1234";
		String requestURI = END_POINT_PATH + "/getNaceDetails/" + orderId;
		
		Mockito.when(itemService.findByOrderId(orderId)).thenReturn(item);
		
		mockMvc.perform(get(requestURI))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.orderId", is(orderId)))
			.andDo(print());
	}
	
	@Test
	public void testListShouldReturn204NoContent() throws Exception {
		Mockito.when(itemService.findAll()).thenReturn(new ArrayList<>());
		
		mockMvc.perform(get(END_POINT_PATH + "/getNaceDetails"))
			.andExpect(status().isNoContent())
			.andDo(print());		
	}
	
	@Test
	public void testListShouldReturn200WithContent() throws Exception {
		List<Item> itemList = new ArrayList<>(); 
		itemList.add(item); 
		
		Mockito.when(itemService.findAll()).thenReturn(itemList);
		
		mockMvc.perform(get(END_POINT_PATH + "/getNaceDetails"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].orderId",is("1234")))
			.andDo(print());		
	}
	

}
