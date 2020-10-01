package com.example.demo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	@OneToMany(mappedBy="person")
    private List<ItemAdd> items;
	
	
	
	
	public long getId() { return id; }
	public int getUserLevel() { return userLevel; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public void setAddress(String password) { this.password = password; }
	
	
}