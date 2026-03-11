package com.example.__202603_practice.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class HelloForm {

	@NotBlank(message = "名前を入力してください")
	@Size(max = 20, message = "名前は20文字以内で入力してください")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
