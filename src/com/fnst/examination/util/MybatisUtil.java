package com.fnst.examination.util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	
	//提供SqlSession
	public static SqlSession getSession() {
		
		String resource = "conf.xml";
		InputStream is= MybatisUtil.class.getClassLoader().getResourceAsStream(resource);
		
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = sessionFactory.openSession();
		
		return session;
	}
}
