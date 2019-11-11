<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 텍스트 에디터를 제일 위에 넣는 이유 -->
<!-- html은 위에서부터 인터프리터 방식으로 읽기 때문에 중복되는 값들은 밑에 나온걸로 적용된다.
부트스트랩 제이쿼리 이런거 겹치기 때문에 (nav, footer) 나브나 풋터보다 위에 둔다 -->

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>

<%@ include file="/include/nav.jsp"%>



<c:if test="${empty sessionScope.user}">
	<script>
		alert('로그인이 필요합니다.');
		location.href = "/blog/user/login.jsp";
	</script>
</c:if>

<!--================Contact Area =================-->
<section class="contact_area">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form class="row contact_form" action="/blog/board?cmd=update" method="post">
					<div class="col-md-12">
						<div class="form-group">
							<input type="text" class="form-control" id="title" name="title" placeholder="Title" value="${board.title}"/>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<textarea class="form-control" id="summernote" name="content">${board.content}</textarea>
						</div>
					</div>
					<div class="col-md-12 text-right">
						<button type="submit" value="submit" class="btn submit_btn">Update</button>
					</div>
					<input type="hidden" name="id" value="${board.id}">
				</form>
			</div>
		</div>
	</div>
</section>
<!--================Contact Area =================-->
<br />
<br />
<script>
	$('#summernote').summernote({
		placeholder : '내용을 입력하세요.',
		tabsize : 2,
		height : 300
	});
	$('.dropdown-toggle').dropdown();
</script>
<%@ include file="/include/footer.jsp"%>
