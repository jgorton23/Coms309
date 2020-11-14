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
public class ItemController {

	@Autowired
	MyDatabase personDB;
	@Autowired
	ItemDatabase ItemDB;
	
	/*
	@RequestMapping("/getItem")
	public ResponseEntity<String> findAllItems() { //YOU MERKED THOIS 
		JSONObject main = new JSONObject();
		List<ItemAdd> items = ItemDB.findAll();
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
	} */
	
	@RequestMapping("/getItem")
	List<ItemAdd> findAllSubscriptions() {
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
