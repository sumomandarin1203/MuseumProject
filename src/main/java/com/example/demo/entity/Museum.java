package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "museums")

public class Museum {
	@Id // 主キー
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 博物館ID

	@Column(name = "museum_name")
	private String museumName; // 博物館名
	@Column(name = "area_id")
	private Integer areaId;//	エリアID
	@Column(name = "city_name")
	private String cityName; // 市町村名

	@Transient
	private String area_name;

	public Integer getId() {
		return id;
	}

	public String getMuseumName() {
		return museumName;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

}
