package com.shop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AuthorMapper;
import com.shop.model.AuthorVO;
import com.shop.model.Criteria;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	AuthorMapper authorMapper;
	
	private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);
	
	// 작가 등록
	@Override
	public void authorEnroll(AuthorVO author) throws Exception {
		// TODO Auto-generated method stub
		authorMapper.authorEnroll(author);		
	}
	
	// 작가 목록조회
	@Override
	public List<AuthorVO> authorGetList(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		log.info("(service)authorGetList()....." + cri);
		return authorMapper.authorGetList(cri);
	}
	
	// 작가 총 개수
	@Override
	public int authorGetTotal(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		log.info("(service)authorGetTotal()......." + cri);
		return authorMapper.authorGetTotal(cri);
	}
	
	// 작가 상세조회
	@Override
	public AuthorVO authorGetDetail(int authorId) throws Exception {
		// TODO Auto-generated method stub
		log.info("authorGetDetail....." + authorId);
		return authorMapper.authorGetDetail(authorId);
	}
	
	// 작가 수정
	@Override
	public int authorModify(AuthorVO author) throws Exception {
		// TODO Auto-generated method stub
		log.info("authorModify..." + author);
		return authorMapper.authorModify(author);
	}
}
