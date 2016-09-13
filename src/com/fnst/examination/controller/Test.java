package com.fnst.examination.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fnst.examination.dao.QuestionDao;
import com.fnst.examination.entity.Question;
import com.fnst.examination.util.MybatisUtil;

public class Test {
	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
		String str = sdf.format(date);
		System.out.println(str);
	}
	
}
