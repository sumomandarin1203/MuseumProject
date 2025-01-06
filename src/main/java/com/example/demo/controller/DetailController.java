package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.AccountInfo;
import com.example.demo.entity.Area;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Museum;
import com.example.demo.model.Account;
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
	/*@Autowired
	HttpSession session;*/
	@Autowired
	Account account;

	@GetMapping("/detail")
	public String detail(@RequestParam("id") Integer id, Model model) {
		int tempAccountId = account.getId();

		Museum museum = museumRepository.findById(id).get();
		List<Comment> commentList = null;
		commentList = commentRepository.findByMuseumId(id);
		Area area = areaRepository.findById(museum.getAreaId()).get();
		// userIdからuserNameをセットする
		for (Comment c : commentList) {
			AccountInfo accountInfo = accountInfoRepository.findById(c.getUserId()).get();
			c.setUserName(accountInfo.getName());
		}
		model.addAttribute("museum_detail", museum);
		model.addAttribute("area", area);
		model.addAttribute("comment", commentList);
		return "detail";
	}

	@PostMapping("/detail")
	public String addcontent(
			@RequestParam(name = "id") Integer museumId,
			@RequestParam(name = "content") String content,
			//@RequestParam(name = "userid") Integer userId,
			Model model) {
		//AccountInfo accountInfo = accountInfoRepository.findById(userId).get();
		Comment comment = new Comment(museumId, content, account.getId());
		commentRepository.save(comment);

		return "redirect:/detail?id=" + museumId;
	}

}
//@GetMapping("/detail/{id}")
