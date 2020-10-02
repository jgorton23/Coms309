package com.example.demo;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class ItemAdd {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;
	
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
		
		public Integer getId() { return id; }
		public String getName() { return name; }
		public Double getPrice() { return price; }
		public String getCategory() { return category; }
		public Date getDate() { return date; }
		public String getNotes() { return notes; }
		public Person getPerson() {	return person; }
		public void setPerson(Person p) {
			person = p;
		}
}
