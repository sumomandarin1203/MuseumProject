package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class AccountInfo {
	@Id // 主キー
	// 主キーが自動採番される
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String password;
	private String email;
	private Integer role;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Integer getRole() {
		return role;
	}

	public AccountInfo() {
		super();
	}

	public AccountInfo(String name, String email, String password, Integer role) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
	}

}
