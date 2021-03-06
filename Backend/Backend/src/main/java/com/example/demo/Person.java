package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Person")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "itemsBought", "subscriptionsBought", "friends", "friendOf"})
class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	@Column
	private int userLevel;
	
	@Column
	private int budget;
	
	@Column(unique=true)
	private String username;
	
	@Column
	private String password;
	
	@OneToMany(mappedBy="person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemAdd> itemsBought;
	
	@ManyToMany
	@JoinTable(
		name = "subscriptionsUsers", 
		joinColumns = @JoinColumn(name = "user_id"), 
		inverseJoinColumns = @JoinColumn(name = "subscription_id")
	)
	@JsonIgnore
	private List<Subscription> subscriptionsBought;
	
	@ManyToMany
	@JoinTable(name="tbl_friends",
	 joinColumns=@JoinColumn(name="userId"),
	 inverseJoinColumns=@JoinColumn(name="friendId")
	)
	@JsonIgnoreProperties("Person")
	@JsonIgnore(true)
	private List<Person> friends;

	@ManyToMany
	@JoinTable(name="tbl_friends",
	 joinColumns=@JoinColumn(name="friendId"),
	 inverseJoinColumns=@JoinColumn(name="userId")
	)
	@JsonIgnoreProperties("friendOf")
	@JsonIgnore(true)
	private List<Person> friendOf;
	
	public int getId() { return id; }
	public int getUserLevel() { return userLevel; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public void setPassword(String p) { password = p; }
	public List<ItemAdd> getItemsBought() { return itemsBought; }
	public void setItemsBought(List<ItemAdd> items) { itemsBought = items; }
	public String toString() { return username; }
	
	@JsonIgnore
	public List<Subscription> getSubscriptionsBought() { return subscriptionsBought;}
	public void setSubscriptionsBought(List<Subscription> subs) {subscriptionsBought = subs;}
	
	@JsonIgnore
	public List<Person> getFriends() { return friends; }
	
	@JsonIgnore
	public List<Person> getFriendsOf() { return friendOf; }
	
	public void setFriends(List<Person> f) { friends = f; }
	public void setFriendsOf(List<Person> f) { friendOf = f; }
	public int getBudget() { return budget; }
	public void setBudget(int b) { budget = b; }
	
	public int totalSubscriptionCost() {
		int total = 0;
		for (int i = 0; i < subscriptionsBought.size(); i++) {
			total += subscriptionsBought.get(i).getPrice();
		}
		return total;
	}
	
	public boolean overBudget() {
		int total = 0;
		for (int i = 0; i < itemsBought.size(); i++) {
			total += itemsBought.get(i).getPrice();
		}
		return (total<budget);
	}
	
	public void addItem(ItemAdd I) {
		itemsBought.add(I);
	}

	public void subscribe(Subscription s){
		subscriptionsBought.add(s);
	}
	
	public void addFriend(Person p) {
		friends.add(p);
	}
}