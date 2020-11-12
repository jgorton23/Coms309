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
	
	@DeleteMapping("/deleteSubscriptions/{id}")
	String deleteSubscriptions(@PathVariable Integer id) {
		Optional<Person> optionalP = db.findById(id);
		List<Subscription> l = new List<Subscription>()
		optionalP.setSubscriptionsBought(l);
		return "deleted " + id;
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

	@PostMapping("/verifyLogin")
	JSONObject verifyLogin(@RequestBody Person p) {
		List<Person> persons = db.findAll();
		for(int i=0; i < persons.size();i++){
			Person p2 = persons.get(i);
			if(p2.getUsername() == p.getUsername()){
				return verifyPassword(p2, p.getPassword());
			}
		}
		return new JSONObject();
	}

	public JSONObject verifyPassword(Person person, String password){
		JSONObject obj = new JSONObject();
		if(person.getPassword()==password){
			try {
				obj.put("id", person.getId());
				obj.put("userLevel", person.getUserLevel());
				obj.put("username", person.getUsername());
				obj.put("password", person.getPassword());
				obj.put("subscriptionsBought", person.getSubscriptionsBought());
				obj.put("friends", person.getFriends());
				obj.put("friendsOf", person.getFriendsOf());
			} catch (JSONException e) { return new JSONObject(); }
			return obj;
		}
		return new JSONObject();
	}

	@DeleteMapping("/person/{id}")
	String deletePerson(@PathVariable Integer id) {
		db.deleteById(id);
		return "deleted " + id;
	}
	
	@PostMapping("/addFriend/{id}")
	List<Person> addFriend(@RequestBody Person p, @PathVariable int id) {
		Optional<Person> optionalP = db.findById(id);
		if (optionalP.isPresent()) {
			Person p1 = optionalP.get();
			p1.addFriend(p1);
			return p1.getFriends();
		}
		else {
			return p.getFriends();
		}
	}
	
	@PostMapping("/addMutualFriend/{id}")
	List<Person> addMutualFriend(@RequestBody Person p, @PathVariable int id) {
		Optional<Person> optionalP = db.findById(id);
		if (optionalP.isPresent()) {
			Person p1 = optionalP.get();
			p1.addFriend(p1);
			p.addFriend(p1);
			return p1.getFriends();
		}
		else {
			return p.getFriends();
		}
	}
	
}
