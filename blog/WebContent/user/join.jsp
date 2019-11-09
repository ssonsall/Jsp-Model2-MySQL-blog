<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/nav.jsp"%>

<!--================Contact Area =================-->
<section class="contact_area">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form class="row contact_form" action="/blog/user?cmd=join" method="post"
					onsubmit="return validateCheck()" enctype="multipart/form-data">
					<div class="col-md-10">
						<div class="form-group">
							<input type="text" class="form-control" id="username" name="username" required="required" maxlength="20"
								placeholder="Enter Your ID">
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group float-right">
							<a style="cursor: pointer;" class="blog_btn" onClick="usernameDuplicationCheck()">중복체크</a>
						</div>
					</div>
					<span class="col-md-12" id="duplicationCheckResult"></span>
					<div class="col-md-12">
						<div class="form-group">
							<input type="password" class="form-control" name="password" id="password"
								required="required" maxlength="20" placeholder="Enter Your Password">
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<input type="password" class="form-control" name="passwordCheck" id="passwordCheck"
								required="required" maxlength="20" placeholder="Confirm Your Password">
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<input type="email" class="form-control" name="email" required="required" maxlength="40"
								placeholder="Enter Your Email">
						</div>
					</div>
					<!-- 유저프로필 시작 -->
					<div class="col-md-12">
						<div class="form-group">
							<input type="file" class="form-control" name="userProfile" accept=".jpg, .jpeg, .png" maxlength="40">
						</div>
					</div>
					<!-- 유저프로필 끝 -->
					<!-- 도로명 주소 시작 -->
					<div class="col-md-10">
						<div class="form-group">
							<input type="text" class="form-control" id="roadFullAddr" name="address" required="required"
								placeholder="Search Your Address" readonly>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group float-right">
							<a style="cursor: pointer;" class="blog_btn" onClick="goPopup()">주소찾기</a>
						</div>
					</div>
					<!-- 도로명 주소 끝 -->
					<div class="col-md-12 text-right">
						<button type="submit" value="submit" class="btn submit_btn">Join</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<br />
<br />
<!--================Contact Area =================-->

<script>	
	function validateCheck() {
		var password = document.querySelector('#password').value;
		var passwordCheck = document.querySelector('#passwordCheck').value;

		if (password === passwordCheck) {
			console.log('비밀번호가 동일합니다');
			return true;
		} else {
			console.log('비밀번호가 틀렸습니다');
			alert('비밀번호 불일치');
			return false;
		}

		if (roadFullAddr == "") {
			alert('주소를 입력하세요');
			return false;
		}
	}

	function usernameDuplicationCheck() {
		var url = "http://localhost:8000/blog/api/user";
		var username = document.querySelector('#username').value;
		var dupResult = document.querySelector('#duplicationCheckResult');
		fetch(url,{
			method : "POST",
			headers: {
			 	"Content-Type": "application/x-www-form-urlencoded; charset=utf-8"
			 },
			body : "username="+username
		}).then(function(response){
			return response.text();
		}).then(function(responseCode){		
			if(responseCode === 'possible'){
				console.log('possible');				
				dupResult.setAttribute('style','color:green');
				dupResult.innerHTML='사용가능 아이디';				
			}else{
				console.log('impossible');
				dupResult.setAttribute('style','color:red');
				dupResult.innerHTML='사용불가 아이디';
			}
		});
	}

	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/blog/juso/jusoPopup.jsp", "pop",
			"width=570,height=420, scrollbars=yes, resizable=yes");
	}

	//주소입력 버튼을 누르면 콜백됨
	function jusoCallBack(roadFullAddr) {
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		var juso = document.querySelector('#roadFullAddr');
		juso.value = roadFullAddr;
	}
</script>

<%@ include file="/include/footer.jsp"%>