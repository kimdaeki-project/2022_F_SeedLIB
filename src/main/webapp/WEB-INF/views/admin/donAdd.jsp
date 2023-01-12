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
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/default.css">
    <link rel="stylesheet" href="/css/board.css">
    <link rel="stylesheet" href="/css/button.css">
    <link rel="stylesheet" href="/css/layout.css">
    <link rel="stylesheet" href="/css/sub.css">
    <link rel="shortcut icon" href="/images/favicon.png">

    <title>뿌리깊은 관리자 : 씨앗도서관 ☘</title>

    <!-- ========== All CSS files linkup & sidebar ========= -->
    <c:import url="./temp/sidebar-css.jsp"></c:import>
    <style>
          .pagination{
        display: flex;
        flex-direction: column;
        margin-bottom: 40px;
        align-items: center;
    }

    .pagDiv{
        width: 700px;
    }

    
    .pageList {
        list-style:none;
        padding-left:0px;
        width: 500px;
        margin-left: auto;
        /* margin-right: auto; */
    }

    .pageSet{
        float: left;
        margin-left: 10px;
        margin-right: 10px;
    }

    #pageNext{
        width: 30px;
        /* background-color: #77af9c;
        color: #d7fff1; */
        color: #0cc086;

    }

    #pagePre{
        width: 30px;
        color: #0cc086;
    }

    .aplBtn{
        position: absolute;
        right: 0;
        bottom: 0;
        padding: 3px 15px;
        background-color: #06e3ab;
        color: #f3fefa;
    }

    </style>
<!-- 제이쿼리 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  </head>
  <body>
    <!-- ======== main-wrapper start =========== -->
    <main class="main-wrapper">
    
      <!-- ========== header start ========== -->
      <c:import url="./temp/header.jsp"></c:import>
      <!-- ========== header end ========== -->

      <!-- ========== card components start ========== -->
      <section class="card-components">
        <div class="container-fluid">
          <!-- ========== title-wrapper start ========== -->
          <div class="title-wrapper pt-30">
            <div class="row align-items-center">
              <div class="col-md-6">
                <div class="title mb-30">
                  <h2>기증 도서 신청 정보</h2>
                </div>
              </div>
              <!-- end col -->
              <div class="col-md-6">
                <div class="breadcrumb-wrapper mb-30">
                  <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                      <li class="breadcrumb-item">
                        <a href="../main">관리자 페이지 메인</a>
                      </li>
                      <li class="breadcrumb-item">
                        <a href="./donBoList">기증 도서 관리</a>
                      </li>
                      <li class="breadcrumb-item active" aria-current="page">
                        기증 도서 신청 정보
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

          <!-- ========== cards-styles start ========== -->
          <div class="cards-styles">
            <!-- ========= card-style-2 start ========= -->
            <div class="row" style="justify-content: center;">
              <div class="col-xl-4 col-lg-4 col-md-6 col-sm-6" style="width:70%;">
                <div class="card-style-2 mb-30" style="width:100%;">
                  <div>
                    <!--Real Contents Start-->
                    <!-- 서치영역 -->
                    <form id="searchForm" name="searchForm" method="get">
                    <div class="searchWrap">
                        <div class="searchFormWeb">
                            <div class="searchToparea">
                                <div class="searchBardiv hope">
                                    <div class="searchInputbox" style="margin-right: 0px;">
                                        <input hidden="hidden">
                                        <input type="text" id="query" name="query" title="제목을 입력하세요." onkeyup="enterkey()" value="" placeholder="도서명을 입력하세요." autocomplete="off">
                                        <input type="button" id="searchBtn" title="검색" class="unifiedSearchbtn">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <!-- 서치결과영역 -->
                        <div class="searchResultWrap">
                            <div class="result_box">
                                <div class="listSetting">
                                    <div class="result_screen">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="bookList listViewStyle hope">
                            <ul class="listWrap">
                            </ul>
                        </div>						
                            </form>
                            <!-- 서치영역 끝 -->
                            <!-- 페이징 -->
                            <div class="pagingWrap">
                                <div class="pagination">
                                </div>
                            </div>
                            <!-- 페이징 끝-->
                
                </div>
                    <div>
                        <form id="registForm" name="registForm" action-="setDon" method="post">
                                            <div><h4 class="dtitle">신청자 정보</h4></div>
                                            <div class="boardWrap">
                                                <table class="board-view">
                                                    <colgroup>
                                                        <col style="width:20%">
                                                        <col>
                                                    </colgroup>
                                                    <tbody>
                                                        <tr>
                                                            <th scope="row">신청자명</th>
                                                            <td><input type="text" id="userName" name="userName" class="form-control" value="${don.userName}" readonly></td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row">이메일</th>
                                                            <td>
                                                              <c:choose>
                                                                <c:when test="${don.emailAgree eq 'Y'}">
                                                                  <input type="text" id="email" name="email" class="form-control" value="${don.email}" readonly>
                                                                </c:when>
                                                                <c:when test="${don.emailAgree eq 'N'}">
                                                                  <input type="text" id="email" name="email" class="form-control" value="이메일 수신 비희망" readonly>
                                                                </c:when>
                                                              </c:choose>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                
                                            <div class="boardWrap mt10">
                                                <table class="board-view">
                                                  <div><h4 class="dtitle">기증도서 정보</h4></div>
                                                    <colgroup><col style="width:20%">
                                                    <col>
                                                    </colgroup><tbody>
                                                        <tr>
                                                            <th scope="row"><label for="libCode">신청도서관</label><span class="essential">*</span></th>
                                                            <td>
                                                                <input type="text" readonly name="donLib" id="donLib" class="form-control" libNum="${don.libVO.libNum}" value="${don.libVO.libName}">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row"><label for="title">기증도서명</label><span class="essential themeFC">*</span></th>
                                                            <td>
                                                                <input type="text" id="donTitle" name="donTitle" value="${don.donTitle}" class="form-control">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row"><label for="author">저자</label><span class="essential themeFC">*</span></th>
                                                            <td><input type="text" id="donWriter" name="donWriter" value="${don.donWriter}" class="form-control"></td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row"><label for="publisher">출판사</label><span class="essential themeFC">*</span></th>
                                                            <td><input type="text" id="donPublisher" name="donPublisher" value="${don.donPublisher}" class="form-control"></td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row"><label for="publishYear">발행연도</label></th>
                                                            <td><input type="number" id="donYear" min="1500", max="", step="1", name="donYear" value="${don.donYear}" maxlength="4" class="form-control"></td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row"><label for="isbn">ISBN</label></th>
                                                            <td>
                                                                <input type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" id="isbn" name="isbn" value="${don.isbn}" maxlength="30" class="form-control">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row"><label for="price">정가</label></th>
                                                            <td><input type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" readonly id="price" name="price" value="${don.price}" class="form-control"></td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row"><label for="requestReason">의견</label><span class="essential themeFC">*</span></th>
                                                            <td><textarea readonly name="donMemo" id="donMemo" class="form-control">${don.donMemo}</textarea></td>
                                                        </tr>
                                                        <tr>
                                                          <th scope="row"><label for="requestReason">카테고리</label><span class="essential themeFC">*</span></th>
                                                          <td><div class="select-position">
                                                            <select name="category" id="category">
                                                              <option value="">선택안함</option>
                                                              <option value="0">000 : 총류</option>
                                                              <option value="1">100 : 철학</option>
                                                              <option value="2">200 : 종교</option>
                                                              <option value="3">300 : 사회과학</option>
                                                              <option value="4">400 : 자연과학</option>
                                                              <option value="5">500 : 기술과학</option>
                                                              <option value="6">600 : 예술</option>
                                                              <option value="7">700 : 언어</option>
                                                              <option value="8">800 : 문학</option>
                                                              <option value="9">900 : 역사</option>
                                                            </select>
                                                          </div></td>
                                                        </tr>
                                                        <tr>
                                                          <th scope="row"><label for="agree">이메일 수신 여부</label><span class="essential themeFC">*</span></th>
                                                          <td>
                                                            <c:choose>
                                                              <c:when test="${don.emailAgree eq 'Y'}">
                                                                동의
                                                              </c:when>
                                                              <c:when test="${don.emailAgree eq 'N'}">
                                                                비동의
                                                              </c:when>
                                                            </c:choose>
                                                          </td>
                                                      </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <input type="hidden" name="image" id="image" value="${don.image}">
                                        </form>
                                        <div class="btnGroup">
                                          <button type="button" id="registBtn" class="btn themeBtn" value="${don.donNum}">입고 완료</button>
                                          <button type="button" id="cnclBtn" class="btn cncl" value="${don.donNum}">신청 반려</button>
                                        </div>
                                        <!-- End Of the Real Contents-->
                    </div>
                                    </div>
                                </div>
                        </div>
                </div>
                </div>
              </div>
              <!-- end col -->
            </div>
            <!-- ========= card-style-2 end ========= -->
          </div>
          <!-- ========== cards-styles end ========== -->
        </div>
        <!-- end container -->
      </section>
      <!-- ========== card components end ========== -->

      <!-- ========== footer start =========== -->
      <c:import url="./temp/footer.jsp"></c:import>
      <!-- ========== footer end =========== -->
      
    </main>
    <!-- ======== main-wrapper end =========== -->

    <!-- ========= All Javascript files linkup ======== -->
    <script src="/js/admin/donAdd.js"></script>
  </body>
</html>