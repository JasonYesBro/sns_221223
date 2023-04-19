package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.Comment;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/create")
	public Map<String, Object> CreateComment(
			@RequestParam("comment") String content
			, @RequestParam("postId") int postId
			, HttpSession session
			, Model model) {
		Map<String, Object> result = new HashMap<>();
		 
		
		// 댓글작성시 로그인 상태가 아니라면
		if (session.getAttribute("userId") == null) {
			result.put("code", 500);
			result.put("errorMessage", "로그인 해주시기 바랍니다.");
			return result;
		}		
		
		int userId = (int)session.getAttribute("userId");
		
		// insert DB
		int rowCnt = 0;
		rowCnt = commentBO.addComment(content, postId, userId);
		
		// if분기문
		if (rowCnt > 0) {
			// 댓글쓰기에 성공하였다면 내가 쓴 댓글이 보여지면서 새로고침이 되어야함.
			// 방금 내가 저장한 댓글을 바로 불러올 수 있도록 해야함
			// model.add
			result.put("code", 1);
			result.put("result", "댓글 저장에 성공하였습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "댓글 저장에 실패하였습니다.");
		}		
		
		return result;
	}
	
	@PostMapping("/delete")
	public Map<String, Object> deleteComment(
			@RequestParam("id") int id
			,HttpSession session) {
		
		Map<String, Object> result = new HashMap<>(); 
		
		int userId = (int)session.getAttribute("userId");
		
		int rowCnt = 0;
		rowCnt = commentBO.deleteCommentById(id);
		
		if (rowCnt > 0) {
			result.put("code", 1);
			result.put("result", "댓글이 삭제되었습니다.");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "댓글삭제에 실패하였습니다.");
		}
		
		return result;
	}
}
