package com.shop.service;

import java.util.List;

import com.shop.model.BookVO;
import com.shop.model.Criteria;

public interface BookService {
	
	// 상품 검색
	public List<BookVO> getGoodsList(Criteria cri);
		
	// 상품 총 개수
	public int goodsGetTotal(Criteria cri);

}
