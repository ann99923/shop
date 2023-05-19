package com.shop.mapper;

import java.util.List;

import com.shop.model.BookVO;
import com.shop.model.Criteria;

public interface BookMapper {
	
	// 상품 검색
	public List<BookVO> getGoodsList(Criteria cri);
	
	// 상품 총 개수
	public int goodsGetTotal(Criteria cri);
	
	// 작가 id 리스트 요청
	public String[] getAuthorIdList(String keyword);

}
