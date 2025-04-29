package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	//특정페이지에 있는회원리스트 조회
	//예상 페이지 번호 : 0
	
	@Autowired
	private	MemberRepository repository;
		
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Page<MemberDTO> getList(int page) {
		
		//페이지 번호
		int pageIndex = (page == 0) ? 0 : page - 1;
		
		//페이지 조건 생성
		//회원가입 날짜를 기준으로 역정렬
		Sort sort = Sort.by("regDate").descending();
		
		Pageable pageable = PageRequest.of(pageIndex, 10, sort);
		
		Page<Member> entityPage = repository.findAll(pageable);
		
		Page<MemberDTO> dto = entityPage.map(entity -> entityToDto(entity));
		
  		return dto;
	}
	
	/*게시물 테이블의 pk는 자동으로생성 -> 중복x
	 * 회원테이블의 pk는 수종
	 * 회원의 아이디가 중복되면 회원가입 실패*/
	@Override
	public boolean register(MemberDTO dto) {
		//회원 아이디 중복확인
		String id = dto.getId();
		
		//회원 아이디 존재확인
		Optional<Member> optional = repository.findById(id);
		
		if(optional.isPresent()) {
			System.out.println("이미 사용중인 아이디");
			return false;
		} else {
		Member entity = dtoToEntity(dto);
		
		//테이블에 회원 데이터 추가 전 패스워드를 암호화
		String password = entity.getPassword();
		String enpw = passwordEncoder.encode(password);
		
		//패스워드를 교체
		entity.setPassword(enpw);
		
		repository.save(entity);
		return true; 
		}
	}

	@Override
	public MemberDTO read(String id) {
		Optional<Member> optional = repository.findById(id);
		
		if(optional.isPresent()) {
			Member entity = optional.get();
			MemberDTO dto = entityToDto(entity);
			
			return dto;
		}
		
		return null;
	}
}
