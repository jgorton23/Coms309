package com.example.demo;


import java.util.ArrayList;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller      // this is needed for this to be an endpoint to springboot
@ServerEndpoint(value = "/chat/{sender}/{receiver}")  // this is Websocket url
public class Chat {

  // cannot autowire static directly (instead we do it by the below
  // method
	private static MessageDatabase msgDB; 

	/*
   * Grabs the MessageRepository singleton from the Spring Application
   * Context.  This works because of the @Controller annotation on this
   * class and because the variable is declared as static.
   * There are other ways to set this. However, this approach is
   * easiest.
	 */
	@Autowired
	public void setMessageRepository(MessageDatabase db) {
		msgDB = db;  // we are setting the static variable
	}

	// Store all socket session and their corresponding username.
	// private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
    // private static Map<String, Session> usernameSessionMap = new Hashtable<>();
    private String sender;
    private String receiver;

	private final Logger logger = LoggerFactory.getLogger(Chat.class);

	@OnOpen
	public void onOpen(Session session, @PathParam("sender") String sender, @PathParam("receiver") String receiver) 
      throws IOException {

		logger.info("Entered into Open");

    // store connecting user information
		// sessionUsernameMap.put(session, username);
        // usernameSessionMap.put(username, session);
        this.sender = sender;
        this.receiver = receiver;

		//Send chat history to the newly connected user
		sendMessage(sender, getChatHistory(sender, receiver));
		
    // broadcast that new user joined
		String message = "User:" + sender + " has Joined the Chat";
        sendMessage(sender, message);
        sendMessage(receiver, message);
	}


	@OnMessage
	public void onMessage(Session session, String message) throws IOException {

		// Handle new messages
		logger.info("Entered into Message: Got Message:" + message);
        String username = sender;//sessionUsernameMap.get(session);
        String destUsername = receiver;

        // Direct message to a user using the format "@username <message>"
		// if (message.startsWith("@")) {
		// 	String destUsername = message.split(" ")[0].substring(1); 

            // send the message to the sender and receiver
			sendMessage(destUsername, "[DM] " + username + ": " + message);
			sendMessage(username, "[DM] " + username + ": " + message);

		//} 
        // else { // broadcast
		// 	broadcast(username + ": " + message);
		// }

		// Saving chat history to repository
		msgDB.save(new Message(username, destUsername, message));
	}


	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered into Close");

    // // remove the user connection information // idk what should happen on close but not this
	// 	String username = sessionUsernameMap.get(session);
	// 	sessionUsernameMap.remove(session);
	// 	usernameSessionMap.remove(username);

    // // broadcase that the user disconnected
	// 	String message = username + " disconnected";
	// 	broadcast(message);
	}


	@OnError
	public void onError(Session session, Throwable throwable) {
		// Do error handling here
		logger.info("Entered into Error");
		throwable.printStackTrace();
	}


	private void sendMessage(String receiver, String message) {
		try {
			session.getBasicRemote().sendText(message);
		} 
    catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}


    // useless - just from tutorial saved for now in case I want to reference while writing something useful
    //
	// private void broadcast(String message) {
	// 	sessionUsernameMap.forEach((session, username) -> {
	// 		try {
	// 			session.getBasicRemote().sendText(message);
	// 		} 
    //   catch (IOException e) {
	// 			logger.info("Exception: " + e.getMessage().toString());
	// 			e.printStackTrace();
	// 		}

	// 	});

	// }
	

  // Gets the Chat history from the repository
	private String getChatHistory(String sender, String receiver) {
        List<Message> l = msgDB.findAll();
        List<Message> messages = new ArrayList<>();
        for(int i = 0; i < l.size(); i++){
            if(l.get(i).getSender() == sender && l.get(i).getReceiver() == receiver){
                messages.add(l.get(i));
            }
        }
    
    // convert the list to a string
		StringBuilder sb = new StringBuilder();
		if(messages != null && messages.size() != 0) {
			for (Message message : messages) {
				sb.append(message.getSender() + ": " + message.getContent() + "\n");
			}
		}
		return sb.toString();
	}

}
