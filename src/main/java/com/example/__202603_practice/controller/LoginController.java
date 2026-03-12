package com.example.__202603_practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログインコントローラークラス ログイン・ログアウトに関するリクエストを処理する
 */
@Controller
public class LoginController {

	/**
	 * ログイン画面を表示する
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
