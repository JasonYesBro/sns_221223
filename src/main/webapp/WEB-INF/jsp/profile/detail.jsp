<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
        <div><span>${post.userId}</span></div>
        <div>
            <div>
                <img src="${post.imagePath}" alt="게시물이미지" width="300">
            </div>
            <div>
                <p>${post.content}</p>
            </div>
        </div>
        <!-- 댓글들 보여지기 -->
        <div class="comment-box d-flex justify-content-start">
            <div>
                <span class="mr-2 font-weight-bold">tmdgus5611</span>
            </div>
            <div class="comment-content-box">    
                <span class="">댓글내용 Lorem ipsum dolor sit amet consectetur adipisicing elit. Ducimus, quo! Odio voluptas nihil corrupti quasi magnam voluptates asperiores vel illo itaque quod?</span>
            </div>
            <div class="d-flex align-items-center">
                <img src="deleteImg.png" class="delete-img" alt="" width="13">
            </div>
        </div>
    </div>