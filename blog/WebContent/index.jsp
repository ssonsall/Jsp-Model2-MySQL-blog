<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Page</title>
</head>
<body>
	<!-- 서블릿 타고 가야함. 리스트 목록 가져와야지 -->
	<script>
	function moveToList(authStatus){
		${sessionScope.user.id}
		if(authStatus === 1 ){
			alert('이메일 인증이 성공했습니다.');
			location.href = "/blog/board?cmd=list&page=1";
		}else if(authStatus === 0){
			alert('이메일 인증이 실패했습니다.');
			location.href = "/blog/board?cmd=list&page=1";
		}else {
		location.href = "/blog/board?cmd=list&page=1";
		}
	}	
	moveToList(${param.authStatus});
	</script>
</body>
</html>