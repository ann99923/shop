package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.MemberMapper;
import com.shop.model.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public void memberJoin(MemberVO vo) {
		// TODO Auto-generated method stub
		mapper.memberJoin(vo);
	}

	@Override
	public int idCheck(String memberId) {
		// TODO Auto-generated method stub
		return mapper.idCheck(memberId);
	}
	
	@Override
	public MemberVO memberLogin(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		return mapper.memberLogin(member);
	}
}
