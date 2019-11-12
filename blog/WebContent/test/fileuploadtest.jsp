<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<!-- 미리보기 구현 -->
<div>
	<img id="img__wrap" src="">
</div>

<form action="fileuploadactiontest.jsp" method="POST" enctype="multipart/form-data">
   username=<input type="text" name="username"/><br>
   image=<input id="img__input" type="file" name="userProfile"/><br>
   <input type="submit" value="확인"/><br>
</form>

<script src="/blog/js/jquery-3.2.1.min.js"></script>
<script>
	$('#img__input').on("change",handleImgFile); //(어떤 변화가있을때, 그때 뭐할래)
	function handleImgFile(e){
		var f = e.target.files[0];
		
		if(!f.type.match("image.*")){
			return;
		}
		
		var reader = new FileReader();
		reader.onload = function(e){
			$('#img__wrap').attr("src", e.target.result);
		}
		
		reader.readAsDataURL(f);
	}
</script>
</body>
</html>