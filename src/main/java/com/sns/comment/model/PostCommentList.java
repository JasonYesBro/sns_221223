package com.sns.comment.model;

import java.util.List;

public class PostCommentList {
	private int postId;
	private List<Comment> commentList;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

}
