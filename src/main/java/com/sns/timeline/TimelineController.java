package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	@Autowired
	private PostBO postBO;

	@GetMapping(value="/timeline_view", produces = "image/jpeg")
	public String timelineView(Model model, HttpSession session) {
		model.addAttribute("view", "timeline/timeline");

		// 세션이 있는지 확인하여 로그인되어있다면 게시글폼 띄우기
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			// 비로그인 상태라면
			return "redirect:/user/sign_in_view";
		}

		List<Post> postList = postBO.getPostList();

		// TODO postList 받아오기
		model.addAttribute("postList", postList);
		model.addAttribute("view", "timeline/timeline");
		
		return "template/layout";
	}
}
