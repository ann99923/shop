package com.shop.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTest {
	
	@Autowired
	private MemberMapper memberMapper;
	
//	@Test
//	public void memberJoin() throws Exception {
//		MemberVO member = new MemberVO();
//		member.setMemberId("shop_test");
//		member.setMemberPw("shop_test");
//		member.setMemberName("shop_test");
//		member.setMemberMail("shop_test");
//		member.setMemberAddr1("shop_test");
//		member.setMemberAddr2("shop_test");
//		member.setMemberAddr3("shop_test");
//		memberMapper.memberJoin(member);
//	}
	
//	@Test
//	public void memberIdChk() throws Exception {
//		String id = "admin";
//		String id2 = "12";
//		memberMapper.idCheck(id);
//		memberMapper.idCheck(id2);
//	}
	
	@Test
	public void memberLogin() throws Exception{
		MemberVO member = new MemberVO();
		
		/* 올바른 아이디 비번 입력 경우 */
		member.setMemberId("1234");
		member.setMemberPw("1234");
		
		/* 올바르지 않은 아이디 비번 입력 경우 */
//		member.setMemberId("1");
//		member.setMemberPw("1");
		
		memberMapper.memberLogin(member);
		System.out.println("결과 값: " + memberMapper.memberLogin(member));
		
		
	}
	
}
