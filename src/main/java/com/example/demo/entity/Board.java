package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "tbl_board")
public class Board extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //auto increament
	int no; //게시물 번호
	
	@Column(length = 100, nullable = false)
	String title;
	
	@Column(length = 1500, nullable = false)
	String content;
	
//	@Column(length = 50, nullable = false)
//	String writer;
	
	@ManyToOne // 관계차수는 1:N로 설정
	Member writer;
	
	
}

/*
 * 데이터 베이스에 tbl_board라는 테이블이 없으면 자동으로 생성
 * 엔티티 구조가 바뀌었을때는 삭제 후 재생성 
 * */
 