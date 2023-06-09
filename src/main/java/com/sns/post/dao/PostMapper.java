package com.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostMapper {
	public List<Post> selectPostList();

	public int insertPost(
			@Param("userId") int userId
			, @Param("content") String content
			, @Param("imagePath") String imagePath);

	public List<Post> selectPostListByLoginId(int userId);

	public Post selectPostByPostId(int postId);

	public int deleteCommentsByPostId(int postId);
	
	public int deleteLikesByPostId(int postId);

	public int deletePostByPostId(@Param("postId") int postId, @Param("userId") int userId);
}
