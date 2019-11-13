<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="/include/nav.jsp"%>
<%-- <c:if test="${sessionScope.user.id eq 21}"><h1>로그인함</h1></c:if>
<c:if test="${empty sessionScope.user.id}"><h1>널</h1></c:if>
<c:if test="${sessionScope.user.id eq 0}"><h1>공백</h1></c:if> --%>

<!--================Blog Area =================-->
<section class="blog_area">
	<div class="container">
		<div class="row">
			<div class="col-lg-8">
				<div class="blog_left_sidebar">
					<!-- 블로그 글 시작 -->
					<c:choose>
						<c:when test="${count ne 0}">
							<c:forEach var="listView" items="${viewList}">
								<article class="blog_style1">
									<div class="blog_img">
										<img class="img-fluid" style="width: 100%; max-height: 269.347px" src="${listView.previewImg}" alt="">
									</div>
									<div class="blog_text">
										<div class="blog_text_inner">
											<div class="cat">
												<div class="cat_btn" href="#"><i class="fa fa-at" aria-hidden="true"></i> ${listView.username}</div>
												<div class="cat_btn">
													<i class="fa fa-calendar" aria-hidden="true"></i> ${listView.createDate}
												</div>
												<div class="cat_btn">
													<i class="fa fa-eye" aria-hidden="true"></i> ${listView.readCount}
												</div>
											</div>
											<h4 style="word-break: break-all;">${listView.title}</h4>
											<!-- content 미리보기 세팅 -->
											<div
												style="display: -webkit-box; -webkit-box-orient: vertical; text-align: left; overflow: hidden; text-overflow: ellipsis; white-space: normal; line-height: 1.2; height: 2.4em; -webkit-line-clamp: 2; margin-bottom: 20px; word-break: break-all">${listView.content}</div>
											<a class="blog_btn" href="/blog/board?cmd=detail&id=${listView.id}">Read More</a>
										</div>
									</div>
								</article>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<article class="blog_style1">
								<div class="blog_text">
									<div class="blog_text_inner">
										<h1>포스팅 된 글이 없습니다.</h1>
									</div>
								</div>
							</article>
						</c:otherwise>
					</c:choose>
					<!-- 블로그 글 끝 -->

					<!-- 여기까지 삭제 -->

					<!-- 페이징하기 -->
					<nav class="blog-pagination justify-content-center d-flex">
						<ul class="pagination">
							<c:choose>
								<c:when test="${param.page%5 ne 0}">
									<fmt:parseNumber var="navPage" value="${param.page/5}" integerOnly="true"></fmt:parseNumber>
								</c:when>
								<c:otherwise>
									<fmt:parseNumber var="navPage" value="${(param.page-1)/5}" integerOnly="true"></fmt:parseNumber>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${count%5 ne 0}">
									<fmt:parseNumber var="totalPage" value="${count/5}" integerOnly="true"></fmt:parseNumber>
								</c:when>
								<c:otherwise>
									<fmt:parseNumber var="totalPage" value="${(count-1)/5}" integerOnly="true"></fmt:parseNumber>
								</c:otherwise>
							</c:choose>

							<c:if test="${navPage ne 0}">
								<li class="page-item"><a href="/blog/board?cmd=list&page=${navPage*5}" class="page-link" aria-label="Previous"> <span aria-hidden="true"> <span class="lnr lnr-chevron-left"></span>
									</span>
								</a></li>
							</c:if>
							<c:forEach var="i" begin="${navPage*5+1}" end="${navPage*5+5}">
								<c:if test="${i le count}">
									<c:choose>
										<c:when test="${i eq param.page}">
											<li class="page-item active"><a href="/blog/board?cmd=list&page=${i}" class="page-link"> <span aria-hidden="true">${i}</span>
											</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a href="/blog/board?cmd=list&page=${i}" class="page-link"> <span aria-hidden="true">${i}</span>
											</a></li>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
							<c:if test="${navPage lt totalPage}">
								<li class="page-item"><a href="/blog/board?cmd=list&page=${navPage*5+6}" class="page-link" aria-label="Next"> <span aria-hidden="true"> <span class="lnr lnr-chevron-right"></span>
									</span>
								</a></li>
							</c:if>
						</ul>
					</nav>
				</div>
			</div>
			
			<!-- 검색 -->
			<div class="col-lg-4">
				<div class="blog_right_sidebar">
					<aside class="single_sidebar_widget search_widget">
						<div class="input-group">
							<input type="text" id="searchKeyword" class="form-control" placeholder="Search Posts"> <span class="input-group-btn">
								<button class="btn btn-default" type="button" onclick="searchByAll()">
									<i class="lnr lnr-magnifier"></i>
								</button>
							</span>
						</div>

						<!-- /input-group -->
						<div class="br"></div>
					</aside>

					<aside class="single_sidebar_widget popular_post_widget">
						<h3 class="widget_title">Popular Posts</h3>
						<c:forEach var="hotBoard" items="${hotBoardList}">
							<div class="media post_item">
								<img src="${hotBoard.previewImg}" alt="post" width="100px" height="80px">
								<div class="media-body">
									<a href="/blog/board?cmd=detail&id=${hotBoard.id}"><h3 style="word-break: break-all;">${hotBoard.title}</h3></a>
									<p>${hotBoard.createDate}</p>
								</div>
							</div>
						</c:forEach>
						<div class="br"></div>
					</aside>
				</div>
			</div>
		</div>
	</div>
</section>
<!--================Blog Area =================-->

<%@include file="/include/footer.jsp"%>
<script>
	function searchByAll() {
		var keyword = $('#searchKeyword').val();
		location.href = '/blog/board?cmd=search&page=1&keyword=' + keyword;
	}
</script>
</body>
</html>