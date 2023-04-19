<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CountingStarGram</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.4.js"
	integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
	crossorigin="anonymous">
</script>
<!-- 내가 만든 스타일시트 -->
<link rel="stylesheet" type="text/css" href="/static/css/style.css" />
</head>
<body>
	<div id="wrap">
		<header>
			<jsp:include page="../include/header.jsp"></jsp:include>
		</header>
		<section>
			<jsp:include page="../${ view }.jsp" />
		</section>
		<div id="top-btn">
			<a class=""><img src="/static/img/star.png" alt="" id="top-star" /></a>
		</div>
	</div>
</body>
<script>
	let backToTop = document.getElementById('top-btn');
	
	let checkScroll=()=>{
	
	    // 페이지가 수직으로 얼마나 스크롤되었는지를 확인하는 값(픽셀 단위로 변환) pageOffset
	    let pageOffset = window.pageYOffset;
	
	    // 이 값이 0이 아니면, 클래스를 show를 선언하고, 그렇지 않다면 show를 삭제한다. 디폴트 css는 hidden상태
	    // 0이면 스크롤이 안된상태 이고, 0이 아니면 스크롤이 일어난상태 
	    if(pageYOffset !== 0){
	        // id= backtotop  class="show"를 추가한다.
	        backToTop.classList.add('show');  
	    }else{
	        backToTop.classList.remove('show'); 
	    }
	}
	
	let moveBackToTop=()=>{
	    if(window.pageYOffset > 0 ){
	        //스무스하게 스크롤 하기 //어디까지 올라갈지 위치를 정한다. behavior 자연스럽게 이동.
	        window.scrollTo({top:0, behavior:"smooth"});
	    }
	}
	
	
	// 스크롤할때마다, checkScroll을 호출해라.
	window.addEventListener('scroll', checkScroll);
	// 클릭하면 oveBackToTop를 호출해라
	backToTop.addEventListener('click',moveBackToTop);
</script>
</html>