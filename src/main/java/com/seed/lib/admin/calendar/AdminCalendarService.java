package com.seed.lib.admin.calendar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seed.lib.admin.program.AdProgramVO;

@Service
public class AdminCalendarService {
	
	@Autowired
	private AdminCalendarMapper calendarMapper;
	
	public List<AdminCalendarVO> getSchedule() throws Exception{
		return calendarMapper.getSchedule();
	}
	
	public int setSchedule(AdminCalendarVO calendarVO) throws Exception{
		AdProgramVO adProgramVO = new AdProgramVO();
		adProgramVO.setProTitle(calendarVO.getTitle());
		adProgramVO.setPsDt(calendarVO.getStart());
		adProgramVO.setPlDt(calendarVO.getEnd());
		calendarMapper.setAdSchedule(adProgramVO);
		return calendarMapper.setSchedule(calendarVO);
	}
	
	public int setPrSchedule(AdminCalendarVO calendarVO) throws Exception{
		return calendarMapper.setPrSchedule(calendarVO);
	}
	
	public int setScheduleUpdate(AdProgramVO adProgramVO) throws Exception{
		return calendarMapper.setScheduleUpdate(adProgramVO);
	}
	
	public int setScheduleDelete(AdProgramVO adProgramVO) throws Exception{
		return calendarMapper.setScheduleDelete(adProgramVO);
	}

}
