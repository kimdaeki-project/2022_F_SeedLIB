package com.seed.lib.admin.program;

import static org.assertj.core.api.Assertions.registerCustomDateFormat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.seed.lib.util.DateUtil;

import lombok.extern.slf4j.Slf4j;
//@SpringBootTest
@Slf4j
class FormatTest {

	//@Test
	void test() throws Exception {
		
		String data = "2022-12-13 16:42:23";
		// Date 에는 시 분 초 다 들어가있는데 날짜만 잘려서 나오는 것이었음!
		Date date = DateUtil.parseDate(data);
		// 만약 시간을 따로 쓰려면 이렇게 가져와야함!
		log.info("Date : {} ", new java.util.Date(date.getTime()).toString());
		
	}

}
