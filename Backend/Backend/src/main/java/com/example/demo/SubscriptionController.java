package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	ResponseEntity<String> findAllSubscriptions() {
		JSONObject main = new JSONObject();
		List<Subscription> subscriptions = subDB.findAll();
		for (int i = 0; i < subscriptions.size(); i++) {
			JSONObject subscription = new JSONObject();
			try {
				subscription.put("id", subscriptions.get(i).getId());
				subscription.put("name", subscriptions.get(i).getName());
				subscription.put("price", subscriptions.get(i).getPrice());
				subscription.put("date", subscriptions.get(i).getDate());
				subscription.put("notes", subscriptions.get(i).getNotes());
				main.put("Subscription"+i+"", subscription);
			} catch (JSONException e) {
				return new ResponseEntity<String>("failedReturn", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>(main.toString(), HttpStatus.OK);
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
