<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/nav.jsp"%>

<!--================Contact Area =================-->
<section class="contact_area">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form class="row contact_form" action="/blog/user?cmd=update" method="post" onsubmit="return validateCheck()">
					<input type="hidden" name="id" value="${sessionScope.user.id}" />
					<div class="col-md-12">
						<div class="form-group">
							<input type="text" class="form-control" value="${sessionScope.user.username}" name="username" maxlength="20" placeholder="Enter Your ID" readonly>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<input type="password" class="form-control" name="password" id="password" required="required" maxlength="20" placeholder="Enter Your Password">
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<input type="password" class="form-control" name="passwordCheck" id="passwordCheck" required="required" maxlength="20" placeholder="Confirm Your Password">
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<input type="email" class="form-control" value="${sessionScope.user.email}" name="email" maxlength="40" placeholder="Enter Your Email" readonly>
						</div>
					</div>
					<!-- 유저프로필 시작 -->
					<div class="col-md-12">
						<div class="form-group">
							<input type="file" class="form-control" name="userProfile" maxlength="40">
						</div>
					</div>
					<!-- 유저프로필 끝 -->
					<!-- 도로명 주소 시작 -->
					<div class="col-md-10">
						<div class="form-group">
							<input type="text" class="form-control" value="${sessionScope.user.address}" id="roadFullAddr" name="address" required="required" placeholder="Search Your Address" readonly>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group float-right">
							<a style="cursor: pointer;" class="blog_btn" onClick="goPopup()">Search Address</a>
						</div>
					</div>
					<!-- 도로명 주소 끝 -->
					<div class="col-md-12 text-right">
						<button type="submit" value="submit" class="btn submit_btn">Update</button>
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