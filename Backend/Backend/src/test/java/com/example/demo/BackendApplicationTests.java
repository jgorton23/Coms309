package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void testItemAdd() {
		Person p = new Person();
		ArrayList<ItemAdd> itemlst = new ArrayList<ItemAdd>();
		p.setItemsBought(itemlst);
		ItemAdd item = new ItemAdd();
		item.setPrice(25);
		p.addItem(item);
		
		assertThat(item == p.getItemsBought());
	}
	
	
	@Test
	public void testOverBudget() {
		Person p = new Person();
		p.setBudget(24);
		ArrayList<ItemAdd> itemlst = new ArrayList<ItemAdd>();
		p.setItemsBought(itemlst);
		ItemAdd item = new ItemAdd();
		item.setPrice(25);
		
		assertThat(p.overBudget());
	}
	
}
