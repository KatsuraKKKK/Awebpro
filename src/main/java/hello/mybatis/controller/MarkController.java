package hello.mybatis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.mybatis.data.MarkMapper;
import hello.mybatis.model.Mark;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mark")
public class MarkController {
	private static Log log = LogFactory.getLog(MarkController.class);
	
	@Autowired
	private MarkMapper markMapper;
	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response) {
		List<Mark> marks = markMapper.getItemMarks(1);
		log.info(marks.size());
		ModelAndView mav = new ModelAndView("list");
		mav.addObject("marks", marks);
		return mav;
	}
	
}
