<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ajax Test reply model json으로 날리기</title>
    <script>
        let replyJS = {
            id: null,
            boardId: 5,
            userId: 3,
            content: "왜 깨짐?",
            createDate: null
        }

        function send() {
            //replyJS를 json으로 (JSON.stringify())
            let url = 'http://localhost:8000/blog/test/reply';
            let replyJS_String = JSON.stringify(replyJS);
            fetch(url, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json; charset=utf-8"                   
                },
                body: replyJS_String
            }).then(function (response) {
            	//console.log(response.text())
                return response.text();
            }).then(function (data) {            	
				console.log(data);
            });
            //ajax호출 : /blog/test/reply     POST 방식
        }

    </script>
</head>

<body>
    <button onclick="send()">전송</button>
</body>
</html>