package com.seed.lib.admin.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.seed.lib.book.loan.BookLoanService;
import com.seed.lib.member.MemberVO;
import com.seed.lib.util.HdPager;

//@SpringBootTest
class AdMemberServiceTest {

	@Autowired
	private AdMemberService adMemberService;
	
	@Autowired
	private BookLoanService bookLoanService;
	
	
	
	//@Test
	void getAdMemberListTest() throws Exception {
		HdPager hdPager = new HdPager();
		
		List<MemberVO> memberVOs = adMemberService.getAdMemberList(hdPager);
		
		assertNotEquals(0, memberVOs.size());
		
	}

}
