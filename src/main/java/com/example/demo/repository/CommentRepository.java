package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	// select * from comments where museum_id = ?
	List<Comment> findByMuseumId(Integer museumId);

	List<Comment> findByUserId(Integer userId);
}