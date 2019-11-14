<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/include/nav.jsp"%>

<c:if test="${empty sessionScope.user}">
	<script>
		alert('로그인이 필요합니다.');
		location.href = "/blog/user/login.jsp";
	</script>
</c:if>

<!--================Blog Area =================-->
<section class="blog_area single-post-area">
	<div class="container">
		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-8">
				<div class="main_blog_details" id="main_blog_details">
					<a href="#">
						<h4 style="word-break: break-all;">${view.title}</h4>
					</a>
					<div class="user_details">
						<div class="float-left">
							<c:if test="${view.username eq sessionScope.user.username}">
								<a href="/blog/board?cmd=updateForm&id=${view.id}">Update</a>
								<a href="/blog/board?cmd=delete&id=${view.id}">Delete</a>
							</c:if>
						</div>
						<div class="float-right">
							<div class="media">
								<div class="media-body">
									<h5>${view.username}</h5>
									<p>${view.createDate}</p>
								</div>
								<div class="d-flex">
									<img src="/blog/${view.userProfile}" width="75px" height="75px">
								</div>
							</div>
						</div>
					</div>
					<!-- 본문시작 -->
					<div style="word-break: break-all;">
					<p>${view.content}</p>
					</div>
					<!-- 본문끝 -->
					<hr />
				</div>
				<!-- 댓글 쓰기 시작 -->
				<div class="comment-form" style="margin-top: 0px;">
					<h4 style="margin-bottom: 20px">${view.username}에게댓글남기기</h4>
					<form id="comment-submit">
						<div class="form-group" id="comment-submit">
							<input type="hidden" name="userId" value="${sessionScope.user.id}" /> <input type="hidden" name="boardId" value="${view.id}" />
							<textarea id="content" style="height: 60px" class="form-control mb-10" rows="2" name="content" placeholder="Messege" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Messege'"
								required=""></textarea>
						</div>
						<button type="button" onclick="commentWrite()" class="primary-btn submit_btn">Post Comment</button>
					</form>
				</div>
				<!-- 댓글 쓰기 끝 -->
				<hr />
				<!-- 댓글 시작 -->
				<div class="comments-area" style="padding-top: 0px" id="comments-area">
					<c:forEach var="comment" items="${commentList}">
						<!-- 댓글 아이템 시작 -->
						<div class="comment-list" style="padding-bottom: 10px" id="comment-id-${comment.id}">
							<!-- id 동적으로 만들기 -->
							<div class="single-comment justify-content-between d-flex">
								<div class="user justify-content-between d-flex">
									<div class="thumb">
										<img src="/blog/${comment.user.userProfile}" width="60px" height="60px">
									</div>
									<div class="desc">
										<h5>
											<a href="#">${comment.user.username}</a>
										</h5>
										<p class="date">${comment.createDate}</p>
										<p class="comment" style="word-break: break-all;">${comment.content}</p>
									</div>
								</div>
								<div class="reply-btn">
									<c:choose>
										<c:when test="${sessionScope.user.username eq comment.user.username}">
											<button onclick="commentDelete(${comment.id})" class="btn-reply text-uppercase">삭제</button>
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
									<input type="button" onclick="replyListShow(${comment.id},'${comment.user.username}')" class="btn-reply text-uppercase" id="replyshowclosebtn-id-${comment.id}" value="답글"
										style="outline: none;" />
								</div>
							</div>
							<hr />
						</div>

						<!-- 댓글 아이템 끝 -->

						<!-- 대댓글 시작 : 보기상태에서 누를때 아래 div안에 prepend 할 것임-->
						<div id='reply-block-${comment.id}' style="margin-bottom: 20px;"></div>
						<!-- 대댓글 끝 -->
					</c:forEach>
				</div>
				<!-- 댓글 끝 -->




			</div>
			<div class="col-lg-2"></div>
		</div>
	</div>
</section>
<!--================Blog Area =================-->

<%@include file="/include/footer.jsp"%>

<!-- comment script -->
<script>

   function commentItemForm(id, username, content, createDate, userProfile) {
      var comment_list = "<div class='comment-list' style='padding-bottom: 10px' id='comment-id-" + id + "'> ";
      comment_list += "<div class='single-comment justify-content-between d-flex'> ";
      comment_list += "<div class='user justify-content-between d-flex'> ";
      comment_list += "<div class='thumb'><img src='/blog/" + userProfile + "' alt='' width='60px' height='60px'></div>";
      comment_list += "<div class='desc'><h5><a href='#'>" + username + "</a></h5> ";
      comment_list += "<p class='date'>" + createDate + "</p><p class='comment' style='word-break: break-all;'>" + content + "</p></div></div> ";
      comment_list += "<div class='reply-btn'>";
      comment_list += "<button onClick='commentDelete(" + id + ")' class='btn-reply text-uppercase'>삭제</button>";
      comment_list += "<input type='button' onclick='replyListShow(" + id + "," + "&#39;" + username + "&#39;" +")' class='btn-reply text-uppercase' id='replyshowclosebtn-id-" + id + "' value='답글' style='outline: none;' /></div></div><hr/></div>";
      comment_list += "<div id='reply-block-"+id+"' style='margin-bottom: 20px;'></div>";
      return comment_list;
   }

   function replyItemForm(id, username, content, createDate, userProfile) {
      var replyItem = "<div class='comment-list left-padding' style='padding-bottom : 10px' id='reply-id-" + id + "'>";
      replyItem += "<div class='single-comment justify-content-between d-flex'>";
      replyItem += "<div class='user justify-content-between d-flex'>";
      replyItem += "<div class='thumb'><img src='/blog/" + userProfile + "' alt='' width='60px' height='60px'></div>";
      replyItem += "<div class='desc'><h5><a href='#'>" + username + "</a></h5>";
      replyItem += "<p class='date'>" + createDate + "</p>";
      replyItem += "<p class='comment'>" + content + "</p>";
      replyItem += "</div></div>";
      if('${sessionScope.user.username}' == username){
      replyItem += "<div class='reply-btn'><button onClick='replyDelete(" + id + ")' class='btn-reply text-uppercase'>삭제</button>";
      }else{}
      replyItem += "</div></div><hr/></div>";
      return replyItem;
   }

   //reply 보기/닫기 - ajax
   function replyListShow(comment_id, comment_username) {
      console.log(comment_username);
      if ($('#replyshowclosebtn-id-' + comment_id).val() === '답글') {
         $('#replyshowclosebtn-id-' + comment_id).val('닫기');

         var comment_form_inner = "<div class='comment-form' style='margin-top:0px; padding-top: 0px;'><h4 style='margin-bottom: 20px'>" + comment_username + "에게 답글 남기기</h4><form id='reply-submit-" + comment_id + "'><div class='form-group' id='reply-submit-" + comment_id + "'><input type='hidden' name='commentId' value='" + comment_id + "' /><input type='hidden' name='userId' value='${sessionScope.user.id}' /><textarea id='content-" + comment_id + "' style='height: 60px' class='form-control mb-10' rows='2' name='content' placeholder='Messege' onfocus='this.placeholder = ''' onblur='this.placeholder = 'Messege'' required=''></textarea></div><button type='button' onclick='replyWrite(" + comment_id + ")' class='primary-btn submit_btn'>Post Reply</button></form></div>";
         $('#reply-block-' + comment_id).append(comment_form_inner);         

         $.ajax({
            method: 'POST',
            url: '/blog/api/reply?cmd=list',
            contentType: 'text/plain; charset=utf-8',
            data: comment_id + "",
            dataType: 'json',
            success: function (response) {
				for (reply of response) {
                  var reply_element = replyItemForm(reply.id, reply.user.username, reply.content, reply.createDate, reply.user.userProfile);
                  $('#reply-block-' + reply.commentId).prepend(reply_element);
               }
            },
            error: function (xhr) {
               alert('삭제 실패' + response);
            }
         });
      } else {
         $('#replyshowclosebtn-id-' + comment_id).val('답글');
         $('#reply-block-' + comment_id).empty();
      }
   }
   
   //reply 쓰기 - ajax
   function replyWrite(comment_id) {
	   if(${sessionScope.user.emailCheck} != 1){
		   alert('이메일 인증이 필요한 서비스입니다.');
		   location.href = "/blog/board?cmd=detail&id=${view.id}";
	   }else{
      var reply_submit_string = $('#reply-submit-' + comment_id).serialize();
      console.log(reply_submit_string);
      $.ajax({
         method: 'POST',
         url: '/blog/api/reply?cmd=write',
         contentType: 'application/x-www-form-urlencoded; charset=utf-8',
         data: reply_submit_string,
         //dataType: 'json',
         success: function (response) {
            console.log(response);
            var reply_element = replyItemForm(response.id, response.user.username, response.content, response.createDate, response.user.userProfile);
            $('#reply-block-' + response.commentId).prepend(reply_element);
            $('#content-' + comment_id).val('');
         },
         error: function (xhr) {
            alert('대댓글 실패');
         }
      });
	   }
   }

   //reply 삭제  - ajax
   function replyDelete(reply_id) {
      $.ajax({
         method: 'POST',
         url: '/blog/api/reply?cmd=delete',
         contentType: 'text/plain; charset=utf-8',
         data: reply_id + "",
         success: function (response) {
            //해당 엘리멘트 삭제
            if (response === 'ok') {
               $('#reply-id-' + reply_id).remove();
            }
         },
         error: function (xhr) {
            console.log(xhr);
         }
      })
   }





   //comment 쓰기
   function commentWrite() {
	   if(${sessionScope.user.emailCheck} != 1){
		   alert('이메일 인증이 필요한 서비스입니다.');
		   location.href = "/blog/board?cmd=detail&id=${view.id}";
	   }else{
      var comment_submit_string = $('#comment-submit').serialize();
      console.log(comment_submit_string);
      $.ajax({
         method: 'POST',
         url: '/blog/api/comment?cmd=write',
         contentType: 'application/x-www-form-urlencoded; charset=utf-8',
         data: comment_submit_string,
         dataType: 'json',
         success: function (response) {
            console.log('response >> ' + response);
            if (response.responseData.status === 'ok') {
               var comment_element = commentItemForm(response.id, response.user.username, response.content, response.createDate, response.user.userProfile);
               console.log(response.user.userProfile);
               $('#comments-area').prepend(comment_element);
               $('#content').val('');
            }
         },
         error: function (xhr) {
            console.log('에러에러');
         }
      })
	   }
   }

   //comment 삭제
   function commentDelete(comment_id) {
      $.ajax({
         method: 'POST',
         url: '/blog/api/comment?cmd=delete',
         contentType: 'text/plain; charset=utf-8',
         data: comment_id + "",
         success: function (response) {
            //해당 DOM remove 필요   
            //원래는 부모를 찾아서 그 child를 삭제하는 형태로 해야함.
            //근데 제이쿼리 쓰면 아래와 같은 방식으로 간단히 됨.
            $('#comment-id-' + comment_id).remove();
            replyListShow(comment_id);
         },
         error: function (xhr) {
            alert('삭제 실패' + response);
         }
      });
   }



</script>
</body>

</html>