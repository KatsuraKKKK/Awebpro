package com.github.katsurakkkk.mybatis;

import com.github.katsurakkkk.mybatis.mapper.MarkMapper;
import com.github.katsurakkkk.mybatis.model.Mark;

import java.io.Reader;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloMybatis {

	private static ApplicationContext applicationContext;
	static {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public static void main (String[] args) {
		MarkMapper mapper = (MarkMapper)applicationContext.getBean("markMapper");
		Mark mark = mapper.selectMarkByID(1);
		System.out.println(mark.getDiscr());
	}
}
