package com.seed.lib.hope;

import java.sql.Date;

import com.seed.lib.book.LibVO;
import com.seed.lib.util.StatusVO;

import lombok.Data;

@Data
public class HopeVO {
	private Long hopNum;
	private String hopTitle;
	private String hopWriter;
	private String hopPublisher;
	private String hopMemo;
	private Date hopDate;
	private Long isbn;
	private String userName;
	private String image;
	private String email;
	private String price;
	private Long hopYear;
	private Long category;
	private String emailAgree;
	
	private StatusVO statusVO;
	private LibVO libVO;
}
