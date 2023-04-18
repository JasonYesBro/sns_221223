package com.sns.comment.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {

	public int insertComment(
			@Param("content") String content
			, @Param("postId") int postId
			, @Param("userId") int userId);
}
