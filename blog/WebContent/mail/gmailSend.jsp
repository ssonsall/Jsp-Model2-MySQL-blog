
<%@page import="blog.util.Gmail"%>
<%@page import="java.util.Properties"%>
<%@page import="blog.util.SHA256"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="java.util.Properties"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//메일 전송하기
	String id = (String) request.getAttribute("id"); // 등록된 id값
	String username = (String) request.getAttribute("username"); // 등록된 username값
	String to = (String) request.getAttribute("email"); // 1) 이메일 받기
	String from = "ssonsall@gmail.com"; //메일을 보낼 주체 (개념상 블로그서버겠지)
	String code = SHA256.getEncrypt(to, "cos"); //2) 코드값 해쉬
	//String host = "http://localhost:8000/blog"; 안적어도 된다고 하심

	//3) 사용자에게 보낼 메일 내용
	String subject = "MyBlog 이메일 인증 서비스입니다.";
	StringBuffer sb = new StringBuffer();
	sb.append("<h2>안녕하세요! "+username+" 님!</h2><br/>");
	sb.append("<h2>아래의 이메일 인증을 눌러서 진행해주세요.</h2><br/>");
	//주소에 이메일 안남기기 위해서 서버 한번 더 타는걸로
	sb.append("<a href='http://localhost:8000/blog/auth?code=" + code + "&id=" + id + "'>");
	sb.append("<h2>이메일 인증하기</h2></a>");

	String content = sb.toString();

	//4) 이메일을 보낼때 필요한 설정값
	Properties p = new Properties();
	p.put("mail.smtp.user", from);
	p.put("mail.smtp.host", "smtp.googlemail.com");
	p.put("mail.smtp.port", "465"); //TLS 587, SSL 465
	p.put("mail.smtp.starttls.enable", "true");
	p.put("mail.smtp.auth", "true");
	p.put("mail.smtp.debug", "true");
	p.put("mail.smtp.socketFactory.port", "465");
	p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	p.put("mail.smtp.sockerFactory.fallback", "false");

	//이메일 전송!!
	try {
		Authenticator auth = new Gmail(); //5) 내가 설정한 아이디, 비밀번호 넘기기
		Session ses = Session.getInstance(p, auth);
		ses.setDebug(true);
		MimeMessage msg = new MimeMessage(ses);
		msg.setSubject(subject);
		Address fromAddr = new InternetAddress(from);
		msg.setFrom(fromAddr);
		Address toAddr = new InternetAddress(to);
		msg.addRecipient(Message.RecipientType.TO, toAddr);
		msg.setContent(content, "text/html; charset=UTF8");
		Transport.send(msg); // 메일을 최종적으로 보내는 함수
		session.setAttribute("mailAuth", to);
	} catch (Exception e) {
		//비정상 = 히스토리 백
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이메일 인증 오류입니다.')");
		script.println("history.back();");
		script.println("</script>");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>인증 메일이 전송되었습니다. 이메일에서 인증해주세요.</h1>
</body>
</html>