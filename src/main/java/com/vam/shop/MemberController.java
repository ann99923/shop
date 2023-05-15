package com.vam.shop;


import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.MemberVO;
import com.shop.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;

	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	// 로그인 페이지 이동
	@GetMapping("/login")
	public void loginPageGET() {
		logger.info("로그인 페이지");
	}
	
	// 로그인
	@PostMapping("/login.do")
	public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception {
//		System.out.println("로그인 메소드 진입");
//		System.out.println("전달된 데이터: " + member);
		
		HttpSession session = request.getSession();
		String rawPw = "";
		String encodePw = "";			
		MemberVO lvo = memberService.memberLogin(member);
		
		if(lvo != null) {	// 아이디와 비밀번호 일치(로그인 성공)
			rawPw = member.getMemberPw();	// 사용자가 제출한 비밀번호
			encodePw = lvo.getMemberPw();	// DB에 저장된 인코딩 된 비밀번호
			if(true == pwEncoder.matches(rawPw, encodePw)) {
				lvo.setMemberPw("");
				session.setAttribute("member", lvo);
				return "redirect:/main";
			}else {
				rttr.addFlashAttribute("result", 0);
				return "redirect:/member/login";
			}
		}else {		// 일치하는 아이디가 없을때(로그인 실패)
			rttr.addFlashAttribute("result", 0);
			return "redirect:/member/login";	// 로그인 페이지로 이동
		}
		
//		if(lvo == null) {	// 로그인 실패
//			int result = 0;
//			rttr.addFlashAttribute("result", result);
//			return "redirect:/member/login";
//		}
//		session.setAttribute("member", lvo);
//		return "redirect:/main";
	}
	
	// 회원가입 페이지 이동
	@GetMapping("/join")
	public void joinPageGET() {
		logger.info("회원가입 페이지");
	}
	
	// 회원가입
	@PostMapping("/join")
	public String memberJoinPOST(MemberVO member) throws Exception {
//		logger.info("join 진입");
//		
//		// 회원 가입 서비스 실행
//		memberService.memberJoin(member);
//		logger.info("회원가입 성공");
		
		String rawPw = "";		// 인코딩 전 비밀번호
		String encodePw = "";	// 인코딩 후 비밀번호
		
		rawPw = member.getMemberPw();	// 비밀번호 데이터 얻음
		encodePw = pwEncoder.encode(rawPw);	// 비밀번호 인코딩
		member.setMemberPw(encodePw);		// 인코딩된 비밀번호 member 객체에 다시 저장
		
		// 회원 가입 쿼리 실행
		memberService.memberJoin(member);
		
		return "redirect:/main";
	}
	
	// 아이디 중복검사
	@PostMapping("/memberIdChk")
	@ResponseBody	//view와 연동되지 않고 return 값만 전송
	public String memberIdChkPOST(String memberId) throws Exception {
		logger.info("memberIdChk() 진입");
		
		int result = memberService.idCheck(memberId);
		
		if(result != 0) {
			return "fail";	// 중복 아이디가 존재하는 경우
		}else {
			return "success";	// 중복 아이디가 없는 경우
		}
	}
	
	// Mail 인증
	// 객체로 전달
//	@GetMapping("/sendMail")
//	public void sendMailTest() throws Exception {
//		String subject = "test 메일";
//		String content = "메일 테스트 내용" + "<img src=\"https://t1.daumcdn.net/cfile/tistory/214DCD42594CC40625\">";
//		String from = "ann99923@naver.com";
//		String to = "ann99923@naver.com";
//		
//		try {
//			MimeMessage mail = mailSender.createMimeMessage();
//			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
//			mailHelper.setFrom(from);
//			mailHelper.setTo(to);
//			mailHelper.setSubject(subject);
//			mailHelper.setText(content, true);
//			
//			FileSystemResource file = new FileSystemResource(new File("c:\\Temp\\test.txt"));
//			mailHelper.addAttachment("test.txt", file);
//			
//			mailSender.send(mail);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
	// 그냥 전달
//	@GetMapping("/sendMail")
//	public void sendMailTest2() throws Exception {
//		String subject = "test 메일";
//		String content = "메일 테스트 내용" + "<img src=\"https://t1.daumcdn.net/cfile/tistory/214DCD42594CC40625\">";
//		String from = "ann99934@naver.com";
//		String to = "ann99934@naver.com";
//		
//		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
//			
//			@Override
//			public void prepare(MimeMessage mimeMessage) throws Exception {
//				// TODO Auto-generated method stub
//				final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//				mailHelper.setFrom(from);
//				mailHelper.setTo(to);
//				mailHelper.setSubject(subject);
//				mailHelper.setText(content, true);
//				FileSystemResource file = new FileSystemResource(new File("c:\\Temp\\test.txt"));
//				mailHelper.addAttachment("test.txt", file);
//			}
//		};
//		mailSender.send(preparator);
//	}
	
	// 이메일 인증
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {
		logger.info("이메일 데이터 전송 확인");
		logger.info("인증메일 : " + email);
		
		/* 인증번호(난수) 생성 */
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		logger.info("인증번호 : " + checkNum);
		
		/* 이메일 보내기 */
		String setFrom = "ann99923@naver.com";
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content = "홈페이지를 방문해주셔서 감사합니다." + 
						"<br><br>" + 
						"인증 번호는 " + checkNum + "입니다." + 
						"<br>" +
						"해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
			mailHelper.setFrom(setFrom);
			mailHelper.setTo(toMail);
			mailHelper.setSubject(title);
			mailHelper.setText(content, true);	
			mailSender.send(mail);
		}catch(Exception e) {
			e.printStackTrace();
		}
		String num = Integer.toString(checkNum);
		return num;
	}
	
	// 로그아웃
	@GetMapping("/logout.do")
	public String logoutGET(HttpServletRequest request) throws Exception {
		logger.info("logoutMainGET 메서드 진입");
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/main";
	}
	
	@PostMapping("/logout.do")
	@ResponseBody
	public void logoutPOST(HttpServletRequest request) throws Exception {
		logger.info("비동기 로그아웃 메소드 진입");
		HttpSession session = request.getSession();
		session.invalidate();
	}
	
}
