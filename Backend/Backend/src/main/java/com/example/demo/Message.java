package com.example.demo;


 import java.sql.Date;


import javax.persistence.*;


@Entity
@Table(name = "messages")
public class Message {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column
    private String userName;

    @Column
    private String content;
	
	public Message() {};
	
	public Message(String userName, String content) {
		this.userName = userName;
		this.content = content;
    }
    
    public String getUserName() { return userName; }
    public String getContent() { return content; }
}
