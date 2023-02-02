package com.cafeProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cafeProject.constant.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member") // 테이블명
@Getter
@Setter
@ToString
public class Mamber {
	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String memberEmail;
	
	
	private String memberPw;
	
	
	private String memberNm;
	
	@Enumerated(EnumType.STRING)
	private Role memberRole;
	
	
}
