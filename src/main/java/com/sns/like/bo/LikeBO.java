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
		// selectLikeCountByPostIdOrUserId 이 메서드로 대체 가능
		
		if (like == null) {
			likeMapper.insertLikeByPostIdAndUserId(postId, userId);
			return 1;
		} else {
			likeMapper.deleteLikeByPostIdAndUserId(postId, userId);
			return 0;
		}
	}

	public boolean existLike(int postId, Integer userId) {
		
		// 비로그인인 경우
		if(userId == null) {
			return false;
		}
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0;
	}

	public Integer getLikeCntByPostId(int postId) {
//		return likeMapper.selectLikeCntByPostId(id);
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
	}
}
