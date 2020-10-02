package com.example.demo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Person")
class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	@Column
	private int userLevel;
	
	@Column(unique=true)
	private String username;
	
	@Column
	private String password;
	
	@OneToMany(mappedBy="person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemAdd> itemsBought;
	
	
	
	
	public long getId() { return id; }
	public int getUserLevel() { return userLevel; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public void setAddress(String password) { this.password = password; }
	public List<ItemAdd> getItemsBought() {
		return itemsBought;
	}
	public void setItemsBought(List<ItemAdd> items) {
		itemsBought = items;
	}
	
	
}