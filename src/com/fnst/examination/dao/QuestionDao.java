package com.fnst.examination.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.fnst.examination.entity.Question;

public interface QuestionDao {
	public void addQuestion(@RequestParam("context") String context, @RequestParam("answer") String answer, @RequestParam("type") String type,
								@RequestParam("level") String level, @RequestParam("point") String point, @RequestParam("image") String image);
	public void updateQuestion(@Param("context") String context, @Param("answer") String answer, @Param("type") String type,
			@Param("level") String level, @Param("point") String point, @Param("image") String image);
	public void deleteQuestion(int id);
	
	public String selectAnswerById(int id);
	public List<Question> selectAllQuestion();
}
