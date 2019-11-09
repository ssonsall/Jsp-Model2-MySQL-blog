<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax Test 2</title>
</head>
<body>

	<button onclick="fetchTest()">sendPost3 Request</button>

	<script>
		let postNum2 = 0;
		let postNum3 = 0;
		let sum = 0;

		function sendPost2() {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					postNum2 = this.responseText;
					console.log("postNum2 >> " + postNum2);
				}
			};

			/* 데이터 넘기는 (요청) 부분 */
			xhttp.open("POST", "http://localhost:8000/blog/test2", true);
			xhttp.setRequestHeader("Content-type", "text/plain"); //MIME타입을 잘 적어줘야한다.
			xhttp.send();
		}

		function fetchTest() { 
				fetch("http://localhost:8000/blog/test2",{
				method : "POST",
			}).then(function (response) {
				postNum2 = response.text();
				console.log("fetch에서 postNum2 >> " + postNum2);
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						postNum3 = this.responseText;
						console.log("postNum3 >> " + postNum3);
						let sum = Number(postNum2) + Number(postNum3);
						console.log("sum >> "+sum);
					}
				};
				xhttp.open("POST", "http://localhost:8000/blog/test3", true);
				xhttp.setRequestHeader("Content-type", "text/plain");
				xhttp.send();
				return response.text();
			}).then(function (data){
				console.log(data);
			})
		}
/* 			function sendPost3() {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					postNum3 = this.responseText;
					console.log("postNum3 >> " + postNum3);
					let sum = Number(postNum2) + Number(postNum3);
					console.log("sum >> "+sum);
				}
			};

			xhttp.open("POST", "http://localhost:8000/blog/test3", true);
			xhttp.setRequestHeader("Content-type", "text/plain");
			xhttp.send();
		} */
	</script>
</body>
</html>