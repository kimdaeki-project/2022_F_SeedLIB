#  지역도서관 사이트
### 송지혜(대표), 구효경, 김유리, 서소영, 안다은


```bash
교육원   : 구디아카데미
강  사   : 김대기
과정명   : 클라우드 활용 자바개발자 양성과정(1회차)
기  간   : 2022-07-04 2022-12-22
프로젝트 : Final Project
주  제   :  지역도서관 사이트
```

# 구디 아카데미 최종 프로젝트
## 씨앗 도서관
* 온라인 서비스를 이용해 사용자의 편의성을 향상시킨 지역 도서관 웹사이트
* 기간 : 2022.11.15 ~ 2022.12.22
* 환경 : Apache Tomcat 9.0.64, Chrome 브라우저
* 주제 : 도서 대출, 도서 상세 정보 공유 및 시설 이용
* 개발 : Spring Tool Suite 4 version , MariaDB
* 사용 언어 : JAVA(JDK 1.7), MariaDB, HTML, CSS, JavaScript, JSP & Servlet
* 사용 기술 : Spring Framework, MyBatis, jQuery, Ajax, Open API(I'mport 결제 , 구글 메일, Kakao Map)
* 디자인 프레임워크 : Bootstrap

## 팀원 역할 분담

<img src="https://user-images.githubusercontent.com/108776178/210174783-b8855162-6f55-4b39-b2ca-47bf1ab0aa42.png">

## ERD

* Notion: https://www.notion.so/ERD-DB-0223600829ec439fbbca047f6c33caf7

* ERDCloud: https://www.erdcloud.com/d/7Q9CsauvLNgGT6ijj

## 개발 환경 구축
<img src="https://user-images.githubusercontent.com/108776178/210174709-9c135c96-c621-413a-b8da-5a0354eddaf5.png">

## Notion
* https://www.notion.so/b57b6de55b4a4bb28262fd56f826e1c5

## PPT
* https://docs.google.com/presentation/d/1WppEde7OnjiiK8Px4KuRbVQdnoTVs2Ep/edit?usp=sharing&ouid=103528894120254139033&rtpof=true&sd=true

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

## 3. 통합쿼리문
```
-- 소영 ----------------------------------------------------------------------------------------------------------------------------------------------
1. BOOK

-- 도서 BOOK
	CREATE TABLE BOOK (
	ISBN	BIGINT	AUTO_INCREMENT,
	TITLE	VARCHAR (500)	NOT NULL,
	WRITER	VARCHAR (500)	NOT NULL,
	PUBLISHER	VARCHAR (500)	NOT NULL,
	BOOKDATE	INT NOT NULL,
	CATEGORY	INT	NOT NULL,
	IMAGE	VARCHAR(500)	NULL,
	BOOKCOUNT	INT	NOT NULL,
	BOOKHEART	BIGINT	NOT NULL,
	NUM	INT	NOT NULL,
  REGDATE DATE NOT NULL,
	CONSTRAINT BOOK_ISBN_PK PRIMARY KEY (ISBN)
);

-- 전체 도서관
CREATE TABLE LIBRARY (
	LIBNUM INT(50) NOT NULL,
	LIBNAME VARCHAR(500),
	CONSTRAINT LIBRARY_LIBNUM_PK PRIMARY KEY (LIBNUM)
);

-- 도서보유 도서관
-- 1 : 대출 가능 | 0: 대출 불가
CREATE TABLE BOOKLIB (
   BLIBNUM INT(50) NOT NULL,
   LIBNUM INT(50) NOT NULL,
   ISBN BIGINT NOT NULL,
	 QUANTITY INT DEFAULT 1,
	 ABLE BIT(1) NOT NULL DEFAULT 1,
   CONSTRAINT BOOKLIB_BLIBNUM_PK PRIMARY KEY (BLIBNUM),
   CONSTRAINT BOOKLIB_LIBNUM_FK FOREIGN KEY(LIBNUM) REFERENCES LIBRARY (LIBNUM),
   CONSTRAINT BOOKLIB_ISBN_FK FOREIGN KEY(ISBN) REFERENCES BOOK (ISBN)
);

-- 대출
-- 1 : 대출 중/연체O | 0: 반납/연체X
CREATE TABLE LOAN (
	LOANNUM INT(50) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	USERNAME VARCHAR(200),
	ISBN BIGINT NOT NULL,
	LOANSDATE DATE,
	LOANLDATE DATE,
	OVERDUE BIT(1) DEFAULT 0,
	RTSTATUS BIT(1) DEFAULT 0,
	EXTENSION INT DEFAULT 0,
	CONSTRAINT LO_USERNAME_FK FOREIGN KEY(USERNAME) REFERENCES MEMBER (USERNAME),
	CONSTRAINT BOL_ISBN_FK FOREIGN KEY(ISBN) REFERENCES BOOK (ISBN)
);

-- 상호대차
CREATE TABLE MUTUALLOAN (
	MUNUM INT auto_increment NOT NULL,
	ISBN BIGINT NULL,
	USERNAME VARCHAR(200) NULL,
	REGDATE DATE DEFAULT NOW() NULL,
	LIBNUM INT(50) NULL,
	CONSTRAINT MUTUALLOAN_pk PRIMARY KEY (MUNUM),
	CONSTRAINT MUTUALLOAN_FK FOREIGN KEY (ISBN) REFERENCES book(ISBN) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT MUTUALLOAN_FK_1 FOREIGN KEY (USERNAME) REFERENCES `member`(USERNAME) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT MULIBNUM_FK FOREIGN KEY (LIBNUM) REFERENCES library(LIBNUM)
);

-- 도서대출예약
CREATE TABLE RESERVATION (
	RVNUM INT(50) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	ISBN BIGINT NOT NULL,
	USERNAME VARCHAR(200),
	ENABLEDATE DATE,
	CONSTRAINT RV_ISBN_FK FOREIGN KEY(ISBN) REFERENCES BOOK (ISBN),
	CONSTRAINT RV_USERNAME_FK FOREIGN KEY(USERNAME) REFERENCES MEMBER (USERNAME)
);

-- 반납 MYRETURN
CREATE TABLE MYRETURN (
	RTNUM	BIGINT	auto_increment PRIMARY KEY,
	LOANNUM	BIGINT	NOT NULL,
	ISBN	BIGINT	NOT NULL,
	RTDATE DATE	DEFAULT now()
);

-- 책꽂이
CREATE TABLE SHELF (
	SHNUM BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	USERNAME VARCHAR(200) NOT NULL,
	SHNAME VARCHAR(500) NOT NULL,
	SHMEMO VARCHAR(500),
	CREDATE DATE DEFAULT now() NULL,
	CONSTRAINT SH_USERNAME_FK FOREIGN KEY(USERNAME) REFERENCES MEMBER (USERNAME)
);

-- 도서 책꽂이 저장
CREATE TABLE PICK (
	PICKNUM BIGINT AUTO_INCREMENT NOT NULL,
	ISBN BIGINT NOT NULL,
	SHNUM BIGINT NOT NULL,
	REGDATE DATE DEFAULT NOW() NULL,
	CONSTRAINT PICK_pk PRIMARY KEY (PICKNUM),
	CONSTRAINT PICK_FK FOREIGN KEY (ISBN) REFERENCES book(ISBN) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT PICK_FK_1 FOREIGN KEY (SHNUM) REFERENCES shelf(SHNUM) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 도서 좋아요
CREATE TABLE MBBOOKLIKE (
	BOOKLIKE BIGINT PRIMARY KEY auto_increment NOT NULL,
	ISBN BIGINT NOT NULL,
	USERNAME varchar(200) NOT NULL,
	CONSTRAINT MBBOOKLIKE_FK FOREIGN KEY (ISBN) REFERENCES book(ISBN),
	CONSTRAINT MBBOOKLIKE_FK_1 FOREIGN KEY (USERNAME) REFERENCES `member`(USERNAME)
);

-- 프로그램 (사용자 신청)
-- ## PROGRAM 테이블 먼저 생성하고 아래 테이블 생성
CREATE TABLE MEMBERPROGRAM (
	MPNUM BIGINT auto_increment NOT NULL,
	USERNAME varchar(200) NULL,
	PRONUM INT(100) NULL,
	PROCHECK BIT DEFAULT 0 NULL,
	PROSTATE BIT DEFAULT 1 NULL,
	CONSTRAINT MEMBERPROGRAM_pk PRIMARY KEY (MPNUM),
	CONSTRAINT MEMBERPROGRAM_FK FOREIGN KEY (USERNAME) REFERENCES `member`(USERNAME),
	CONSTRAINT MEMBERPROGRAM_FK_1 FOREIGN KEY (PRONUM) REFERENCES `program`(PRONUM)
);

-- 다은 ----------------------------------------------------------------------------------------------------------------------------------------------
2. MEMBER
--회원관리
CREATE TABLE `MEMBER` ( 
	`USERNAME` VARCHAR(200) NOT NULL,
	`PASSWORD` VARCHAR(200) DEFAULT NULL,
	`NAME` VARCHAR(200) DEFAULT NULL,
	`GENDER` VARCHAR(10),
	`BIRTH` DATE DEFAULT NULL,
	`EMAIL` VARCHAR(200) DEFAULT NULL,
	`PHONE` VARCHAR(200) DEFAULT NULL,
	`POST` VARCHAR(200) DEFAULT NULL,
	`ADDRESS1` VARCHAR(200) DEFAULT NULL,
	`ADDRESS2` VARCHAR(200) DEFAULT NULL,
	`REGDATE` DATE DEFAULT NULL,
	`ENABLED` BIT(1) DEFAULT 0,
	`LOANSTATUS` BIT(1) DEFAULT 0,
	`OVMYRCOUNT` int DEFAULT NULL,
	`ACCOUNTNONLOCKED` BIT(1) DEFAULT 1,
	CONSTRAINT `MEMBER_ID_PK` PRIMARY KEY (`USERNAME`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 게시판
CREATE TABLE BOARD(
	BOARDNUM INT NOT NULL AUTO_INCREMENT,
	TITLE VARCHAR(500) NOT NULL,
	CONTENT LONGBLOB NOT NULL,
	WRITER VARCHAR(10) NOT NULL,
	REGDATE DATE NOT NULL,
	HIT INT(11),
CONSTRAINT BOARD_NUM_PK PRIMARY KEY (BOARDNUM)
)

CREATE TABLE BOARDFILE (
	FILENUM INT NOT NULL AUTO_INCREMENT,
	BOARDNUM INT,
	FILENAME VARCHAR (500),
	ORINAME VARCHAR (500),
CONSTRAINT BOARDFILE_NUM_PK PRIMARY KEY(FILENUM),
CONSTRAINT BOARDFILE_BN_FK FOREIGN KEY (BOARDNUM) REFERENCES BOARD(BOARDNUM)
)


create table QNA(
	QNANUM INT NOT NULL AUTO_INCREMENT,
	TITLE VARCHAR(500) NOT NULL,
CONTENTS VARCHAR(500) NOT NULL,
CONSTRAINT QNA_NUM_PK PRIMARY KEY(QNANUM)
)

-- 지혜 ----------------------------------------------------------------------------------------------------------------------------------------------
-- LIBRARY, MEMBER 테이블 먼저 만들어야 됨
-- 기증도서
CREATE TABLE `DONATION` (
  `DONNUM` int(11) NOT NULL AUTO_INCREMENT,
  `DONTITLE` varchar(500) DEFAULT NULL,
  `DONWRITER` varchar(500) DEFAULT NULL,
  `DONPUBLISHER` varchar(500) DEFAULT NULL,
  `DONMEMO` varchar(500) DEFAULT NULL,
  `DONDATE` date DEFAULT NULL,
  `EMAILAGREE` varchar(200) DEFAULT NULL,
  `ISBN` bigint(20) DEFAULT NULL,
  `USERNAME` varchar(200) DEFAULT NULL,
  `IMAGE` varchar(300) DEFAULT NULL,
  `EMAIL` varchar(200) DEFAULT NULL,
  `PRICE` varchar(200) DEFAULT NULL,
  `DONYEAR` int(11) DEFAULT NULL,
  `CATEGORY` int(11) DEFAULT NULL,
  PRIMARY KEY (`DONNUM`),
  KEY `DONATION_USERNAME_FK` (`USERNAME`),
  CONSTRAINT `DONATION_USERNAME_FK` FOREIGN KEY (`USERNAME`) REFERENCES `MEMBER` (`USERNAME`) ON DELETE CASCADE
);
-- 기증도서 인서트
INSERT INTO DONATION
(DONTITLE, DONWRITER, DONPUBLISHER, DONMEMO, DONDATE, EMAILAGREE, ISBN, USERNAME, IMAGE, EMAIL, PRICE, DONYEAR, CATEGORY)
VALUES(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

3-1 (HOPE 쪽과 공용)
CREATE TABLE STATUS (
	STATNUM INT,
	STATNAME VARCHAR(500),
	CONSTRAINT STATUS_STATNUM_PK PRIMARY KEY (STATNUM)
);
-- 공용인서트
INSERT INTO STATUS VALUES (0, '신청 중');
INSERT INTO STATUS VALUES (1, '신청 취소');
INSERT INTO STATUS VALUES (2, '입고 완료');
INSERT INTO STATUS VALUES (3, '신청 반려');

3-2
CREATE TABLE DOSTAT (
	DONSTAT INT AUTO_INCREMENT,
	DONNUM INT,
	STATNUM INT,
	CONSTRAINT DOSTAT_DONSTAT_PK PRIMARY KEY (DONSTAT),
	CONSTRAINT DOSTAT_DONNUM_FK FOREIGN KEY (DONNUM) REFERENCES DONATION (DONNUM) ON DELETE CASCADE,
	CONSTRAINT DOSTAT_STATNUM_FK FOREIGN KEY (STATNUM) REFERENCES STATUS (STATNUM) ON DELETE CASCADE
);
-- 인서트
INSERT INTO DOSTAT
(DONNUM, STATNUM)
VALUES(NULL, NULL);

3-3
CREATE TABLE DONLIB (
	BLNUM INT AUTO_INCREMENT,
	LIBNUM INT(50) NOT NULL,
	DONNUM INT NOT NULL,	
	CONSTRAINT DONLIB_BLNUM_PK PRIMARY KEY (BLNUM),
	CONSTRAINT DONLIB_LIBNUM_FK FOREIGN KEY(LIBNUM) REFERENCES LIBRARY (LIBNUM) ON DELETE CASCADE,
	CONSTRAINT DONLIB_DONNUM_FK FOREIGN KEY(DONNUM) REFERENCES DONATION (DONNUM) ON DELETE CASCADE
);
-- 인서트
INSERT INTO DONLIB
(LIBNUM, DONNUM)
VALUES(0, 0);

4. HOPE
-- 신청도서
CREATE TABLE `HOPE` (
  `HOPNUM` int(11) NOT NULL AUTO_INCREMENT,
  `HOPTITLE` varchar(500) DEFAULT NULL,
  `HOPWRITER` varchar(500) DEFAULT NULL,
  `HOPPUBLISHER` varchar(500) DEFAULT NULL,
  `HOPMEMO` varchar(500) DEFAULT NULL,
  `HOPDATE` date DEFAULT NULL,
  `ISBN` bigint(20) DEFAULT NULL,
  `USERNAME` varchar(200) DEFAULT NULL,
  `IMAGE` varchar(300) DEFAULT NULL,
  `EMAIL` varchar(200) DEFAULT NULL,
  `PRICE` varchar(200) DEFAULT NULL,
  `HOPYEAR` int(11) DEFAULT NULL,
  `CATEGORY` int(11) DEFAULT 0,
  PRIMARY KEY (`HOPNUM`),
  KEY `HOPE_USERNAME_FK` (`USERNAME`),
  CONSTRAINT `HOPE_USERNAME_FK` FOREIGN KEY (`USERNAME`) REFERENCES `MEMBER` (`USERNAME`) ON DELETE CASCADE
);
-- 인서트
INSERT INTO HOPE
(HOPTITLE, HOPWRITER, HOPPUBLISHER, HOPMEMO, HOPDATE, ISBN, USERNAME, IMAGE, EMAIL, PRICE, HOPYEAR, CATEGORY)
VALUES(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0)

	4-1
CREATE TABLE STATUS (
	STATNUM INT,
	STATNAME VARCHAR(500),
	CONSTRAINT STATUS_STATNUM_PK PRIMARY KEY (STATNUM)
);
-- 공용인서트
INSERT INTO STATUS VALUES (0, '신청 중');
INSERT INTO STATUS VALUES (1, '신청 취소');
INSERT INTO STATUS VALUES (2, '입고 완료');
INSERT INTO STATUS VALUES (3, '신청 반려');

	4-2
CREATE TABLE HOPLIB (
	BLNUM INT AUTO_INCREMENT,
	LIBNUM INT(50) NOT NULL,
	HOPNUM INT NOT NULL,	
	CONSTRAINT HOPLIB_BLNUM_PK PRIMARY KEY (BLNUM),
	CONSTRAINT HOPLIB_LIBNUM_FK FOREIGN KEY(LIBNUM) REFERENCES LIBRARY (LIBNUM) ON DELETE CASCADE,
	CONSTRAINT HOPLIB_HOPE_FK FOREIGN KEY(HOPNUM) REFERENCES HOPE (HOPNUM) ON DELETE CASCADE
);
-- 인서트
INSERT INTO HOPLIB
(LIBNUM, HOPNUM)
VALUES(0, 0);

	4-3
CREATE TABLE HOSTAT (
	HOPSTAT INT AUTO_INCREMENT,
	HOPNUM INT,
	STATNUM INT,
	CONSTRAINT HOSTAT_HOPSTAT_PK PRIMARY KEY (HOPSTAT),
	CONSTRAINT HOSTAT_HOPNUM_FK FOREIGN KEY (HOPNUM) REFERENCES HOPE (HOPNUM) ON DELETE CASCADE,
	CONSTRAINT HOSTAT_STATNUM_FK FOREIGN KEY (STATNUM) REFERENCES STATUS (STATNUM) ON DELETE CASCADE
);
-- 인서트
INSERT INTO HOSTAT
(HOPNUM, STATNUM)
VALUES(NULL, NULL);

5. 열람실 예약
-- 열람실 테이블
CREATE TABLE `STUDYROOM` (
  `ROOMNUM` int(11) NOT NULL AUTO_INCREMENT,
  `ROOMNAME` varchar(200) DEFAULT NULL,
  `LASTNUM` int(50) NOT NULL,
  PRIMARY KEY (`ROOMNUM`)
);
-- 열람실 생성 인서트
INSERT INTO STUDYROOM
(ROOMNUM, ROOMNAME, LASTNUM)
VALUES(1, '일반열람실(여)', 80);
INSERT INTO STUDYROOM
(ROOMNUM, ROOMNAME, LASTNUM)
VALUES(2, '일반열람실(남)', 80);
INSERT INTO STUDYROOM
(ROOMNUM, ROOMNAME, LASTNUM)
VALUES(3, '노트북실', 60);

-- 좌석 예약 상세
CREATE TABLE `STUDYDETAIL` (
  `RVNUM` int(11) NOT NULL AUTO_INCREMENT,
  `RVABLE` bit(1) NOT NULL,
  `SEATNUM` int(11) DEFAULT NULL,
  `RVDATE` date DEFAULT NULL,
  `ROOMNUM` int(11) DEFAULT NULL,
  `USERNAME` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`RVNUM`),
  KEY `STUDYDETAIL_ROOMNUM_FK` (`ROOMNUM`),
  KEY `STUDYDETAIL_USERNAME_FK` (`USERNAME`),
  CONSTRAINT `STUDYDETAIL_ROOMNUM_FK` FOREIGN KEY (`ROOMNUM`) REFERENCES `STUDYROOM` (`ROOMNUM`),
  CONSTRAINT `STUDYDETAIL_USERNAME_FK` FOREIGN KEY (`USERNAME`) REFERENCES `MEMBER` (`USERNAME`)
);
-- 사물함 대여
CREATE TABLE `LOCKER` (
  `LOCKERLAST` int(11) NOT NULL,
  PRIMARY KEY (`LOCKERLAST`)
);
-- 사물함 갯수 인서트
INSERT INTO LOCKER
(LOCKERLAST)
VALUES(40);

-- 사물함 대여 상태
CREATE TABLE `LOCKERRENT` (
  `RENTNUM` int(11) NOT NULL AUTO_INCREMENT,
  `IMP_UID` varchar(100) DEFAULT NULL,
  `MERCHANT_UID` varchar(100) DEFAULT NULL,
  `LOCKERNUM` int(11) DEFAULT NULL,
  `AMOUNT` int(11) DEFAULT NULL,
  `STDATE` date DEFAULT NULL,
  `ENDATE` date DEFAULT NULL,
  `RENTDAYS` int(11) DEFAULT NULL,
  `RENTSTAT` bit(1) DEFAULT NULL,
  `USERNAME` varchar(200) DEFAULT NULL,
  `LOCKERLAST` int(11) DEFAULT NULL,
  PRIMARY KEY (`RENTNUM`),
  KEY `LOCKERRENT_USERNAME_FK` (`USERNAME`),
  KEY `LOCKERRENT_LOCKERLAST_FK` (`LOCKERLAST`),
  CONSTRAINT `LOCKERRENT_LOCKERLAST_FK` FOREIGN KEY (`LOCKERLAST`) REFERENCES `LOCKER` (`LOCKERLAST`),
  CONSTRAINT `LOCKERRENT_USERNAME_FK` FOREIGN KEY (`USERNAME`) REFERENCES `MEMBER` (`USERNAME`)
);

-- 환불
CREATE TABLE `MONEYBACK` (
  `BACKNUM` int(11) NOT NULL AUTO_INCREMENT,
  `BACKPRICE` int(11) DEFAULT NULL,
  `BACKDATE` date DEFAULT NULL,
  `RENTNUM` int(11) DEFAULT NULL,
  `MERCHANT_UID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`BACKNUM`),
  KEY `MONEYBACK_RENTNUM_FK` (`RENTNUM`)
);
-- 환불 인서트
INSERT INTO MONEYBACK
(BACKPRICE, BACKDATE, RENTNUM, MERCHANT_UID)
VALUES(500, SYSDATE(), 1, 123890800);

-- 일정
CREATE TABLE `SCHEDULE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(500) DEFAULT NULL,
  `START` datetime DEFAULT NULL,
  `END` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
);
-- 인서트
INSERT INTO SCHEDULE
(TITLE, `START`, `END`)
VALUES(NULL, NULL, NULL);

-- 효경 ----------------------------------------------------------------------------------------------------------------------------------------------
-- 문화행사 PROGRAM
CREATE TABLE PROGRAM (
	PRONUM	INT	AUTO_INCREMENT PRIMARY KEY,
	PROCATE	VARCHAR(200)	NULL,
	RECSTATUS	VARCHAR(10)	NULL,
	PROTITLE	VARCHAR(500)	NULL,
	PROCONTENTS	VARCHAR(500)	NULL,
	TARGET	VARCHAR(200)	NULL,
	TOTAL	INT	NULL,
	PRSDATE	DATE	NULL,
	PRLDATE	DATE	NULL,
	PSDATE	DATETIME	NULL,
	PLDATE	DATETIME	NULL,
	PROTEACHER	VARCHAR(200)	NULL,
	PROPLACE	VARCHAR(200)	NULL
);

-- 제본 장바구니 CART
CREATE TABLE CART (
	CANUM	INT	AUTO_INCREMENT,
	CACOUNT	INT	NOT NULL,
	CACOLOR	VARCHAR(200)	NOT NULL,
	CAPAPER	VARCHAR(200)	NOT NULL,
	CASTPAGE	INT	NOT NULL,
	CALSPAGE	INT	NOT NULL,
	CATTPAGE	INT	NOT NULL,
	CAAMOUNT	INT	NOT NULL,
	CASTATUS	VARCHAR(200)	NOT NULL,
	USERNAME	VARCHAR(200)	NOT NULL,
	ISBN	BIGINT	NOT NULL,
	IMP_UID	VARCHAR(100)	NOT NULL,
	MERCHANT_UID	VARCHAR(100)	NOT NULL,
	CONSTRAINT CART_CANUM_PK PRIMARY KEY (CANUM),
	CONSTRAINT CART_ISBN_FK FOREIGN KEY(ISBN) REFERENCES BOOK (ISBN) ON DELETE CASCADE,
	CONSTRAINT CART_USERNAME_FK FOREIGN KEY(USERNAME) REFERENCES MEMBER (USERNAME) ON DELETE CASCADE
);

-- 유리 ----------------------------------------------------------------------------------------------------------------------------------------------
-- 인기 검색어
CREATE TABLE POPULAR (
WORD VARCHAR(500)
)

INSERT INTO POPULAR VALUES ("파친코");
INSERT INTO POPULAR VALUES ("한국사");

-- 문화프로그램 인서트
INSERT INTO seedlib.PROGRAM (PROCATE,RECSTATUS,PROTITLE,PROCONTENTS,TARGET,TOTAL,PRSDATE,PRLDATE,PSDATE,PLDATE,PROTEACHER,PROPLACE) VALUES
	 ('이벤트','마감','[자연 인형극] "똥장군" (11/11 금 15:00)','[자연인형극 - 똥장군]

진행 : 극단 칠스
후원 : 서울문화예술재단
일시 : 11/11 금 15시-16시
장소 : 3층 시청각실

※ 팀으로 신청하실 경우 비고란에 총 참석 인원을 적어주세요.','누구나',30,'2022-10-26','2022-11-10','2022-11-11 00:00:00','2022-11-11 00:00:00','극단칠스 강사님','씨앗도서관'),
	 ('특강','마감','[송년의달] 크리스마스 레진아트 키링 만들기','<크리스마스 레진아트 키링 만들기>

❍ 일    시 : 12.12(화) 16:30~18:00
❍ 접    수 : 11. 22.(화) 10:00부터 도서관 홈페이지 선착순 모집
❍ 장    소 : 1층 어린이 문화교실
❍ 대    상 : 초등 1-2학년 10명
❍ 재 료 비 : 5,000원 (수강신청 후 재료 준비를 위해 개별 안내드리는 강사 선생님 계좌로 미리 입금하셔야 합니다.)
❍ 문    의 : 씨앗도서관

*소수 인원으로 진행되는 만큼 당일 참석 가능하신 분만 신청 바랍니다. ','어린이',10,'2022-11-22','2022-12-11','2022-12-12 00:00:00','2022-12-12 00:00:00','이다영 강사님','씨앗도서관'),
	 ('특강','마감','같이 배워요! 삽화컬러링','<같이 배워요! 삽화 컬러링>

❍ 일    시 : 12.17(수), 18(목) 14:00~16:00
❍ 접    수 : 12. 01(목). 10:00부터 도서관 홈페이지 선착순 모집
❍ 장    소 : 1층 어린이 문화교실
❍ 대    상 : 초등 3-5학년 10명
❍ 재 료 비 : 5,000원 (수강신청 후 재료 준비를 위해 개별 안내드리는 강사 선생님 계좌로 미리 입금하셔야 합니다.)
❍ 문    의 : 씨앗도서관

*소수 인원으로 진행되는 만큼 당일 참석 가능하신 분만 신청 바랍니다. ','어린이',10,'2022-12-01','2022-12-16','2022-12-17 14:00:00','2022-12-18 16:00:00','이연주 강사님','씨앗도서관'),
	 ('특강','마감','상식쏙쏙 독서토론','<생각이 커지는 상식쏙쏙 독서토론>
함께 책을 읽고 생각을 나누며 독서능력 및 통찰력을 기르는 수업

-강의기간 : 12.20~12.30(격일 총 5회) 18:00~20:00 
-강의대상 : 중등 1-3학년 20명
-강의방법 : ZOOM을 활용한 실시간 온라인 수업
-준비물 : 필기도구, 교재
-교재 및 재료비 : 5회분 5,000원(입금 및 수령은 신청자에 한하여 별도 안내)

*꾸준히 참여 가능하신 분만 신청 바랍니다.
*자녀 이름 및 생년월일로 신청 가능합니다.
*수업의 효과를 위하여 휴대폰 접속은 불가합니다.
*모집 정원의 60% 미달 신청 시 강좌가 폐강될 수 있습니다.','청소년',20,'2022-12-05','2022-12-16','2022-12-20 18:00:00','2022-12-30 20:00:00','김소연 강사님','씨앗도서관'),
	 ('이벤트','접수중','일상 속 힐링 씨앗 판타스틱 북콘서트','오훈석 작가와 함께 즐기는 북콘서트!
일시 : 12/22 목요일 오후 4시~6시
운영 : 씨앗도서관
장소 : 1층 대회의실

미술관에 대한 다양한 이야기와 교과서에 나오는 명화는
언제부터 어떻게 유명해졌는지, 유명한 화가들의 어린시절은
또 어떠했을지 함께 알아보아요~

강의 시작 전 미리 보고 오면 좋은 작품들이 있습니다.
-살바도르 달리, <기억의 지속> 
-장 프랑수아 밀레, <이삭줍기>
-에드바르 뭉크, <절규>
-구스타프 클림트, <키스>
-피에트 몬드리안, <빨강, 파랑, 노랑이 있는 구성>
-조르주 피에르 쇠라, <그랑드 자트 섬의 일요일 오후>
-레오나르도 다 빈치, <모나리자>
-오귀스트 로댕, <생각하는 사람>
-산드로 보티첼리, <비너스의 탄생>
-요하네스 페르메이르, <진주 귀걸이를 한 소녀>
-빈센트 반 고흐, <별이 빛나는 밤>','누구나',50,'2022-12-12','2022-12-20','2022-12-22 16:00:00','2022-12-22 18:00:00','오훈석 작가님','씨앗도서관'),
	 ('독서회','예정','새싹작가의 독서낭독회','어린이들의 마음표현 및 독서 낭독 수업입니다.

◯ 대상 : 초3~초5
◯ 일시 : 2022. 12. 31 오후 5시~ 7시
◯ 장소 : 씨앗도서관 다문화실(대면수업)
◯ 문의 : 씨앗도서관(031-8045-6139)

◈ 본 수업은 문화체육관광부 지원사업의 일환으로 운영됩니다.','어린이',20,'2022-12-21','2022-12-29','2022-12-31 17:00:00','2022-12-31 19:00:00','서희정 선생님','씨앗도서관'),
	 ('정기강좌','예정','성인동화구연지도 (자격증반)','<성인 동화구연지도(자격증반)>
구연 실기에 필요한 이론을 습득하고 연령별,주제별 그림책을 통해 구연술과 동시/손유희/율동동요 등을 배우는 강좌

-강의기간 : 1.16. ~3.10.(매주 금, 8회) 10시~12시
-강의대상 : 성인 15명
-강의장소 : 씨앗도서관 4층 시청각실(대면수업)
-준비물 : 필기구

*모집 정원의 60% 미달 신청 시 강좌가 폐강될 수 있습니다. 
*사정에 따라 강의 내용이 변경될 수 있습니다.','성인',15,'2023-01-02','2023-01-11','2023-01-16 00:00:00','2023-03-10 00:00:00','유지수 강사님','씨앗도서관');
```
