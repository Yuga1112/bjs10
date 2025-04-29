package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//config 클래스는 일반 클래스와 달리 스프링 프로젝트가 실행될때 제일 먼저 실행
import org.springframework.security.web.SecurityFilterChain;

//시큐리티와 관련된 설정을 담고 있는 클래스
@Configuration //스프링 설정 클래스
@EnableWebSecurity  //보안
public class SecurityConfig {

	//커스텀 필터체인을 만들어서 빈으로등록
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//메뉴 접근권한 설정
		//requestMatchers : url경로를 사용하여 접근 권한 설정 함수
		
		// 인자 : url주소
		// 접근권한 : permitAll, authenticated, hasAnyRole
		//permitAll : 아무나 접근가능
		// authenticated : 인증된 사람만 (로그인)
		// hasAnyRole : 권한을 가지고 있는 사람만 가능
		
		// 게시물관리 댓글관리는 일반사용자 또는 관리자만 가능
		// 회원관리는 관리자만
		http.authorizeHttpRequests()
			.requestMatchers("/register").permitAll()
			.requestMatchers("/").authenticated()
			.requestMatchers("/board/*").hasAnyRole("ADMIN", "USER")
			.requestMatchers("/commit/*").hasAnyRole("ADMIN", "USER")
			.requestMatchers("/member/*").hasAnyRole("ADMIN");
		
		//CSRF 비활성화
		// 시큐리티는 기본적으로 csrf 기능이 활성화되어 있어 post메소드 사용불가
		//post 메소드를 사용하려면 csrf 기능을 비활성화
		http.csrf(csrf -> csrf.disable());
		
		//로그인 및 로그아웃 기능
		http.formLogin();
		http.logout();
		
		return http.build();
		
	}
	
	
	//프로젝트가 시작될때 BCryptPasswordEncoder의 인스턴스가 컨테이너에 빈으로 등록
	
	//패스워드를 암호화하는 빈으로 등록
	//빈 : 스프링에 필요 / 로그인
	@Bean
	public PasswordEncoder passwordEncoder() {
		
	//	BCrypt : 암호화인코더 종류
		return new BCryptPasswordEncoder();
	}
}

