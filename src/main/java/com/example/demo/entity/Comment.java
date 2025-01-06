package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import lombok.Data;

@Entity
@Table(name = "comments")
@Data
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //コメントID

	@Column(name = "museum_id")
	private Integer museumId;
	@Column(name = "content")
	private String content;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "lastupdate")
	private Date lastupdate;
	@Transient
	private String userName;

	public Comment() {
	}

	public Comment(Integer museumId, String content) {
		super();
		this.museumId = museumId;
		this.content = content;
	}

	public Comment(Integer museumId, String content, Integer userId) {
		super();
		this.museumId = museumId;
		this.content = content;
		this.userId = userId;
	}

	public Comment(Integer museumId, String content, String userName) {
		super();
		this.museumId = museumId;
		this.content = content;
		this.userName = userName;
	}

	/*public Integer getId() {
		return id;
	}
	
	public Integer getMuseumId() {
		return museumId;
	}
	
	public String getContent() {
		return content;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public Date getLastupdate() {
		return lastupdate;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}*/

}
