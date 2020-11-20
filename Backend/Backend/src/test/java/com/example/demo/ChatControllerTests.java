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

import com.example.demo.*;

public class ChatControllerTests {

	@Mock
	MessageDatabase msgDB;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void chatHistoryTest() {
		List<Message> messages = new ArrayList<Message>();
		Message chat1 = new Message();
		Message chat2 = new Message();
		Message chat3 = new Message();

		messages.add(chat1);
		messages.add(chat2);
		messages.add(chat3);

		when(msgDB.findAll()).thenReturn(messages);

		List<Message> chatHistory = msgDB.findAll();

		assertEquals(3, chatHistory.size());
		verify(msgDB, times(1)).findAll();
    }

}
