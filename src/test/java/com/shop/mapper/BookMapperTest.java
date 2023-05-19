package com.shop.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.BookVO;
import com.shop.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookMapperTest {
	
	@Autowired
	private BookMapper mapper;
	
	@Test
	public void getGoodeListTest() {
		Criteria cri = new Criteria();
		
		// 테스트 키워드
		// cri.setKeyword("위지윅");
		System.out.println("cri: " + cri);
		
		List<BookVO> list = mapper.getGoodsList(cri);
		System.out.println("list: " + list);
		
		System.out.println("--------");
		int goodsTotal = mapper.goodsGetTotal(cri);
		System.out.println("total: " + goodsTotal);
	}

}
