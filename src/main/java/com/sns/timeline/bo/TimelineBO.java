package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.like.dao.LikeMapper;
import com.sns.like.model.Like;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class TimelineBO {
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeMapper likeMapper;
	// 비로그인도 카드 리스트가 보여져야 하기 때문에 userId는 null 가능 -> Integer
	public List<CardView> generatedCardViewList(Integer userId) {
		List<CardView> cardViewList = new ArrayList<>();
		
		// postBO
		List<Post> postList = postBO.getPostList();
		
		
		for (Post post : postList) {
			CardView cardView = new CardView();
			// postList 담기
			cardView.setPost(post);
			
			// user정보 담기
			// 유저정보를 가져오기 위해서는 post의 userId 와 user의 id가 같은 것을 가져와야 함 
			User user = new User(); 
			user = userBO.getUserByUserId(post.getUserId());
			cardView.setUser(user);
			
			// commentList 담기
			// 댓글을 단 사람의 loginId를 가져오기
			List<CommentView> commentList = commentBO.generatedCommentViewList(post.getId());
			
			cardView.setCommentList(commentList);
			
			// 좋아요 담기
			//cardView.setFilledLike();
			if (userId != null) {
				
				Like like = null; 
				like = likeMapper.selectLikeByPostIdAndUserId(post.getId(), userId);
				
				if (like == null) {
					cardView.setFilledLike(false);
				} else {
					cardView.setFilledLike(true);
				}
			} else {
				cardView.setFilledLike(false);
			}
			// 좋아요 유무 체크 담기
			
			
			cardViewList.add(cardView);
		}
		
		return cardViewList;
	}
	
}
