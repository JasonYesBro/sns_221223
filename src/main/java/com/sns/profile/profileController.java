package com.sns.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.profile.bo.ProfileBO;
import com.sns.user.model.ProfileView;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/profile")
@Controller
public class profileController {

	@Autowired
	private ProfileBO profileBO;
	
	@GetMapping("/{userLoginId}")
	public String getUserProfile(HttpSession session, Model model) {
		
		if( session.getAttribute("userLoginId") == null) {
			return "redirect:/user/sign_in_view";
		}
		
		String userLoginId = (String)session.getAttribute("userLoginId");
		int userId = (int)session.getAttribute("userId");
		
		ProfileView profileView = profileBO.generatedProfileViewList(userId, userLoginId);
		
		// 프로필화면에 들어갈 것 내 정보 + 게시물들
		model.addAttribute("profile", profileView);
		model.addAttribute("view", "profile/profile");
		
		return "template/layout";
	}
	
}
