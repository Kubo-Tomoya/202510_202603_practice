package com.example.__202603_practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.__202603_practice.entity.User;

/**
 * ユーザーリポジトリインターフェース JpaRepositoryを継承することでCRUD操作が自動的に使用可能になる
 * 第1引数：対応するEntityクラス
 * 第2引数：主キーの型
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	// 基本的なCRUD操作はJpaRepositoryが自動的に提供する
	// 必要に応じてカスタムクエリをここに追加する
}
