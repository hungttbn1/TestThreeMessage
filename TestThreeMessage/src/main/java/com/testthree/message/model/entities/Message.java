package com.testthree.message.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "message")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	
	@Column(name = "sender", nullable = false)
	private String sender;
		
	@Column(name = "recipient", nullable = false)
	private String recipient;
	
	
	@Column(name = "title")
	private String title;
		
	@Column(name = "content")
	private String content;
	
	

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Message(Long id, String sender, String recipient, String title, String content) {
		super();
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.title = title;
		this.content = content;
	}
	


	public Message(String sender, String recipient, String title, String content) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.title = title;
		this.content = content;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
