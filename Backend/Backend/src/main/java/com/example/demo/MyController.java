package com.example.demo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
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
	
	@RequestMapping("/getFriends/{id}")
	List<Person> getFriends(@PathVariable int id) { 
		Optional<Person> optionalP = db.findById(id);
		if (optionalP.isPresent()) {
			Person p = optionalP.get();
			return p.getFriends();
		}
		else {
			return Collections.emptyList();
		}
	}
	
	@RequestMapping("/getSummary/{id}")
	JSONObject getSummary(@PathVariable int id) {
		return getPersonSummary(id);
	}
	
	public JSONObject getPersonSummary(int id) {
		Optional<Person> optionalP = db.findById(id);
		if (optionalP.isPresent()) {
			Person p = optionalP.get();
			List<ItemAdd> items = p.getItemsBought();
			JSONObject obj = new JSONObject();
			int groceries = 0, utilities = 0, rent = 0, entertainment = 0, leisure = 0;
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).getCategory() == "groceries") {
					groceries += items.get(i).getPrice();
				}
				else if (items.get(i).getCategory() == "utilities") {
					utilities += items.get(i).getPrice();
				}
				else if (items.get(i).getCategory() == "rent") {
					rent += items.get(i).getPrice();
				}
				else if (items.get(i).getCategory() == "entertainment") {
					entertainment += items.get(i).getPrice();
				}
				else if (items.get(i).getCategory() == "leisure") {
					leisure += items.get(i).getPrice();
				}
			}
			try {
				obj.put("groceries", groceries);
				obj.put("utilities", utilities);
				obj.put("rent", rent);
				obj.put("entertainment", entertainment);
				obj.put("leisure", leisure);
			} catch (JSONException e) { return new JSONObject(); }
			return obj;
		}
		else {
			return new JSONObject();
		}
	}

	@DeleteMapping("/person/{id}")
	String deletePerson(@PathVariable Integer id) {
		db.deleteById(id);
		return "deleted " + id;
	}

}
