package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

import jakarta.transaction.Transactional;

// 추가 수정 삭제 시 commit 처리
@Transactional

// repository : 테이블에 sql을 전달하여 데이터를 조회 수정 삭제하는 컴포넌트

// repository : sql을 생성
// jdbc : sql을 전달

// 리파지토리 만드는 방법
// 1. jpa 상속받기
// 2. entity와 pk 타입정의

public interface BoardRepository extends JpaRepository<Board, Integer>, QuerydslPredicateExecutor<Board> {
	
	//특정회원이 작성한 게시물을 삭제하는 메소드
	
	//메소드를 추가하는 방법
	//쿼리메소드 or 쿼리어노테이션
	//쿼리어노테이션을 사용하여 메소드를 추가
	//규칙 : 
	//메소드 이름은 자유롭게 작성
	
	//순수 sql
//	@Query(nativeQuery = true, value = "Delete From Tbl_board where writer_id = :id")
//	public void deleteWriter(@Param("id") String id);
	
	//jpql
	// 규칙 : table 대신 entity를 사용
	//컬럼대신필드사용
	@Modifying
	@Query("delete from Board where writer = :member")
	public void deleteWriter(@Param("member") Member member);
	
	

}


 