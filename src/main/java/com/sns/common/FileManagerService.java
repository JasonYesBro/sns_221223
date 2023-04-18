package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	public static final String FILE_UPLOAD_PATH = "/Users/jasonmilian/Desktop/megaProject/6_spring_project/sns/workspace/images/";
	private static final Logger logger = LoggerFactory.getLogger(FileManagerService.class);
	
	public String saveFile(String loginId, MultipartFile file) {
		
		// 저장 path
		String dirName = loginId + "_" +  System.currentTimeMillis() + "/";// 사용자의 id + 저장한 시간으로 폴더명을 지음
		String filePath = FILE_UPLOAD_PATH + dirName;
		
		File directory = new File(filePath);
		
		// 폴더 만드는 데 실패했을 
		if( directory.mkdir() == false ) {
			return null;
		}
		
//		logger.debug("---------------"+ file.getOriginalFilename());
//		logger.debug("------" + ogName.split("[.]")[1]);
		
		String ogName = file.getOriginalFilename();
		
		// . 으로 split 할때 []안에 .을 넣어 연산해야함 or 역슬래시(\) 다음에 오는 문자를 일반 문자로 취급 +escape
		String ext = ogName.split("\\.")[1]; // 확장자를 꺼냄
		String fileName = file.getOriginalFilename().replaceAll(file.getOriginalFilename(), "postImg." + ext);
		
		try {
			byte[] bytes = file.getBytes();
		
			Path path = Paths.get(filePath + fileName);
			Files.write(path, bytes); // 파일 업로드
					
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return "/images/" + dirName + fileName;
	}
}
