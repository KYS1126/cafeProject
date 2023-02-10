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

@Entity
@Table(name="cafe_img") 
@Getter
@Setter
public class CafeImg extends BaseEntity{
	
	@Id
	@Column(name="item_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //이미지 고유 번호
	
	private String imgNm; //이미지 이름
	
	private String imgOri; //원본 이미지 파일명
	
	private String imgUrl; //이미지 경로
	
	private String repimgYn; //대표 이미지 여부
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cafe_id")
	private Cafe cafe;
	
	public void updateCageImg(String oriImgName, String imgName, String imgUrl) {
		this.imgOri = oriImgName;
		this.imgNm = imgName;
		this.imgUrl = imgUrl;
	}
	
	
	
	
	
	
}
