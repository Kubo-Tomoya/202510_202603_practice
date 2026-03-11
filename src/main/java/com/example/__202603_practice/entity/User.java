package com.example.__202603_practice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * ユーザーエンティティクラス DBの「users」テーブルと対応するクラス
 */
@Entity
@Table(name = "users")
public class User {

	/** ユーザーID（主キー・自動採番） */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** ユーザー名（必須・20文字以内） */
	@Column(nullable = false, length = 20)
	private String name;

	/**
	 * ユーザーIDを取得する
	 * 
	 * @return ユーザーID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * ユーザーIDを設定する
	 * 
	 * @param id ユーザーID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * ユーザー名を取得する
	 * 
	 * @return ユーザー名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ユーザー名を設定する
	 * 
	 * @param name ユーザー名
	 */
	public void setName(String name) {
		this.name = name;
	}
}
