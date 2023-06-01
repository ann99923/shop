package com.shop.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookMapperTest {
	
	@Autowired
	private BookMapper mapper;
	
//	@Test
//	public void getGoodeListTest() {
//		Criteria cri = new Criteria();
//		
//		// 테스트 키워드
//		// cri.setKeyword("위지윅");
//		System.out.println("cri: " + cri);
//		
//		List<BookVO> list = mapper.getGoodsList(cri);
//		System.out.println("list: " + list);
//		
//		System.out.println("--------");
//		int goodsTotal = mapper.goodsGetTotal(cri);
//		System.out.println("total: " + goodsTotal);
//	}
	
	/* 작가 id 리스트 요청 */
//	@Test
//	public void getAuthorId() {
//		String keyword = "작가1";
//		String[] list = mapper.getAuthorIdList(keyword);
//		
//		System.out.println("결과: " + list.toString());
//		
//		for(String id : list) {
//			System.out.println("개별 결과: " + id);
//		}
//	}
	
	/* 검색(동적 쿼리 적용) - 작가 */
//	@Test
//	public void getGoodsListTest1() {
//		Criteria cri = new Criteria();
//		String type= "A";
//		String keyword1 = "유홍준";	// DB에 등록된 작가 데이터
//	//	String keyword2 = "없음";		// DB에 등록되지 않은 작가 데이터
//		String cateCode = "";
//		
//		cri.setType(type);
//		cri.setKeyword(keyword1);
//		cri.setAuthorArr(mapper.getAuthorIdList(keyword1));
//		cri.setCateCode(cateCode);
//		
//		List<BookVO> list = mapper.getGoodsList(cri);
//		
//		System.out.println("cri: " + cri);
//		System.out.println("list: " + list);
//	}
	
	/* 검색(동적 쿼리 적용) - 책제목 */
//	@Test
//	public void getGoodsListTest2() {
//		Criteria cri = new Criteria();
//		String type= "T";
//		String keyword1 = "위지윅";	// DB에 등록된 책 제목 데이터
//	//	String keyword2 = "없음";		// DB에 등록되지 않은 책 제목 데이터
//		String cateCode = "";
//		
//		cri.setType(type);
//		cri.setKeyword(keyword1);
//		cri.setAuthorArr(mapper.getAuthorIdList(keyword1));
//		cri.setCateCode(cateCode);
//		
//		List<BookVO> list = mapper.getGoodsList(cri);
//		
//		System.out.println("cri: " + cri);
//		System.out.println("list: " + list);
//	}
	
	/* 검색(동적 쿼리 적용) - 카테고리 */
//	@Test
//	public void getGoodsListTest3() {
//		Criteria cri = new Criteria();
//		String type= "C";
//		String keyword = "";
//		String cateCode = "101001";
//		
//		cri.setType(type);
//		cri.setKeyword(keyword);
//		cri.setAuthorArr(mapper.getAuthorIdList(keyword));
//		cri.setCateCode(cateCode);
//		
//		List<BookVO> list = mapper.getGoodsList(cri);
//		
//		System.out.println("cri: " + cri);
//		System.out.println("list: " + list);
//	}
	
	/* 검색(동적 쿼리 적용) - 카테고리 + 작가 */
//	@Test
//	public void getGoodsListTest4() {
//		Criteria cri = new Criteria();
//		String type= "AC";
//		String keyword1 = "작가1";	// DB에 등록된 작가 데이터
//	//	String keyword2 = "없음";		// DB에 등록되지 않은 작가 데이터
//		String cateCode = "101001";
//		
//		cri.setType(type);
//		cri.setKeyword(keyword1);
//		cri.setAuthorArr(mapper.getAuthorIdList(keyword1));
//		cri.setCateCode(cateCode);
//		
//		List<BookVO> list = mapper.getGoodsList(cri);
//		
//		System.out.println("cri: " + cri);
//		System.out.println("list: " + list);
//	}
	
	/* 검색(동적 쿼리 적용) - 카테고리 + 책제목 */
//	@Test
//	public void getGoodsListTest5() {
//		Criteria cri = new Criteria();
//		String type= "TC";
//		String keyword1 = "유홍준";	// DB에 등록된 책 제목 데이터
//	//	String keyword2 = "없음";		// DB에 등록되지 않은 책 제목 데이터
//		String cateCode = "101001";
//		
//		cri.setType(type);
//		cri.setKeyword(keyword1);
//		cri.setAuthorArr(mapper.getAuthorIdList(keyword1));
//		cri.setCateCode(cateCode);
//		
//		List<BookVO> list = mapper.getGoodsList(cri);
//		
//		System.out.println("cri: " + cri);
//		System.out.println("list: " + list);
//	}
	
	/* 카테고리 테스트 */
//	@Test
//	public void getCateListTest1() {
//		Criteria cri = new Criteria();
//		
//		String type = "TC";
//		String keyword = "이미지";
////		String type = "A";
////		String keyword = "작가1";
//		
//		cri.setType(type);
//		cri.setKeyword(keyword);
//		cri.setAuthorArr(mapper.getAuthorIdList(keyword));
//		
//		String[] cateList = mapper.getCateList(cri);
//		for(String codeNum : cateList) {
//			System.out.println("codeNum::::: " + codeNum);
//		}
//	}
	
	/* 카테고리 정보 얻기 */
	@Test
	public void getCateInfoTest1() {
		Criteria cri = new Criteria();
		
		String type = "TC";
		String keyword = "이미지";
		String cateCode = "101001";
		
		cri.setType(type);
		cri.setKeyword(keyword);
		cri.setCateCode(cateCode);
		mapper.getCateInfo(cri);
	}

}
