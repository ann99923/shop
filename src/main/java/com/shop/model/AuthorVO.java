package com.shop.model;

import java.util.Date;

import lombok.Data;

@Data
public class AuthorVO {
	
	private int authorId;	// 작가 아이디
	private String authorName;	// 작가 이름
	private String nationId;	// 국가 아이디
	private String nationName;	// 작가 국가
	private String authorIntro;	// 작가 소개
	private Date regDate;	// 등록날짜
	private Date updateDate;	// 수정날짜
	
//	public void setNationId(String nationId) {
//		this.nationId = nationId;
//		if(nationId.equals("01")) {
//			this.nationId = "국내";
//		}else {
//			this.nationId = "국외";
//		}
//	}

}
