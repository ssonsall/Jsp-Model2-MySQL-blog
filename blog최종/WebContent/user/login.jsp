<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/include/nav.jsp"%>

<!-- application (서버만 살아있으면 안없어진다) > session > request > pageContext : Scope 종류 및 생명주기 -->


<!--================Contact Area =================-->
<section class="contact_area">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form class="row contact_form" action="/blog/user?cmd=login" method="post">
					<div class="col-md-12">
						<div class="form-group">
							<c:choose>
								<c:when test="${empty cookie.username.value}">
									<input type="text" class="form-control" id="username" name="username" required="required" placeholder="Enter Your ID">
								</c:when>
								<c:otherwise>
									<input type="text" class="form-control" id="username" name="username" required="required" value="${cookie.username.value}">
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<input type="password" class="form-control" id="password" name="password" required="required" placeholder="Enter Your Password">
						</div>
					</div>
					<div class="col-md-12 text-right">
						<label><input type="checkbox" name="rememberMe" /> Remember me</label>
					</div>

					<div class="col-md-12 text-right">
						<button type="submit" value="submit" class="btn submit_btn">Login</button>
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
	function showAlert(authStatus){
		${sessionScope.user.id}
		if(authStatus === 1 ){
			alert('이메일 인증이 성공했습니다.');
		}else if(authStatus === 0){
			alert('이메일 인증이 실패했습니다.');
		}else {

		}
	}	
	showAlert(${param.authStatus});
</script>

<%@ include file="/include/footer.jsp"%>