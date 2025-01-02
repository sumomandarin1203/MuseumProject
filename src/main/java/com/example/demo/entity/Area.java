package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "areas")
public class Area {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // エリアID

	@Column(name = "area")
	private String area; // エリア名

	public Integer getId() {
		return id;
	}

	public String getArea() {
		return area;
	}

}
