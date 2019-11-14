<%@page import="java.io.PrintWriter"%>
<%@page import="blog.util.SHA256"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//code값 받기
	String code = (String)request.getAttribute("code");
	String id = (String)request.getAttribute("id");
	String email = (String)request.getAttribute("email");

	//return code 값이랑 send code 값을 비교해서 동일하면
	boolean rightCode = SHA256.getEncrypt(email , "cos").equals(code) ? true : false;

	PrintWriter script = response.getWriter();
	if (rightCode) {
		//DB에 칼럼 emailCheck(Number)    ->      1 = 인증, 0 = 미인증     인증되면 1을 update
		script.println("<script>");
		//DB 업데이트 필요
		script.println("location.href='/blog/user?cmd=authUpdate&id=" + id + "'"); 
		script.println("</script>");
	} else {
		script.println("<script>");
		script.println("alert('이메일 인증을 실패하였습니다.')");
		script.println("location.href='/blog/index.jsp'"); //list 페이지로 보내자
		script.println("</script>");
	}

	//인증완료  ->  로그인 페이지 이동

	//미인증  ->  에러페이지 이동
%>