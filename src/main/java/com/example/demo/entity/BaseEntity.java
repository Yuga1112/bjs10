package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

//날짜 관리만 하는 엔티티

// 일반적인 엔티티가 아닌 상속을 위한 클래스 명시
@MappedSuperclass
// 이벤트 리스너
@EntityListeners(value = AuditingEntityListener.class)
@Getter
public class BaseEntity {

	//데이터가 등록될때 현재시간이 등록됨
	@CreatedDate
	LocalDateTime regDate;   //등록일
	
	//데이터가 수정될때 현재시간이 등록됨
	@LastModifiedDate
	LocalDateTime modDate;  //수정일
	
}

/*
 * 게시물 : 번호 제목 내용 작성자 등록일 수정일
 * 사용자가 입력하는 데이터 : 제목 내용 작성자
 * 시스템이 입력하는 데이터 : 번호 등록일 수정일
 * */
 