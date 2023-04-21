package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	//private static final Logger logger = LoggerFactory.getLogger(TimelineController.class);
	
	@Autowired
	private TimelineBO timelineBO;

	@GetMapping("/timeline_view")
	public String timelineView(Model model, HttpSession session) {
		model.addAttribute("view", "timeline/timeline");

		// 세션이 있는지 확인하여 로그인되어있다면 게시글폼 띄우기
		Integer userId = (Integer)session.getAttribute("userId");

//		if (userId == null) {
//			// 비로그인 상태라면
//			return "redirect:/user/sign_in_view";
//		}
		// 비로그인 사용자도 받아야 함
		
		
		// 한번에 가져오는 방식
		List<CardView> cardList = timelineBO.generatedCardViewList(userId);
		
		model.addAttribute("cardList", cardList);
		model.addAttribute("view", "timeline/timeline");
		
		return "template/layout";
	}
}
