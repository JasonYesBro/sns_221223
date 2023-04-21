package com.sns.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.model.Comment;
import com.sns.user.model.User;

@Repository
public interface CommentMapper {

	public int insertComment(
			@Param("content") String content
			, @Param("postId") int postId
			, @Param("userId") int userId);

	public List<Comment> selectCommentListByPostId(@Param("postId") int postId);

	public int deleteCommentById(@Param("id") int id);

}
