<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="/images/favicon.png">
    <title>통합검색 : 씨앗도서관 ☘</title>
</head>
<body>
<div id="wrap">
    <!-- 헤더 -->
    <c:import url="../temp/header.jsp"></c:import>
    <!-- 헤더 -->

    <div id="container" class="sub">
		
        <div class="sVisualWrap">
            <div class="sVisual">
                <h3>통합검색</h3>
            </div>
        </div>
        <div id="contentGroup">
                    
        <!-- 사이드바 -->
        <c:import url="../sideBar/AsideBar.jsp"></c:import>
        <!-- 사이드바 -->

        <div id="contentcore">
            <div class="naviandtitle">
                <h3>통합검색</h3>
                <div class="navi">
                    <a href="/seoksu/index.do" title="홈으로 이동" class="home"><span class="blind">홈</span></a>
                    <i class="arrow"></i> 자료검색 <i class="arrow"></i>소장자료검색<i class="arrow"></i>통합검색
                </div>
                
            </div>
    
            <div id="contents" class="contentArea">
                        
                <!-- Contents Start-->
                <form action="./simpleresult" id="searchForm" name="searchForm" method="get">

                    <input type="hidden" id="userName" value="${member.userName}">

                    <!-- 서치랩 -->
                    <div class="searchWrap">
                        <div class="searchFormWeb">

                            <div class="searchToparea">

                                <div class="searchBardiv">
                                    <div class="searchTit">통합자료

                                    </div>
                                    <div class="searchInputbox">
                                        <input hidden="hidden" />
                                        <input type="text" onkeyup="enterkey()" id="totalSearchValue" name="search" title="검색어를 입력하세요." value="${param.search}" placeholder="검색어를 입력하세요." autocomplete="off">
                                        <input type="button" id="searchBtn" title="검색" class="unifiedSearchbtn">
                                    </div>
                                    <div>
                                        <a href="./detail" class="btn input" title="고급검색 페이지 이동">고급검색</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    
                
                    <!-- 서치랩끝 -->

                    <!-- 검색설정랩 -->
                    <div class="searchResultWrap">
                        <div class="result_box">
                            <div class="listSetting" >
                                <c:if test="${not empty list}">
                                <div class="result_screen">'<strong>키워드:${param.search}</strong>'에 대한 검색결과 총 
                                    <span id="totalCnt">${count}</span> 건
                                </div>
                                </c:if>
                                <div style="display: flex;">
                                    <c:if test="${not empty message}">
                                        <div class="result_screen" style="margin: 0px auto;">'<strong>${message}</strong>'
                                        </div>
                                    </c:if>
                                </div>

                                <c:if test="${not empty list}">
                                    <div class="listStyleType">
                                        <select id="kind" name="kind" title="정렬조건 선택">
                                            <option class="kinds" value="score" selected>정확도 순</option>
                                            <option class="kinds" value="title">서명 순</option>
                                            <option class="kinds" value="writer">저자 순</option>
                                            <option class="kinds" value="publisher">발행처 순</option>
                                            <option class="kinds" value="num">대출수 순</option>
                                        </select>

                                        <!-- <a href="" id="sortListBtn" class="btn">확인</a> -->
                                        <input type="button" id="searchBtn1" class="btn" value="확인">
                                        <!-- <input type="button" id="searchBtn" title="검색" class="unifiedSearchbtn"> -->
                                        
                                    </div>
                                </c:if>
                            </div>

                        </div>
                    </div>
                    <!-- 검색설정랩 -->

                    <script src="/js/search.js"></script>

                    <!-- 리스트 -->
                    <div id="bookList">
                        <div class="bookList listViewStyle">
                            <ul class="listWrap">
                                <c:forEach items="${list}" var="list" varStatus="status">
                                    <li>
                                        <div class="bookArea">
                                            <div class="bookImg"><a href="#link" onclick="location.href='../book/detail?isbn=${list.isbn}'");"><img alt="${list.title}" src="${list.image}"></a></div>
                        
                                            <div class="bookData">
                                                <div class="book_dataInner">
                                                    <!-- <span class="book_kind">단행본</span> -->
                                                    <a href="#link" onclick="location.href='../book/detail?isbn=${list.isbn}'" class="book_name kor on" style="font-weight: bold;">${list.title}<span class="highlight word"></span></a>
                                                    <ul class="dot-list clearfix">
                                                        <li class="kor on"><span>저자</span> : ${list.writer}</li>
                                                        <li class="kor on"><span>발행처</span> : ${list.publisher}</li>
                                                        <li><span>발행년도</span> : ${list.bookDate}</li>
                                                        <li><span>ISBN</span> : ${list.isbn}</li>
                                                            
                                                    </ul>
                                                </div>

                                                
                        
                                                <div class="bookDetailInfo">
                                                    <ol>
                                                        <li class="tlqkf"><a id="btn_haveInfo${status.count}" onclick="bookInfo1(${list.isbn})" class="btn_haveInfo" title="소장정보 축소됨" data-id="${status.count}">소장정보</a></li>
                                                        <li><a id="btn_sergeInfo${status.count}" class="btn_sergeInfo" title="서지정보 축소됨">서지정보</a></li>
                                                        <li><button class="btn" title="찜하기 새창열림" style="padding: 0px 15px; height: 30px; background-color: #fff; color: #00e3ae; border: 1px solid #00e3ae;">찜</a></li>
                                                    </ol>
                                                </div>

                                                
                                                
                                            </div>
                                        </div>
                        
                                        <!-- 소장정보 -->
                                        <div id="collectionInfo${status.count}" class="roqkrcla bookDetailLayer whereLibrary" tabindex="0">
                                            <div class="thisBook-libraryList">
                                                <div class="tblWrap scrollTable">
                                                    <table class="tbl">
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">도서관</th>
                                                                <th scope="col">대출상태</th>
                                                                <th scope="col">자료실</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody class="cnrkgktpdy">
                                                                <!-- <tr class="MA">
                                                                    <td></td>
                                                                    <td>대출중<br>(예약가능)<br>(예약:1명)</td>
                                                                    <td>2022.11.25</td>
                                                                    <td></td>
                                                                    <td></td>
                                                                </tr> -->
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>

                                        

                                        
                                        <!-- 서지정보 -->
                                        <div id="bookInfo${status.count}" class="bookDetailLayer bookInfo" tabindex="0">

                                            <div class="thisBook-bibliographicInfo">
                                                <div class="tblWrap">
                                                    <table class="tbl">
                                                        <colgroup>
                                                            <col style="width:15%">
                                                            <col>
                                                        </colgroup>
                                                        <tbody>
                                                            <tr>
                                                                <th scope="row">자료유형</th>
                                                                <td class="ta_l">인쇄자료(책자형) 형태의 자료입니다</td>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row">독자유형</th>
                                                                <td class="ta_l">일반</td>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row">저자사항</th>
                                                                <td class="ta_l">${list.writer}</td>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row">발행사항</th>
                                                                <td class="ta_l">서울:${list.publisher},${list.bookDate}</td>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row">형태사항</th>
                                                                <td class="ta_l">
                                                                        <p>?page</p>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row">ISBN사항</th>
                                                                <td class="ta_l">${list.isbn} 03190 ￦14000</td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>

                            <script>

                            $(".btn_haveInfo").on("click", function () {
                                $(".whereLibrary").removeClass("open");
                                $(".bookInfo").removeClass("open");
                                $(".btn_sergeInfo").removeClass("on");
                                if (!$(this).hasClass("on")) {
                                    $(".btn_haveInfo").removeClass("on");
                                    $(this).addClass("on");
                                    $(this).parents("li").children(".whereLibrary").addClass("open").attr("tabIndex", "0");
                                    $(this).attr("title", "소장정보 확장됨");
                                } else {
                                    $(this).removeClass("on");
                                    $(".whereLibrary").removeClass("open").removeAttr("tabIndex", "0");
                                    $(this).attr("title", "소장정보 축소됨");
                                }
                            });
                            // 서지정보 view/hide
                            $(".btn_sergeInfo").on("click", function () {
                                $(".whereLibrary").removeClass("open");
                                $(".bookInfo").removeClass("open");
                                $(".btn_haveInfo, .btn_bkdanbi").removeClass("on");
                                if (!$(this).hasClass("on")) {
                                    $(".btn_sergeInfo").removeClass("on");
                                    $(this).addClass("on");
                                    $(this).parents("li").children(".bookInfo").addClass("open").attr("tabIndex", "0");
                                    $(this).attr("title", "서지정보 확장됨");
                                } else {
                                    $(this).removeClass("on");
                                    $(".bookInfo").removeClass("open").removeAttr("tabIndex", "0");
                                    $(this).attr("title", "서지정보 축소됨");
                                }
                            });

                                // $(".btn_haveInfo").on("click", function(){
                                //     let id=$(this).attr("data-id")
                                //     if(!$(this).hasClass("on")) {
                                //         $(this).addClass("on");
                                //         $(".whereLibrary").addClass("open");
                                //         $(".bookInfo").removeClass("open");
                                //         $(this).prop("title","소장정보 확장됨");
                                //     }
                                // })
                                // $(".tlqkf").on("click", ".btn_haveInfo", function () {
                                //     if ($(this).hasClass("on")) {
                                //         $(this).removeClass("on");
                                //         $(".whereLibrary").removeClass("open");
                                //         $(".bookInfo").removeClass("open");
                                //         $(".btn_sergeInfo").removeClass("on");
                                //     let id=$(this).attr("data-id")
                                //     }else {
                                //         $(this).addClass("on");
                                //         let id=$(this).attr("data-id")
                                //         id = "#collectionInfo"+id;
                                //         $(id).addClass("open");
                                //     }
                                // });

                                // for(let i=1;i<=10;i++) {
                                //     $('#'+"btn_haveInfo"+i).click(function(){            
                                        
                                //         if(!$('#'+"collectionInfo"+i).hasClass("open")) {
                                //             $('#'+"collectionInfo"+i).addClass("open");
                                //             $(this).addClass("on");
                                //             $('#'+"bookInfo"+i).hide();
                                //         } else {
                                //             $('#'+"collectionInfo"+i).removeClass("open");
                                //             $(this).removeClass("on");
                                            
                                //         }

                                        
                                //     });
                                // } 

                                // for(let i=1;i<=10;i++) {
                                //     $('#'+"btn_sergeInfo"+i).click(function(){            
                                        
                                //         if(!$('#'+"bookInfo"+i).hasClass("open")) {
                                //             $('#'+"bookInfo"+i).addClass("open");
                                //             $(this).addClass("on");
                                //             $('#'+"collectionInfo"+i).hide();
                                //         } else {
                                //             $('#'+"bookInfo"+i).removeClass("open");
                                //             $(this).removeClass("on");
                                            
                                //         }
                                //     });
                                // } 

                                
                                // for(let i=1;i<=10;i++) {
                                //     $('#'+"btn_haveInfo"+i).click(function(){            
                                //         console.log("아");
                                //         $('#'+"collectionInfo"+i).toggle();
                                //         $('#'+"bibliographicInfo"+i).removeClass("open");
                                //     });
                                // } 

                                // for(let i=1;i<=10;i++) {
                                //     $('#'+"btn_sergeInfo"+i).click(function(){            
                                //         console.log("아");
                                //         $('#'+"bibliographicInfo"+i).toggle();
                                //         $('#'+"collectionInfo"+i).removeClass("open");
                                //     });
                                // } 


                                // for(let i=1;i<=10;i++) {
                                //     $('#'+"btn_haveInfo"+i).click(function(){
                                //             if($('#'+"collectionInfo"+i).hasClass("open") === true) {
                                //                 $('#'+"collectionInfo"+i).removeClass("open");
                                //                 $(this).prop("title","소장정보 축소됨");
                                //                 return false;
                                //             }
                                //     });
                                // } 
                                

                            </script>

                        </div>
                        <c:if test="${not empty list}">
                        <div class="pagingWrap">
                            <!-- <input type="hidden" name="page" value="${pager.page}"> -->
                            <p class="paging">
                                <a href="./simpleresult?search=${pager.search}&kind=${pager.kind}&page=1" class="btn-paging first ${pager.page eq 1?'disabledLink':''}"><span class="blind">맨 첫 페이지로 가기</span></a>
                                
                                <a href="./simpleresult?search=${pager.search}&kind=${pager.kind}&page=${pager.startNum-1}" class="btn-paging prev ${pager.page eq 1?'disabledLink':''}"><span class="blind">이전 10개 보기</span></a>
                                
                                <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
                                    <!-- <span class="current">1</span> -->
                                    <a href="./simpleresult?search=${pager.search}&kind=${pager.kind}&page=${i}" id="ppaaggee${i}">${i}</a>
                                    
                                </c:forEach>
                                <a href="./simpleresult?search=${pager.search}&kind=${pager.kind}&page=${pager.lastNum+1}" class="btn-paging next ${pager.next?'':'disabledLink'}"><span class="blind">다음 10개 보기</span></a>
                                <a href="./simpleresult?search=${pager.search}&kind=${pager.kind}&page=${pager.totalPage1}" class="btn-paging last ${pager.page eq pager.totalPage1?'disabledLink':''}"><span class="blind">맨 마지막 페이지로 가기</span></a>
                                </p>
                        </div>
                        </c:if>
                        <!-- //페이징 -->

                       
                        
                    </div>
                    <!-- 리스트 -->

                </form>
                <!-- End Of the Real Contents-->
                
            </div>
        </div>
    </div>

    
    <!-- footer -->
    <c:import url="../temp/footer.jsp"></c:import>
    <!-- //footer -->
</div>

<script>

    try {
        // 페이징 활성화 
            // 화살표들 disabledLink 클래스 걸려있으면 누를수없게 a태그에서 href 속성을 삭제시킴
            let disables = document.querySelectorAll(".disabledLink")
            disables.forEach(element => {
                element.removeAttribute('href');
            });

            let urll = window.location.href;
            let urlnn = urll.lastIndexOf('=')+1;
            urllastt = urll.substring(urlnn);

            //파라미터로 받은 페이지 번호에 해당하는 id를 찾아서 걔를 초록색으로 바꿈.
            let ppaaggee = document.querySelector('#'+"ppaaggee"+'${pager.page}');

            ppaaggee.style.background="#9be15d";
            ppaaggee.style.color="#fff";
        // 페이징 활성화 
        
    } catch (error) {
        
    }

    
                          
                          
                     
$("#liboption").click(function(){
    let check = $("#sh_util03").attr("class");
    if(check=="divSelect") {
        console.log("클릭");
        $("#sh_util03").attr("class","divSelect current");
        $("#liboptionicon").attr("class","sh_util03 on");
        $("#liboptionicon").attr("title","확장됨");
    } else{
        $("#sh_util03").attr("class","divSelect");
        $("#liboptionicon").attr("class","축소됨");
    }
    
})



let k = '${param.kind}'

const kinds = document.getElementsByClassName('kinds');
for(let i=0;i<kinds.length;i++){
    if(k==kinds[i].value) {
        kinds[i].setAttribute('selected','selected');
        break;
    }
}

</script>

<script defer>

let kw = '${param.search}'
    $(".book_dataInner>a:contains('"+kw+"')").each(function (d, f) {
        var regex = new RegExp(kw, 'gi');
        f.innerHTML=f.innerHTML.replace(regex, '<span class="highlight word">'+kw+'</span>');
    });
    $(".book_dataInner>ul>li:nth-child(1):contains('"+kw+"')").each(function (d, f) {
        var regex = new RegExp(kw, 'gi');
        f.innerHTML=f.innerHTML.replace(regex, '<span class="highlight word">'+kw+'</span>');
    });
    $(".book_dataInner>ul>li:nth-child(2):contains('"+kw+"')").each(function (d, f) {
        var regex = new RegExp(kw, 'gi');
        f.innerHTML=f.innerHTML.replace(regex, '<span class="highlight word">'+kw+'</span>');
    });
</script>
<script>

    function bookInfo1(isbn) {

        $.ajax({
            type:"GET",
            url :"./simpleresultInfo1",
            traditional:true, //배열을 전송할 때 사용, true
            data:{
                isbn:isbn
            },
            success : function(data){
                // console.log("성공");
                // console.log("그냥데이터",data.detail);

                const detail = data.detail;
                //let str = JSON.stringify(data);
                //console.log(str);
                
                $.each(detail, function(index, item) { // 데이터 =item
                    let ableMsg = "";
                    let cc = "";
                    let cnrk = "";
                    let cnrkgktpdy = document.querySelectorAll(".cnrkgktpdy");

                    for(let i=0;i<item.libVOs.length; i++) {
                        
                        if(item.bookLibVOs[i].quantity!=0) {
                            ableMsg = "대출 가능<br>(대출 가능 권수 : "+item.bookLibVOs[i].quantity+"권)";
                        } else {
                            ableMsg = "대출 불가능"
                        }
                        
                        if(item.libVOs[i].libNum == 0) {
                            if(item.bookLibVOs[i].able == 1) {
                                cc = '<button type="button" onclick="setLoan('+isbn+')" class="btn white small" id="LoanAlretBtn" title="대출신청">대출신청</button>';
                            } else {
                                cc = '<button type="button" class="btn white small" id="ResAlretBtn" title="예약신청">예약신청</button>';
                            }
                        } else {
                            if(item.bookLibVOs[i].able == 1) {
                                cc = '<button type="button" id="MuAlretBtn" class="btn white small">상호대차</button>';
                            }
                        }
                        
                        // let dsffs = '<tr class="MA"><td></td><td>대출중<br>(예약가능)<br>(예약:1명)</td><td>2022.11.25</td><td></td><td></td></tr>';
                        // cnrk += '<tr class="MA"><td>'+item.libVOs[i].libName+'</td><td>'+ableMsg+'</td><td>반납예정일</td><td>'+cc+'</td><td></td></tr>';
                        cnrk += '<tr class="MA"><td>'+item.libVOs[i].libName+'</td><td>'+ableMsg+'</td><td>2층 일반 자료실</td></tr>';
                        
                    }
                    $(".cnrkgktpdy").html(cnrk);

                });
            },
            error   : function(){
                console.log("실패");
            }
        })
    }

    
    //-------------------------------------------------
    //디테일 페이지에서 대출신청 눌렀을 때
    function setLoan(isbn) {
        console.log("되니");
        console.log($("#userName").val());

        const bookLoanVO = {
            isbn : isbn,
            userName : $("#userName").val()
        }
        
        $.ajax({
            type : "POST",
            url : "/book/loan",
            data:JSON.stringify(bookLoanVO),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success:function(data){
                switch (data){
                    case 2:
                        alert("해당 책을 이미 대출 중입니다.")
                        break;
                    case 1:
                        let check = window.confirm("도서를 대출했습니다.\n마이페이지에서 확인하시겠습니까?");
                        if(check){
                            opener.location.href="/mypage/loan";
                            break;
                        } else{
                            opener.location.href="../"; 
                            break;
                        } 
                    }
                },
            error:function(){
                console.log("ERROR");
            }
        })
    }

</script>
</body>
</html>