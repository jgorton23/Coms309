package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mockito.account.Account;
import com.mockito.account.AccountRepository;
import com.mockito.account.AccountService;

public class SubControllerTest {

	@Mock
	SubscriptionDatabase subDB;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	// @Test
	// public void getAccountByIdTest() {
	// 	when(repo.getAccountByID(1)).thenReturn(new Account(1, "jDoe", "123456", "jDoe@gmail.com"));

	// 	Account acct = acctService.getAccountByID(1);

	// 	assertEquals("jDoe", acct.getUserID());
	// 	assertEquals("123456", acct.getPassword());
	// 	assertEquals("jDoe@gmail.com", acct.getEmail());
	// }

	@Test
	public void findAllItemsTest() {
		List<Subscription> list = new ArrayList<Subscription>();
		Subscription s1 = new Subscription();
		Subscription s2 = new Subscription();
		Subscription s3 = new Subscription();

		list.add(s1);
		list.add(s2);
		list.add(s3);

		when(repo.findAll()).thenReturn(list);

		List<Subscription> subList = subDB.findAll();

		assertEquals(3, subList.size());
		verify(repo, times(1)).findAll();
	}

}
