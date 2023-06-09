<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
</style>
<body>
	<!-- 로그인 될때 -->
	<c:if test="${ not empty userId }">
		<div class="container text-group-box">
			<!-- 글쓰기 영역 -->
			<div class="text-box">
				<textarea id="contentBox" class="post-input-box font-weight-lighter"
					name="" rows="5">내용을 입력해주세요.
	            </textarea>
				<div class="d-flex justify-content-between align-items-center">
					<div class="d-flex">
						<input type="file" id="file" class="d-none"
							accept=".gif, .jpg, .png, .jpeg"> <a href="#"
							id="fileUploadBtn"><img src="../static/img/imgIcon.png"
							class="img-icon" alt="" width="40"></a>
						<div id="fileName" class="ml-2"></div>
					</div>
					<div>
						<button id="writeBtn" type="button" class="btn btn-sm btn-primary">게시</button>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<!--  Post 시작 -->
	<div id="post-wrapper">
		<!-- 첫번째 게시물 -->
		<c:forEach items="${cardList}" var="card">
			<div class="container post-box pt-1 pb-3">
				<div class="d-flex justify-content-between align-items-center mb-1">
					<span class="post-id-box font-weight-bold">${card.user.loginId}</span>
					<!-- 글쓴이와 로그인유저와 같을때만 표시 -->
					<c:if test="${card.user.id eq userId}">
					 	<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" alt="더보기 이미지" class="see-more-icon" width="30" data-toggle="modal" data-target="#modal" data-post-id="${card.post.id}">
					</c:if>
				</div>
				<div>
					<div class="mb-2">
						<img src="${card.post.imagePath}" alt="" class="post-img" data-post-id="${card.post.id}">
					</div>
				</div>
				<div class="ml-2">
					<div class="d-flex align-items-center mb-2">
							<c:if test="${ card.filledLike eq false }">
								<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" alt="빈하트이미지" width="20px" class="un-like-img mr-2" data-post-id="${card.post.id}">
							</c:if>
							<c:if test="${ card.filledLike eq true }">
								<img src="https://www.iconninja.com/files/527/809/128/heart-icon.png" alt="좋아요하트이미지" width="20px" class="like-img mr-2" data-post-id="${card.post.id}">
							</c:if>
						 <span>좋아요 </span><span class="post-like mr-1 ml-1">${card.likeCnt}</span><span>개</span>
					</div>
					<div class="post-content-box mb-2">
						<div class="mb-2">
							<span class="mr-2 font-weight-bold">${ card.user.loginId }</span> <span>${ card.post.content }</span>
						</div>
					</div>
					<div class="comment-list-box">
						<c:forEach items="${card.commentList}" var="commentList" varStatus="status">
							<c:if test="${card.post.id eq commentList.comment.postId}">
								<%-- <c:forEach items="${result.commentList}" var="comment"
									varStatus="status"> --%>
									<div class="comment-box d-flex">
										<div>
											<span class="mr-2 font-weight-bold">${commentList.commentUserId }</span>
										</div>
										<div class="comment-content-wrapper d-flex justify-content-between">
											<div class="comment-content-box">
												<span class="">${commentList.comment.content}</span>
											</div>
											<div class="d-flex align-items-center">
												<c:if test="${ userId eq commentList.comment.userId }">
												<!-- a 태그로 감싸기? -->
													<img src="../static/img/deleteImg.png" class="delete-img"
														alt="삭제버튼" data-delete-id="${commentList.comment.id}" width="13">
												</c:if>
											</div>
										</div>
									</div>
								<%-- </c:forEach> --%>
								<c:if test="${empty card.commentList}">
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
									data-post-id="${card.post.id}">게시</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
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
		$(document).ready(function() {
			// 파일업로드 이미지 클릭 -> 숨겨져 있는 file태그를 동작시킴
			$('#fileUploadBtn').on('click', function(e) {
				e.preventDefault(); // a태그를 클릭했을때 스크롤이 올라가는 현상 방지
				$('#file').click(); // input file 을 클릭한 것과 같은 효과임
			});

			// 사용자가 이미지를 선택했을 때 유효성 확인 및 업로드 된 파일명 노출
			$('#file').on('change', function(e) {
				let fileName = e.target.files[0].name;

				// 확장자 유효성 확인
				let ext = fileName.split(".").pop()
						.toLowerCase();
				if (ext != "jpg" && ext != "png"
						&& ext != "jpeg"
						&& ext != "gif") {
					alert("이미지 파일만 업로드할 수 있습니다.");
					$(this).val(""); // 파일 초기화 진행 잘못된파일이 들어가 있는 상태로 서버에 보내질수 있음 !
					$('#fileName').text(""); // 파일이름 비우기 ! 위와 같은 이유 !
					return;
				}
				// 유효성 통과한 이미지는 상자에 업로드 된 파일 이름 노출
				$('#fileName').text(fileName);
			});

			$('#writeBtn').on('click', function(e) {
				// validation
				let content = $('#contentBox')
						.val();
				let fileName = $('#fileName')
						.html().trim();

				// content는 null 허용임
				if (fileName == "") {
					alert("사진을 선택해주세요.");
					return false;
				}
				// 글내용, 이미지, 확장자 체크
				let formData = new FormData();
				formData.append("content",
						content);
				formData.append("file",
						$('#file')[0].files[0]);

				// ajax 전송
				$.ajax({
					// request
					type : "post",
					url : "/post/create",
					data : formData,
					enctype : "multipart/form-data" // 파일 업로드를 위한 필수 설정
					,
					processData : false // 파일 업로드를 위한 필수 설정
					,
					contentType : false
					// response
					,
					success : function(
							data) {
						if (data.code == 1) {
							// 성공
							alert("게시글이 저장되었습니다.");
							location.href = "/timeline/timeline_view";
						} else {
							// 실패
							alert(data.errorMessage);
						}
					},
					error : function(
							status,
							request,
							error) {
						alert("관리자에게 문의해주시기 바랍니다.");
					}
				});

			});

			// 게시버튼 클릭
			$('.commentCreateBtn').on('click', function(e) {
				let comment = $(this)
						.parent()
						.siblings()
						.children(
								'.commentInputBox')
						.val();
				let postId = $(this).data(
						'post-id');

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
			
			// 더보기 이미지 클릭
			$(".see-more-icon").click(function(e) {
				let postId = $(this).data('post-id'); // getting
				
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
								location.reload(true);
							} else {
								alert(data.errorMessage);
							}
						}
					});
				});
			});
			
			// 게시물 상세보기
			$('.post-img').click(function() {
    			// post-id
    			let postId = $(this).data('post-id');
    			// AJAX 통신
    			location.href="/detail/"+postId;
    		});
		});
	</script>
</body>

</html>