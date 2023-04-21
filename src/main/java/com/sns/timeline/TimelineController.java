package com.sns.timeline;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.PostCommentList;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	//private static final Logger logger = LoggerFactory.getLogger(TimelineController.class);
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private TimelineBO timelineBO;

	@GetMapping("/timeline_view")
	public String timelineView(Model model, HttpSession session) {
		model.addAttribute("view", "timeline/timeline");

		// 세션이 있는지 확인하여 로그인되어있다면 게시글폼 띄우기
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			// 비로그인 상태라면
			return "redirect:/user/sign_in_view";
		}
		
		// 내가 한 방식 -> 시간과 자원을 소비함
//		List<Post> postList = postBO.getPostList();
//		List<PostCommentList> result = new ArrayList<>();

		
		// 한번에 가져오는 방식
		List<CardView> cardList = timelineBO.generatedCardViewList();
		
		
		// 내가 한 방
//		for (Post post : postList) {
//			logger.debug("--post--"+ post.getId());
//			
////			Map<Post, List<Comment>> comment = new HashMap<>();
//			List<Comment> commentList = commentBO.getCommentListByPostId(post.getId());
//			
//			PostCommentList postCommentList = new PostCommentList();
//			postCommentList.setPostId(post.getId());
//			postCommentList.setCommentList(commentList);
////			comment.put(post, commentList);
//			result.add(postCommentList);
//		}
		
		// postList 받아오기
		//model.addAttribute("postList", postList);
		//model.addAttribute("result", result);
		//model.addAttribute("userId", result);
		
		model.addAttribute("cardList", cardList);
		model.addAttribute("view", "timeline/timeline");
		
		return "template/layout";
	}
}
