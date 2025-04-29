package com.example.demo.dto;
// DTO: 컨트롤러와 뷰 사이에서 데이터를 전달하는 객체
//		데이터를 담기 위해 사용하는 클래스
//		뷰와 컨트롤러 사이에서 데이터를 주고받기 휘해서 사용
// 엔티티: 컨트롤러와 DB 사이에서 데이터를 전달하는 객체

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
	
	int no;
	String title;
	String content;
	String writer;
	LocalDateTime regDate;
	LocalDateTime modDate;
	
}
