package com.shop.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.AuthorVO;
import com.shop.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorServiceTest {

	@Autowired
	AuthorService service;
	
//	@Test
//	public void authorEnrollTest() throws Exception {
//		AuthorVO author = new AuthorVO();
//		author.setAuthorName("서비스");
//		author.setNationId("02");
//		author.setAuthorIntro("서비스 테스트");
//		service.authorEnroll(author);
//	}
	
	// 작가 목록
//	@Test
//	public void authorGetList() throws Exception {
//		Criteria cri = new Criteria(3, 10);
//		List<AuthorVO> list = service.authorGetList(cri);
//		for(int i=0; i<list.size(); i++) {
//			System.out.println("list" + i + "........" + list.get(i));
//		}
//	}
	
	// 작가 상세조회
//	@Test
//	public void authorGetDetailTest() throws Exception {
//		int authodId = 30;
//		System.out.println("author..." + service.authorGetDetail(authodId));
//	}
	
	// 작가 수정
	@Test
	public void authorModifyTest() throws Exception {
		AuthorVO author = new AuthorVO();
		
		author.setAuthorId(1);
		System.out.println("수정 전.." + service.authorGetDetail(author.getAuthorId()));
		
		author.setAuthorName("수정 서비스 테스트");
		author.setNationId("01");
		author.setAuthorIntro("수정 서비스 테스트");
		service.authorModify(author);
		
		System.out.println("수정 전.." + service.authorGetDetail(author.getAuthorId()));		
	}
}
