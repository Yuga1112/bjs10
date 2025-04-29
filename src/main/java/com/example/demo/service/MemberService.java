package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import org.springframework.data.domain.Page;


//서비스 : 컨트롤러와 리파지토리 사이에서 데이터를 변환
//비즈니스 로직을 처리


public interface MemberService {

	
	
	//변환메소드는 일반함수로 작성
	//자식 클래스에서 공통으로 사용하기 때문에
	 //인터페이스에 일반 메소드 만들기 default 키워드
	
	//entity - > dto
	//매개변수 :entity
	//리턴값 : dto
	default MemberDTO entityToDto(Member member) {
		
		MemberDTO dto = MemberDTO.builder()
				.id(member.getId())
				.name(member.getName())
				.password(member.getPassword())
				.role(member.getRole())
				.regDate(member.getRegDate())
				.modDate(member.getModDate())
				.build();
				
				
				return dto;
	}
	
	//dto -> entity
	//매개변수 : dto
	//리턴값 : entity
	default Member dtoToEntity(MemberDTO dto) {
		
		//회원데이터 중에서 날짜는?
		Member member = Member.builder()
				.id(dto.getId())
				.name(dto.getName())
				.password(dto.getPassword())
				.role(dto.getRole())
				.build();
		
		return member;
		
		
	}
	//회원 목록 조회
	Page<MemberDTO> getList(int page);
	
	//회원가입
	boolean register(MemberDTO dto);
	
	//회원 상세조회
	//매개변수 아이디
	//반환값 : 특정 회원의 정보
	MemberDTO read(String id);
}
