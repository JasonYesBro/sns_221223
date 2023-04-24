package com.sns.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

@RequestMapping("/detail")
@Controller
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	// TODO 클릭한 게시물 상세보기 페이지
	@RequestMapping("{postId}")
	public String postView(@PathVariable("postId") int postId, Model model) {
		
		Post post = postBO.getPostByPostId(postId);
		
		
		model.addAttribute("view", "profile/detail");
		model.addAttribute("post", post);
		
		return "template/layout";
	}
}
