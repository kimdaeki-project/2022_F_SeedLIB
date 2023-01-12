package com.seed.lib.hope;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.seed.lib.book.BookVO;
import com.seed.lib.util.HdPager;

@Mapper
public interface HopeMapper {

//검증용
	public int isHaveBook(Map<String, Object> map) throws Exception;
	public int getHaveBook(Map<String, Object> map) throws Exception;
	public int getOverLapBook(Map<String, Object> map) throws Exception;
	public int getMonth(HopeVO hopeVO) throws Exception;
	
//인서트용	
	public int setHope(HopeVO hopeVO) throws Exception;
	public int setHopeStat(HopeVO hopeVO) throws Exception;
	public int setHopeLib(Map<String, Object> map) throws Exception;

//마이페이지용
	public Long getTotalCount(HdPager hdPager) throws Exception;
	public List<HopeVO> getHopeList(HdPager hdPager) throws Exception;
	public int getOverList(String userName) throws Exception;
	public int getBookHisList(String userName) throws Exception;
	public int getMuList(String userName) throws Exception;
	public int getDonList(String userName) throws Exception;

//신청 취소	
	public int setUpdateHope(HopeVO hopeVO) throws Exception;
	
//Admin 페이지
	public Long getAdminTotalCount(HdPager hdPager) throws Exception;
	public List<HopeVO> getAdminHopList(HdPager hdPager) throws Exception;
	public HopeVO getHopeOne(HopeVO hopeVO) throws Exception;
	public int setHopeCncl(HopeVO hopeVO) throws Exception; 
	public Long bookCount() throws Exception;
	public int setHopeOne(BookVO bookVO) throws Exception;
	public int setLibOne(Map<String, Object> map) throws Exception;
	public int setAdminHopeStat(HopeVO hopeVO) throws Exception;
	
	//메인
	public int getLoanList(String userName) throws Exception;
	public int getRevList(String userName) throws Exception;
	public int getProList(String userName) throws Exception;
	public int getHopList(String userName) throws Exception;
	
	
	
}
