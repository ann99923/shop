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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.mapper.AttachMapper;
import com.shop.model.AttachImageVO;
import com.shop.service.AttachService;

@Controller
public class ShopController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@Autowired
	private AttachMapper attachMapper;
	
	// 메인 페이지 이동
	@GetMapping("/main")
	public void mainPageGET() {
		logger.info("메인 페이지 진입");
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
		
		return new ResponseEntity<List<AttachImageVO>>(attachMapper.getAttachList(bookId), HttpStatus.OK);
	}

}
