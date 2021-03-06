package com.example.demo;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "items")
public class ItemAdd {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column
		private int id;
	
		@Column
		private String name;
		
		@Column
		private Double price;
		
		@Column
		private String category;
		
		@Column
		private Date date;
		
		@Column
		private String notes;
		
		@ManyToOne
		@JoinColumn(name = "person_id")
		private Person person;
		
		public int getId() { return id; }
		public String getName() { return name; }
		public Double getPrice() { return price; }
		public String getCategory() { return category; }
		public Date getDate() { return date; }
		public String getNotes() { return notes; }
		public Person getPerson() {	return person; }
		public void setPerson(Person p) { person = p;}
		public void setPrice(int p) { price = (double) p; }
}
