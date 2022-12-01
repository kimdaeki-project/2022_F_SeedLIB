package com.seed.lib.studyroom;

import java.sql.Date;

import lombok.Data;

@Data
public class LockerVO {
	private Integer rentNum; 
	private int lockerNum;
	private int price;
	private String stDate;
	private String enDate;
	private int rentDays;
	private int rentStat;
	private String userName;
	private int lockerLast;
}
