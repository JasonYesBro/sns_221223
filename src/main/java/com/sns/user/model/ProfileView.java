package com.sns.user.model;

import java.util.List;

import com.sns.post.model.Post;

public class ProfileView {
	private int userId;
	private List<Post> postList;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	// List<Like> likeView;
}
