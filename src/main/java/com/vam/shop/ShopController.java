package com.vam.shop;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	// 메인 페이지 이동
	@GetMapping("/main")
	public void mainPageGET() {
		logger.info("메인 페이지 진입");
	}
	
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

}
