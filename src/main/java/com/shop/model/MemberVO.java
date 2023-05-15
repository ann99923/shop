package com.shop.model;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	
	private String memberId;	// 회원 id
	private String memberPw;	// 회원 비밀번호
	private String memberName;	// 회원 이름
	private String memberMail;	// 회원 이메일
	private String memberAddr1;	// 회원 우편번호
	private String memberAddr2;	// 회원 주소
	private String memberAddr3;	// 회원 상세주소
	private int adminCk;		// 관리자 구분(0:일반사용자, 1:관리자)
	private Date regDate;		//
	private int money;			// 
	private int point;			// 포인트

}
