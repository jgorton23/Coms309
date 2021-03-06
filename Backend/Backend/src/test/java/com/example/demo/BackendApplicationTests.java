package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

	@Test
	public void testSubscribe() {
		Person p = new Person();
		ArrayList<Subscription> sublst = new ArrayList<Subscription>();
		p.setSubscriptionsBought(sublst);
		Subscription s = new Subscription();
		s.setNotes("Subscribed");
		p.subscribe(s);

		assertThat(p.getSubscriptionsBought() == s);
	}
	
	@Test
	public void testAddFriend() {
		Person p = new Person();
		ArrayList<Person> friends = new ArrayList<Person>();
		p.setFriends(friends);
		Person friend = new Person();
		p.addFriend(friend);
		
		assertThat(friend == p.getFriends());
	}
	
	@Test
	public void testTotalSubscriptionCost() {
		Person p = new Person();
		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();
		p.setSubscriptionsBought(subscriptions);
		Subscription subscription1 = new Subscription();
		subscription1.setPrice( (double) 50);
		p.subscribe(subscription1);
		Subscription subscription2 = new Subscription();
		subscription2.setPrice( (double) 150);
		p.subscribe(subscription2);
		
		assertThat(p.totalSubscriptionCost() == 200);
	}

	@Test
	public void testGetMessageContent() {
		String content = "this is the content";
		Message m = new Message("jacob", content);
		assertThat(m.getContent() == "this is the content");
	}

	@Test
	public void testGetMessageUsername() {
		String content = "";
		Message m = new Message("jacob", content);
		assertThat(m.getUserName() == "jacob");
	}
}
