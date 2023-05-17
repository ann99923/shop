package com.shop.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.AuthorVO;
import com.shop.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorMapperTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorMapperTest.class);

	@Autowired
	private AuthorMapper mapper;
	
	// 작가 등록 테스트
//	@Test
//	public void authorEnrollTest() throws Exception {
//		AuthorVO author = new AuthorVO();
//		author.setAuthorName("테스트");
//		author.setNationId("01");
//		author.setAuthorIntro("테스트");
//		
//		mapper.authorEnroll(author);
//	}
	
	// 작가 목록 테스트
//	@Test
//	public void authorGetListTest() throws Exception {
//		Criteria cri = new Criteria(3, 10);		//3 페이지 & 10개 행 표시
//		cri.setKeyword("테스트");
//		
//		List<AuthorVO> list = mapper.authorGetList(cri);
//		for(int i=0; i<list.size(); i++) {
//			System.out.println("list" + i + "........" + list.get(i));
//		}
//	}
	
	// 작가 총 수 테스트
//	@Test
//	public void authorGetTotalTest() throws Exception {
//		Criteria cri = new Criteria();
//		cri.setKeyword("테스트");
//		
//		int total = mapper.authorGetTotal(cri);
//		System.out.println("total....." + total);
//	}
	
	//작가 상세 테스트
//	@Test
//	public void authorGetDetailTest() throws Exception {
//		int authorId=1;
//		AuthorVO author = mapper.authorGetDetail(authorId);
//		logger.info("author....." + author);
//	}
	
	// 작가 수정 테스트
//	@Test
//	public void authorModifyTest() throws Exception {
//		AuthorVO author = new AuthorVO();
//		
//		author.setAuthorId(1);
//		System.out.println("수정 전.." + mapper.authorGetDetail(author.getAuthorId()));
//		
//		author.setAuthorName("수정테스트");
//		author.setNationId("02");
//		author.setAuthorIntro("수정테스트");
//		mapper.authorModify(author);
//		
//		System.out.println("수정 후.." + mapper.authorGetDetail(author.getAuthorId()));
//	}
	
	// 작가 삭제 테스트
	@Test
	public void authorDeleteTest() {
		int authorId = 1795;
		int result = mapper.authorDelete(authorId);
		if(result == 1) {
			System.out.println("삭제 성공");
		}
	}
}
