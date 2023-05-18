package com.shop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	@Override
	public void bookEnroll(BookVO book) {
		// TODO Auto-generated method stub
		log.info("(service)bookEnroll...");
		adminMapper.bookEnroll(book);
		
		if(book.getImageList() == null || book.getImageList().size() <= 0) {
			return;
		}
		
		book.getImageList().forEach(attach ->{
			attach.setBookId(book.getBookId());
			adminMapper.imageEnroll(attach);
		});
	}
	
	// 카테고리 리스트
	@Override
	public List<CateVO> cateList() {
		// TODO Auto-generated method stub
		log.info("(service)cateList...");
		return adminMapper.cateList();
	}
	
	// 상품 리스트
	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		// TODO Auto-generated method stub
		log.info("(service)goodsGetList...");
		return adminMapper.goodsGetList(cri);
	}
	
	// 상품 총 개수
	@Override
	public int goodsGetTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return adminMapper.goodsGetTotal(cri);
	}
	
	// 상품 조회 페이지
	@Override
	public BookVO goodsGetDetail(int bookId) {
		// TODO Auto-generated method stub
		log.info("(service)goodsGetDetail..." + bookId);
		return adminMapper.goodsGetDetail(bookId);
	}
	
	//상품 수정
	@Override
	public int goodsModify(BookVO vo) {
		// TODO Auto-generated method stub
		log.info("goodsModify...");
		
		return adminMapper.goodsModify(vo);
	}
	
	// 상품 삭제
	@Override
	public int goodsDelete(int bookId) {
		// TODO Auto-generated method stub
		log.info("goodsDelete..");
		
		return adminMapper.goodsDelete(bookId);
	}
}
