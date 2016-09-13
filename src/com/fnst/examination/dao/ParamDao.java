package com.fnst.examination.dao;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.fnst.examination.entity.Param;


public interface ParamDao {
	public void insertParam(@RequestParam("level") String level,@RequestParam("choiceNum") String choiceNum, 
						@RequestParam("vacantNum") String vacantNum, @RequestParam("questNum")String questNum,
						@RequestParam("choiceScore")String choiceScore,@RequestParam("vacantScore")String vacantScore,
						@RequestParam("questScore")String questScore,@RequestParam("repeatRate")String repeatRate,
						@RequestParam("javaRate")String javaRate,@RequestParam("javascriptRate")String javascriptRate,
						@RequestParam("htmlRate")String htmlRate,@RequestParam("cssRate")String cssRate,
						@RequestParam("pythonRate")String pythonRate,@RequestParam("rubyRate")String rubyRate);
	public void deleteParamById(int id);
	public Param selectParamById(int id);
	public Param selectParamLast();
}
