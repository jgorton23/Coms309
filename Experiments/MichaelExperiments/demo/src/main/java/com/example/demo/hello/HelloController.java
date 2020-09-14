package com.example.demo.hello;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
	
	private modelHello helloMessage = new modelHello("Hello, this statement was made by an model(?).");
	
	@Autowired
	DataBase db;
	
	@GetMapping("/LocalDB/{id}")
	LocalDB getLocalDB(@PathVariable Integer id) {
		return db.findOne(id);
	}
	
	@RequestMapping("/LocalDBs")
	List<LocalDB> hello() {
		return db.findAll();
	}
	
	@PostMapping("/LocalDB")
	LocalDB createLocalDB(@RequestBody LocalDB p) {
		db.save(p);
		return p;
	}
	
	/*
	@RequestMapping("/hi")
	public String sayHi() {
		return helloMessage.getMessage();
	}
	
	@PostMapping("/hi")
	public void addMessage(@RequestBody String s) {
		helloMessage.addString(s);
	}
	*/
}
