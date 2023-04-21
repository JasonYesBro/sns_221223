package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeMapper;
import com.sns.like.model.Like;


@Service
public class LikeBO {
	@Autowired
	private LikeMapper likeMapper;
	
	public int likeByPostIdAndUserId(int postId, int userId) {
		Like like = likeMapper.selectLikeByPostIdAndUserId(postId, userId);
		
		if (like == null) {
			likeMapper.insertLikeByPostIdAndUserId(postId, userId);
			return 1;
		} else {
			likeMapper.deleteLikeByPostIdAndUserId(postId, userId);
			return 0;
		}
	}
}
