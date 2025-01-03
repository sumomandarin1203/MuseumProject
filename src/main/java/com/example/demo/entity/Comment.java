package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")

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

	public Integer getId() {
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

}
