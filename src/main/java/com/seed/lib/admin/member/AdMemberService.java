package com.seed.lib.admin.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seed.lib.member.MemberMapper;
import com.seed.lib.member.MemberVO;
import com.seed.lib.util.HdPager;

@Service
public class AdMemberService {

	@Autowired
	private AdMemberMapper adMemberMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	public List<MemberVO> getAdMemberList(HdPager hdPager)throws Exception{
		
		hdPager.makeRow();
		hdPager.getNum(adMemberMapper.getAdMemberCount(hdPager));
		
		return adMemberMapper.getAdMemberList(hdPager);
	}
	
	// 계정 잠금
	public int setLocked(MemberVO memberVO)throws Exception{
		return memberMapper.setLocked(memberVO);
	}
	
	// 계정 잠금 풀기
	public int setUnLocked(MemberVO memberVO)throws Exception{
		return memberMapper.setUnLocked(memberVO);
	}
	
}
