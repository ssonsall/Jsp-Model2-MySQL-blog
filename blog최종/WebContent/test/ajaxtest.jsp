<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax Test</title>
</head>
<body>
	<h1>Ajax Test</h1>
	<hr />
	<div id="demo">1</div>

	<button type="button" onclick="loadDoc()">Change Count</button>

	<script>
		var reply = {
			id : null,
			boardId : 1,
			userId : 3,
			content : 'input tag에 적힌 값을 들고오자.',
			createDate : null
		};
		console.log(reply);
		
		var replyString = JSON.stringify(reply);
		
		console.log(replyString);

		function loadDoc() {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					/* 응답 받는 부분 */
					var jsonData = JSON.parse(this.responseText);
					document.querySelector("#demo").innerHTML = jsonData.name + " " + jsonData.sal;
				}
			};
			
			/* 데이터 넘기는 (요청) 부분 */
			xhttp.open("POST", "http://localhost:8000/blog/test", true);
			xhttp.setRequestHeader("Content-type","application/json"); //MIME타입을 잘 적어줘야한다.
			xhttp.send(replyString);
		}
	</script>
</body>
</html>