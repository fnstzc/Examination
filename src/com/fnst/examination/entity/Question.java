package com.fnst.examination.entity;

import org.springframework.web.multipart.MultipartFile;

public class Question {
	private int id;
	private String context;
	private String image;
	private String type;
	private String level;
	private String point;
	private String answer;
	
	public Question() {};
	
	public Question(String context, String answer, String type,
						String level, String point,String image) {
		this.context = context;
		this.image = image;
		this.type = type;
		this.level = level;
		this.point = point;
		this.answer = answer;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", context=" + context + ", image="
				+ image + ", type=" + type + ", level=" + level + ", point="
				+ point + ", answer=" + answer + "]";
	}
	
}
