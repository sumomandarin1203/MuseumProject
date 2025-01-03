package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.AccountInfo;
import com.example.demo.entity.Area;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Museum;
import com.example.demo.repository.AccountInfoRepository;
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
	@Autowired
	AccountInfoRepository accountInfoRepository;

	@GetMapping("/detail")
	public String detail(@RequestParam("id") Integer id, Model model) {
		Museum museum = museumRepository.findById(id).get();
		List<Comment> commentList = null;
		commentList = commentRepository.findByMuseumId(id);
		Area area = areaRepository.findById(museum.getAreaId()).get();

		// AccountInfo accountInfo= accountInfoRepository.findById()
		ArrayList<AccountInfo> name = null;
		for (Comment n : commentList) {
			AccountInfo accountInfo = accountInfoRepository.findById(n.getUserId()).get();
			//name = accountInfoRepository.findById(n.getUserId()).get();
			n.setUserName(accountInfo.getName());
		}
		model.addAttribute("museum_detail", museum);
		model.addAttribute("area", area);
		model.addAttribute("comment", commentList);
		//model.addAttribute("name", name);
		return "detail";
	}

}
//@GetMapping("/detail/{id}")
