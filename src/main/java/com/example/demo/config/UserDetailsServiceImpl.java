package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomUser;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

//로그인 처리 클래스

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	MemberService service;
	
	//로그인 시도 시 인증서비스인 loadUsername 함수 호출
	//매개변수 : username(아이디)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("login id : " + username);
		
		//해당 아이디 존재 확인
		MemberDTO dto = service.read(username);
		
		//회원 존재 없음 - 로그인실패
		if(dto == null) {
			throw new UsernameNotFoundException(username);
		} else {
			//회원이 존재한다면 인증객체 만들어서 반환
			
		}
		return new CustomUser(dto);
	}

}
