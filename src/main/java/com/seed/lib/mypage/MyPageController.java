package com.seed.lib.mypage;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seed.lib.admin.program.AdProgramVO;
import com.seed.lib.book.BookVO;
import com.seed.lib.book.loan.BookLoanService;
import com.seed.lib.book.loan.BookLoanVO;
import com.seed.lib.book.loan.MyReturnVO;
import com.seed.lib.donation.DonationService;
import com.seed.lib.donation.DonationVO;
import com.seed.lib.hope.HopeService;
import com.seed.lib.hope.HopeVO;
import com.seed.lib.member.MemberService;
import com.seed.lib.member.MemberVO;
import com.seed.lib.program.ProgramService;
import com.seed.lib.studyroom.LockerCancelVO;
import com.seed.lib.studyroom.LockerService;
import com.seed.lib.studyroom.LockerVO;
import com.seed.lib.studyroom.StudyDetailVO;
import com.seed.lib.studyroom.StudyRoomService;
import com.seed.lib.util.BookLoanPager;
import com.seed.lib.util.FullCalendarVO;
import com.seed.lib.util.HdPager;
import com.seed.lib.util.ProgramPager;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("mypage")
@Slf4j
public class MyPageController {
	@Autowired
	private HopeService hopeService;
	@Autowired
	private DonationService donationService;
	@Autowired
	private StudyRoomService roomService;
	@Autowired
	private LockerService lockerService;
	@Autowired
	private BookLoanService loanService;
    @Autowired
	private MemberService memberService;
    @Autowired
  	private MypageService mypageService;
    @Autowired
    private ProgramService programService;
	
	
  
  
    @GetMapping("myIndex")
	public ModelAndView getMyPage(HttpSession session, MemberVO memberVO) throws Exception{
		ModelAndView mv =new ModelAndView();
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    memberVO  = (MemberVO)authentication.getPrincipal();
		memberVO= mypageService.getMyPage(memberVO);

		int loanCount = hopeService.getLoanList(memberVO.getUsername());
		int overCount = hopeService.getOverList(memberVO.getUsername());
		int revCount = hopeService.getRevList(memberVO.getUsername());
		int hisCount = hopeService.getBookHisList(memberVO.getUsername());
		int muCount = hopeService.getMuList(memberVO.getUsername());
		int proCount = hopeService.getProList(memberVO.getUsername());
		int hopCount = hopeService.getHopList(memberVO.getUsername());
		int donCount = hopeService.getDonList(memberVO.getUsername());
		mv.addObject("loanCount", loanCount);
		mv.addObject("overCount", overCount);
		mv.addObject("revCount", revCount);
		mv.addObject("hisCount", hisCount);
		mv.addObject("muCount", muCount);
		mv.addObject("proCount", proCount);
		mv.addObject("hopCount", hopCount);
		mv.addObject("donCount", donCount);
		mv.addObject("vo", memberVO);
		mv.setViewName("mypage/myIndex");
		return mv;
	    }
	
		@GetMapping("memberCheck")
		public String memberCheck() throws Exception {
		
	     return "mypage/memberCheck"; 
	     
		}
	
		@PostMapping("memberCheck")
		@ResponseBody
		public String memberCheck2(String id, String pw, HttpSession session) throws Exception {
			log.info("PW:{}",pw);
			log.info("ID:{}",id);
			session.setAttribute("result", "ok");
			
	     return ""; 
	     
		}
	
	@GetMapping("memberModify")
	public ModelAndView setUpdate(HttpSession session, MemberVO memberVO,ModelAndView mv)throws Exception {
		
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    memberVO  = (MemberVO)authentication.getPrincipal();
	    
		memberVO = mypageService.getMyPage(memberVO);
		mv.addObject("my", memberVO);
		mv.setViewName("mypage/memberModify");
		return mv;
	}
	
	@PostMapping("memberModify")
	public ModelAndView setUpdate(MemberVO memberVO)throws Exception{
		
		ModelAndView mv =new ModelAndView();
		
		//memberVO = mypageService.getMyPage(memberVO);
		int result = memberService.setUpdate(memberVO);
		mv.setViewName("redirect:myIndex");
		
		return mv;
		
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////	
	@GetMapping("hopeList")
	public ModelAndView setHList(HdPager hdPager, HttpSession session, MemberVO memberVO)throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    memberVO  = (MemberVO)authentication.getPrincipal();
	    memberVO = mypageService.getMyPage(memberVO);
	    ModelAndView mv = new ModelAndView();
			hdPager.setUserName(memberVO.getUsername());
			List<HopeVO> hl = hopeService.getHopeList(hdPager);
			mv.addObject("hlist", hl);
			mv.addObject("pager", hdPager);
			return mv;
	}
	
	@PostMapping("hopeCancel")
	@ResponseBody
	public int setDeleteHope(@RequestBody HopeVO hopeVO) throws Exception{
		int result = hopeService.setUpdateHope(hopeVO);		
		return result;
	}
	
	@GetMapping("donList")
	public ModelAndView setDList(HdPager hdPager, HttpSession session, MemberVO memberVO)throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    memberVO  = (MemberVO)authentication.getPrincipal();
	    memberVO = mypageService.getMyPage(memberVO);
	    ModelAndView mv = new ModelAndView();
			hdPager.setUserName(memberVO.getUsername());
			List<DonationVO> dl = donationService.getDonList(hdPager);
			mv.addObject("dlist", dl);
			mv.addObject("pager", hdPager);
			return mv;
	}
	
	@GetMapping("seatHistory")
	public ModelAndView getSeatHistory (HttpSession session, MemberVO memberVO) throws Exception{
		roomService.changeAllSeat();
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    memberVO  = (MemberVO)authentication.getPrincipal();
	    memberVO = mypageService.getMyPage(memberVO);
	    ModelAndView mv = new ModelAndView();
			String userName = memberVO.getUserName();
			List<StudyDetailVO> sdl = roomService.getSeatHistory(userName);
			List<FullCalendarVO> cl = new ArrayList<>();
			JSONArray js = new JSONArray();
			for(StudyDetailVO s: sdl) {
				FullCalendarVO calendarVO = new FullCalendarVO();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", s.getSeatNum().toString());
				jsonObject.put("title", s.getSeatNum().toString());
				jsonObject.put("start", s.getRvDate().toString());
				jsonObject.put("display", "background");
				jsonObject.put("classNames", "room-"+s.getRvAble());
				js.add(jsonObject);
			}			
			mv.addObject("cl", js);		
			mv.addObject("memberVO", memberVO);		
			return mv;
	}
	
	@PostMapping("exitSeat")
	@ResponseBody
	public int exitMySeat(String exitNum, String userName) throws Exception{
		log.info(userName);
		StudyDetailVO detailVO = new StudyDetailVO();
		detailVO.setUserName(userName);
		detailVO.setSeatNum(Integer.parseInt(exitNum));
		return roomService.exitMySeat(detailVO);
	}
	
	@GetMapping("lockerHistory")
	public ModelAndView getLockerList(HttpSession session, HdPager hdPager, MemberVO memberVO) throws Exception{
		lockerService.exitAllLocker();
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	    Authentication authentication = context.getAuthentication();
	    memberVO  = (MemberVO)authentication.getPrincipal();
	    memberVO = mypageService.getMyPage(memberVO);
	    ModelAndView mv = new ModelAndView();

		hdPager.setUserName(memberVO.getUsername());
		List<LockerVO> loList = lockerService.getLockerHistory(hdPager);

		mv.addObject("locker", loList);
		mv.addObject("pager", hdPager);
		return mv;
	}
	
	@PostMapping("lockerCancel")
	@ResponseBody
	public int setLockerCancel(
			String merchant_uid,
			String reason,
			String cancel_request_amount
			) throws Exception {
		String msg = "";
//		ModelAndView mv = new ModelAndView();
		
		//토큰 발급
		IamportResponse<AccessToken> token=null;
		token = lockerService.getToken();
		
		log.info(cancel_request_amount);
		
		Integer checksum = lockerService.getLockerPrice(merchant_uid);
//		checksum = checksum - Integer.parseInt(cancel_request_amount);
		String code = lockerService.setLockerCancel(token, reason, merchant_uid, checksum.toString(), cancel_request_amount);
		log.info("token:{}",token.getResponse().getToken());
		
			if(code.equals("0")) {
				//구매 상태 변경
				int result = lockerService.exitMyLocker(merchant_uid);
				LockerCancelVO cancelVO = new LockerCancelVO();
				cancelVO.setRentNum(lockerService.getLockerOne(merchant_uid).getRentNum());
				cancelVO.setReason(reason);
				cancelVO.setCancel_request_amount(Integer.parseInt(cancel_request_amount));
				cancelVO.setMerchant_uid(merchant_uid);

				int cancelResult = lockerService.setLockerCancelOne(cancelVO);
				return lockerService.setLockerCancelOne(cancelVO);
			} else {
				return 0;
			}
		}
	
	
	
	
	//오늘 날짜 가져오기
	public static String getCurrentDate() {
		Date today = new java.util.Date();
		Locale currentLocale = new Locale("KOREAN", "KOREA");
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
		return formatter.format(today);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//대출 목록
	@GetMapping("book/loan")
	public ModelAndView getLoanList (HttpSession session, MemberVO memberVO, BookLoanPager pager) throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		ModelAndView mv = new ModelAndView();

		if(context != null) {
			Authentication authentication = context.getAuthentication();
		    memberVO  = (MemberVO)authentication.getPrincipal();
		    memberVO = mypageService.getMyPage(memberVO);
	
			if(memberVO != null) {
				mv.addObject("member", memberVO);
						
				//대출 목록
				pager.setUserName(memberVO.getUsername());
				pager.setRtStatus(1);
				List<BookVO> li = loanService.getLoanList(pager);
				mv.addObject("li", li);
				
				//대출 중인 책 권수
				BookLoanVO loVO = new BookLoanVO();
				loVO.setRtStatus(1);
				loVO.setUserName(memberVO.getUsername());
				int count = loanService.getLoanCount(loVO);
				mv.addObject("count", count);
			}
		}
		return mv;
	}
	
	//대출 이력 목록
	@GetMapping("book/loanHistory")
	public ModelAndView getLoanHistoryList (HttpSession session, MemberVO memberVO, BookLoanPager pager) throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		ModelAndView mv = new ModelAndView();

		if(context != null) {
		    Authentication authentication = context.getAuthentication();
		    memberVO  = (MemberVO)authentication.getPrincipal();
		    memberVO = mypageService.getMyPage(memberVO);
			
			if(memberVO != null) {
				mv.addObject("member", memberVO);
						
				//대출 목록
				pager.setUserName(memberVO.getUsername());
				pager.setRtStatus(0);
				List<BookVO> li = loanService.getLoanList(pager);
				mv.addObject("li", li);
			
				//대출 중인 책 권수
				BookLoanVO loVO = new BookLoanVO();
				loVO.setRtStatus(0);
				loVO.setUserName(memberVO.getUsername());
				int count = loanService.getLoanCount(loVO);
				mv.addObject("count", count);
			}
		}
		return mv;
	}
	
	//대출 연장 - 최대 2번
	@GetMapping("extension")
	public String setExtension (BookLoanVO loVO) throws Exception{
		// 연장 횟수가 0, 1일때만 신청 가능 -> setExtension -> 저장 후 +1 리턴
		// 2이면 불가능 -> 3 리턴
		BookLoanVO loanVO = new BookLoanVO();
		//만기일 변경
		//연장횟수 변경
		loanVO.setExtension(loanVO.getExtension()+1);
		return "redirect:./bookLoan";
	}	
	
	//예약 목록
	@GetMapping("book/reservation")
	public ModelAndView getReList (HttpSession session, MemberVO memberVO, BookLoanPager pager) throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		ModelAndView mv = new ModelAndView();

		if(context != null) {
		    Authentication authentication = context.getAuthentication();
		    memberVO  = (MemberVO)authentication.getPrincipal();
		    memberVO = mypageService.getMyPage(memberVO);
			
			if(memberVO != null) {
				mv.addObject("member", memberVO);
						
				//예약 목록
				pager.setUserName(memberVO.getUsername());
				List<BookVO> li = loanService.getReList(pager);
				mv.addObject("li", li);
			
				//예약 중인 책 권수
				BookLoanVO loVO = new BookLoanVO();
				loVO.setUserName(memberVO.getUsername());
				int count = loanService.getReCount(loVO);
				mv.addObject("count", count);
			}
		}	
		return mv;
	}
	
	//상호대차 목록
	@GetMapping("book/mutual")
	public ModelAndView getMuList (HttpSession session, MemberVO memberVO, BookLoanPager pager) throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		ModelAndView mv = new ModelAndView();

		if(context != null) {
			Authentication authentication = context.getAuthentication();
		    memberVO  = (MemberVO)authentication.getPrincipal();
		    memberVO = mypageService.getMyPage(memberVO);
	
			
			if(memberVO != null) {
				mv.addObject("member", memberVO);
						
				//상호대차 목록
				pager.setUserName(memberVO.getUsername());
				List<BookVO> li = loanService.getMuList(pager);
				mv.addObject("li", li);
			
				//대출 중인 책 권수
				BookLoanVO loVO = new BookLoanVO();
				loVO.setUserName(memberVO.getUsername());
				int count = loanService.getMuCount(loVO);
				mv.addObject("count", count);
			}
		}
		return mv;
	}
	
	//프로그램 신청 목록
	@GetMapping("program")
	public ModelAndView getMyPro (HttpSession session, MemberVO memberVO, ProgramPager pager) throws Exception{
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		ModelAndView mv = new ModelAndView();

		if(context != null) {
			Authentication authentication = context.getAuthentication();
		    memberVO  = (MemberVO)authentication.getPrincipal();
		    memberVO = mypageService.getMyPage(memberVO);
	
			if(memberVO != null) {
				mv.addObject("member", memberVO);
						
				//목록
				pager.setUserName(memberVO.getUserName());
				List<AdProgramVO> li = programService.getMyPro(pager);
				mv.addObject("li", li);
			}
		}
		return mv;
	}
}