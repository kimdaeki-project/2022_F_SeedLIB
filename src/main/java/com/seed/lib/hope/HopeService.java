package com.seed.lib.hope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seed.lib.book.BookVO;
import com.seed.lib.util.HdPager;
import com.seed.lib.util.MailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HopeService {

	@Autowired
	private HopeMapper hopeMapper;
	
	@Autowired
	private MailService mailService;

	public int getHaveBook(Map<String, Object> map) throws Exception{
		return hopeMapper.getHaveBook(map);
	}
	
	public int getOverLapBook(Map<String, Object> map) throws Exception{
		return hopeMapper.getOverLapBook(map);
	}
	
	public int getMonth(HopeVO hopeVO) throws Exception{
		return hopeMapper.getMonth(hopeVO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int setHope(HopeVO hopeVO, Map<String, Object> map) throws Exception{
		if(hopeVO.getEmailAgree().equals("N")) {
			hopeVO.setEmail("nope");
		}
		int result = hopeMapper.setHope(hopeVO);
		map.put("hopNum", hopeVO.getHopNum());
		if(result<0) {
			throw new Exception();
		}else {
			result = hopeMapper.setHopeLib(map);
			if(result<0) {
				throw new Exception();
			}else {
				result = hopeMapper.setHopeStat(hopeVO);
				if(result<0) {
					throw new Exception();
				} else {
					return result;
				}
			}
		}
	}
	
	public int setUpdateHope(HopeVO hopeVO) throws Exception{
		return hopeMapper.setUpdateHope(hopeVO);
	}
	
	public List<HopeVO> getHopeList(HdPager hdPager) throws Exception{
		hdPager.makeRow();
		hdPager.getNum(hopeMapper.getTotalCount(hdPager));
		return hopeMapper.getHopeList(hdPager);
	}
	
	public List<HopeVO> getAdminHopList(HdPager hdPager) throws Exception{
		hdPager.makeRow();
		hdPager.getNum(hopeMapper.getAdminTotalCount(hdPager));
		return hopeMapper.getAdminHopList(hdPager);
	}
	
	public HopeVO getHopeOne(HopeVO hopeVO) throws Exception{
		return hopeMapper.getHopeOne(hopeVO);
	}
	
	public int setHopeCncl(HopeVO hopeVO) throws Exception{
		return hopeMapper.setHopeCncl(hopeVO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int setHopeOne(HopeVO hopeVO) throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("libNum", hopeVO.getLibVO().getLibNum());
		map.put("isbn", hopeVO.getIsbn());
		int result = hopeMapper.isHaveBook(map);
		if(result<1) {
			BookVO bookVO = new BookVO();
			bookVO.setIsbn(hopeVO.getIsbn());
			bookVO.setTitle(hopeVO.getHopTitle());
			bookVO.setWriter(hopeVO.getHopWriter());
			bookVO.setPublisher(hopeVO.getHopPublisher());
			bookVO.setBookDate(hopeVO.getHopYear());
			bookVO.setCategory(hopeVO.getCategory());
			bookVO.setImage(hopeVO.getImage());
			bookVO.setNum(hopeMapper.bookCount()+1L);
			result = hopeMapper.setHopeOne(bookVO);
			if(result<1) {
				throw new Exception();
			}
		}
		
		if(result<1) {
			throw new Exception();
		}else {
			result = hopeMapper.setLibOne(map);
			if(result<1) {
				throw new Exception();
			}else {
				result = hopeMapper.setAdminHopeStat(hopeVO);
				if(result<1) {
					throw new Exception();
				} else {
					if(!hopeVO.getEmail().equals("nope")) {
						mailService.sendMail(hopeVO.getEmail(),hopeVO.getUserName(),"H");
					}
					return result;
				}
			}
		}
	}
	
	public int getLoanList(String userName) throws Exception{
		return hopeMapper.getLoanList(userName);
	}
	
	public int getRevList(String userName) throws Exception{
		return hopeMapper.getRevList(userName);
	}
	
	public int getProList(String userName) throws Exception{
		return hopeMapper.getProList(userName);
	}
	
	public int getHopList(String userName) throws Exception{
		return hopeMapper.getHopList(userName);
	}
}
