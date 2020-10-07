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
public class SubscriptionController {

	@Autowired
	MyDatabase personDB;
	@Autowired
	SubscriptionDatabase subDB;
	
	
	@RequestMapping("/getSubscriptions")
	public List<Subscription> findAllItems() {
		return subDB.findAll();
	}
	
	/*
	@PostMapping("/addSubscription")
	String createSubscription(@RequestBody Subscription subscription) {
		Optional<Person> optionalP = personDB.findById(subscription.getPerson().getId());
		if (optionalP.isPresent()) {
			Person p = optionalP.get();
			subscription.setPerson(p);
			subDB.save(subscription);
			return "Success";
		}
		else {
			return "Failure";
		}
	}
	
	/*
	@GetMapping("/person/{id}")
	Person getPerson(@PathVariable Integer id) {
		return db.findOne(id);
	}
	
	@RequestMapping("/items")
	List<Subscription> hello() {
		return db.findAll();
	}
	*/

}
