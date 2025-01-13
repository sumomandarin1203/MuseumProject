package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Account;

@Aspect
@Component

public class CheckLoginAspect {
	@Autowired
	Account account;

	// 全Controllerクラスの全メソッド処理の前
	@Before("execution(* com.example.demo.controller.*Controller.*(..))")
	public void writeLog(JoinPoint jp) {
		if (account == null || account.getName() == null
				|| account.getName().length() == 0) {
			System.out.println("ゲスト");
		} else {
			System.out.println(account.getName() + ":");
		}
		System.out.println(jp.getSignature());
	}

	// 未ログインの場合ログインページにリダイレクト
	@Around("execution(* com.example.demo.controller.DetailController.*(..)) ||"
			+ "execution(* com.example.demo.controller.MuseumController.*(..))")
	public Object checkLogin(ProceedingJoinPoint jp) throws Throwable {
		if (account == null || account.getName() == null
				|| account.getName().length() == 0) {
			System.err.println("ログインしていません！");
			// リダイレクト先を指定
			return "redirect:/login?error=notLoggedIn";
		}
		// Controller内のメソッド実行
		return jp.proceed();
	}

}
