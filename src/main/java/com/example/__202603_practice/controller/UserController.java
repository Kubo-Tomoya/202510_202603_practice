package com.example.__202603_practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.__202603_practice.form.HelloForm;
import com.example.__202603_practice.service.UserService;

/**
 * ユーザーコントローラークラス リクエストの受け取りと画面遷移のみを担当する
 */
@Controller
@RequestMapping("/users")
public class UserController {

	/** ユーザーサービス */
	@Autowired
	private UserService userService;

	/**
	 * ユーザー一覧を表示する
	 * 
	 * @param model モデル
	 * @return ユーザー一覧画面
	 */
	@GetMapping
	public String index(Model model) {
		model.addAttribute("users", userService.findAll());
		return "user/index";
	}

	/**
	 * ユーザー新規登録画面を表示する
	 * 
	 * @param model モデル
	 * @return ユーザー新規登録画面
	 */
	@GetMapping("/new")
	public String newUser(Model model) {
		model.addAttribute("helloForm", new HelloForm());
		return "user/new";
	}

	/**
	 * ユーザーを新規登録する
	 * 
	 * @param helloForm フォーム
	 * @param bindingResult バリデーション結果
	 * @return ユーザー一覧画面へリダイレクト
	 */
	@PostMapping
	public String create(@Validated HelloForm helloForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "user/new";
		}
		userService.create(helloForm);
		return "redirect:/users";
	}

	/**
	 * ユーザー編集画面を表示する
	 * 
	 * @param id ユーザーID
	 * @param model モデル
	 * @return ユーザー編集画面
	 */
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("helloForm", userService.getForm(id));
		model.addAttribute("userId", id);
		return "user/edit";
	}

	/**
	 * ユーザーを更新する
	 * 
	 * @param id ユーザーID
	 * @param helloForm フォーム
	 * @param bindingResult バリデーション結果
	 * @return ユーザー一覧画面へリダイレクト
	 */
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Validated HelloForm helloForm,
	    BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("userId", id);
			return "user/edit";
		}
		userService.update(id, helloForm);
		return "redirect:/users";
	}

	/**
	 * ユーザーを削除する
	 * 
	 * @param id ユーザーID
	 * @return ユーザー一覧画面へリダイレクト
	 */
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		userService.delete(id);
		return "redirect:/users";
	}
}
