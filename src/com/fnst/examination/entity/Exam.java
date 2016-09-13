package com.fnst.examination.entity;

public class Exam {
	public int examId;
	public int question1_Id;
	public int question2_Id;
	public int question3_Id;
	public int question4_Id;
	public int question5_Id;
	public int question6_Id;
	public int question7_Id;
	public int question8_Id;
	public int question9_Id;
	public int question10_Id;
	
	public Exam () {};

	public Exam(int question1_Id, int question2_Id, int question3_Id,
			int question4_Id, int question5_Id, int question6_Id,
			int question7_Id, int question8_Id, int question9_Id,
			int question10_Id) {
		
		this.question1_Id = question1_Id;
		this.question2_Id = question2_Id;
		this.question3_Id = question3_Id;
		this.question4_Id = question4_Id;
		this.question5_Id = question5_Id;
		this.question6_Id = question6_Id;
		this.question7_Id = question7_Id;
		this.question8_Id = question8_Id;
		this.question9_Id = question9_Id;
		this.question10_Id = question10_Id;
	}
	
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public int getQuestion1_Id() {
		return question1_Id;
	}
	public void setQuestion1_Id(int question1_Id) {
		this.question1_Id = question1_Id;
	}
	public int getQuestion2_Id() {
		return question2_Id;
	}
	public void setQuestion2_Id(int question2_Id) {
		this.question2_Id = question2_Id;
	}
	public int getQuestion3_Id() {
		return question3_Id;
	}
	public void setQuestion3_Id(int question3_Id) {
		this.question3_Id = question3_Id;
	}
	public int getQuestion4_Id() {
		return question4_Id;
	}
	public void setQuestion4_Id(int question4_Id) {
		this.question4_Id = question4_Id;
	}
	public int getQuestion5_Id() {
		return question5_Id;
	}
	public void setQuestion5_Id(int question5_Id) {
		this.question5_Id = question5_Id;
	}
	public int getQuestion6_Id() {
		return question6_Id;
	}
	public void setQuestion6_Id(int question6_Id) {
		this.question6_Id = question6_Id;
	}
	public int getQuestion7_Id() {
		return question7_Id;
	}
	public void setQuestion7_Id(int question7_Id) {
		this.question7_Id = question7_Id;
	}
	public int getQuestion8_Id() {
		return question8_Id;
	}
	public void setQuestion8_Id(int question8_Id) {
		this.question8_Id = question8_Id;
	}
	public int getQuestion9_Id() {
		return question9_Id;
	}
	public void setQuestion9_Id(int question9_Id) {
		this.question9_Id = question9_Id;
	}
	public int getQuestion10_Id() {
		return question10_Id;
	}
	public void setQuestion10_Id(int question10_Id) {
		this.question10_Id = question10_Id;
	}
}
