package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Museum;
import com.example.demo.repository.AreaRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.MuseumRepository;

@Controller
public class DetailController {

	@Autowired
	MuseumRepository museumRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	AreaRepository areaRepository;

	@GetMapping("/detail")
	public String detail(@RequestParam("id") Integer id, Model model) {
		Museum museum = museumRepository.findById(id).get();
		List<Comment> commentList = null;
		commentList = commentRepository.findByMuseumId(id);
		model.addAttribute("museum_detail", museum);
		model.addAttribute("comment", commentList);
		return "detail";
	}

}
//@GetMapping("/detail/{id}")
