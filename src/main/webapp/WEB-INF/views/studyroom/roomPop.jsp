<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/default.css">
    <link rel="stylesheet" href="/css/board.css">
    <link rel="stylesheet" href="/css/button.css">
    <link rel="stylesheet" href="/css/layout.css">
    <link rel="stylesheet" href="/css/sub.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>
        .contentsGroup {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .seatArea {
            width: 100%;
            margin-left: 25px;
            margin-top: 50px;
            margin-bottom: 10px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        
        .seat {
            display: block;
            font-size: 30px;
            font-weight: bold;
        }

        .btnArea {
            margin-top: 30px;
        }

        .tbl td{
            padding: 0 5px;
            font-size: 17px;
        }

        .tabTr{
            line-height: 45px;
        }

        hr {
        width: 87%;
        border : 0px;
        border-top: 0.5px solid #cfcece; 
        margin-bottom: 30px;
        }

        #revBtn{
            border-radius: 5px;
            text-shadow: 1px 1px 3px rgb(0 0 0 / 30%);
            font-size: 18px !important;
        }

    </style>

    <title>좌석 예약 : 씨앗도서관 ☘️</title>
</head>
<body>
    <input type="hidden" id="userName" value="${memberVO.userName}">
    <div class="contentsGroup">
        <div class="seatArea">
            <span class="seat" style="margin-bottom: 10px;">🌱 예 약 정 보 🌱</span>
            <hr style="width: 35%;">  
            <c:choose>
                <c:when test="${roomName eq '일반열람실(여)'}">
                    <span id="roomNum" style="font-size: 25px;" class="seat" value="1">열람실 : ${roomName} </span>
                </c:when>
                <c:when test="${roomName eq '일반열람실(남)'}">
                    <span id="roomNum" style="font-size: 25px;" class="seat" value="2">열람실 : ${roomName} </span>
                </c:when>
                <c:when test="${roomName eq '노트북실'}">
                    <span id="roomNum" style="font-size: 25px;" class="seat" value="3">열람실 : ${roomName} </span>
                </c:when>
            </c:choose>
            <span id="seatNum" style="font-size: 25px;" class="seat" value="${seatNum}">좌석 번호 : ${seatNum} </span>
        </div>
        <hr style="margin-top: 30px;">   
        <div>
            <div style="margin-bottom: 15px;">
                <span style="font-size: 17px;">● 이용 시간</span>
            </div>
            <table class="tbl">
                <thead>
                    <tr>
                        <th scope="col">구 분</th>
                        <th scope="col">평 일</th>
                        <th scope="col">주 말</th>
                        <th scope="col">방역시간</th>
                        <th scope="col">비 고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="tabTr">
                        <td style="text-align: center;">열 람 실</td>
                        <td style="text-align: center;"><span style="color:#2980b9;">08:00 ~ 23:00</span></td>
                        <td style="text-align: center;">08:00 ~ 23:00</td>
                        <td rowspan="2" style="text-align: center;">수시 방역</td>
                        <td rowspan="2" style="text-align: center;"><span style="color:#ff0000;">온/오프라인 예약</span></td>
                    </tr>
                    <tr class="tabTr">
                        <td style="text-align: center;">노트북실</td>
                        <td style="text-align: center;"><span style="color:#2980b9;">08:00 ~ 23:00</span></td>
                        <td style="text-align: center;">08:00 ~ 23:00</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="btnArea">
            <button type="button" class="btn themeBtn" id="revBtn">좌석 예약</button>
        </div>
    </div>
   <script src="/js/studyroomRes.js"></script> 
</body>
</html>