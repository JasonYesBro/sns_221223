package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.model.CommentView;
import com.sns.post.model.Post;
import com.sns.user.model.User;

// View용 객체
// 게시글 1개와 매핑
public class CardView {

	// 글 정보
	private Post post;

	// 유저 정보
	private User user;

	// 댓글 정보
	private List<CommentView> commentList;

	private boolean filledLike;
	
	private Integer likeCnt;

	public Integer getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(Integer likeCnt) {
		this.likeCnt = likeCnt;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CommentView> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentView> commentList) {
		this.commentList = commentList;
	}

	public boolean isFilledLike() {
		return filledLike;
	}

	public void setFilledLike(boolean filledLike) {
		this.filledLike = filledLike;
	}

	// 좋아요 정보
	// private Like like;

	// 로그인한 사람의 좋아요 유무 체크(boolean)

}
