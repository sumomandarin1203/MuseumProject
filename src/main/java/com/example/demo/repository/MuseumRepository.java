package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Museum;

public interface MuseumRepository extends JpaRepository<Museum, Integer> {

}
