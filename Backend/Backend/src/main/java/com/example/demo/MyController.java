package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

	@Autowired
	MyDatabase db;
	
	/*
	@GetMapping("/person/{id}")
	Person getPerson(@PathVariable Integer id) {
		return db.findOne(id);
	}
	*/
	
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
	Person findperson(@PathVariable long id) {
		return db.findById(id);
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
