package com.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AdminMapper;
import com.shop.model.BookVO;

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
}
