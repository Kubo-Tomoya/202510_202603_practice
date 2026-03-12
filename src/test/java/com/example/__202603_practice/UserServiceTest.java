package com.example.__202603_practice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.__202603_practice.entity.User;
import com.example.__202603_practice.form.HelloForm;
import com.example.__202603_practice.repository.UserRepository;
import com.example.__202603_practice.service.UserService;

/**
 * UserServiceのテストクラス Mockitoを使用してRepositoryをモック化しテストを行う
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	/** モック化したUserRepository */
	@Mock
	private UserRepository userRepository;

	/** テスト対象のUserService */
	@InjectMocks
	private UserService userService;

	/**
	 * 全ユーザー取得のテスト 登録されている全ユーザーが取得できることを確認する
	 */
	@Test
	public void testFindAll() {
		// テストデータの準備
		User user1 = new User();
		user1.setName("テストユーザー1");
		User user2 = new User();
		user2.setName("テストユーザー2");

		// モックの設定
		when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

		// テスト実行
		List<User> users = userService.findAll();

		// 検証
		assertEquals(2, users.size());
		assertEquals("テストユーザー1", users.get(0).getName());
		assertEquals("テストユーザー2", users.get(1).getName());
	}

	/**
	 * ユーザー1件取得のテスト 指定したIDのユーザーが取得できることを確認する
	 */
	@Test
	public void testFindById() {
		// テストデータの準備
		User user = new User();
		user.setName("テストユーザー");

		// モックの設定
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));

		// テスト実行
		User result = userService.findById(1L);

		// 検証
		assertEquals("テストユーザー", result.getName());
	}

	/**
	 * 存在しないIDでユーザー取得した場合のテスト 例外が発生することを確認する
	 */
	@Test
	public void testFindByIdNotFound() {
		// モックの設定
		when(userRepository.findById(99L)).thenReturn(Optional.empty());

		// 例外が発生することを検証
		assertThrows(Exception.class, () -> userService.findById(99L));
	}

	/**
	 * ユーザー新規登録のテスト フォームの内容が正しくUserエンティティに変換されて保存されることを確認する
	 */
	@Test
	public void testCreate() {
		// テストデータの準備
		HelloForm form = new HelloForm();
		form.setName("新規ユーザー");

		// テスト実行
		userService.create(form);

		// saveが1回呼ばれたことを検証
		verify(userRepository, times(1)).save(org.mockito.ArgumentMatchers.any(User.class));
	}

	/**
	 * ユーザー削除のテスト 指定したIDのユーザーが削除されることを確認する
	 */
	@Test
	public void testDelete() {
		// テスト実行
		userService.delete(1L);

		// deleteByIdが1回呼ばれたことを検証
		verify(userRepository, times(1)).deleteById(1L);
	}
}
