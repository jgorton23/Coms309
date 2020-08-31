package com.example.demo.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ColeCoolController {
	
	@RequestMapping("/cole")
	public String sayHi() {
		return "Cole is very cool! This is the third controller.";
	}
}