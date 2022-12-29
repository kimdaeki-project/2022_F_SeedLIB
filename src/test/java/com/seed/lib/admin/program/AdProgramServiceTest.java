package com.seed.lib.admin.program;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.seed.lib.util.DateUtil;
import com.seed.lib.util.HdPager;

//@SpringBootTest
class AdProgramServiceTest {

	@Autowired
	private AdProgramService programService;
	
	@Autowired
	private AdProgramMapper programMapper;
	
	//@Test
	void setProgramDeleteTest()throws Exception{
		
		AdProgramVO programVO = new AdProgramVO();
		
		programVO.setProNum(104L);
		
		int result = programService.setProgramDelete(programVO);
		
		assertEquals(1, result);
		
	}
	
	//@Test
	void setProgramUpdateTest()throws Exception{
		
		DateUtil dateUtil = new DateUtil();
		AdProgramVO programVO = new AdProgramVO();
		
		programVO.setProNum(5L);
		programVO.setProCate("이벤트");
		programVO.setRecStatus("접수중");
		programVO.setProTitle("무료나눔");
		programVO.setProContents("문화행사내용");
		programVO.setTarget("학생");
		programVO.setTotal(10L);
		Date prsDate = dateUtil.parseDate("2022-11-23 00:00:00");
		programVO.setPrsDate(prsDate);
		Date prlDate = dateUtil.parseDate("2022-12-01 00:00:00");
		programVO.setPrlDate(prlDate);
		Date psDate = dateUtil.parseDate("2022-12-05 10:00:00");
		programVO.setPrsDate(psDate);
		Date plDate = dateUtil.parseDate("2022-12-05 11:00:00");
		programVO.setPrlDate(plDate);
		programVO.setProTeacher("이유 선생님");
		String name="웅이";
		String teacher = "선생";		
		int result = programService.setProgramUpdate(programVO, name, teacher);
		
		assertEquals(1, result);
		
	}
	
	//@Test
	void getProgramDetailTest()throws Exception{
		
		AdProgramVO programVO = new AdProgramVO();
		
		programVO.setProNum(4L);
		
		programVO = programService.getProgramDetail(programVO);
		
		assertNotNull(programVO);
		
	}
	
	//@Test
	void getProgramListTest()throws Exception{
		
		HdPager hdPager = new HdPager();
		List<AdProgramVO> programVOs = programService.getProgramList(hdPager);
		
		assertNotEquals(0, programVOs.size());
		
	}
	
	//@Test
	void setProgramAddsTest()throws Exception{
		
		DateUtil dateUtil = new DateUtil();
		
		for(int i=0; i<100; i++) {
			
			AdProgramVO programVO = new AdProgramVO();
			
			String psDt = "2022-11-23";
			String psTime = "09:00";
			String plDt = "2022-11-23";
			String plTime = "10:00";
			String name = "효경"+i;
			String teacher = "강사님";
			programVO.setProCate("특강"+i);
			programVO.setRecStatus("예정"+i);
			programVO.setProTitle("책이야기"+i);
			programVO.setProContents("문화행사내용"+i);
			programVO.setTarget("학생");
			programVO.setTotal(30L+i);
			Date prsDate = dateUtil.parseDate("2022-08-02 00:00:00");
			programVO.setPrsDate(prsDate);
			Date prlDate = dateUtil.parseDate("2022-08-23 00:00:00");
			programVO.setPrlDate(prlDate);
			programVO.setProPlace("씨앗도서관"+i);
			
			int result = programService.setProgramAdd(programVO, name, teacher);
			
			if(i%10==0) {
				Thread.sleep(500);
			}
			
		}
		System.out.println("for문 끝");
	}
	
	//@Test
	void setProgramAddTest()throws Exception {

		String psDt = "2022-11-23";
		String psTime = "09:00";
		String plDt = "2022-11-23";
		String plTime = "10:00";
		String name = "효경";
		String teacher = "강사님";
		
		AdProgramVO programVO = new AdProgramVO();
		DateUtil dateUtil = new DateUtil();
		
		programVO.setProCate("특강");
		programVO.setRecStatus("예정");
		programVO.setProTitle("책이야기");
		programVO.setProContents("문화행사내용");
		programVO.setTarget("학생");
		programVO.setTotal(30L);
		Date prsDate = dateUtil.parseDate("2022-08-02 00:00:00");
		programVO.setPrsDate(prsDate);
		Date prlDate = dateUtil.parseDate("2022-08-23 00:00:00");
		programVO.setPrlDate(prlDate);
		
		programVO.setPsDt("2022-11-26 14:07:00");
		programVO.setPlDt("2022-11-26 14:07:00");
		
		programVO.setProPlace("씨앗도서관");
		
		int result = programMapper.setProgramAdd(programVO);
		
		assertEquals(1, result);
		
	}

}
