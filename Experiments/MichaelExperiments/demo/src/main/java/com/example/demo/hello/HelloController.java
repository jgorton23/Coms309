package com.example.demo.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.modelHello;

@RestController
public class HelloController {
	
	private modelHello helloMessage = new modelHello("Hello, this statement was made by an model(?).");
	
	@RequestMapping("/hi")
	public String sayHi() {
		return helloMessage.getMessage();
	}
}
