<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@  taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="icon" href="/images/favicon.png">
	<title>마이페이지 : 씨앗도서관 ☘️ </title>
<link rel="stylesheet" href="/css/default.css">
<link rel="stylesheet" href="/css/contents.css">
<link rel="stylesheet" href="/css/layout.css">
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/sub.css">
<script src="https://code.jquery.com/jquery-3.6.0.slim.js" integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>

</head>
<body>

	<div class="wrap">

<c:import url="../temp/header.jsp"></c:import>


<div id="container" class="sub">
<div class="sVisualWrap">
	<div class="sVisual">
		<h3>나의정보</h3>
	</div>
</div>
		<div id="contentGroup">
			
<div id="lnbNav" class="navArea">
	
	<c:import url="../sideBar/FsideBar.jsp"></c:import>

</div>

			<div id="contentcore">
				



<div class="naviandtitle">
	<h3>나의정보</h3>
	<div class="navi"><a href="/intro/index.do" title="홈으로 이동" class="home"><span class="blind">홈</span></a><i class="arrow"></i> 마이페이지 <i class="arrow"></i>나의정보</div>
	
</div>



<script src="/include/js/clipboard.min.js"></script>

				<div id="contents" class="contentArea">
					


<div id="popblackBG"></div>
					<!--Real Contents Start-->
					<div class="myPageWrap">
						<div class="clearfix">
							<!-- PC버전 정회원일때 -->
							<div class="myInfoBox">
								<div class="myInner">
									<div class="myTop">
										<strong>${vo.name} </strong> 님, 안녕하세요.
									</div>
								</div>
									<div class="myInfo" style="background: white;">
										<div class="memType" style="margin: -10px 0 0 20px;">
											<strong class="member typeB">준회원</strong>
										</div>
										<div class="myInfoList" style="margin: -10px 0 0 20px;">
											<ul class="dot-list" style="margin: -10px 0 0 20px;">
												<li><span>아이디</span> : ${vo.userName} </li>
												<li><span>회원가입일</span> : ${vo.regDate} </li>
												<li><span>휴대폰번호</span> : ${vo.phone} </li>
												
												<!-- <li><span>회원증번호</span> : </li>
												<li class="mobileShow"><span class="emp1">※ 모바일회원증 캡처 이미지 사용 불가</span></li>-->
											</ul>
										</div>
										<div class="btnModify">							
											<a href="/mypage/memberModify" class="btn white" style="transform: translate(3px, -58px);">회원정보수정</a>
										</div>
									</div>
							</div>
									<div>
										<h4 class="stitle">서비스 이용현황</h4>
										<ul class="myState clearfix">
								
											<li>
												<a href="/mypage/book/loan">
													<strong class="tit">대출중인<br>도서</strong>
													<span class="num">${loanCount}</span>
												</a>
											</li>
											<li>
												<!-- 연체도서가 있을때 a태그에 red 클래스 추가 -->
												<a href="/mypage/book/loan" class="red">
													<strong class="tit">연체중인<br>도서</strong>
													<span class="num">${overCount}</span>
												</a>
											</li>
											<li>
												<a href="/mypage/book/reservation">
													<strong class="tit">예약중인<br>도서</strong>
													<span class="num">${revCount}</span>
												</a>
											</li>
											<li>
												<a href="/mypage/book/loanHistory">
													<strong class="tit">반납완료<br>도서</strong>
													<p class="ref">클릭 시 이전 이력 확인</p>
													<span class="num">${hisCount}</span>
												</a>
											</li>
											<li>
												<a href="/mypage/book/mutual">
													<strong class="tit">상호대차<br>신청도서</strong>
													<span class="num">${muCount}</span>
												</a>
											</li>
											<li>
												<a href="/mypage/hopeList">
													<strong class="tit">신청중인<br>희망도서</strong>
													<span class="num">${hopCount}</span>
												</a>
											</li>
											<li>
												<a href="/mypage/donList">
													<strong class="tit">신청중인<br>기증도서</strong>
													<span class="num">${donCount}</span>
												</a>
											</li>
										
											<li>
												<a href="/mypage/program">
													<strong class="tit">신청중인<br>문화행사</strong>
													<span class="num">${proCount}</span>
												</a>
											</li>
										</ul>

									</div>
							</div>

							
						</div>
					</div>
					<!-- End Of the Real Contents-->
				</div>
			</div>
		</div>
	</div>
      <!-- footer -->
	  <c:import url="../temp/footer.jsp"></c:import>
	  <!-- //footer --> 
</div>

</body>
</html>