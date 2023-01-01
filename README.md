# 구디 아카데미 최종 프로젝트
## 씨앗 도서관
* 온라인 서비스를 이용해 사용자의 편의성을 향상시킨 지역 도서관 웹사이트
* 기간 : 2022.11.15 ~ 2022.12.22
* 환경 : Apache Tomcat 9.0.64, Chrome 브라우저
* 주제 : 도서 대출, 도서 상세 정보 공유 및 시설 이용
* 개발 : Spring Tool Suite 4 version , oracle sqldeveloper
* 사용 언어 : JAVA(JDK 1.7), oracle db, HTML, CSS, JavaScript, JSP & Servlet
* 사용 기술 : Spring Framework, MyBatis, jQuery, Ajax, Open API(Iamport 결제 , 구글 메일, Kakao Map)
* 디자인 프레임워크 : Bootstrap

## 팀원 역할 분담

<img src="https://user-images.githubusercontent.com/108776178/210174783-b8855162-6f55-4b39-b2ca-47bf1ab0aa42.png">

## ERD

* 노션: https://www.notion.so/ERD-DB-0223600829ec439fbbca047f6c33caf7

* ERDCloud: https://www.erdcloud.com/d/7Q9CsauvLNgGT6ijj

## 개발 환경 구축
<img src="https://user-images.githubusercontent.com/108776178/210174709-9c135c96-c621-413a-b8da-5a0354eddaf5.png">

## 노션
https://www.notion.so/b57b6de55b4a4bb28262fd56f826e1c5

# SeedLib
<img src="https://user-images.githubusercontent.com/103366296/209656844-132abf23-99db-4300-ad74-c21f6a2df93f.jpeg">


## 1. 기능별 Package
```
1. 도서정보     : book
2. 검색         : search
3. 희망도서     : hope
4. 도서기증     : donation
5. 열람실       : studyroom
6. 문화프로그램 : program
7. 게시판       : board
8. 도서관안내   : guide
9. 도서관소개   : info
10. 도서관정책  : policy
11. 회원        : member
12. 마이페이지  : mypage
13. 관리자      : admin
14. 설정        : config
15. 공통        : util
```

## 2. properties 분리
```
 1. application.properties
	공통 설정
	보안을 요구하지 않는 공통 설정
 2. application-dev.properties
	개발시 필요한 설정
	DB연결등 보안을 요구하는 개별 설정

- application-dev.properties 는 ignore파일에 등록 됨
- github에 등록되지 않음에 유의
- JSP 연결 확인 완료
```
