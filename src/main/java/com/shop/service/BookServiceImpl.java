package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.BookMapper;
import com.shop.model.BookVO;
import com.shop.model.Criteria;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BookServiceImpl implements BookService {
	
	// 상품 검색
	@Autowired
	private  BookMapper bookMapper;

	@Override
	public List<BookVO> getGoodsList(Criteria cri) {
		// TODO Auto-generated method stub
		log.info("getGoodsList..");
		
		String type = cri.getType();
		String[] typeArr = type.split("");
		String[] authorArr = bookMapper.getAuthorIdList(cri.getKeyword());
		
		if(type.equals("A") || type.equals("AC") || type.equals("AT") || type.equals("ACT")) {
			if(authorArr.length == 0) {
				return new ArrayList();
			}
		}
		
		for(String t : typeArr) {
			if(t.equals("A")) {
				cri.setAuthorArr(authorArr);
			}
		}
		
		return bookMapper.getGoodsList(cri);
	}

	// 상품 총 갯수
	@Override
	public int goodsGetTotal(Criteria cri) {
		// TODO Auto-generated method stub
		log.info("goodsGetTotal..");
		
		return bookMapper.goodsGetTotal(cri);
	}

}
