package com.vam.shop;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.model.AuthorVO;
import com.shop.model.BookVO;
import com.shop.model.Criteria;
import com.shop.model.PageDTO;
import com.shop.service.AdminService;
import com.shop.service.AuthorService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AdminService adminService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@GetMapping("/main")
	public void adminGET() throws Exception {
		logger.info("관리자 페이지 이동");
	}
	
	/* 상품 관리 페이지 접속 */
	@GetMapping("/goodsManage")
	public void goodsManageGET(Criteria cri, Model model) throws Exception {
		logger.info("상품 관리 페이지 접속");
		
		// 상품 리스트 데이터
		List list = adminService.goodsGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		}else {
			model.addAttribute("listCheck", "empty");
		}
		
		// 페이지 인터페이스 데이터
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.goodsGetTotal(cri)));
	}
	
	// 상품 등록 페이지
	@GetMapping("/goodsEnroll")
	public void goosEnrollGET(Model model) throws Exception {
		logger.info("상품 등록 페이지 접속");
		
		ObjectMapper objm = new ObjectMapper();
		
		List list = adminService.cateList();
		
		String cateList = objm.writeValueAsString(list);
		model.addAttribute("cateList", cateList);
		
//		logger.info("변경 전.." + list);
//		logger.info("변경 후.." + cateList);
	}
	
	@GetMapping("/authorManage")
	public void authorManageGET(Criteria cri, Model model) throws Exception {
		logger.info("작가 관리 페이지 접속....." + cri);
		
		/* 작가 목록 출력 데이터 */
		List list = authorService.authorGetList(cri);
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		}else {
			model.addAttribute("listCheck", "empty");
		}
		
		/* 페이지 이동 인터페이스 데이터 */
		int total = authorService.authorGetTotal(cri);
		PageDTO pageMaker = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageMaker);
		
	}
	
	@GetMapping("/authorEnroll")
	public void authorEnrollGET() throws Exception {
		logger.info("작가 등록 페이지 접속");
	}
	
	// 작가 등록 실행
	@PostMapping("/authorEnroll.do")
	public String authorEnrollPOST(AuthorVO author, RedirectAttributes rttr) throws Exception {
		
		logger.info("authorEnroll : " + author);
		authorService.authorEnroll(author);
		rttr.addFlashAttribute("enroll_result", author.getAuthorName());
		
		return "redirect:/admin/authorManage";
	}
	
	// 작가 상세조회 페이지 이동
	@GetMapping({"/authorDetail", "/authorModify"})
	public void authorGetInfoGET(int authorId, Criteria cri, Model model) throws Exception {
		logger.info("authorDetail..." + authorId);
		
		/* 작가 관련 페이지 정보 */
		model.addAttribute("cri", cri);
		
		/* 선택 작가 정보 */
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
	} 
	
	// 작가 수정 실행
	@PostMapping("/authorModify")
	public String authorModifyPOST(AuthorVO author, RedirectAttributes rttr) throws Exception {
		logger.info("authorModifyPOST..." + author);
		
		int result = authorService.authorModify(author);
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/authorManage";
	}
	
	// 상품 등록
	@PostMapping("/goodsEnroll")
	public String goodsEnrollPOST(BookVO book, RedirectAttributes rttr) {
		logger.info("goodsEnrollPOST..." + book);
		
		adminService.bookEnroll(book);
		rttr.addFlashAttribute("enroll_result", book.getBookName());
		return "redirect:/admin/goodsManage";	
	}
	
	// 작가 검색 팝업창
	@GetMapping("/authorPop")
	public void authorPopGET(Criteria cri, Model model) throws Exception {
		logger.info("authorPopGET...");
		cri.setAmount(5);
		
		// 게시물 출력 데이터
		List list = authorService.authorGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);	// 작가가 존재하는 경우
		}else {
			model.addAttribute("listCheck", "empty");	// 작가가 존재하지 않는 경우
		}
		
		// 페이지 이동 인터페이스 데이터
		model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));
	}
	
	// 상품 상세 조회 페이지
	@GetMapping({"/goodsDetail", "/goodsModify"})
	public void goodsGetInfoGET(int bookId, Criteria cri, Model model) throws JsonProcessingException {
		logger.info("goodsGetInfo()..." + bookId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		// 카테고리 리스트 데이터
		model.addAttribute("cateList", mapper.writeValueAsString(adminService.cateList()));
		
		// 목록 페이지 조건 정보
		model.addAttribute("cri", cri);
		
		// 조회 페이지 정보
		model.addAttribute("goodsInfo", adminService.goodsGetDetail(bookId));
	}
	
	// 상품 정보 수정
	@PostMapping("/goodsModify")
	public String goodsModifyPOST(BookVO vo, RedirectAttributes rttr) {
		logger.info("goodsModifyPOST..." + vo);
		
		int result = adminService.goodsModify(vo);
		rttr.addFlashAttribute("modify_result", result);
		return "redirect:/admin/goodsManage";
	}
	
	// 상품 정보 삭제
	@PostMapping("/goodsDelete")
	public String goodsDeletePOST(int bookId, RedirectAttributes rttr) {
		logger.info("goodsDeletePOST..");
		
		int result = adminService.goodsDelete(bookId);
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/goodsManage";
	}
	
	// 작가 정보 삭제
	@PostMapping("/authorDelete")
	public String authorDeletePOST(int authorId, RedirectAttributes rttr) {
		logger.info("authorDeletePOST..");
		int result = 0;
		
		try {
			result = authorService.authorDelete(authorId);
			
		}catch(Exception e) {
			e.printStackTrace();
			result = 2;
			rttr.addFlashAttribute("delete_result", result);
			
			return "redirect:/admin/authorManage";
		}
		
		rttr.addFlashAttribute("delete_result", result);
		return "redirect:/admin/authorManage";
	}
	
	// 첨부 파일 업로드
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxActionPOST(MultipartFile uploadFile) {
		logger.info("uploadAjaxActionPOST...");
		String uploadFolder = "C:\\upload";
		
		// 날짜 폴더 경로
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		String datePath = str.replace("-", File.separator);
		
		// 폴더 생성
		File uploadPath = new File(uploadFolder, datePath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		// 파일 이름
		String uploadFileName = uploadFile.getOriginalFilename();
		
		// uuid 적용 파일 이름
		String uuid = UUID.randomUUID().toString();
		
		uploadFileName = uuid + "_" + uploadFileName;
		
		// 파일 위치, 파일 이름을 합친 File 객체
		File saveFile = new File(uploadPath, uploadFileName);
		
		// 파일 저장
		try {
			uploadFile.transferTo(saveFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		/* 파일 여러개일 경우
		 * 메소드의 매개변수 타입을 배열로 지정 MultipartFile[]
		 * for문 안에 파일 저장 코드 넣기
		 */
		
	}
	
}
