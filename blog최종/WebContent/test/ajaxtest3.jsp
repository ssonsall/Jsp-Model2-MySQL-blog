<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Fetch API 사용하기 - promise</h1>
	<button onclick="getNum1()">누르세요</button>
	
	<script>	
	
		let num1 = 0;
		let num2 = 0;
		let sum = 0;

		function getNum1(){
			fetch("http://localhost:8000/blog/test2", {method : "POST", body : "안녕"}).then(function(data){	
				console.log(data.text());
				return data.text();
				//return data.json();
			}).then(function(data){
				num1 = data;
				getNum2();
			});
		}

		function getNum2(){
			fetch("http://localhost:8000/blog/test3", {method : "POST"}).then(function(data){
				return data.text();
				//return data.json();
			}).then(function(data){
				num2 = data;
				sum = Number(num1) + Number(num2);
				console.log("sum >> "+ sum);
			});
		}
		
		
	</script>
</body>
</html>