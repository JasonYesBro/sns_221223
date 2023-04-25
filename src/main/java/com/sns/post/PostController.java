package com.sns.post;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.timeline.model.CardView;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/detail")
@Controller
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	// TODO 클릭한 게시물 상세보기 페이지
	
	@GetMapping("/{postId}")
	public String postView(@PathVariable("postId") int postId, Model model, HttpSession session) {
		
		int userId = (int)session.getAttribute("userId");
		
		CardView cardView = null;
		
		cardView = postBO.generatedPostView(postId, userId);
		
		model.addAttribute("cardView", cardView);
		model.addAttribute("view", "profile/detail");
		
		return "template/layout";
	}
	
}
