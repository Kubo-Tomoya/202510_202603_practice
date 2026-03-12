package com.example.__202603_practice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.__202603_practice.entity.User;
import com.example.__202603_practice.form.HelloForm;
import com.example.__202603_practice.repository.UserRepository;

/**
 * ユーザーサービスクラス ユーザーに関するビジネスロジックを担当する
 */
@Service
public class UserService {

	/** ユーザーリポジトリ */
	@Autowired
	private UserRepository userRepository;

	/**
	 * 全ユーザーを取得する
	 * 
	 * @return ユーザーリスト
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * ユーザーを1件取得する
	 * 
	 * @param id ユーザーID
	 * @return ユーザー
	 */
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow();
	}

	/**
	 * ユーザーを新規登録する
	 * 
	 * @param helloForm フォーム
	 */
	public void create(HelloForm helloForm) {
		User user = new User();
		user.setName(helloForm.getName());
		userRepository.save(user);
	}

	/**
	 * ユーザーを更新する
	 * 
	 * @param id ユーザーID
	 * @param helloForm フォーム
	 */
	public void update(Long id, HelloForm helloForm) {
		User user = findById(id);
		user.setName(helloForm.getName());
		userRepository.save(user);
	}

	/**
	 * ユーザーを削除する
	 * 
	 * @param id ユーザーID
	 */
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	/**
	 * ユーザー情報をフォームに変換して取得する
	 * 
	 * @param id ユーザーID
	 * @return フォーム
	 */
	public HelloForm getForm(Long id) {
		User user = findById(id);
		HelloForm helloForm = new HelloForm();
		helloForm.setName(user.getName());
		return helloForm;
	}
}
