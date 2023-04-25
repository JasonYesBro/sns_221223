<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
        <div><span>${cardView.user.loginId}</span></div>
        <div>
            <div>
                <img src="${cardView.post.imagePath}" alt="게시물이미지" width="300">
            </div>
            <div>
                <p>${cardView.post.content}</p>
            </div>
        </div>
        <!-- 좋아요 체크, 좋아요 갯수 -->
        <div class="d-flex align-items-center mb-2">
			<c:if test="${ cardView.filledLike eq false }">
				<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" alt="빈하트이미지" width="20px" class="un-like-img mr-2" data-post-id="${cardView.post.id}">
			</c:if>
			<c:if test="${ cardView.filledLike eq true }">
				<img src="https://www.iconninja.com/files/527/809/128/heart-icon.png" alt="좋아요하트이미지" width="20px" class="like-img mr-2" data-post-id="${cardView.post.id}">
			</c:if>
			<span>좋아요 </span><span class="post-like mr-1 ml-1">${cardView.likeCnt}</span><span>개</span>
		</div>
        <!-- 댓글들 보여지기 -->
        <div class="comment-box d-flex justify-content-start">
	        <c:forEach items="${cardView.commentList}" var="commentList">
	            <div>
	                <span class="mr-2 font-weight-bold">${commentList.commentUserId}</span>
	            </div>
	            <div class="comment-content-box">
	                <span class="">${commentList.comment.content}</span>
	            </div>        
	        </c:forEach>
            <!-- <div class="d-flex align-items-center">
                <img src="deleteImg.png" class="delete-img" alt="" width="13">
            </div> -->
        </div>
	<script>
	// 좋아요 부분
	// 비워진 하트를 눌렀을 때
	$('.un-like-img').click(function(e) {
		
		let postId = $(this).data('post-id');
		$.ajax({
			// request
			url : "/like/"+ postId
			
			// response
			, success : function(data) {
				if(data.code == 1) {

					alert("좋아요 되었습니다.");
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			}
			, error : function(status, error, request) {
				alert("관리자에게 문의바랍니다.");
			}
		});
	});
	
	// 채워진 하트를 눌렀을 때
	$('.like-img').click(function(e) {
		let postId = $(this).data('post-id');
		
		$.ajax({
			// request
			url : "/like/"+ postId
			
			// response
			, success : function(data) {
				if(data.code == 2) {

					alert('좋아요 취소되었습니다.');
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			}
			, error : function(status, error, request) {
				alert("관리자에게 문의바랍니다.");
			}
		});
	});
	</script>
    </div>