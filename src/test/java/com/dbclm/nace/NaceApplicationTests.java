package com.dbclm.nace;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import com.dbclm.nace.entity.Item;


/**
 * @author errab
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NaceApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	private static RestTemplate restTemplate;

	private static Item item;

	@Autowired
	private TestItemRepository testItemRepository;

	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
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

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port + "").concat("/nace");
	}

	@Test
	public void testAddItem() {

		Item response = restTemplate.postForObject(baseUrl + "/putNaceDetails", item, Item.class);
		assertEquals("1234", response.getOrderId());
		assertEquals(response.getOrderId(), testItemRepository.findById(response.getId()).get().getOrderId());
	}

	@Test
	@Sql(statements = "INSERT INTO ITEM (order_id, also_includes, code, description, excludes, includes, level,  parent, reference, rulings) "
			+ "VALUES ('999','AC', '','', '','','','', '','')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM ITEM WHERE order_id='999'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetItem() {
		Item item = restTemplate.getForObject(baseUrl + "/getNaceDetails/999", Item.class);
		assertNotNull(item);
		assertEquals("999", testItemRepository.findById(item.getId()).get().getOrderId());
	}

}
