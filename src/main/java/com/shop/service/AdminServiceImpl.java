package com.shop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AdminMapper;
import com.shop.model.BookVO;
import com.shop.model.CateVO;

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
	
	@Override
	public List<CateVO> cateList() {
		// TODO Auto-generated method stub
		log.info("(service)cateList...");
		return adminMapper.cateList();
	}
}
