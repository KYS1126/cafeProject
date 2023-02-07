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
@Table(name="cafe") //매핑할 테이블명 설정. 설정을 하지 않으면 클래스명으로 설정됨
@Getter
@Setter
@ToString
public class Cafe {
	
	@Id
	@Column(name="cafe_id")
	@GeneratedValue(strategy = GenerationType.AUTO) 		
	private Long id;	//상품코드
	
	@Column(nullable = false)
	private String cafeNm;	//카페이름
	
	@Column(nullable = false)
	private String cafeDetail;	//카페상세설명
	
	@Column(nullable = false)
	private String cafeAddress;	//카페 주소
	
	@Column(nullable = false)
	private String cafeTel;	//카페 전화번호
	
	@Column(nullable = false)
	private String cafeClose;	//카페 영업 시간
	

	
}
