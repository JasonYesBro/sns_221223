package com.sns.user.model;

import java.util.List;

import com.sns.post.model.Post;

public class ProfileView {
	private User user;
	private List<Post> postList;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Post> getPostList() {
		return postList;
	}
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
	// List<Like> likeView;
}
