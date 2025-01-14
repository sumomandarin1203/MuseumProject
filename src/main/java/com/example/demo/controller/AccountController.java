package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.AccountInfo;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountInfoRepository;

@Controller
public class AccountController {
	@Autowired
	AccountInfoRepository accountInfoRepository;
	@Autowired
	HttpSession session;
	@Autowired
	Account account;

	// ログイン画面を表示
	@GetMapping({ "/", "login", "logout" })
	public String index(@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		// セッション情報をクリア
		session.invalidate();
		if (error.equals("notLoggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}
		return "login";
	}

	// ログイン
	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {
		if (password == null || password.length() == 0
				|| email == null || email.length() == 0) {
			model.addAttribute("message", "メールアドレス、パスワードを入力してください");
			return "login";
		}

		List<AccountInfo> accountListMail = accountInfoRepository.findByEmail(email);
		List<AccountInfo> accountListPass = accountInfoRepository.findByPassword(password);
		List<AccountInfo> accountListMailandPass = accountInfoRepository.findByEmailAndPassword(email, password);
		if (accountListMailandPass == null || accountListMailandPass.size() == 0) {
			model.addAttribute("message", "メールアドレス、パスワードが異なるか登録されていません");
			return "login";
		}

		account.setName(accountListMail.get(0).getName());
		account.setId(accountListMail.get(0).getId());
		return "redirect:/museums";
	}

	// 新規会員登録画面表示
	@GetMapping("/signup")
	public String showsign() {
		return "signup";
	}

	// 新規会員登録処理
	@PostMapping("/signup")
	public String signup(
			@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "password", defaultValue = "") String password,
			@RequestParam(value = "role", defaultValue = "") Integer role,
			Model model) {
		AccountInfo accountInfo = new AccountInfo(userName, email, password, role);
		List<AccountInfo> accountList = accountInfoRepository.findAll();
		for (AccountInfo ac : accountList) {
			if (accountInfo.getEmail().equals(ac.getEmail())) {
				model.addAttribute("message", "すでに登録されているメールアドレスです");
				return "signup";
			}
		}
		accountInfoRepository.save(accountInfo);
		return "redirect:/";

	}

}
