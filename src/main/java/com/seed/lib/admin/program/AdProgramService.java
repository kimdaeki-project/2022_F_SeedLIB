package com.seed.lib.admin.program;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seed.lib.admin.calendar.AdminCalendarService;
import com.seed.lib.util.DateUtil;
import com.seed.lib.util.HdPager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdProgramService {

	@Autowired
	private AdProgramMapper programMapper;
	
	@Autowired
	private AdminCalendarService calendarService;
	
	// 문화프로그램 신청 승인/거부
	public int setUpdateProCheck(AdProgramVO programVO)throws Exception{
		programMapper.setUpdateProCheck(programVO);
		if(programVO.getProCheck()==1) {
			programVO.setProState(0);
		} else if(programVO.getProCheck()==2) {
			programVO.setProState(2);
		}
		
		return programMapper.setUpdateProState(programVO);
	}
	
	public List<AdProgramVO> getMemberProgram(AdProgramVO programVO)throws Exception{
		return programMapper.getMemberProgram(programVO);
	}
	
	public int setUpdateStatus(AdProgramVO programVO)throws Exception{
		return programMapper.setUpdateStatus(programVO);
	}

	
	// 프로그램 삭제
	public int setProgramDelete(AdProgramVO programVO)throws Exception{
		calendarService.setScheduleDelete(programVO);
		return programMapper.setProgramDelete(programVO);
	}
	
	// 프로그램 상세정보 수정
	public int setProgramUpdate(AdProgramVO programVO, String name, String teacher)throws Exception{
		
		log.info("서비스프로그램VO {}", programVO);
		programVO.makeDate();
		
//		programVO = programMapper.getProgramDetail(programVO);
		programVO.setProTeacher(name+" "+teacher);
		int result=0;
		
		if(programVO != null) {
			
			result = programMapper.setProgramUpdate(programVO);
			calendarService.setScheduleUpdate(programVO);
			
		}
		
		return result;
	}
	
	// 프로그램 상세정보 출력
	public AdProgramVO getProgramDetail(AdProgramVO programVO)throws Exception{
		return programMapper.getProgramDetail(programVO);
	}
	
	// 프로그램 목록 출력
	public List<AdProgramVO> getProgramList(HdPager hdPager)throws Exception{
		hdPager.makeRow();
		hdPager.getNum(programMapper.getProTotalCount(hdPager));
		return programMapper.getProgramList(hdPager);
	}
	
	// 프로그램 추가
	public int setProgramAdd(AdProgramVO programVO, String name, String teacher)throws Exception{
		
		// String을 Date타입으로 바꿔주는 객체
		
		// yyyy-MM-dd HH:mm:ss 형식으로 문자열을 만들어주는 메서드 호출!
		// return은 String
		programVO.makeDate();
		
		// 강사님 이름 합침 000강사, 000선생님 등
		programVO.setProTeacher(name+" "+teacher);
		// 장소는 기본값으로 씨앗도서관 넣어줌
		programVO.setProPlace("씨앗도서관");
		programVO.setRecStatus("예정");
		
		return programMapper.setProgramAdd(programVO);
	}
	
}
