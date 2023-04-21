package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {
	@Autowired
	private LikeBO likeBO;
	
	@RequestMapping("/like/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId
			, HttpSession session) { // parameter를 받아오는것과 똑같은 효과
		Map<String, Object> result = new HashMap<>();
		// 있는지 없는지 확인 -> bo 에서 실시 분기문으로 작성하면 됨  결과는 toggle효과가 있는 것처럼 구현하는 것임 
		Integer userId = (Integer)session.getAttribute("userId");
		
		if (userId == null) {
			result.put("code", 300);
			result.put("errorMessage", "로그인 해주시기 바랍니다.");
			return result;
		}
		
		int like = likeBO.likeByPostIdAndUserId(postId, userId);
		// LikeBO, Mapper, XML
		
		if (like == 1) {
			result.put("code", 1);
			result.put("result", "좋아요 되었습니다.");
		} else if(like == 0){
			result.put("code", 2);
			result.put("result", "좋아요 취소되었습니다.");
		}
		
		return result;
	}
}
