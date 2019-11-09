<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mail Authentication Page</title>
</head>
<body>
	<!-- 조인하면 DB에 넣고 거기서 메일 주소 여기로 넘겨서 -->
	<h1>메일 인증 페이지입니다.</h1>
	<h1>당신의 email 주소는 ${user.email} 입니다.</h1>
	<h1>인증은 나중에 해도 되며, 인증을 하지 않아도 로그인이 가능합니다.</h1>
	<h1>다만, 글작성, 댓글달기, 답글달기는 email 인증을 한 이후에 가능합니다.</h1>
	<br />
	<br />
	<br />
	<form action="http://localhost:8000/blog/api/gmail" method="post">
		<input type="hidden" name="id" value="${user.id}" />
		<input type="hidden" name="email" value="${user.email}" />
		<input type="hidden" name="username" value="${user.username}"/>
		<button type="submit">인증</button>
		<button type="button" onclick="moveToListPage()">나중에</button>
	</form>
	<script>
		function moveToListPage() {
			location.href = "http://localhost:8000/blog/index.jsp";
		}
	</script>
</body>
</html>