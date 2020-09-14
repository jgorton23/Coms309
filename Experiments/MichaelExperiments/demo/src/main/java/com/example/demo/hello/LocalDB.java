package com.example.demo.hello;

import javax.persistence.*;

@Entity
public class LocalDB {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
		Integer id;
		
		@Column
		String user;
		
		public Integer getId() { 
			return id; 
		}
		
		public String getUser() { 
			return user; 
		}
}
