package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

//BoardService 구현 클래스

//@Component를 포함
//해당 클래스는 서비스임 명시하고 컨테이너에 빈으로 등록
@Service
public class BoardServiceImpl implements BoardService {
	
	//서비스에서 사용할 리파지토리 필드로 선언
	@Autowired
	BoardRepository repository;
	
	
	// 오버라이드 : 부모가 물려준 메소드를 재정의 (구현)
	//예상 : 1~3
	@Override
	public Page<BoardDTO> getList(int pageNumber) {
		
		//페이지 번호를 index로 변경
		int pageNum = (pageNumber==0) ? 0 : pageNumber - 1;
		
		//정렬
		Sort sort = Sort.by("no").descending();
		
		//페이지 조건 생성
		Pageable pageable = PageRequest.of(pageNum, 10, sort);
		
		// 페이지 조건을 적용하여 게시물 목록 조회
		Page<Board> page = repository.findAll(pageable);
		
		//entity -> dto
		Page<BoardDTO> dtoPage = null;
		
		//map: page안에 있는 요소를 순회하면서 데이터를 바꾸는 함수
		dtoPage = page.map(entity-> entityToDto(entity));
		
		return dtoPage;
	}
	


	//게시물 등록 메소드 구현
	//리파지토리를 사용해서 테이블에 새로운 게시물을 등록하고 게시물 번호를 반환
	@Override
	public int register(BoardDTO dto) {
		
		Board entity = dtoToEntity(dto);
		
		//리파지토리로 새로운 게시물 (엔티티)을 등록
		
		repository.save(entity);
		int newNo = entity.getNo();
		
		return newNo;
	}
	
	@Override
	public BoardDTO read(int no) {
		
		//특정 게시물 조회
		Optional<Board> optional = repository.findById(no);
		
		//게시물이 있다면 꺼내기
		if(optional.isPresent()) {
			Board board = optional.get();
			
			BoardDTO dto = entityToDto(board);
			
			return dto;
			
		}
		return null;
	}


	@Override
	public void modify(BoardDTO dto) {
		// 수정하기전에 수정가능한 필드를 결정해야한다
		// 수정 가능한것 : 제목 내용 작성자
		// 중간 (애매한) : 작성자
 		// 수정 불가능한것 : 식별자, 날짜
		
		//해당 게시물 존재 확인
		int no = dto.getNo();
		
		Optional<Board> optional = repository.findById(no);
		
		if(optional.isPresent()) {
			Board board = optional.get();
			
			//게시물이 존재하면 수정 진행
			// 기존 데이터에서 제목과 내용을 교체
			board.setTitle(dto.getTitle());
			board.setContent(dto.getContent());
			
			//교체한 데이터를 테이블에 저장
			// 외부에서 전달받은 dto대신 조회로 꺼낸 entity 사용해야함 
			repository.save(board);
		}
		
	}

	//부모가 물려준 함수 구현
	@Override
	public void remove(int no) {
		//게시물이 존재하는지 확인
		Optional<Board> optional = repository.findById(no);
		
		//존재확인이유
		// 타이밍 이슈
		//
		
		if(optional.isPresent()) {
			//게시물이 있으면 삭제
			repository.deleteById(no);
			
		}
	}

	

	
}
