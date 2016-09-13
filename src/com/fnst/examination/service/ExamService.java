package com.fnst.examination.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fnst.examination.entity.Exam;
import com.fnst.examination.entity.Param;
import com.fnst.examination.entity.Question;

public interface ExamService {
	
	//题目 增 删 改
	public void saveQuestion(Question Question);
	public void deleteQuestion(int id);
	public String updateQuestion(Question Question);
	
	//试卷增 删  查
	public void saveExam(Exam exam);
	public void deleteExam();
	public Exam selectExam();
	
	//题目查询
	public Question findQuestionById(int id);
	public List<Question> findAllQuestion();
	
	//获取试卷对象
	public Exam selectQuestToExam();
	
	//获取试卷题目集合
	public HashMap<String, ArrayList<String>> getQuestionListMap();
	
	//获取答案卷
	public String getAnswer();
	
	//获取答案列表
	public List<String> getAnswerList();
	
	//根据ID获取答案
	public String getAnswerById();
}
