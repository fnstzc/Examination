package com.fnst.examination.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fnst.examination.entity.Param;
import com.fnst.examination.entity.Question;
import com.sun.javafx.collections.MappingChange.Map;

public interface BaseService {
	//试卷参数 增 删
	public String saveParam(Param param);
	public void deleteParam(Param param);
	
	//获取试卷参数信息
	public Param getParam();
		
	//登录处理
	public String loginCL(String name, String password);
		
	//获取知识点字符串
	public String getPoint(boolean java,boolean javascript,boolean css, boolean html, boolean python,boolean ruby);
	//获取题目查询列表
	public List<Question> getQuestionQuery(String number,String keywords, String type, String level, String point, String image);
	//获取图片在服务器端地址
	public String getImage(MultipartFile image);
	
	//获取试卷题目ID数组
	public Integer [] getExamId();

}
