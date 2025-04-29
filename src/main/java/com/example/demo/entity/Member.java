package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "tbl_member")
public class Member extends BaseEntity {

	@Id
	@Column(length = 50)
	String id;
	
	// 패스워드 크기 - 해시코드 변환 사용
	@Column(length = 200, nullable = false)
	String password;
	
	@Column(length = 100, nullable = false)
	String name;
	
	@Column(length = 100, nullable = false)
	String role; //등급
}
