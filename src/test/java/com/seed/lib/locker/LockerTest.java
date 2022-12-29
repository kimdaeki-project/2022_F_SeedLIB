package com.seed.lib.locker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.seed.lib.studyroom.LockerMapper;
import com.seed.lib.studyroom.LockerVO;

import lombok.extern.slf4j.Slf4j;

//@SpringBootTest
//@Slf4j
class LockerTest {

	@Autowired
	private LockerMapper lockerMapper;
	
//	@Test
//	public void lockerCountTest() throws Exception{
//		int result = lockerMapper.getLockerCount();
//		log.info("result:{}",result);
//		
//	}
//	
//	@Test
//	public void UseLockerCountTest() throws Exception{
//		LockerVO lockerVO = new LockerVO();
//		lockerVO.setLockerNum(2);
//		int result = lockerMapper.getUseLocker(lockerVO);
//		log.info("UseLocker:{}",result);
//		
//	}
//	
//	@Test
//	public void getOverlapLockerTest() throws Exception{
//		LockerVO lockerVO = new LockerVO();
//		lockerVO.setUserName("member1");
//		int result = lockerMapper.getOverlapLocker(lockerVO);
//		log.info("OverLapLocker:{}",result);
//	}
	
	//@Test
	public void setMyLockerTest() throws Exception{
		LockerVO lockerVO = new LockerVO();
		lockerVO.setUserName("member1");
		lockerVO.setAmount(1000);
		lockerVO.setStDate("2022-12-01");
		lockerVO.setEnDate("2022-12-02");
		lockerVO.setLockerNum(5);
		lockerVO.setRentDays(2);
		
		int result = lockerMapper.setMyLocker(lockerVO);
	}
}
