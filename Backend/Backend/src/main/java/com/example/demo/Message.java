package com.example.demo;


 import java.sql.Date;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Lob;
// import javax.persistence.Table;
// import javax.persistence.Temporal;
// import javax.persistence.TemporalType;
// import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;

// import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "messages")
//@Data
public class Message {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	// @NotNull
    // @Size(max = 100)
    @Column
    private String userName;
	
	// @NotNull
    // @Lob
    @Column
    private String content;
	
	// @NotNull
    // @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent")
    private Date sent = new Date();
	
	
	public Message() {};
	
	public Message(String userName, String content) {
		this.userName = userName;
		this.content = content;
    }
    
    public String getUserName() { return userName; }
    public String getContent() { return content; }
}
