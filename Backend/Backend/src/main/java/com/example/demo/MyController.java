package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

	@Autowired
	MyDatabase db;
	
	@Autowired
	SubscriptionDatabase SubDB;
	
	@RequestMapping("/persons")
	List<Person> getAllPeople() {
		return db.findAll();
	}
		
	/*
	@RequestMapping("/persons")
	ResponseEntity<String> hello() {
		JSONObject main = new JSONObject();
		List<Person> people = db.findAll();
		for (int i = 0; i < people.size(); i++) {
			JSONObject Person = new JSONObject();
			try {
				Person.put("id", people.get(i).getId());
				Person.put("userLevel", people.get(i).getUserLevel());
				Person.put("budget", people.get(i).getBudget());
				Person.put("username", people.get(i).getUsername());
				Person.put("password", people.get(i).getPassword());
				main.put("Person"+i+"", Person);
			} catch (JSONException e) {
				return new ResponseEntity<String>("failedReturn", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>(main.toString(), HttpStatus.OK);
	}
	*/

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
	/*
	@RequestMapping("/getItems/{id}")
	ResponseEntity<String> getItems(@PathVariable int id) { //get items.db.getAll or some stuff I got it. 
		Optional<Person> optionalP = db.findById(id);
		if (optionalP.isPresent()) {
			Person p = optionalP.get();
			JSONObject main = new JSONObject();
			List<ItemAdd> items = p.getItemsBought();
			for (int i = 0; i < items.size(); i++) {
				JSONObject Item = new JSONObject();
				JSONObject Person = new JSONObject();
				try {
					Item.put("id", items.get(i).getId());
					Item.put("name", items.get(i).getName());
					Item.put("price", items.get(i).getPrice());
					Item.put("category", items.get(i).getCategory());
					Item.put("date", items.get(i).getDate());
					Item.put("notes", items.get(i).getNotes());
					
					Person.put("id", items.get(i).getPerson().getId());
					
					Item.put("person", Person);
					
					main.put("Item"+i+"", Item);
				} catch (JSONException e) {
					return new ResponseEntity<String>("failedReturn", HttpStatus.OK);
				}
			}
			return new ResponseEntity<String>(main.toString(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("failedReturn", HttpStatus.OK);
		}
	}
	*/
	
	@RequestMapping("/getSubscriptions/{id}")
	List<Subscription> getsubscriptions(@PathVariable int id) {
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
	@RequestMapping("/getSubscriptions/{id}")
	ResponseEntity<String> getSubscriptions(@PathVariable int id) { // OT NE THI>OKE THAT THIS IS ONE TF THE THINGS YOUR BROKE>wq
		JSONObject main = new JSONObject();
		List<Subscription> subscriptions = SubDB.findAll();
		Optional<Person> subPerson = db.findById(id);
		if (subPerson.isPresent()) {
			Person p = subPerson.get();
			for (int i = 0; i < subscriptions.size(); i++) {
				JSONObject subscription = new JSONObject();
				try {
					if (subscriptions.get(i).getUsersBought().contains(p)) {
						subscription.put("id", subscriptions.get(i).getId());
						subscription.put("name", subscriptions.get(i).getName());
						subscription.put("price", subscriptions.get(i).getPrice());
						subscription.put("date", subscriptions.get(i).getDate());
						subscription.put("notes", subscriptions.get(i).getNotes());
						
						main.put("Subscription"+i+"", subscription);
					}
				} catch (JSONException e) {
					return new ResponseEntity<String>("failedReturn", HttpStatus.OK);
				}
			}
			return new ResponseEntity<String>(main.toString(), HttpStatus.OK);
		}
		return new ResponseEntity<String>("failedReturn", HttpStatus.OK);
	}
	*/
	
	// @DeleteMapping("/deleteSubscriptions/{id}")
	// String deleteSubscriptions(@PathVariable Integer id) {
	// 	Optional<Person> optionalP = db.findById(id);
	// 	List<Subscription> l = new List<Subscription>()
	// 	optionalP.setSubscriptionsBought(l);
	// 	return "deleted " + id;
	// }
	
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
				if (items.get(i).getCategory() == "Groceries") {
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
				obj.put("Groceries", groceries);
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
	
	@PostMapping("/addFriend/{id1}/{id2}")
	String addFriend(@PathVariable int id1, @PathVariable int id2) {
		Optional<Person> optionalP = db.findById(id1);
		Optional<Person> optionalP2 = db.findById(id2);
		if (optionalP.isPresent() && optionalP2.isPresent()) {
			Person p1 = optionalP.get();
			Person p2 = optionalP2.get();
			p1.addFriend(p2);
			db.save(p1);
			return "success";
		}
		else {
			return "failure";
		}
	}
	
	@PostMapping("/addMutualFriend/{id1}/{id2}")
	String addMutualFriend(@PathVariable int id1, @PathVariable int id2) {
		Optional<Person> optionalP1 = db.findById(id1);
		Optional<Person> optionalP2 = db.findById(id2);
		if (optionalP1.isPresent() && optionalP2.isPresent()) {
			Person p1 = optionalP1.get();
			Person p2 = optionalP2.get();
			p1.addFriend(p2);
			p2.addFriend(p1);
			db.save(p1);
			db.save(p2);
			return "success";
		}
		else {
			return "failure";
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
	/*
	@RequestMapping("/getFriends/{id}")
	ResponseEntity<String> getFriends(@PathVariable int id) { 
		Optional<Person> optionalP = db.findById(id);
		JSONObject main = new JSONObject();
		if (optionalP.isPresent()) {
			Person p = optionalP.get();
			List<Person> friends = p.getFriends();
			for (int i = 0; i < friends.size(); i++) {
				JSONObject Person = new JSONObject();
				try {
					Person.put("id", friends.get(i).getId());
					Person.put("userLevel", friends.get(i).getUserLevel());
					Person.put("budget", friends.get(i).getBudget());
					Person.put("username", friends.get(i).getUsername());
					main.put("Person"+i+"", Person);
				} catch (JSONException e) {
					return new ResponseEntity<String>("failedReturn", HttpStatus.OK);
				}
			}
			return new ResponseEntity<String>(main.toString(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("failedReturn", HttpStatus.OK);
		}
	}
	*/

	@PostMapping("/changeBudget/{id}")
	int changeBudget(@RequestBody Person p, @PathVariable int id) {
		Optional<Person> optionalP = db.findById(id);
		if (optionalP.isPresent()) {
			Person p1 = optionalP.get();
			p1.setBudget(p.getBudget());
			db.save(p1);
			return p1.getBudget();
		}
		else {
			return -1;
		}
	}
	
	@PostMapping("/addSubscription/{id1}/{id2}")
	String addSubscription(@PathVariable int id1, @PathVariable int id2) {
		Optional<Person> optionalP = db.findById(id1);
		Optional<Subscription> optionalS = SubDB.findById(id2);
		if (optionalP.isPresent() && optionalS.isPresent()) {
			Person p = optionalP.get();
			Subscription s = optionalS.get();
			p.subscribe(s);
			s.addUser(p);
			db.save(p);
			SubDB.save(s);
			return "Success";
		}
		else {
			return "failure";
		}
	}
	
}
