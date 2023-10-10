package br.com.rodr1gotavares.item_catalog;

import br.com.rodr1gotavares.item_catalog.api.dto.ItemDTO;
import br.com.rodr1gotavares.item_catalog.entity.Item;
import br.com.rodr1gotavares.item_catalog.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class ItemCatalogApplicationTests {

	@Autowired
	private ItemService itemService;

	@Test
	void contextLoads() {

	}

	@Test
	@DisplayName("Tests a item creation, persistence and read")
	void createAndReadItemSuccessCase() {
		Item expectedItem = new Item(
				1L,
				null,
				"Test item",
				new BigDecimal("200.00"),
				10,
				new BigDecimal("2000.00"),
				LocalDate.now(),
				LocalDate.now()
		);
		Item testItem = new Item(
				"Test item",
				new BigDecimal("200.00"),
				10
		);
		this.itemService.create(testItem);
		ItemDTO result = this.itemService.readById(1L).get();
		assertEquals(new ItemDTO(expectedItem), result);
	}
}

