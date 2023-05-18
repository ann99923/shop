package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.AttachImageVO;
import com.shop.model.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminServiceTest {
	
	@Autowired
	private AdminService service;
	
	// 상품 등록 & 상품 이미지 등록 테스트
	@Test
	public void bookEnrollTest() {
		BookVO vo = new BookVO();
		
		// 상품 정보
		vo.setBookName("service 테스트");
		vo.setAuthorId(1);
		vo.setPubleYear("2021-03-17");
		vo.setPublisher("출판사");
		vo.setCateCode("202001");
		vo.setBookPrice(20000);
		vo.setBookStock(300);
		vo.setBookDiscount(0.23);
		vo.setBookIntro("책소개");
		vo.setBookContents("책 목차");
		
		// 이미지 정보
		List<AttachImageVO> imageList = new ArrayList<AttachImageVO>();
		
		AttachImageVO image1 = new AttachImageVO();
		AttachImageVO image2 = new AttachImageVO();
		
		image1.setFileName("test image1");
		image1.setUploadPath("test image1");
		image1.setUuid("test1111");
		
		image2.setFileName("test image2");
		image2.setUploadPath("test image2");
		image2.setUuid("test2222");
		
		imageList.add(image1);
		imageList.add(image2);
		
		vo.setImageList(imageList);
		
		// bookEnroll() 메서드 호출
		service.bookEnroll(vo);
		
		System.out.println("등록된 VO : " + vo);
	}

}
