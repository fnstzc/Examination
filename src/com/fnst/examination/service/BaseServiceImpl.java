package com.fnst.examination.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fnst.examination.dao.ExamDao;
import com.fnst.examination.dao.ParamDao;
import com.fnst.examination.dao.QuestionDao;
import com.fnst.examination.dao.UserDao;
import com.fnst.examination.entity.Exam;
import com.fnst.examination.entity.Param;
import com.fnst.examination.entity.Question;
import com.fnst.examination.entity.User;
import com.fnst.examination.util.MybatisUtil;


@Service
public class BaseServiceImpl implements BaseService{
	
	@Override
	public String loginCL(String name,String password) {
		SqlSession session = MybatisUtil.getSession();
		session.getMapper(UserDao.class);
		User user = session.selectOne("com.fnst.examination.dao.UserDao.selectUserByName",name);
		session.commit();
		session.close();
		if(user==null)return "login_failed";
		if (password.equals(user.getPassword())) {
			return "login_success";
		} else {
			return "login_failed";
		}
	}
	
	public Param getParam() {
		SqlSession session = MybatisUtil.getSession();
		session.getMapper(ParamDao.class);
		Param param = session.selectOne("com.fnst.examination.dao.ParamDao.selectParamLast");
		return param;
	}
	
	@Override
	public String getPoint(boolean java, boolean javascript, boolean css,
			boolean html, boolean python, boolean ruby) {
		
		StringBuilder point = new StringBuilder();
		
		if (java) {
			point.append("java/");
		}
		if (javascript) {
			point.append("javascript/");
		}
		if (css) {
			point.append("css/");
		}
		if (html) {
			point.append("html/");
		}
		if (python) {
			point.append("python/");
		}
		if (ruby) {
			point.append("ruby/");
		}
		
		return point.toString();
	}

	
	@Override
	public List<Question> getQuestionQuery(String number, String keywords,
			String type, String level, String point, String image) {
		SqlSession session = MybatisUtil.getSession();
		List<Question> qList = null;
		List<Question> allQuestionList = new ExamServiceImpl().findAllQuestion();
		for (Question question : allQuestionList) {
			System.out.println("========="+keywords+"-----"+question.getContext());
		}
		//如果用户输入编号进行查询，只会显示出一条数据，当用户输入的其他信息与数据不匹配时，返回找不到所需消息
		if (!number.isEmpty()) {
			int id = Integer.parseInt(number);
			session.getMapper(QuestionDao.class);
			Question question = session.selectOne("com.fnst.examination.dao.QuestionDao.selectQuestionById",id);
			if (question == null) {
				return qList;
			}
			//检查本条记录是否包含用户输入的keywords，不包含则返回找不到
			if (!keywords.isEmpty()) {
				if (!question.getContext().contains(keywords)) {
					return qList;
				} 
			} 
			if (!type.isEmpty()) {
				if (!question.getType().equals(type)) {
					return qList;
				}
			}
			if (!level.isEmpty()) {
				if (!question.getLevel().equals(level)) {
					return qList;
				}
			}
			if (!point.isEmpty()) {
				if (!question.getPoint().contains(point)) {
					return qList;
				}
			}
			if (!image.isEmpty()) {
				if (image.equals("1")) {
					if (question.getImage().isEmpty()) {
						return qList;
					} 
				} else {
					if (!question.getImage().isEmpty()) {
						return qList;
					}
				}
			}
			
			List<Question> questionList = new ArrayList<Question>();
			questionList.add(question);
			return questionList;
		}
		if (!keywords.isEmpty()) {
			List<Question> questionList = new ArrayList<Question>();
			List<Question> questList = new ArrayList<Question>();
			for (Question question : allQuestionList) {
				if (!question.getContext().isEmpty()) {
					if (question.getContext().contains(keywords)) {
						questionList.add(question);
					}
				}
			}
			if (!type.isEmpty()) {
				for (Question question : questionList) {
					if (question.getType().equals(type)) {
						questList.add(question);
					}
				}
			}
			if (!level.isEmpty()) {
				for (Question question : questionList) {
					if (question.getLevel().equals(level)) {
						questList.add(question);
					}
				}
			}
			if (!point.isEmpty()) {
				for (Question question : questionList) {
					if (question.getPoint().contains(point)) {
						questList.add(question);
					}
				}
			}
			if (!image.isEmpty()) {
				for (Question question : questionList) {
					if (image.equals("1")) {
						if (!question.getImage().isEmpty()) {
							questList.add(question);
						}
					} else {
						if (question.getImage().isEmpty()) {
							questList.add(question);
						}
					}
				}
			}
			return questList;
		}
		if (!type.isEmpty()) {
			List<Question> questionList = new ArrayList<Question>();
			List<Question> questList = new ArrayList<Question>();
			boolean flag=false;
			for (Question question : allQuestionList) {
					if (question.getType().equals(type)) {
						questionList.add(question);
				}
			}
			if (!level.isEmpty()) {
				flag=true;
				for (Question question : questionList) {
					if (question.getLevel().equals(level)) {
						questList.add(question);
					}
				}
			}
			if (!point.isEmpty()) {
				flag=true;
				for (Question question : questionList) {
					if (!question.getPoint().isEmpty()) {
						if (question.getPoint().contains(point)) {
							questList.add(question);
						}
					}
				}
			}
			if (!image.isEmpty()) {
				flag=true;
				for (Question question : questionList) {
					if (image.equals("1")) {
						if (!question.getImage().isEmpty()) {
							questList.add(question);
						} 
					} else {
						if (question.getImage().isEmpty()) {
							questList.add(question);
						}
					}
				}
			}
			if(flag)
			return questList;
			else {
				return questionList;
			}
		}
		if (!level.isEmpty()) {
			List<Question> questionList = new ArrayList<Question>();
			List<Question> questList = new ArrayList<Question>();
			for (Question question : allQuestionList) {
					if (question.getLevel().equals(level)) {
						questionList.add(question);
				}
			}
			if (!point.isEmpty()) {
				for (Question question : questionList) {
					if (!question.getPoint().isEmpty()) {
						if (question.getPoint().contains(point)) {
							questList.add(question);
						}
					}
				}
			}
			if (!image.isEmpty()) {
				for (Question question : questionList) {
					if (image.equals("1")) {
						if (!question.getImage().isEmpty()) {
							questList.add(question);
						} 
					} else {
						if (question.getImage().isEmpty()) {
							questList.add(question);
						}
					}
				}
			}
			return questList;
		}
		if (!point.isEmpty()) {
			List<Question> questionList = new ArrayList<Question>();
			List<Question> questList = new ArrayList<Question>();
			for (Question question : allQuestionList) {
					if (!question.getPoint().isEmpty()) {
						if (question.getPoint().contains(point)) {
							questionList.add(question);
						}
					}
			}
			if (!image.isEmpty()) {
				for (Question question : questionList) {
					if (image.equals("1")) {
						if (question.getImage().isEmpty()) {
							questList.add(question);
						} 
					} else {
						if (question.getImage().isEmpty()) {
							questList.remove(question);
						}
					}
				}
			}
			return questList;
		}
		if (!image.isEmpty()) {
			List<Question> questionList = new ArrayList<Question>();
			for (Question question : allQuestionList) {
				if (image.equals("1")) {
					if (!question.getImage().isEmpty()) {
						questionList.add(question);
					}
				} else {
					if (question.getImage().isEmpty()) {
						questionList.add(question);
					}
				}
			}
			return questionList;
		}

		//如果全部都为null则返回空List
		return allQuestionList;

	}

	@Override
	public String saveParam(Param param) {
		SqlSession session = MybatisUtil.getSession();
		session.getMapper(ParamDao.class);
		session.insert("com.fnst.examination.dao.ParamDao.insertParam", param);
		session.commit();
		session.close();
		return "save_ok";
	}

	@Override
	public void deleteParam(Param param) {
		SqlSession session = MybatisUtil.getSession();
		session.getMapper(ParamDao.class);
		session.delete("com.fnst.examination.dao.ParamDao.selectParamLast");
		session.commit();
		session.close();
	}

	@Override
	public String getImage(MultipartFile image) {
		
		
		return null;
	}

	@Override
	public Integer[] getExamId() {
		SqlSession session = MybatisUtil.getSession();
		session.getMapper(ExamDao.class);
		Exam exam = session.selectOne("com.fnst.examination.dao.ExamDao.selectExam");
		
		Integer [] examId = {exam.getExamId(),exam.getQuestion1_Id(),exam.getQuestion2_Id(),exam.getQuestion3_Id(),
				exam.getQuestion4_Id(),exam.getQuestion5_Id(),exam.getQuestion6_Id(),exam.getQuestion7_Id(),
				exam.getQuestion8_Id(),exam.getQuestion9_Id(),exam.getQuestion10_Id()};
		System.out.println(examId[0]);
		return examId;
	}
}
