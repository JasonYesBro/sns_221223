package com.sns.profile.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.user.bo.UserBO;
import com.sns.user.model.ProfileView;
import com.sns.user.model.User;

@Service
public class ProfileBO {
	
	@Autowired
	private PostBO postBO;
	
	
	@Autowired
	private UserBO userBO;
	
	// TODO profile 보여주는 서비스 단계
	// user의 정보와 user가 올린 게시물 보여주기
	public ProfileView generatedProfileViewList(int userId, String userLoginId) {
		
		ProfileView profileView = new ProfileView();
		
		List<Post> postList = postBO.getPostListByLoginId(userId);
		
		User user = userBO.getUserByLoginId(userLoginId);
		
		profileView.setPostList(postList);
		profileView.setUser(user);
		
		return profileView;
	}
}
