<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/nav.jsp"%>
<!--================Blog Area =================-->
<section class="blog_area single-post-area">
	<div class="container">
		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-8">
				<div class="main_blog_details" id="main_blog_details">
					<a href="#">
						<h4>메일 인증 페이지입니다.</h4>
					</a>
					<!-- 본문시작 -->

					당신의 email 주소는
					<span style="font-size: 40px; font-style: bold; color: red;">${user.email}</span>
					입니다.<br /><br /> 인증은 나중에 해도 되며, 인증을 하지 않아도 로그인이 가능합니다.<br /><br />
					다만, 글작성, 댓글달기, 답글달기는 email 인증을 한 이후에 가능합니다.<br /><br />
					<form action="/blog/api/gmail" method="post">
						<input type="hidden" name="id" value="${user.id}" /> <input type="hidden" name="email" value="${user.email}" /> <input type="hidden" name="username" value="${user.username}" />
						<div style="text-align : center;">							
							<button class="blog_btn" type="submit" style="cursor: pointer;">인증</button>
							<button class="blog_btn" type="button" style="cursor: pointer;" onclick="moveToListPage()">나중에</button>
						</div>
					</form>
					<br /><br />
				</div>
			</div>
			<div class="col-lg-2"></div>
		</div>
	</div>
</section>
<!--================Blog Area =================-->


<%@include file="/include/footer.jsp"%>

<!-- comment script -->
<script>
	function moveToListPage() {
		location.href = "/blog/index.jsp";
	}
</script>
</body>

</html>