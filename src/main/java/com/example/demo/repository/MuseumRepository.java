package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Museum;

public interface MuseumRepository extends JpaRepository<Museum, Integer> {
	List<Museum> findByAreaId(Integer areaId);

}
