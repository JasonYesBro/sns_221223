<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="sign-up-box" class="container mt-2">
	<h1 id="" class="display-4 text-secondary text-center mb-3">회원가입</h1>
	<form action="/user/sign_up" method="post" class="" id="signUpForm">
		<div class="form-group">
			<div class="d-flex justify-content-between">
				<input type="text" name="loginId" id="loginId"
					class="form-control col-8" placeholder="아이디를 입력하세요.">
				<button type="button" id="loginIdCheckBtn" 
					class="btn btn-primary form-control mb-1 col-3">중복확인</button>
			</div>
			<small id="idCheckLength" class="text-danger d-none">ID를 4자 이상 입력해주세요.</small>
			<small id="idCheckDuplicated" class="text-danger d-none">이미 사용중인 ID입니다.</small> 
			<small id="idCheckOk" class="text-primary d-none">사용가능한 ID입니다.</small>
		</div>
		<div class="form-group">
			<input type="password" name="password" id="password" class="form-control"
				placeholder="비밀번호를 입력하세요.">
		</div>
		<div class="form-group">
			<input type="password" name="confirmPassword" id="confirmPassword" class="form-control"
				placeholder="비밀번호를 다시 입력하세요."> 
			<small id="passwordCheck" class="text-danger d-none">비밀번호가 일치하지 않습니다.</small>
		</div>
		<div class="form-group">
			<input type="text" name="name" id="name" class="form-control"
				placeholder="이름을 입력하세요.">
		</div>
		<div class="form-group">
			<input type="text" name="email" id="email" class="form-control"
				placeholder="이메일을 입력하세요.">
		</div>
		<button type="submit" id="signUpBtn" class="btn btn-primary form-control">회원가입</button>
	</form>
</div>

<script>
	$(document).ready(function() {
		$('#loginIdCheckBtn').on('click', function() {
			
			// validation box 초기화
			$('#idCheckLength').addClass("d-none");
			$('#idCheckDuplicated').addClass("d-none");
			$('#idCheckOk').addClass("d-none");
			
			// 사용자가 입력한 loginId 변수에 할당
			let loginId = $('#loginId').val().trim();
			if (loginId.length < 4) {
				$('#idCheckLength').removeClass("d-none");
				return false;
			}
		
			$.ajax({
				// request
				url: "/user/is_duplicated_id"
				, data : {"loginId" : loginId}
				
				// response
				, success : function(data) {
					if(data.result) {
						$('#idCheckDuplicated').removeClass("d-none");
					} else {
						$('#idCheckOk').removeClass("d-none");
					}
				}
				, error : function(request, status, error) {
					alert("관리자에게 문의바랍니다.");
				}
			});
			
			
		});
		
		$('#signUpForm').on('submit', function(e) {
			e.preventDefault();
			
			// 초기화
			$('#passwordCheck').addClass("d-none");
			// validation
			let loginId = $('#loginId').val().trim();
			let password = $('#password').val();
			let confirmPassword = $('#confirmPassword').val();
			let name = $('#name').val().trim();
			let email = $('#email').val().trim();
			
			if (!loginId) {
				alert("아이디를 확인해주세요.");
				return false;
			}
			if (!password || !confirmPassword) {
				alert("비밀번호를 확인해주세요.");
				return false;
			}
			if (!(password == confirmPassword)) {
				$('#passwordCheck').removeClass("d-none");
				return false;
			}
			if (!name) {
				alert("이름을 확인해주세요.");
				return false;
			}
			if (!email) {
				alert("이메일을 확인해주세요.");
				return false;
			}
			
			let url = $(this).attr('action');
			let params = $(this).serialize();
			
			// post AJAX
			$.post(url, params)
			.done(function(data) {
				
				if (data.code == 1) {
					alert("가입을 환영합니다.");
					// 로그인 화면으로 이동
					location.href = "/user/sign_in_view";
				} else {
					alert(data.errorMessage);
				}
			});
		});
	});
	
</script>



























