package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.mapper.CommentMapper;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.user.model.User;

@Service
public class CommentBO {
	@Autowired
	private CommentMapper commentMapper;
	
	public int addComment(String content, int postId, int userId) {
		
		return commentMapper.insertComment(content, postId, userId);
	}

	public List<Comment> getCommentListByPostId(int postId) {
		return commentMapper.selectCommentListByPostId(postId);
	}

	public int deleteCommentById(int id) {
		return commentMapper.deleteCommentById(id);
	}
	
	public List<CommentView> generatedCommentViewList(int postId) {
		List<CommentView> commentViewList = new ArrayList<>();
		// 글에 해당하는 댓글들
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		// 반복문 Comment -> commentView
		for ( Comment comment : commentList) {
			CommentView commentView = new CommentView();
			commentView.setComment(comment);
			
			User user = commentMapper.selectLoginIdById(comment.getUserId());
			commentView.setCommentUserId(user.getLoginId());
			
			commentViewList.add(commentView);
		}

		return commentViewList;
	}

}
