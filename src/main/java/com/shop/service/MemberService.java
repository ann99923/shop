package com.shop.service;

import com.shop.model.MemberVO;

public interface MemberService {
	
	// 회원가입
	public void memberJoin(MemberVO vo) throws Exception;
	
	// 아이디 체크
	public int idCheck(String memberId) throws Exception;
	
	// 로그인
	public MemberVO memberLogin(MemberVO member) throws Exception;

}
