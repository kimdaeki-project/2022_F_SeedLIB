package com.seed.lib.studyroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seed.lib.member.MemberVO;
import com.seed.lib.mypage.MypageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("studyroom")
@Slf4j
public class StudyRoomController {
	
	@Value("${locker.imp.key}")
	private String impKey;
	
	@Autowired
	private StudyRoomService service;
	
	@Autowired
	private LockerService lockerService;
	
	@Autowired
	private MypageService mypageService;
	
	@GetMapping("roomList")
	public void getRoomList() throws Exception{
		service.changeAllSeat();
		
	}
	
	@PostMapping("roomList")
	@ResponseBody
	public Map<String, String> sendRoomList(String roomNum, HttpSession session, MemberVO memberVO) throws Exception{
			SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		    Authentication authentication = context.getAuthentication();
		    memberVO  = (MemberVO)authentication.getPrincipal();
		    memberVO = mypageService.getMyPage(memberVO);

		    Map<String, String> mv = new HashMap<>();
					if(memberVO.getGender().equals("여")&&roomNum.equals("1")) {
						mv.put("msg", "[일반열람실(여)] 좌석 정보를 불러옵니다");
						mv.put("url", "./roomInfo?roomNum=1");
					} else if(memberVO.getGender().equals("남")&&roomNum.equals("2")) {
						mv.put("msg", "[일반열람실(남)] 좌석 정보를 불러옵니다");
						mv.put("url", "./roomInfo?roomNum=2");
					} else if(roomNum.equals("3")) {
						mv.put("msg", "[노트북실] 좌석 정보를 불러옵니다");
						mv.put("url", "./roomInfo?roomNum=3");
					} else {
						mv.put("msg", "본인의 성별에 적합한 열람실을 다시 선택해주세요");
						mv.put("url", "/studyroom/roomList");
					}
			return mv;
			}

	
	@GetMapping("roomInfo")
	public ModelAndView getRoomInfo(String roomNum) throws Exception{
		StudyDetailVO detailVO = new StudyDetailVO(); 
		List<StudyDetailVO> sdl = service.getSeatMany(roomNum);		
		for(StudyDetailVO s: sdl) {
			StudyRoomVO roomVO = new StudyRoomVO();
			roomVO.setRoomName(s.getRoomVO().getRoomName());
			roomVO.setLastNum(s.getRoomVO().getLastNum());
			s.setRoomVO(roomVO);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("useList", sdl);
		return mv;
	}
	
	@GetMapping("roomPop/**")
	public ModelAndView getRoomRev(String roomName, int seatNum, HttpSession session, MemberVO memberVO) throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    memberVO  = (MemberVO)authentication.getPrincipal();
	    memberVO = mypageService.getMyPage(memberVO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberVO", memberVO);
		mv.addObject("seatNum", seatNum);
		mv.addObject("roomName", roomName);
		return mv;
	}

	@PostMapping("setSeat")
	@ResponseBody
	public int setMySeat (@RequestBody StudyDetailVO studyDetailVO) throws Exception {
		int result = service.setMySeat(studyDetailVO);
		if(result == 1) {
			return 200;
		} else {
			return result;
		}
	}
	
//////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("locker")
	public ModelAndView getLocker() throws Exception{
		lockerService.exitAllLocker();
		ModelAndView mv = new ModelAndView();
		List<LockerVO> lockerList = lockerService.getLockerCount();
		mv.addObject("imp",impKey);
		mv.addObject("locL", lockerList);
		return mv;
	}
	
	@GetMapping("lockerPop")
	public ModelAndView lockerPop(int lockerNum, HttpSession session, MemberVO memberVO) throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    memberVO  = (MemberVO)authentication.getPrincipal();
	    memberVO = mypageService.getMyPage(memberVO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberVO", memberVO);
		mv.addObject("imp",impKey);
		mv.addObject("lockerNum", lockerNum);
		return mv;
	}
	
	@PostMapping("check")
	@ResponseBody
	public int checkLocker(@RequestBody LockerVO lockerVO) throws Exception {
		int result = lockerService.checkLocker(lockerVO);
		if(result == 0) {
			return 200;
		} else {
			return result;
		}
	}
	
	@PostMapping("payments")
	@ResponseBody
	public int lockerPay(String imp_uid, 
						  String merchant_uid, 
						  String lockerNum,
						  String amount, 
						  String stDate,
						  String enDate,
						  String rentDays,
						  String userName) throws Exception{		
		LockerVO lockerVO = new LockerVO();
		lockerVO.setImp_uid(imp_uid);
		lockerVO.setMerchant_uid(merchant_uid);
		lockerVO.setLockerNum(Integer.parseInt(lockerNum));
		lockerVO.setAmount(Integer.parseInt(amount));
		lockerVO.setStDate(stDate);
		lockerVO.setEnDate(enDate);
		lockerVO.setRentDays(Integer.parseInt(rentDays));
		lockerVO.setUserName(userName);
		int result = lockerService.setMyLocker(lockerVO);
		if(result == 1) {
			return 200;
		} else {
			return result;
		}
		
	}
}
