<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.post-box {
	width: 600px;
}

.post-img {
	height: 550px;
}
</style>
<div class="container post-box pt-1 mt-2 pb-3">

	<div class="d-flex justify-content-between align-items-center mb-1">
		<span class="post-id-box font-weight-bold">${cardView.user.loginId}</span>
		<!-- 글쓴이와 로그인유저와 같을때만 표시 -->
		<c:if test="${cardView.user.id eq userId}">
			<img src="https://www.iconninja.com/files/860/824/939/more-icon.png"
				alt="더보기 이미지" class="see-more-icon" width="30" data-toggle="modal"
				data-target="#modal" data-post-id="${cardView.post.id}">
		</c:if>
	</div>
	<div>
		<div class="mb-2">
			<img src="${cardView.post.imagePath}" alt="" class="post-img">
		</div>
	</div>
	<div class="ml-2">
		<!-- 좋아요 체크, 좋아요 갯수 -->
		<div class="d-flex align-items-center mb-2">
			<c:if test="${ cardView.filledLike eq false }">
				<img
					src="https://www.iconninja.com/files/214/518/441/heart-icon.png"
					alt="빈하트이미지" width="20px" class="un-like-img mr-2"
					data-post-id="${cardView.post.id}">
			</c:if>
			<c:if test="${ cardView.filledLike eq true }">
				<img
					src="https://www.iconninja.com/files/527/809/128/heart-icon.png"
					alt="좋아요하트이미지" width="20px" class="like-img mr-2"
					data-post-id="${cardView.post.id}">
			</c:if>
			<span>좋아요 </span><span class="post-like mr-1 ml-1">${cardView.likeCnt}</span><span>개</span>
		</div>
		<div class="post-content-box mb-2">
			<div class="mb-2">
				<span class="mr-2 font-weight-bold">${cardView.user.loginId}</span>
				<span>${cardView.post.content}</span>
			</div>
		</div>
		<div class="comment-list-box">
			<c:forEach items="${cardView.commentList}" var="commentList" varStatus="status">
				<c:if test="${cardView.post.id eq commentList.comment.postId}">
					<div class="comment-box d-flex">
						<div>
							<span class="mr-2 font-weight-bold">${commentList.commentUserId }</span>
						</div>
						<div
							class="comment-content-wrapper d-flex justify-content-between">
							<div class="comment-content-box">
								<span class="">${commentList.comment.content}</span>
							</div>
							<div class="d-flex align-items-center">
								<c:if test="${ userId eq commentList.comment.userId }">
									<!-- a 태그로 감싸기? -->
									<img src="../static/img/deleteImg.png" class="delete-img"
										alt="삭제버튼" data-delete-id="${commentList.comment.id}"
										width="13">
								</c:if>
							</div>
						</div>
					</div>
					<%-- </c:forEach> --%>
					<c:if test="${empty cardView.commentList}">
						<span>댓글이 없습니다.</span>
					</c:if>
				</c:if>
			</c:forEach>
			<div class="comment-form-box form-row align-items-center mt-2">
				<div class="commentInput">
					<input type="text" class="commentInputBox comment-input-box"
						placeholder="댓글달기">
				</div>
				<div class="">
					<button type="button"
						class="commentCreateBtn btn btn-sm btn-primary"
						data-post-id="${cardView.post.id}">게시</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="modal">
	<%-- modal-dialog-centered 수직가운데 정렬 --%>
	  <div class="modal-dialog modal-dialog-centered modal-sm">
	    <div class="modal-content text-center">
		    <div class="py-2 btn border-bottom">
	    	    <a href="#" id="deletePostBtn">삭제하기</a>
		    </div>
		    <%-- data-dismiss="modal" 모달닫기 --%>
		    <div class="py-2 btn" data-dismiss="modal">
	          <a href="#">취소하기</a>
		    </div>
	    </div>
	  </div>
	</div>
<script>
	// 좋아요 부분
	// 비워진 하트를 눌렀을 때
	$('.un-like-img').click(function(e) {

		let postId = $(this).data('post-id');
		$.ajax({
			// request
			url : "/like/" + postId

			// response
			,
			success : function(data) {
				if (data.code == 1) {

					alert("좋아요 되었습니다.");
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			},
			error : function(status, error, request) {
				alert("관리자에게 문의바랍니다.");
			}
		});
	});

	// 채워진 하트를 눌렀을 때
	$('.like-img').click(function(e) {
		let postId = $(this).data('post-id');

		$.ajax({
			// request
			url : "/like/" + postId

			// response
			,
			success : function(data) {
				if (data.code == 2) {

					alert('좋아요 취소되었습니다.');
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			},
			error : function(status, error, request) {
				alert("관리자에게 문의바랍니다.");
			}
		});
	});
	// 게시버튼 클릭
	$('.commentCreateBtn').on('click', function(e) {
		let comment = $(this).parent().siblings().children('.commentInputBox').val();
		let postId = $(this).data('post-id');

		console.log(comment);
		if (comment == "") {
			alert("댓글 내용을 작성해주세요.");
		}
		
		$.ajax({
			// request
			type : "post",
			url : "/comment/create",
			data : {
				"comment" : comment,
				"postId" : postId
			}
			// response
			,
			success : function(
					data) {
				if (data.code == 1) {
					alert("댓글 작성 완료");
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			},
			error : function() {
				alert("관리자에게 문의하시기 바랍니다.");
			}
		});
	});
	
	$('.delete-img').click(function() {
		let commentId = $(this).data('delete-id');
		
		$.ajax({
			type: "post"
			, url : "/comment/delete"
			, data : {
				"id" : commentId
			}
			, success : function(data) {
				if (data.code == 1) {
					alert("댓글이 삭제되었습니다.");
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
	
	// 더보기 이미지 클릭
	$(".see-more-icon").click(function(e) {
		let postId = $(this).data('post-id'); // getting
		let userId = $(".post-id-box").html().trim();
		
		// 클릭한 postId를 하나의 모달태그에 심어줘야 함 
		$('#modal').data('post-id', postId); // setting
		
		// 모달의 삭제버튼 클릭 시
		// 모달안에 있는 del버튼 이라고 명시
		$('#modal #deletePostBtn').on('click', function(e) {
			e.preventDefault();
			
			let postId = $('#modal').data('post-id');
			
			// AJAX
			$.ajax({
				type: "DELETE"
				, url: "/post/delete"
				, data: {"postId" : postId}
				// response
				, success: function(data){
					if(data.code == 1) {
						alert("게시글 삭제가 되었습니다.");
						location.href="/profile/"+userId;
					} else {
						alert(data.errorMessage);
					}
				}
			});
		});
	});
</script>
</div>