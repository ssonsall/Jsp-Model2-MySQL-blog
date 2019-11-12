<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String path = application.getRealPath("media");
		MultipartRequest multi = new MultipartRequest(request, path, 1024 * 1024 * 2, //2MB
				"UTF-8", new DefaultFileRenamePolicy());

		String username = multi.getParameter("username");
		String filename = multi.getFilesystemName("userProfile"); //정책에 변경된 이름. 실제 저장된 이름
		String originFilename = multi.getOriginalFileName("userProfile"); // 사용자가 실제 올린 파일 이름
		String contextPath = getServletContext().getContextPath(); //이렇게 해야 내 컨텍스트 패쓰가 바껴도 안망가진다
		String downloadPath = getServletContext().getRealPath("media");
		String filepath = contextPath + "/media/" + filename;
	%>
	path >>
	<%=path%><br /> username >>
	<%=username%><br /> filename >>
	<%=filename%><br /> originFilename >>
	<%=originFilename%><br /> contextPath >>
	<%=contextPath%><br /> downloadPath >>
	<%=downloadPath%><br />
	<img src="<%=filepath%>" width="500px" height="500px">

</body>
</html>