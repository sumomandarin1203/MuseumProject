package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Area;
import com.example.demo.entity.Museum;
import com.example.demo.model.Account;
import com.example.demo.repository.AreaRepository;
import com.example.demo.repository.MuseumRepository;

@Controller
public class MuseumController {
	@Autowired
	Account account;
	//@Autowired
	//AccountInfoRepository accountInfoRepository;
	@Autowired
	MuseumRepository museumRepository; // museumテーブル操作用
	@Autowired
	AreaRepository areaRepository;
	// 一覧表示？

	@GetMapping("/museums")
	public String index(Model model) {
		// museumsテーブルから全博物館の一覧を取得
		List<Museum> museumList = museumRepository.findAll();
		// Thymleafにデータを渡す
		model.addAttribute("museums", museumList);

		List<Area> areaList = areaRepository.findAll();
		//areaList = areaRepository.findById(id);
		model.addAttribute("areas", areaList);
		// museum.htmlを出力
		return "museums";
	}

}
