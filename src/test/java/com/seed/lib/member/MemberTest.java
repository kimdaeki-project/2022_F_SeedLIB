package com.seed.lib.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

//@SpringBootTest
class MemberTest {

	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//@Test
	void test() throws Exception {
//		for(int i=0; i<20; i++) {
//			MemberVO memberVO =new MemberVO();
//			memberVO.setUserName("zz"+i+i+"zz");
//			memberVO.setPassword(passwordEncoder.encode("1"));
//			memberVO.setName("홍회상");
//			memberVO.setEmail("doso@naver.com");
//			memberVO.setGender("남");
//			memberVO.setPhone("0102472389");
//			memberVO.setPost("aasf");
//			memberVO.setAddress1("asdf");
//			memberVO.setAddress2("aasdff");
//			memberVO.setBirth(null);
//			int result= mapper.setJoin(memberVO);
//		}
		
		MemberVO memberVO =new MemberVO();
		memberVO.setUserName("zzzzz");
		memberVO.setPassword(passwordEncoder.encode("1"));
		memberVO.setName("홍회상");
		memberVO.setEmail("doso@naver.com");
		memberVO.setGender("남");
		memberVO.setPhone("0102472389");
		memberVO.setPost("aasf");
		memberVO.setAddress1("asdf");
		memberVO.setAddress2("aasdff");
		memberVO.setBirth(null);
		int result= mapper.setJoin(memberVO);
	
	}

}
