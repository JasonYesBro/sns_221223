package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.like.model.Like;

@Repository
public interface LikeMapper {

	public int insertLikeByPostIdAndUserId(@Param("postId") int postId, @Param("userId") int userId);
	
	public Like selectLikeByPostIdAndUserId(@Param("postId") int postId, @Param("userId") int userId);

	public int deleteLikeByPostIdAndUserId(@Param("postId")int postId, @Param("userId")int userId);
}
