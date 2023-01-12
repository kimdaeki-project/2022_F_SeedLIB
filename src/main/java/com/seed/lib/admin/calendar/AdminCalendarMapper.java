package com.seed.lib.admin.calendar;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.seed.lib.admin.program.AdProgramVO;

@Mapper
public interface AdminCalendarMapper {

	public List<AdminCalendarVO> getSchedule() throws Exception;
	public int setSchedule(AdminCalendarVO calendarVO) throws Exception;
	public int setScheduleUpdate(AdProgramVO adProgramVO) throws Exception;
	public int setScheduleDelete(AdProgramVO adProgramVO) throws Exception;
}
