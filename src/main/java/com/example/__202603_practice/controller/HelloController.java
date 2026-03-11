package com.example.__202603_practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.__202603_practice.form.HelloForm;

@Controller
public class HelloController {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("helloForm", new HelloForm());
		return "hello";
	}

	@PostMapping("/hello")
	public String hello(@Validated HelloForm helloForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "hello";
		}

		model.addAttribute("name", helloForm.getName());
		return "result";
	}
}
