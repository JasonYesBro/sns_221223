package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.dao.PostMapper;
import com.sns.post.model.Post;

@Service
public class PostBO {
	@Autowired
	private PostMapper postMapper;
	
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
}
