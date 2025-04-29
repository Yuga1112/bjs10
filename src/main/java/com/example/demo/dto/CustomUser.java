package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/*
 * 사용자 인증 클래스
 * 시큐리티는 로그인을 처리할대 User라는 클래스를 사용
 */
public class CustomUser extends User{

	//매개면수 : DTO
	//DTO -> User 변환
	public CustomUser(MemberDTO dto) {
		
		//인증 클래스에 필요한 값
		//아이디 패스워드 권한
		// 권한 (string -> GrantedAuthority)
		
		super(dto.getId(), dto.getPassword(), Arrays.asList(new SimpleGrantedAuthority(dto.getRole())));
		
	}

	
}
