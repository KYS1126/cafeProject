package com.cafeProject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.Temporal;

import com.cafeProject.dto.InsertCafeDto;

import lombok.*;

@Entity
@Table(name="cafe") //매핑할 테이블명 설정. 설정을 하지 않으면 클래스명으로 설정됨
@Getter
@Setter
@ToString
public class Cafe extends BaseEntity{
	
	@Id
	@Column(name="cafe_id")
	@GeneratedValue(strategy = GenerationType.AUTO) 		
	private Long id;	//카페 고유 번호
	
	@Column(nullable = false)
	private String cafeNm;	//카페 이름
	
	@Column(nullable = false)
	private String cafeDetail;	//카페상세설명
	
	@Column(nullable = false)
	private String cafeAddress;	//카페 주소
	
	@Column(nullable = false)
	private String cafeTel;	//카페 전화번호
	
	@Column(nullable = false)
	private String cafeClose;	//카페 휴무일
	
    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CafeImg> childList = new ArrayList<>();
	
	//DTO를 엔티티에 각각 매핑
	public void updateCafe(InsertCafeDto insertCafeDto) {
		this.cafeNm = insertCafeDto.getCafeNm();
		this.cafeDetail = insertCafeDto.getCafeDetail();
		this.cafeAddress = insertCafeDto.getCafeAddress();
		this.cafeTel = insertCafeDto.getCafeTel();
		this.cafeClose = insertCafeDto.getCafeClose();
	}
	
}
