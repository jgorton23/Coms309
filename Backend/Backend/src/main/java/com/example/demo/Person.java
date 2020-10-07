package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Person")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "itemsBought"})
class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	@Column
	private int userLevel;
	
	@Column(unique=true)
	private String username;
	
	@Column
	private String password;
	
	@OneToMany(mappedBy="person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemAdd> itemsBought;
	
	//private ArrayList<String> friendId = new ArrayList<String>();
	
	public int getId() { return id; }
	public int getUserLevel() { return userLevel; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public void setAddress(String password) { this.password = password; }
	public List<ItemAdd> getItemsBought() { return itemsBought; }
	public void setItemsBought(List<ItemAdd> items) { itemsBought = items; }
	public String toString() { return username; }
	
}