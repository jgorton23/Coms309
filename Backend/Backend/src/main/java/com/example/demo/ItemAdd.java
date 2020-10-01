package com.example.demo;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "Items")
public class ItemAdd {

	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	    @JoinColumn(name="Person_id", nullable=false)
	    private Person person;
		
		public Integer getId() { return id; }
		public String getName() { return name; }
		public Double getPrice() { return price; }
		public String getCatagory() { return category; }
		public Date getDate() { return date; }
		public String getNotes() { return notes; }
		public Person getPerson() {	return person; }
}
