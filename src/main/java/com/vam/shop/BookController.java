package com.vam.shop;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.mapper.AttachMapper;
import com.shop.model.AttachImageVO;
import com.shop.model.BookVO;
import com.shop.model.Criteria;
import com.shop.model.PageDTO;
import com.shop.service.AttachService;
import com.shop.service.BookService;

@Controller
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private BookService bookService;
	
	// 메인 페이지 이동
	@GetMapping("/main")
	public void mainPageGET(Model model) {
		logger.info("메인 페이지 진입");
		model.addAttribute("cate1", bookService.getCateCode1());
		model.addAttribute("cate2", bookService.getCateCode2());
		
	}
	
	// 이미지 출력
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		logger.info("getImage()..." + fileName);
		
		File file = new File("C:\\upload\\" + fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 이미지 정보 반환
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachImageVO>> getAttachList(int bookId){
		logger.info("getAttachList.." + bookId);
		
		return new ResponseEntity<List<AttachImageVO>>(attachService.getAttachList(bookId), HttpStatus.OK);
	}
	
	// 상품 검색
	@GetMapping("/search")
	public String searchGoodsGET(Criteria cri, Model model) {
		logger.info("cri: " + cri);
		
		List<BookVO> list = bookService.getGoodsList(cri);
		logger.info("pre list: " + list);
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			logger.info("list: " + list);
		}else {
			model.addAttribute("listCheck", "empty");
			return "/search";
		}
		
		model.addAttribute("pageMaker", new PageDTO(cri, bookService.goodsGetTotal(cri)));
		
		String[] typeArr = cri.getType().split("");
		for(String s : typeArr) {
			if(s.equals("T") || s.equals("A")) {
				model.addAttribute("filter_info", bookService.getCateInfoList(cri));
			}
		}
		return "/search";
	}

}
