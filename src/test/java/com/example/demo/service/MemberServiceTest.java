package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.MemberDTO;

//MemberServiceTest 기능 테스트

@SpringBootTest
public class MemberServiceTest {

	@Autowired
	MemberService service;
	
	@Test
	void 서비스확인() {
		System.out.println("service : " + service);
		// Bean(빈) : service : com.example.demo.service.MemberServiceImpl@63541960
	}
	
	@Test
	void 회원등록() {
		
		//dto생성
		MemberDTO dto = MemberDTO.builder()
				.id("user2")
				.password("1234")
				.name("둘리")
				.build();
		//테이블에 새로운 회원데이터
		//repository - save
		//service - register
		service.register(dto);
	}
}
