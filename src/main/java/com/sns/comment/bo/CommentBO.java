package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.mapper.CommentMapper;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class CommentBO {
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;
	
	public int addComment(String content, int postId, int userId) {
		
		return commentMapper.insertComment(content, postId, userId);
	}

	public List<Comment> getCommentListByPostId(int postId) {
		return commentMapper.selectCommentListByPostId(postId);
	}

	public int deleteCommentById(int id) {
		return commentMapper.deleteCommentById(id);
	}
	
	/**
	 * 댓글들과 댓글을 작성한 로그인ID를 보여주기 위한 API
	 * @param postId
	 * @return
	 */
	public List<CommentView> generatedCommentViewList(int postId) {
		List<CommentView> commentViewList = new ArrayList<>();
		// 글에 해당하는 댓글들
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		// 반복문 Comment -> commentView
		for ( Comment comment : commentList) {
			// 하나의 댓글뷰
			CommentView commentView = new CommentView();
			commentView.setComment(comment);
			
			// 댓글쓴 유저의 아이디
			// UserBO를 활용하여 user 가져오기
			User user = userBO.getUserByUserId(comment.getUserId());
			//User user = commentMapper.selectLoginIdById(comment.getUserId());
			
			commentView.setCommentUserId(user.getLoginId());
			
			// list에 담기
			commentViewList.add(commentView);
		}

		return commentViewList;
	}

}
