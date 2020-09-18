package com.example.demo;

import java.sql.Date;
import javax.persistence.*;

@Entity
public class ItemAdd {

	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
		Integer id;
	
		@Column
		String name;
		
		@Column
		Double price;
		
		@Column
		String catagory;
		
		@Column
		Date date;
		
		@Column
		String notes;
		
		public Integer getId() { return id; }
		public String getName() { return name; }
		public Double getPrice() { return price; }
		public String getCatagory() { return catagory; }
		public Date getDate() { return date; }
		public String getNotes() { return notes; }
}
