package com.anshul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.anshul.dao.FeedbackDao;
import com.anshul.entity.Feedback;

@Controller
@EnableWebMvc
public class IndexController {
	
	@Autowired
	FeedbackDao fdao;
	
	Feedback feedback;
	
	@RequestMapping("/")
	public String home() {
		return "index.jsp";
	}
	
	@RequestMapping("/addFeedback")
	public ModelAndView addAndDisplayFeedback(@ModelAttribute("feedback") Feedback fdbk) {//This needs default constructor
	
		fdao.addFeedback(fdbk);
		ModelAndView mv = new ModelAndView("displayFeedbacks.jsp");
		mv.addObject("feedbacks", fdao.getFeedbacks());
		return mv;
		
	}
	
	@RequestMapping(path="/displayJSONFormat")
	@ResponseBody
	public List<Feedback> displayJSONFormat() {
	
		//mvc:annotation-driven tag is added in dispatcher servlet
		//jackson dependency is added in pom
		List<Feedback> feedbacks = fdao.getRandomFeedbacks();
		return feedbacks;
		
	}
	

}

