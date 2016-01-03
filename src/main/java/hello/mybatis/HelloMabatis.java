package hello.mybatis;

import hello.mybatis.data.MarkMapper;
import hello.mybatis.model.Mark;

import java.io.Reader;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloMabatis {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	
	//spring
	private static ApplicationContext applicationContext;
	static {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}
	
	public static void main (String[] args) {
		/*
		HelloMabatis helloMybatis = new HelloMabatis();
		helloMybatis.init();
		helloMybatis.getItemMarks();
		*/
		MarkMapper mapper = (MarkMapper)applicationContext.getBean("markMapper");
		Mark mark = mapper.selectMarkByID(1);
		System.out.println(mark.getDiscr());
	}
	
	public void init() {
		try{
			reader = Resources.getResourceAsReader("mybatis-cfg.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void oneResult() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			/*
			Mark mark = (Mark)session.selectOne("hello.mybatis.model.MarkMapper.selectMarkByID", 1);
			*/
			
			MarkMapper markOperation = session.getMapper(MarkMapper.class);
			Mark mark = markOperation.selectMarkByID(1);
			
			System.out.println(mark.getDiscr());
			System.out.println(mark.getMoney());
		}finally {
			session.close();
		}
	}
	
	public void listResult() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			MarkMapper markOperation = session.getMapper(MarkMapper.class);
			List<Mark> marks = markOperation.selectMarks("%");
			
			for(Mark mark : marks) {
				System.out.println("id: " + mark.getId());
				System.out.println("money: " + mark.getMoney());
				System.out.println("time: " + mark.getTime());
				System.out.println("discr: " + mark.getDiscr());
				//System.out.println("item_id: " + mark.getItem_id());
			}
			
		}finally {
			session.close();
		}
	}
	
	public void addMark() {
		Mark mark = new Mark();
		//mark.setItem_id(1);
		mark.setMoney(500);
		mark.setTime(new Date());
		mark.setDiscr("add mark");
		SqlSession session = sqlSessionFactory.openSession();
		try{
			MarkMapper markOperation = session.getMapper(MarkMapper.class);
			markOperation.addMark(mark);
			session.commit();
			listResult();
		}finally {
			session.close();
		}
	}
	
	public void updateMark() {
		SqlSession session = sqlSessionFactory.openSession();
		try{
			listResult();
			MarkMapper markOperation = session.getMapper(MarkMapper.class);
			Mark mark = markOperation.selectMarkByID(1);
			mark.setMoney(mark.getMoney() + 1);
			markOperation.updateMark(mark);
			session.commit();
			listResult();
		}finally {
			session.close();
		}
	}
	
	public void deleteMark() {
		SqlSession session = sqlSessionFactory.openSession();
		try{
			listResult();
			MarkMapper markOperation = session.getMapper(MarkMapper.class);
			markOperation.deleteMark(2);
			session.commit();
			listResult();
		}finally {
			session.close();
		}
	}
	
	public void getItemMarks() {
		SqlSession session = sqlSessionFactory.openSession();
		try{
			MarkMapper markOperation = session.getMapper(MarkMapper.class);
			List<Mark> marks = markOperation.getItemMarks(1);
			
			for(Mark mark : marks) {
				System.out.println(mark.getItem().getName());
			}
		}finally {
			session.close();
		}
	}
	
}
