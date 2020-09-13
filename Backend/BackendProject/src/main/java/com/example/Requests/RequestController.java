package com.example.Requests;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
	
@RestController
public class RequestController {

	public RequestModel requestModelMessage = new RequestModel("Hello, this statement was made by an model.", 1);
	
	@RequestMapping("/hello2")
	public String sayHi() {
		return requestModelMessage.getMessage();
	}
	
	@PostMapping("/hello2")
	public void addMessage(@RequestBody String s) {
		requestModelMessage.addString(s);
	}
	
	
}
