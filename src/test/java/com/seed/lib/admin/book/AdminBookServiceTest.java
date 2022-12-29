package com.seed.lib.admin.book;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.seed.lib.book.BookVO;
import com.seed.lib.member.MemberVO;
import com.seed.lib.util.AdbookPager;

//@SpringBootTest
class AdminBookServiceTest {
	
	@Autowired
	private AdminBookService adminBookService;
	
	//@Test
	void getAdBookListTest() throws Exception{
		AdbookPager adbookPager = new AdbookPager();
		
		List<BookVO> bookVOs = adminBookService.getAdBookList(adbookPager);
		
		assertNotEquals(0, bookVOs.size());
		
	}
	
	//@Test
	void setBookAddTest() throws Exception {
		
		BookVO bookVO = new BookVO();
		bookVO.setTitle("title");
		bookVO.setWriter("writer");
		bookVO.setPublisher("출판사");
		bookVO.setBookDate(2022L);
		bookVO.setImage("이미지");

		
	}

}
