package com.fnst.examination.dao;

import org.springframework.web.bind.annotation.RequestParam;

import com.fnst.examination.entity.Exam;


public interface ExamDao {
	public void addExam( @RequestParam("question1_Id") int question1_Id,@RequestParam("question2_Id") int question2_Id,
							@RequestParam("question3_Id") int question3_Id, @RequestParam("question4_Id") int question4_Id,
							@RequestParam("question5_Id") int question5_Id, @RequestParam("question6_Id") int question6_Id,
							@RequestParam("question7_Id") int question7_Id, @RequestParam("question8_Id") int question8_Id,
							@RequestParam("question9_Id") int question9_Id, @RequestParam("question10_Id") int question10_Id);
	public Exam selectExam();
	public void deleteExam();
}
