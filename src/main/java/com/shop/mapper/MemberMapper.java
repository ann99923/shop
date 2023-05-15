package com.shop.mapper;

import com.shop.model.MemberVO;

public interface MemberMapper {
	
	// 회원가입
	public void memberJoin(MemberVO member);
	
	// 아이디 체크
	public int idCheck(String memberId);
	
	// 로그인
	public MemberVO memberLogin(MemberVO member);
}
