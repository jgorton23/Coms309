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
	List<Subscription> findAllItems() {
		return subDB.findAll();
	}
	
	@RequestMapping("/getSubscriptions/{id}")
	List<Subscription> findAllItemsByID(@PathVariable int id) {
		List<Subscription> l = new List<Subscription>();
	}
	
	@PostMapping("/addSubscription")
	String createSubscription(@RequestBody Subscription subscription) {
		Optional<Person> optionalP = personDB.findById(subscription.getPerson());
		if (optionalP.isPresent()) {
			Person p = optionalP.get();
			subscription.setPosterId(p.getId());
			subDB.save(subscription);
			return "Success";
		}
		else {
			return "Failure";
		}
	}
	
}
