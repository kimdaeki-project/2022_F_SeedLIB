<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="icon" href="/images/favicon.png">
    <title>회원 목록 : 씨앗도서관 ☘</title>
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- ========== All CSS files linkup & sidebar ========= -->
    <c:import url="../temp/sidebar-css.jsp"></c:import>
    
    <script type="text/javascript" defer src="/js/admin/adMember.js"></script>
  </head>
  <body>
    <!-- ======== main-wrapper start =========== -->
    <main class="main-wrapper">
      <!-- ========== header start ========== -->
      <c:import url="../temp/header.jsp"></c:import>
      <!-- ========== header end ========== -->

      <!-- ========== table components start ========== -->
      <section class="table-components">
        <div class="container-fluid">
          <!-- ========== title-wrapper start ========== -->
          <div class="title-wrapper pt-30">
            <div class="row align-items-center">
              <div class="col-md-6">
                <div class="title mb-30">
                  <h2>회원 목록</h2>
                </div>
              </div>
              <!-- end col -->
              <div class="col-md-6">
                <div class="breadcrumb-wrapper mb-30">
                  <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                      <li class="breadcrumb-item">
                        <a href="../main">관리자페이지 메인</a>
                      </li>
                      <li class="breadcrumb-item"><a href="./proList">회원 관리</a></li>
                      <li class="breadcrumb-item active" aria-current="page">
                        회원 목록
                      </li>
                    </ol>
                  </nav>
                </div>
              </div>
              <!-- end col -->
            </div>
            <!-- end row -->
          </div>
          <!-- ========== title-wrapper end ========== -->

          <!-- ========== tables-wrapper start ========== -->
          <div class="tables-wrapper">
            <div class="row">
              <div class="col-lg-12">
                <div class="userList">
                  <div class="card-style mb-30">
                    <form action="./adMemberList" method="get">
                      <div class="d-flex flex-wrap justify-content-between align-items-center py-1 mb-2">
                        <div class="left">
                        </div>
                        <div class="right d-flex align-items-center">
                          <div class="input-style-3" style="margin-bottom: 0px; margin-right: 16px;">
                            <input type="text" name="search" placeholder="아이디를 입력하세요" style="height: 46px;" />
                            <span class="icon">
                              <i style="transform: translateY(-5px);" class="lni lni-search-alt"></i>
                            </span>
                          </div>
                          <div>
                            <button type="submit" class="main-btn btn-sm rounded-md info-btn">
                              검색
                              <span class="icon"><i class="lni lni-checkmark ms-1"></i></span>
                            </button>
                          </div>
                        </div>
                      </div>
                    </form>
                    <div class="table-wrapper table-responsive">
                      <table class="table text-center">
                        <thead>
                          <tr>
                            <th class="text-start"><h6>#</h6></th>
                            <th><h6>이름</h6></th>
                            <th><h6>아이디</h6></th>
                            <th><h6>성별</h6></th>
                            <th><h6>생년월일</h6></th>
                            <th><h6>이메일</h6></th>
                            <th><h6>회원가입일</h6></th>
                            <th><h6>연체반납횟수</h6></th>
                            <th><h6>회원상태</h6></th>
                            <th><h6>계정활성화/비활성화</h6></th>
                          </tr>
                          <!-- end table row-->
                        </thead>
                        <tbody>
                          <c:forEach items="${memberList}" var="MemberVO">
                            <tr>
                              <td>
                                <div class="employee-image">
                                  <!-- 이미지 어떤걸 넣을까 고민중 -->
                                  <img src="/images/logo200.png" alt="씨앗도서관"/>
                                </div>
                              </td>
                              <td class="min-width">
                                <p>${MemberVO.name}</p>
                              </td>
                              <td class="min-width">
                                <input type="hidden" name="userName" value="${MemberVO.userName}">
                                <p>${MemberVO.userName}</p>
                              </td>
                              <td class="min-width">
                                <p>${MemberVO.gender}</p>
                              </td>
                              <td class="min-width">
                                <fmt:formatDate value="${MemberVO.birth}" pattern="yyyy-MM-dd"/>
                              </td>
                              <td class="min-width">
                                <p>${MemberVO.email}</p>
                              </td>
                              <td class="min-width">
                                <fmt:formatDate value="${MemberVO.regDate}" pattern="yyy-MM-dd"/>
                              </td>
                              <td class="min-width">
                                <p>${MemberVO.ovMyrCount}</p>
                              </td>
                              <td>
                                <c:choose>
                                  <c:when test="${MemberVO.enabled eq 'true' and MemberVO.accountNonLocked eq 'true'}">
                                    <span id="changeBtn" class="status-btn success-btn">
                                      회원
                                    </span>
                                  </c:when>
                                  <c:when test="${MemberVO.enabled eq 'false'}">
                                    <span id="changeBtn" class="status-btn close-btn">
                                      회원탈퇴
                                    </span>
                                  </c:when>
                                  <c:otherwise>
                                    <span id="changeBtn" class="status-btn info-btn">
                                      회원잠금
                                    </span>
                                  </c:otherwise>
                                </c:choose>
                              </td>
                              <td>
                                <div class="action d-grid gap-2 d-md-flex justify-content-center">
                                  <button type="button" class="unLockId" data-user-id="${MemberVO.userName}">
                                    <a class="text-info">
                                      <i class="lni lni-unlock"></i>
                                    </a>
                                  </button>
                                  /
                                  <button type="button" class="lockId" data-user-id="${MemberVO.userName}">
                                    <a class="text-danger">
                                      <i class="lni lni-lock"></i>
                                    </a>
                                  </button>
                                </div>
                              </td>
                            </tr>
                          </c:forEach>
                          <!-- end table row -->
                        </tbody>
                      </table>
                      <!-- end table -->
                    </div>
                    <nav aria-label="Page navigation example">
                      <ul class="pagination justify-content-end">
                        <c:if test="${pager.pre}">
                          <li class="page-item">
                            <a class="page-link text-success" href="./adMemberList?page=${pager.startNum-1}" aria-label="Previous">
                              <span aria-hidden="true">&laquo;</span>
                            </a>
                          </li>
                        </c:if>
                        <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
                          <li class="page-item"><a class="page-link text-success" href="./adMemberList?page=${i}">${i}</a></li>
                        </c:forEach>
                        <c:if test="${pager.next}">
                          <li class="page-item">
                            <a class="page-link text-success" href="./adMemberList?page=${pager.lastNum+1}" aria-label="Next">
                              <span aria-hidden="true">&raquo;</span>
                            </a>
                          </li>
                        </c:if>
                      </ul>
                    </nav>
                  </div>
                  <!-- end card -->
                </div>
              </div>
              <!-- end col -->
            </div>
            <!-- end row -->
          </div>
          <!-- ========== tables-wrapper end ========== -->
        </div>
        <!-- end container -->
      </section>
      <!-- ========== table components end ========== -->

      <!-- ========== footer start =========== -->
      <c:import url="../temp/footer.jsp"></c:import>
      <!-- ========== footer end =========== -->
    </main>
    <!-- ======== main-wrapper end =========== -->

    <!-- ========= All Javascript files linkup ======== -->
    <c:import url="../temp/js.jsp"></c:import>
  </body>
</html>