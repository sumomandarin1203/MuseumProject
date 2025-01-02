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
	public String index() {
		// セッション情報をクリア
		session.invalidate();
		return "login";
	}
	/*public String index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		// セッション情報をクリア
		session.invalidate();
		// エラーパラメータのチェック
		if (error.equals("notLoggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}	
		return "login";
	}*/

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
			model.addAttribute("message", "メールアドレス、パスワードが登録されていません");
			return "login";
		}

		account.setName(accountListMail.get(0).getName());
		return "redirect:/museums";
	}
	/*public String login(
			@RequestParam("password") String password,
			@RequestParam("email") String email,
			Model model) {
		// email、passwordが空の時エラーとする
		if (password == null || password.length() == 0
				|| email == null || email.length() == 0) {
			model.addAttribute("message", "メールアドレス、パスワードを入力してください");
			return "login";
		}
		List<UserInfo> mail = userRepository.findByEmail(email);
		List<UserInfo> pass = userRepository.findByPassword(password);
		List<UserInfo> mailpass = userRepository.findByEmailAndPassword(email, password);
		
		if (mailpass.isEmpty()) {
			model.addAttribute("message", "メールアドレスかパスワードが正しくありません");
			return "login";
		}
		
		if (mail.get(0).getName() == pass.get(0).getName()) {
			account.setName(mail.get(0).getName());
			account.setId(mail.get(0).getId());
		}
		account.setEmail(email);
		// 「/  」へのリダイレクト
		return "redirect:/     ";
	}*/

}
