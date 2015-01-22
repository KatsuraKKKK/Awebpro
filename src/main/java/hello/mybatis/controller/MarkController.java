package hello.mybatis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.mybatis.inter.IMarkOperation;
import hello.mybatis.model.Mark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mark")
public class MarkController {
	@Autowired
	private IMarkOperation markMapper;
	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response) {
		List<Mark> marks = markMapper.getItemMarks(1);
		ModelAndView mav = new ModelAndView("list");
		mav.addObject("marks", marks);
		return mav;
	}
	
}
