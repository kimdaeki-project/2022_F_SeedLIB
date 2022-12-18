package com.seed.lib;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seed.lib.book.BookVO;
import com.seed.lib.search.PopularVO;
import com.seed.lib.search.SearchService;
import com.seed.lib.studyroom.StudyRoomService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	private StudyRoomService roomService;
	@Autowired
	private SearchService searchService;
	
	@GetMapping("/admin")
	@ResponseBody
	public String admin() throws Exception {
		return "admin Role";	
	}
	

	@GetMapping("/user")
	@ResponseBody
	public String member()throws Exception {
	
		return "Member Role";
	}

	
	@GetMapping("/")
	public ModelAndView setHome() throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = roomService.changeAllSeat();
		log.info("changeSeat:{}", result);
		
		List<PopularVO> ar = searchService.getPopularWord();
		List<BookVO> accessionBook = searchService.getAccessionBook();
		List<BookVO> popularBook = searchService.getPopularBook();
		mv.addObject("popular", ar);
		mv.addObject("accessionBook", accessionBook);
		mv.addObject("popularBook", popularBook);
		mv.setViewName("index");
		return mv;
	}
	
	@GetMapping("/test")
	public String setTest() {
		return "test";
	}
}
