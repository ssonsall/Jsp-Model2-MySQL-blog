<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/nav.jsp"%>

<!--================Contact Area =================-->
<section class="contact_area">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form class="row contact_form" action="/blog/user?cmd=update" method="post" onsubmit="return validateCheck()" enctype="multipart/form-data">
					<input type="hidden" name="id" value="${sessionScope.user.id}" />
					<input type="hidden" name="emailCheck" value="${sessionScope.user.emailCheck}" />
					<div class="col-md-3">
						<div class="form-group" style="margin-bottom: 0px">
							
						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group" style="margin-bottom: 0px; margin-top: 5px;">
							<b>아이디</b>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group" style="margin-bottom: 0px">
							<input type="text" class="form-control" id="username" value="${sessionScope.user.username}" name="username" onkeyup="usernameDuplicationCheck()" required="required" maxlength="20" placeholder="Enter Your ID" readonly>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group" style="margin-bottom: 0px">
							
						</div>
					</div>
						<div class="col-md-1">
						
					</div>
					<div class="col-md-3">	
					</div>
					<div class="col-md-4">
						<div class="form-group" style="text-align: center; font-size: 12px !important" id="duplicationCheckResult">  </div>
					</div>
					<div class="col-md-4">
					</div>
					
					<div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
						<div class="col-md-1">
						<div class="form-group" style="margin-top: 5px;">
							<b>비밀번호</b>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<input type="password" onkeyup="passwordcheck()" class="form-control" name="password" id="password" required="required" maxlength="20" placeholder="Enter Your Password">
						</div>
					</div>
										<div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
										<div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group" style="margin-top: 5px;">
							<b>확인</b>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group" style="margin-bottom: 0px">
							<input type="password" onkeyup="passwordcheck()" class="form-control" name="passwordCheck" id="passwordCheck" required="required" maxlength="20" placeholder="Confirm Your Password">
						</div>
					</div>
										<div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
					<div class="col-md-1">
						<span class="form-group" style="text-align: center; font-size: 12px !important" id=""></span>
					</div>
										<div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
					<div class="col-md-8">
						<span class="form-group" style="text-align: center; font-size: 12px !important" id="passwordCheckString"> 비밀번호일치확인 </span>
					</div>
										<div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group" style="margin-bottom: 0px; margin-top: 5px;">
							<b>이메일</b>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group" style="margin-bottom: 0px">
							<input type="email" class="form-control" value="${sessionScope.user.email}" name="email" required="required" maxlength="40" placeholder="Enter Your Email" readonly>
						</div>
					</div>
										<div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
					<div class="col-md-4">
						
					</div>
					
					<div class="col-md-8">
						<div class="form-group" style="text-align: center; font-size: 12px !important" id="">  </div>
					</div>
					<!-- 유저프로필 시작 -->
					<div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
					<div class="col-md-1" style="margin-top: 5px;">
						 <b>사진</b>
					</div>
					<div class="col-md-5">
						 <img id="profilePreview" src="/blog/userprofile/${sessionScope.user.userProfile}" width="75px" height="75px" class="form-group"
							style="text-align: center;" />
					</div>
					<div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
					
					
					<div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
					
					<div class="col-md-1">
						<div class="form-group" style="margin-bottom: 0px">
						
						</div>
					</div>
					<div class="col-md-5">

							<input id="inputImage" type="file" name="userProfile" accept=".jpg, .jpeg, .png" maxlength="40"/>


					</div>
					<div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
					
					
					<div class="col-md-4">						
					</div>
					<div class="col-md-8">
						<span id="profileImageCheck" class="form-group" style="text-align: center; font-size: 12px !important; color:green;" id="">변경없음</span>						 
					</div>
					<div class="col-md-4">						
					</div>
					<div class="col-md-8">
						<span id="profileImageCheck" class="form-group" style="text-align: center; font-size: 12px !important" id=""> .jpg, .jpeg, .png 파일을 등록하세요 </span>						 
					</div>

					<!-- 유저프로필 끝 -->
					<!-- 도로명 주소 시작 -->
					<div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group" style="margin-top: 5px;">
							<b>주소</b>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<input type="text" style="cursor: pointer;" onClick="goPopup()" value="${sessionScope.user.address}" class="form-control" id="roadFullAddr" name="address" required="required" placeholder="Search Your Address" readonly>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
<!-- 					<div class="col-md-2">
						<div class="form-group float-right">
							<a style="cursor: pointer;" class="blog_btn" onClick="goPopup()">주소찾기</a>
						</div>
					</div> -->
					<!-- 도로명 주소 끝 -->
					<div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
					<div class="col-md-6 text-right">
						<button type="submit" value="submit" class="btn submit_btn">Update</button>
					</div>
				    <div class="col-md-3">
						<div class="form-group">

						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<br />
<br />
<!--================Contact Area =================-->

<%@ include file="/include/footer.jsp"%>

<script>
	var imgFileChk = false;
	var idDupChk = false;
	var f;
	$('#inputImage').on("change",handleImgFile); //(어떤 변화가있을때, 그때 뭐할래)
	function handleImgFile(e){
		f = e.target.files[0];
		
		if(f === undefined){
			document.querySelector("#profileImageCheck").innerHTML = "변경없음";
			document.querySelector("#profileImageCheck").setAttribute('style','color:green;text-align: center; font-size: 12px');
			$('#profilePreview').attr("src", "");
			$('#profilePreview').attr("src", "/blog/userprofile/${sessionScope.user.userProfile}");
		}
		
		if(!f.type.match("image.*")){
	         document.querySelector("#profileImageCheck").innerHTML = "File 이름 > "+ f.name + " Image 파일이 아닙니다.";
	         document.querySelector("#profileImageCheck").setAttribute('style','color:red;text-align: center; font-size: 12px');
			 $('#profilePreview').attr("src", "");
			 $('#profilePreview').attr("src", "/blog/userprofile/noImg.png");
	         imgFileChk = false;
		return;
		}
	
        document.querySelector("#profileImageCheck").innerHTML = "File 이름 > "+f.name;
        document.querySelector("#profileImageCheck").setAttribute('style','color:green;text-align: center; font-size: 12px');
		
		var reader = new FileReader();
		reader.onload = function(e){
			$('#profilePreview').attr("src", "");
			$('#profilePreview').attr("src", e.target.result);
		}
		imgFileChk = true;
		reader.readAsDataURL(f);
	}

	function validateCheck() {
		var password = document.querySelector('#password').value;
		var passwordCheck = document.querySelector('#passwordCheck').value;

		
		if(f === undefined){
			imgFileChk = true;
		}
		
		if (password === passwordCheck) {
			console.log('비밀번호가 동일합니다');
			if (roadFullAddr == "") {
				alert('주소를 입력하세요');
				return false;
			}else{
				if(imgFileChk){
					if(idDupChk){
						return true;
					}else{
						return true;
					}	
				}else{
					alert('이미지 파일을 올려주세요.');
					return false;
				}
			}
		} else {
			console.log('비밀번호가 틀렸습니다');
			alert('비밀번호 불일치');
			return false;
		}


	}

	function usernameDuplicationCheck() {
		var url = "/blog/api/user";
		var username = document.querySelector('#username').value;
		var dupResult = document.querySelector('#duplicationCheckResult');
		fetch(
				url,
				{
					method : "POST",
					headers : {
						"Content-Type" : "application/x-www-form-urlencoded; charset=utf-8"
					},
					body : "username=" + username
				})
				.then(function(response) {
					return response.text();
				})
				.then(
						function(responseCode) {
							if (responseCode === 'possible') {
								console.log('possible');
								idDupChk = true;
								dupResult
										.setAttribute('style',
												'color:green;text-align: center; font-size: 12px');
								dupResult.innerHTML = '사용가능 아이디';
							} else {
								console.log('impossible');
								idDupChk = false;
								dupResult
										.setAttribute('style',
												'color:red;text-align: center; font-size: 12px');
								dupResult.innerHTML = '사용불가 아이디';
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
	
	   function passwordcheck(){
		      var password = document.querySelector('#password').value;
		      var passwordCheck = document.querySelector('#passwordCheck').value;
		      if (password === passwordCheck) {
		         document.querySelector("#passwordCheckString").innerHTML = "비밀번호 일치";
		         document.querySelector("#passwordCheckString").setAttribute('style','color:green;text-align: center; font-size: 12px');
		         return true;
		      } else {
		         console.log('비밀번호가 불일치');
		         document.querySelector("#passwordCheckString").innerHTML = "비밀번호 불일치";
		         document.querySelector("#passwordCheckString").setAttribute('style','color:red;text-align: center; font-size: 12px');
		         return false;
		      }
		   }
</script>