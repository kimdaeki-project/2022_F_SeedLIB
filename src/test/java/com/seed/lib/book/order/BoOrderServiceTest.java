package com.seed.lib.book.order;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.seed.lib.book.BookVO;
import com.seed.lib.member.MemberVO;

//@SpringBootTest
class BoOrderServiceTest {

	@Autowired
	private BoOrderService boOrderService;
	
	//@Test
	void getBoCartListTest()throws Exception{
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUserName("mbkoo");
		
		// MemberVO memberVO2 = boOrderService.getBoCartList(memberVO);
		
		// assertNotNull(memberVO2);
		
	}
	
	//@Test
	void setBoCartTest()throws Exception{
		
		BookVO bookVO = new BookVO();
		bookVO.setIsbn(9780062882059L);
		
		BookPrintVO bookPrintVO = new BookPrintVO();
		bookPrintVO.setCaStPage(20L);
		bookPrintVO.setCaLsPage(40L);
		bookPrintVO.setCaTtPage(20L);
		bookPrintVO.setCaAmount(3000L);
		bookPrintVO.setUserName("member");
		bookPrintVO.setBookVO(bookVO);
		
		int result = boOrderService.setBoCart(bookPrintVO);
		
		assertEquals(1, result);
		
	}
	
	//@Test
	void test()throws Exception {
		
		BoOrderVO boOrderVO = new BoOrderVO();
		
		int result = boOrderService.setBoOrder(boOrderVO);
		
		assertEquals(1, result);
		
	}

}
