package com.example.__202603_practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Securityの設定クラス 認証・認可の設定を行う
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * パスワードエンコーダーを定義する BCryptアルゴリズムを使用してパスワードをハッシュ化する
	 * 
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * ユーザー情報をメモリ上に定義する 実務ではDBからユーザー情報を取得する
	 * 
	 * @return InMemoryUserDetailsManager
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder().username("admin")
		    .password(passwordEncoder().encode("password")).roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user);
	}

	/**
	 * セキュリティの設定を行う
	 * 
	 * @param http HttpSecurity
	 * @return SecurityFilterChain
	 * @throws Exception 例外
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
		    // ログイン画面は認証不要
		    .requestMatchers("/login").permitAll()
		    // それ以外は認証が必要
		    .anyRequest().authenticated()).formLogin(form -> form
		        // ログイン画面のURL
		        .loginPage("/login")
		        // ログイン成功時の遷移先
		        .defaultSuccessUrl("/users").permitAll())
		    .logout(logout -> logout
		        // ログアウト成功時の遷移先
		        .logoutSuccessUrl("/login").permitAll());
		return http.build();
	}
}
