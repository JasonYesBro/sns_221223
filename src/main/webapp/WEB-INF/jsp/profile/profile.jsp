<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>

    #profile-box {
        min-width: 850px;
        max-width: 900px;
    }
    
    #left-profile {
    	width:20%;
    }
    
    #right-profile {
    	width:80%;
    }
    #profile-user-id {
    	font-size:25px;
    }
    #post-group {
    	min-width:870px;
    }
    #post-list-box {
        border-top: 1px solid #dddd;
    }
    
    .profile-post-box {
        width:285px;
        height:300px;
        padding: 1.5px !important;
    }
    
    .profile-post-box:hover {
    	cursor:pointer;
    }

    .profile-post-img {
        width:100%;
        height:100%;
        object-fit: cover;
        border-top: 1px solid #ffff;
        border-radius: 1.5%;
    }
</style>
<body>
    <div id="profile-box" class="container d-flex flex-column">
        <div class="d-flex  justify-content-between mb-3 mt-3">
            <div id="left-profile" class="">
            <c:choose>
	            <c:when test="${ empty profile.user.profileImagePath}">
		            <img src="/static/img/defaultProfileImg.png" alt="프로필 이미지" width="150">
	            </c:when>
	            <c:otherwise>
		            <img src="${profile.user.profileImagePath}" alt="프로필 이미지" width="150">
	            </c:otherwise>
            </c:choose>
            </div>
            <div id="right-profile" class="ml-3 d-flex flex-column justify-content-center">
                <div class="d-flex justify-content-between">
                    <div class="d-flex align-items-center">
                        <span id="profile-user-id" class="font-weight-bold">${profile.user.loginId}</span>
                    </div>
                    <button type="button" class="btn btn-secondary">프로필 편집</button>
                </div>
                <div class="mt-2">
                    <p>${profile.user.email}</p>
                </div>
            </div>
        </div>
        <div id="post-group">
            <table class="table table-borderless" id="post-list-box">
                <tr class="d-flex justify-content-start flex-wrap">
		            <c:forEach items="${profile.postList}" var="post">
		            	<td class="profile-post-box" data-post-id="${post.id}"><img src="${post.imagePath}" class="profile-post-img" alt="게시물 이미지"></td>
		            </c:forEach>
            	</tr>
            </table>
        </div>
    </div>
    <script>
    	/* 클릭한 게시물 상세보기로 이동 */
    	$(document).ready(function() {
    		$('.profile-post-box').click(function() {
    			let postId = $(this).data('post-id');
    			// AJAX 통신
    			location.href="/detail/"+postId;
    		});
    		
    		
    	});
    </script>
</body>