<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div
	class="container text-center d-flex justify-content-around align-items-center">
	<div id="header-title-box">
		<h1 class="">
			<a href="/timeline/timeline_view" class="logo"><img
				src="/static/img/curby.jpg" alt="" width=80 /></a> <a
				href="/timeline/timeline_view" class="logo">CountingStarGram</a>
		</h1>
	</div>
	<c:if test="${ not empty userId }">
		<div id="header-user-box">
			<h4 class="">
				<a href="/profile/${userLoginId}" id="header-login-id"><span>"${userLoginId}"</span></a>
				<a href="/user/sign_out" class="log-out">logout</a>
			</h4>

			<!-- <div>
			<img src="" alt="프로필 이미지" width="60" />
		</div> -->
	</c:if>
	<c:if test="${ empty userId }">
		<div id="header-user-box">
			<a href="/user/sign_in_view">로그인</a>
		</div>
	</c:if>
</div>