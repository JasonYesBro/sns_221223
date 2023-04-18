package com.sns.comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.mapper.CommentMapper;

@Service
public class CommentBO {
	@Autowired
	private CommentMapper commentMapper;
	
	public int addComment(String content, int postId, int userId) {
		
		return commentMapper.insertComment(content, postId, userId);
	}

}
