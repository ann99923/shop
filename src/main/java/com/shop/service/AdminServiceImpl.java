package com.shop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AdminMapper;
import com.shop.model.BookVO;
import com.shop.model.CateVO;
import com.shop.model.Criteria;

@Service
public class AdminServiceImpl implements AdminService {
	
	private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private AdminMapper adminMapper;

	// 상품 등록
	@Override
	public void bookEnroll(BookVO book) {
		// TODO Auto-generated method stub
		log.info("(service)bookEnroll...");
		adminMapper.bookEnroll(book);
	}
	
	// 카테고리 리스트
	@Override
	public List<CateVO> cateList() {
		// TODO Auto-generated method stub
		log.info("(service)cateList...");
		return adminMapper.cateList();
	}
	
	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		// TODO Auto-generated method stub
		return adminMapper.goodsGetList(cri);
	}
	
	@Override
	public int goodsGetTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return adminMapper.goodsGetTotal(cri);
	}
}
