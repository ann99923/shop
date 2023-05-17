package com.shop.model;

import java.util.Date;

import lombok.Data;

@Data
public class BookVO {
	
	private int bookId;			// 상품 id
	private String bookName;	// 상품 이름
	private int authorId;		// 작가 id
	private String authorName;	// 작가 이름
	private String publeYear;		// 출판일
	private String publisher;	// 출판사
	private String cateCode;	// 카테고리 코드
	private String cateName;	// 카테고리 이름
	private int bookPrice;		// 상품 가격
	private int bookStock;		// 상품 재고
	private double bookDiscount;	// 상품 할인율
	private String bookIntro;	// 상품 소개
	private String bookContents;	// 상품 목차
	private Date regDate;		// 등록 날짜
	private Date updateDate;	// 수정 날짜

}
