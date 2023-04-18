package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, Object> addPost(
			@RequestParam(value="content", required=false) String content
			, @RequestParam("file") MultipartFile file
			, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		int userId = (int)session.getAttribute("userId");
		String userloginId = (String)session.getAttribute("userLoginId");
		
		int rowCnt = 0;
		rowCnt = postBO.addPost(userId, userloginId, content, file);
		
		if (rowCnt > 0) {
			result.put("code", 1);
			result.put("result", "게시글이 저장되었습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "게시글 저장에 실패하였습니다.");
		}
		
		return result;
	}
}
