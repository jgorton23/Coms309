package com.example.demo;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscription {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column
		private int id;
	
		@Column
		private String name;
		
		@Column
		private Double price;
		
		@Column
		private Date date;
		
		@Column
		private String notes;
		
		@Column
		private int posterId;
		
		@ManyToMany(mappedBy = "subscriptionsBought")
		List<Person> users;
		
		public int getId() { return id; }
		public String getName() { return name; }
		public Double getPrice() { return price; }
		public Date getDate() { return date; }
		public String getNotes() { return notes; }
		public int getPerson() { return posterId; }
		public void setPosterId(int i) { posterId = i;}
		public List<Person> getUsersBought() { return users;}
}
