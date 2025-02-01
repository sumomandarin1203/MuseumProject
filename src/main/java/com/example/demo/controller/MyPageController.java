package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.AccountInfo;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Museum;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountInfoRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.MuseumRepository;

@Controller
public class MyPageController {
	@Autowired
	Account account;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	AccountInfoRepository accountInfoRepository;
	@Autowired
	MuseumRepository museumRepository;

	// マイページ画面表示
	@GetMapping("/mypage")
	public String mypage(@RequestParam("id") Integer id, Model model) {
		AccountInfo accountInfos = accountInfoRepository.findById(id).get();
		List<Comment> commentList = commentRepository.findByUserId(accountInfos.getId());
		for (Comment c : commentList) {
			Museum museum = museumRepository.findById(c.getMuseumId()).get();
			c.setMuseumName(museum.getMuseumName());
		}
		model.addAttribute("comment", commentList);
		//if (account.getId() != null) {
		return "mypage";
		//} else {
		//	return "redirect:/login?error=notLoggedIn";
		//}
	}

	// マイページでコメントを削除
	@PostMapping("/mypage")
	public String commentDelete(@RequestParam("id") Integer id, Model model) {
		Comment comment = commentRepository.findById(id).get();
		commentRepository.deleteById(id);
		return "redirect:/mypage?id=" + comment.getUserId();
	}
}