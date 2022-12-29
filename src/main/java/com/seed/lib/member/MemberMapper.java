package com.seed.lib.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Mapper
public interface MemberMapper {

	public Integer getIdCheck(MemberVO memberVO)throws Exception;	
	public MemberVO getLogin(String userName)throws UsernameNotFoundException;
	public int setMemberRole(MemberVO memberVO)throws Exception;
	public int setJoin(MemberVO memberVO) throws Exception;
	public int setUpdate(MemberVO memberVO) throws Exception;
	public int setDelete(MemberVO memberVO) throws Exception;
	
	// 연체 반납 횟수 증가
	public int setOverCount(MemberVO memberVO)throws Exception;
	
	// 계정 잠금
	public int setLocked(MemberVO memberVO)throws Exception;
	
	// 계정 잠금 풀기
	public int setUnLocked(MemberVO memberVO)throws Exception;
	
}
