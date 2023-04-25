package com.sns.post.bo;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostMapper;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class PostBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private FileManagerService fileManager;
	
	// TODO 게시물 게시 api
	// 게시물이 들어갈 때 이미지 path 처리 해줘야 함
	public int addPost(int userId, String userloginId, String content, MultipartFile file) {
		
		String imagePath = null;
		if (file != null) {
			// 서버에 이미지 업로드 후 imagaPath 받아옴
			imagePath = fileManager.saveFile(userloginId, file);
		}
		return postMapper.insertPost(userId, content, imagePath);
	}

	public List<Post> getPostList() {
		
		return postMapper.selectPostList();
	}

	public List<Post> getPostListByLoginId(int userId) {

		return postMapper.selectPostListByLoginId(userId);
	}

	public Post getPostByPostId(int postId) {

		return postMapper.selectPostByPostId(postId);
	}
	
	public int deletePostByPostId(int postId, int userId) {
		
		Post post = getPostByPostId(postId);
		if(post == null) {
			logger.warn("[delete post] postId : {}, userId : {}", postId, userId);
			return 0;
		}
		// DB
		// 글안에 이미지 삭제
		String imagePath = null;
		imagePath = post.getImagePath();
		
		if (imagePath != null ) {
			logger.warn("[delete imagePath] imagePath : {}", imagePath);
			
			fileManager.deleteFile(imagePath);
		}
		
		// 글안에 댓글들 삭제
		deleteCommentByPostId(postId);
		
		// 글안에 좋아요 삭제
		deleteLikesByPostId(postId);
		
		// 글이 삭제되고 좋아요가 삭제 되었을 시에 post삭제
		return postMapper.deletePostByPostId(postId, userId);
		
	}
	
	public int deleteCommentByPostId(int postId) {
		
		return postMapper.deleteCommentsByPostId(postId);
	}
	
	public int deleteLikesByPostId(int postId) {
		return postMapper.deleteLikesByPostId(postId);
	}
	
	public CardView generatedPostView(int postId, int userId) {
		CardView cardView = new CardView();
		//cardView.setCommentList(commentBO.getCommentListByPostId(postId));
		Post post = new Post();
		post = getPostByPostId(postId);
		
		User user = new User(); 
		user = userBO.getUserByUserId(post.getUserId());
		// 일반 commentList를 불러올게 아니라 댓글을 작성한 사용자와 댓글을 같이 보여줘야 하기 때문에 generatedView객체를 가져온다.
		cardView.setCommentList(commentBO.generatedCommentViewList(postId));
		cardView.setPost(post);
		cardView.setUser(user);
		cardView.setFilledLike(likeBO.existLike(post.getId(), userId));
		cardView.setLikeCnt(likeBO.getLikeCntByPostId(post.getId()));
		
		return cardView;
	}
}
