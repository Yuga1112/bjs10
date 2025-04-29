package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.dto.BoardDTO;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.dto.*;
/*
 * 서비스 : 
 * 1. 게시물 등록, 조회 수정 삭제 기능을 구현
 * 2. 컨트롤러 리파지토리 사이에서 데이터를 변환 
 * */
public interface BoardService {

	// 매개변수 : 새로운 게시물 정보
	// 리턴값 : 새로운 게시물 번호
	int register(BoardDTO dto);
	
	//게시물 조회
	//리턴값 : 게시물 리스트
	//매개변수 : 페이지번호
	//반환값 : 게시물 목록이 들어있는 페이지 객체
	Page<BoardDTO> getList(int pageNumber);
	
	//게시물 상세 조회
	// 매개변수 : 게시물 번호
	//반환값 해당 게시물의 정보
	BoardDTO read(int no);
	
	//게시물 수정
	//매개변수 : 수정된 게시물 정보
	//반환값 : 처리결과는 없음
	void modify(BoardDTO dto);
	
	//게시물 삭제
	//매개변수 : 게시물번호
	//반환값 :처리결과없음
	void remove(int no);
	
	//DTO를 Entity로 바꾸는 메소드
	// 인터페이스에 일반 함수를 추가하는 방법
	// default 키워드 사용
	default Board dtoToEntity (BoardDTO dto) {
		
		Member member = Member.builder().id(dto.getWriter()).build();
	
		Board board = Board.builder()
				.no(dto.getNo())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.build();
		
		return board;
	}
	
	//entity를 dto로 바꾸는 함수
	default BoardDTO entityToDto(Board entity) {
		
		BoardDTO dto = BoardDTO.builder()
				.no(entity.getNo())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter().getId())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();
		return dto;
	}
	
	
}

/* 상속을 위한 클래스에서 함수의 목적
 * 추상메소드 : 자식 클래스에서 다르게 사용 / 각 클래스에서 구현에서 사용
 * 일반메소드 : 자식 클래스에서 똑같이 사용
 * 
 *
 * */
