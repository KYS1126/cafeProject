package com.cafeProject.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.thymeleaf.util.StringUtils;
import com.cafeProject.dto.CafeListDto;
import com.cafeProject.dto.CafeSearchDto;
import com.cafeProject.dto.QCafeListDto;
import com.cafeProject.entity.Cafe;
import com.cafeProject.entity.QCafe;
import com.cafeProject.entity.QCafeImg;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class CafeRepositoryCustomImpl implements CafeRepositoryCustom{
	
	//jpa 쿼리를 사용하기 위해 필요함.
	private JPAQueryFactory queryFactory;
	
	//EntityManager -> 엔티티 매니저 생성
	public CafeRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	//시간 구하기 일단은 필요없음
/*	private BooleanExpression regDtsAfter(String searchDateType) {
		LocalDateTime dateTime = LocalDateTime.now(); 
		
		//현재 날짜로 부터 이전 날짜를 구해준다.
		if(StringUtils.equals("all", searchDateType) || searchDateType == null)  return null;
		else if(StringUtils.equals("1d", searchDateType)) dateTime = dateTime.minusDays(1); 
		else if(StringUtils.equals("1w", searchDateType)) dateTime = dateTime.minusWeeks(1);
		else if(StringUtils.equals("1m", searchDateType)) dateTime = dateTime.minusMonths(1);
		else if(StringUtils.equals("6m", searchDateType)) dateTime = dateTime.minusMonths(6);
		
		return QCafe.cafe.regTime.after(dateTime); //이후의 시간
	}
*/	
	
	//판매 상태인데 일단은 필요없음
/*	private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
		return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
	}
*/	
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("cafeNm", searchBy)) {
			return QCafe.cafe.cafeNm.like("%" + searchQuery + "%");
		}
		
		return null;
	}
	
	
	@Override 
	public Page<Cafe> getAdminCafePage(CafeSearchDto itemSearchDto, Pageable pageable) {
		List<Cafe> content = queryFactory
				.selectFrom(QCafe.cafe)	//select * from item
				.where(searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery())) // and itemNm(or createBy) LIKE %검색어%
				.orderBy(QCafe.cafe.id.desc())
				.offset(pageable.getOffset())	//데이터를 가져올 시작 index
				.limit(pageable.getPageSize())	//한 번에 가지고 올 최대 개수
				.fetch();
		
//		long total = content.size(); size로 구하면 오류가 나서 쿼리문으로 구현해야 함
		//Wildcard.count = count(*)
		long total = queryFactory.select(Wildcard.count).from(QCafe.cafe)	//전체 레코드의 갯수를 구함
				.where(searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
				.fetchOne();
		return new PageImpl<>(content, pageable, total);
	}
	
	private BooleanExpression cafeNmLike(String searchQuery) {
		return StringUtils.isEmpty(searchQuery) ? null : QCafe.cafe.cafeNm.like("%" + searchQuery + "%");
	}

	@Override
	public Page<CafeListDto> getCafeListPage(CafeSearchDto cafeSearchDto, Pageable pageable) {
		//QItem과 QItemImg 불러온다.
		QCafe cafe = QCafe.cafe;
		QCafeImg cafeImg = QCafeImg.cafeImg;
		
		List<CafeListDto> content = queryFactory.select(
				new QCafeListDto (	//MainItemDto 객체로 저장. (@QueryProjection)
						cafe.id,
						cafe.cafeNm,
						cafe.cafeDetail,
						cafe.cafeAddress,
						cafe.cafeTel,
						cafe.cafeClose,
						cafeImg.imgUrl)
				)
				.from(cafeImg)
				.join(cafeImg.cafe, cafe)	//item을 통해서 두 테이블 join
				.where(cafeImg.repimgYn.eq("Y"))
				.where(cafeNmLike(cafeSearchDto.getSearchQuery()))
				.orderBy(cafe.id.desc())
				.offset(pageable.getOffset())	//데이터를 가져올 시작 index
				.limit(pageable.getPageSize())	//한 번에 가져올 데이터의 최대 개수
				.fetch();
		
		//count(*), count할 때는 fecthOne으로 끝내면 된다. 강의교안 참고 !
		//레코드의 갯수를 구하는 메소드 !
		long total = queryFactory.select(Wildcard.count)
				.from(cafeImg)
				.join(cafeImg.cafe, cafe)
				.where(cafeImg.repimgYn.eq("Y"))
				.where(cafeNmLike(cafeSearchDto.getSearchQuery()))
				.fetchOne();
				
		//이렇게 리턴 시키면 스프링이 알아서 Page 객체를 만들어준다.
		return new PageImpl<>(content, pageable, total);
	}
 
}
