package com.example.demo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

	@Autowired
	MyDatabase db;
	
	@RequestMapping("/persons")
	List<Person> hello() {
		return db.findAll();
	}

	@PostMapping("/person")
	Person createPerson(@RequestBody Person p) {
		db.save(p);
		return p;
	}
	
	@RequestMapping("/persons/{id}")
	Optional<Person> findperson(@PathVariable int id) {
		return db.findById(id);
	}
	
	@RequestMapping("/getItems/{id}")
	List<ItemAdd> getItems(@PathVariable int id) {
		Optional<Person> optionalP = db.findById(id);
		if (optionalP.isPresent()) {
			Person p = optionalP.get();
			return p.getItemsBought();
		}
		else {
			return Collections.emptyList();
		}
	}
	
	@RequestMapping("/getSubscriptions/{id}")
	List<Subscription> getSubscriptions(@PathVariable int id) {
		Optional<Person> optionalP = db.findById(id);
		if (optionalP.isPresent()) {
			Person p = optionalP.get();
			return p.getSubscriptionsBought();
		}
		else {
			return Collections.emptyList();
		}
	}
	
	/*
	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
	List<ItemAdd> getId(@PathVariable int id) {
	    return ((Person) db.findById(id)).getItemsBought();
	}
	
	/*
	@PutMapping("/person/{id}")
	Person updatePerson(@RequestBody Person p, @PathVariable Integer id) {
		Person old_p = db.findOne(id);
		old_p.setAddress(p.address);
		db.save(old_p);
		return old_p;
	}

	@DeleteMapping("/person/{id}")
	String deletePerson(@PathVariable Integer id) {
		db.delete(id);
		return "deleted " + id;
	}
	*/

}
