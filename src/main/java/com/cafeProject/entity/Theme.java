package com.cafeProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Theme") //매핑할 테이블명 설정. 설정을 하지 않으면 클래스명으로 설정됨
@Getter
@Setter
@ToString
public class Theme {

	@Id
	@Column(name="theme_id")
	@GeneratedValue(strategy = GenerationType.AUTO) 		
	private Long id;	//테마코드
	
	@Column(nullable = false)
	private String theme_Nm;	//테마 명
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cafe_id")
	private Cafe cafe;
}
