package com.fnst.examination.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.fnst.examination.dao.ExamDao;
import com.fnst.examination.dao.QuestionDao;
import com.fnst.examination.entity.Exam;
import com.fnst.examination.entity.Param;
import com.fnst.examination.entity.Question;
import com.fnst.examination.util.CommonUtil;
import com.fnst.examination.util.ExamUtil;
import com.fnst.examination.util.ExtPDF;
import com.fnst.examination.util.MybatisUtil;

@Service
public class ExamServiceImpl implements ExamService{

	private final static String FILEPATH = "\\words\\";
	private final static String ANSWER = "answer";
	private final static String EXAM = "exam";
	
	@Override
	public void saveQuestion(Question q) {
		SqlSession session = MybatisUtil.getSession();
		session.getMapper(QuestionDao.class);
		session.insert("com.fnst.examination.dao.QuestionDao.addQuestion",q);
		session.commit();
		session.close();
	}

	@Override
	public void deleteQuestion(int id) {
		SqlSession session = MybatisUtil.getSession();
		session.getMapper(QuestionDao.class);
		session.delete("com.fnst.examination.dao.QuestionDao.deleteQuestion", id);
		session.commit();
		session.close();
	}

	@Override
	public String updateQuestion(Question question) {
		SqlSession session = MybatisUtil.getSession();
			session.getMapper(QuestionDao.class);
			session.update("com.fnst.examination.dao.QuestionDao.updateQuestion",question);
			session.commit();
			session.close();
			return "update_ok";
	}

	//检索所有题库中所有题目，放入List
	public List<Question> findAllQuestion() {
		
		SqlSession session = MybatisUtil.getSession();
		session.getMapper(QuestionDao.class);
		
		List<Question> questionList = session.selectList("com.fnst.examination.dao.QuestionDao.selectAllQuestion");
		
		session.commit();
		session.close();
		
		return questionList;
	}

	@Override
	public Question findQuestionById(int id) {
		
		SqlSession session = MybatisUtil.getSession();
		session.getMapper(QuestionDao.class);

		Question question = session.selectOne("com.fnst.examination.dao.QuestionDao.selectQuestionById", id);
		
		session.commit();
		session.close();
		
		return question;
	}
	
	public String getAnswerById(int id) {
		SqlSession session = MybatisUtil.getSession();
		session.getMapper(QuestionDao.class);
		String answer = session.selectOne("com.fnst.examination.dao.QuestionDao.selectAnswerById",id);
		session.commit();
		session.close();
		
		return answer;
	}
	

	@Override
	public Exam selectQuestToExam() {
		
		Param param = CommonUtil.getCurrentExamParam();
		int level = Integer.parseInt(param.getLevel());
		int repeat = Integer.parseInt(param.getRepeatRate());
		int [] questionType = CommonUtil.getQuestionType();
		int [] pointRate = CommonUtil.getPointRate();
		String [] pointType = CommonUtil.getPointType();
		
		ExamUtil examUtil = new ExamUtil();
		ArrayList<Integer> examIdList = examUtil.createPaperA(level, questionType, pointType, pointRate, repeat);
		
		int [] examQuestId = new int[examIdList.size()];
		
		for (int i = 0; i < examIdList.size(); i++) {
			examQuestId[i] = examIdList.get(i);
		}
		Exam exam = new Exam(examQuestId[0],examQuestId[1],examQuestId[2],examQuestId[3],examQuestId[4],
								examQuestId[5],examQuestId[6],examQuestId[7],examQuestId[8],examQuestId[9]);
		
		Map<String, ArrayList<String>> questListMap= examUtil.getPaperLast();
		
		ExtPDF.extPDF2(questListMap.get("choiceList"), questListMap.get("vacantList"), questListMap.get("questList"),FILEPATH,EXAM);
		return exam;
	}
	
	@Override
	public HashMap<String, ArrayList<String>> getQuestionListMap() {
		
		return null;
	}

	@Override
	public List<String> getAnswerList() {
		List<String> answerList = new ArrayList<String>();
		List<Integer> questList = CommonUtil.getExamListId();
		for (int id : questList) {
			answerList.add(getAnswerById(id));
		}
		return answerList;
	}

	@Override
	public String getAnswer() {
		
		Param param = CommonUtil.getCurrentExamParam();
		int level = Integer.parseInt(param.getLevel());
		int repeat = Integer.parseInt(param.getRepeatRate());
		int [] questionType = CommonUtil.getQuestionType();
		int [] pointRate = CommonUtil.getPointRate();
		String [] pointType = CommonUtil.getPointType();
		
		ExamUtil examUtil = new ExamUtil();
		examUtil.createPaperA(level, questionType, pointType, pointRate, repeat);
		
		Map<String, ArrayList<String>> answerListMap = examUtil.getAnswerLast();
		ExtPDF.extPDF2(answerListMap.get("choiceAnswer"), answerListMap.get("vacantAnswer"), answerListMap.get("questAnswer"),FILEPATH,ANSWER);
		
		return "output_ok";
	}

	@Override
	public String getAnswerById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveExam(Exam exam) {
		SqlSession session = MybatisUtil.getSession();
		session.getMapper(ExamDao.class);
		session.insert("com.fnst.examination.dao.ExamDao.addExam",exam);
		session.commit();
		session.close();
	}

	@Override
	public void deleteExam() {
		SqlSession session = MybatisUtil.getSession();
		session.getMapper(ExamDao.class);
		session.delete("com.fnst.examination.dao.ExamDao.deleteExam");
		session.commit();
		session.close();
	}

	@Override
	public Exam selectExam() {
		SqlSession session = MybatisUtil.getSession();
		session.getMapper(ExamDao.class);
		Exam exam = session.selectOne("com.fnst.examination.dao.ExamDao.selectExam");
		session.commit();
		session.close();
		return exam;
	}
}
