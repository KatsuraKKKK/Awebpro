package com.github.katsurakkkk.mybatis.mapper;

import java.util.List;

import com.github.katsurakkkk.mybatis.model.Mark;


public interface MarkMapper {
	
	public Mark selectMarkByID(int id);
	
	//result type is list
	public List<Mark> selectMarks(String discr);
	
	//add
	public void addMark(Mark mark);
	
	//update
	public void updateMark(Mark mark);
	
	//delete
	public void deleteMark(int id);
	
	public List<Mark> getItemMarks(int id);
	
}
