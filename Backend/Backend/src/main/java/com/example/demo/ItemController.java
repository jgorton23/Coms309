package com.example.demo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

	@Autowired
	MyDatabase personDB;
	@Autowired
	ItemDatabase ItemDB;
	
	
	@RequestMapping("/getItem")
	public List<ItemAdd> findAllItems() {
		return ItemDB.findAll();
	}
	
	@PostMapping("/addItem")
	ItemAdd createItem(@RequestBody ItemAdd item) {
		Optional<Person> optionalP = personDB.findById(item.getPerson().getId());
		if (optionalP.isPresent()) {
			Person p = optionalP.get();
			item.setPerson(p);
			ItemDB.save(item);
			return item;
		}
		else {
			return null;
		}
	}
	/*
	@DeleteMapping("/person/{id}")
	String deletePerson(@PathVariable Integer id) {
		db.delete(id);
		return "deleted " + id;
	}
	*/

}
