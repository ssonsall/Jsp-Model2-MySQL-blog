<!DOCTYPE html>
<html lang="en">

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script>
        $(document).ready(function () {
            $.ajax({
                type: 'POST',
                url: '/blog/test3',
                dataType: 'text', //서버로부터 오길 기대하는 데이터
                contentType: 'text/plain; charset=utf-8',//MIME TYPE
                data: 'I Love You',
                success: function (response) {
                    //alert(response);
                    console.log(response);
                },
                error: function () {
                    alert('fail');
                }
            });
        });

        function send() {
            var formData = $('#user').serialize();
            $.ajax({
                type: 'POST',
                url: '/blog/test4',
                data: formData,
                contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                success: function (response) {
                    console.log(response);
                },
                error: function (xhr,ajaxOption,thrownError) {
                    console.log(xhr.status);
                    console.log(ajaxOption);
                    console.log(thrownError);
                }
            })
        }
    </script>
</head>

<body>
    <form action="" id="user" method="POST">
        <input type="text" name="username" value="cos"><br />
        <input type="text" name="password" value="1234"><br />
        <input type="button" onclick="send()" value="send">
    </form>
</body>

</html>