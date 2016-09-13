package com.fnst.examination.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fnst.examination.entity.Exam;
import com.fnst.examination.entity.Param;
import com.fnst.examination.entity.Question;
import com.fnst.examination.service.BaseService;
import com.fnst.examination.service.ExamService;
import com.fnst.examination.util.CommonUtil;

@Controller
public class ExamController {
	
	@Autowired
	private ExamService examService;
	@Autowired
	private BaseService baseService;
	
	//初始化转发
	@RequestMapping("/")
	public String Init() {
		return "login";
	}
	
	//显示题库管理页面
	@RequestMapping("/tables")
	public String showQuestionManagePage(Model model) {
		List<Question> questionList = examService.findAllQuestion();
		model.addAttribute("questionList", questionList);
		return "tables";
	}
	   //显示题库管理页面
	@ResponseBody
    @RequestMapping(value="/questions")  
    public String getQuestionManagePage(String number,String keywords, String type,
            String level, String point, String image,@RequestParam String aoData) {
	    System.out.println(number+keywords+type+level+point+image+aoData);
        JSONArray jsonarray = JSONArray.fromObject(aoData);
        
        String sEcho = null;
        int iDisplayStart = 0; // 起始索引
        int iDisplayLength = 0; // 每页显示的行数

        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject obj = (JSONObject) jsonarray.get(i);
            if (obj.get("name").equals("sEcho"))
                sEcho = obj.get("value").toString();

            if (obj.get("name").equals("iDisplayStart"))
                iDisplayStart = obj.getInt("value");

            if (obj.get("name").equals("iDisplayLength"))
                iDisplayLength = obj.getInt("value");
        }

        List<Question> questionList ;
        if(number==null&&keywords==null&&type==null&&level==null&&point==null&&image==null
                ||number.equals("undefined")&&keywords.equals("undefined")&&type.equals("undefined")&&level.equals("undefined")&&point.equals("undefined")&&image.equals("undefined"))questionList=examService.findAllQuestion();
        else questionList= baseService.getQuestionQuery(number, keywords, type, level, point, image);
        List<String[]> lst = new ArrayList<String[]>();
        if(questionList!=null){
            for (int i = 0; i < questionList.size(); i++) {
                String q_type = "";
                switch(questionList.get(i).getType()+""){
                    case "0":q_type="填空题";break;
                    case "1":q_type="选择题";break;
                    case "2":q_type="问答题";break;                
                }
                String q_level = "";
                switch(questionList.get(i).getLevel()+""){
                    case "0":q_level="非常简单";break;
                    case "1":q_level="简单";break;
                    case "2":q_level="一般";break;                
                    case "3":q_level="困难";break;                
                    case "4":q_level="非常困难";break;                
                }
                String[] d = { "<input type='checkbox' id='"+questionList.get(i).getId()+"'/>",
                        ""+questionList.get(i).getId(),questionList.get(i).getContext(), questionList.get(i).getAnswer(),
                        q_type,q_level, questionList.get(i).getPoint(),
                        questionList.get(i).getImage()==null||questionList.get(i).getImage().equals("")?"无":"有",
                        "<button class='btn btn-primary edit-btn' onclick='editFunc(this)' data-toggle='modal' data-target='#editModal'   data-backdrop='static' data-keyboard='false'><i class='icon-pencil icon-white'></i></button>"};
                lst.add(d);
            }
        }
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
        getObj.put("iTotalRecords", lst.size());//实际的行数
        getObj.put("iTotalDisplayRecords", lst.size());//显示的行数,这个要和上面写的一样
        
        getObj.put("aaData", lst.subList(iDisplayStart, iDisplayLength+iDisplayStart>lst.size()?lst.size():iDisplayLength+iDisplayStart));//要以JSON格式返回
        return getObj.toString();
    }
	//显示参数设置页面
	@RequestMapping("/setting")
	public String showSettingPage(Model model) {
        Param param = baseService.getParam();
        model.addAttribute("Examparam",param);
		return "setting";
	}
    
    //显示生成试卷页面
    @RequestMapping("/create")
    public String showExtTestPagerPage(Model model) {
        Param param = baseService.getParam();
        model.addAttribute("Examparam",param);
        return "create";
    }       

	
	//显示登录页面
	@RequestMapping("/login")
	public String login(String name,String password,Model model) {
		if(baseService.loginCL(name,password).equals("login_success")) {
			model.addAttribute("status","success");
			model.addAttribute("name", name);
			return "tables";
		} else {
			model.addAttribute("status","failed");
			return "login";
		}
	}
	
	//试题查询处理
	@ResponseBody
	@RequestMapping("/question_query")
	public String queryQuestion(String number,String keywords, String type,
									String level, String point, String image,Model model) {
		List<Question> qList = baseService.getQuestionQuery(number, keywords, type, level, point, image);
		
		if (qList == null) {
			model.addAttribute("status","query_empty");
		} else {
			model.addAttribute("status","query_empty");
			model.addAttribute(qList);
		}
		
		return "tables";
	}
	
	//试题添加
	@ResponseBody
	@RequestMapping("/question_add")
	public String addQuestion(String context, String answer, String type,String level, boolean java,
								boolean javascript,boolean css, boolean html, boolean python,boolean ruby,
								@RequestParam(value="imgFile",required=false) MultipartFile imgFile,HttpServletRequest req) {
		String point = baseService.getPoint(java, javascript, css, html, python, ruby);
		String image = "";
		try {
			if (imgFile != null) {
				image = CommonUtil.getImageUrl(imgFile,req);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("============"+image);
		Question question = new Question(context,answer,type,level,point,image);
		examService.saveQuestion(question);
		return "新增成功！";
	}
	
	//试题删除
	@ResponseBody
	@RequestMapping(value = "/question_delete")
	public String deleteQuestion(@RequestParam(value = "datas[]") String[] idList) {
	    System.out.println(idList);
	    for(int i=0;i<idList.length;i++){
	        examService.deleteQuestion(Integer.parseInt(idList[i]));
	    }
		return "tables";
	}
	//试题根据ID查询
    @ResponseBody
    @RequestMapping("/question_findone")
    public Question findOneQuestion(String id) {
        int qid = Integer.parseInt(id);
        Question question = examService.findQuestionById(qid);
        return question;
    }
	//试题编辑更新
	@ResponseBody
	@RequestMapping("/question_update")
	public String updateQuestion(Question question,boolean java,
            boolean javascript,boolean css, boolean html, boolean python,boolean ruby) {
	    String point = baseService.getPoint(java, javascript, css, html, python, ruby);
	    question.setPoint(point);
	    System.out.println(question.toString());
		examService.updateQuestion(question);
		return "编辑成功！";
	}
	
    //获取默认的参数
    @ResponseBody
    @RequestMapping("/setting_default")
    public Param settingDefault(Model model) {
        Param param = baseService.getParam();
        return param;
    }
	//保存 参数信息
    @ResponseBody
	@RequestMapping("/parameter_save")
	public String saveParameter(Param param,Model model) {
		if(baseService.saveParam(param).equals("save_ok")) {
			model.addAttribute("status","success");
		}
		return "setting";
	}
    @ResponseBody
    @RequestMapping("/outputExam")//生成试卷
	public String examOutput(Model model) {
    	
    	Exam exam = examService.selectQuestToExam();
    	examService.getAnswer();
    	
    	if (examService.selectExam() != null) {
    		examService.deleteExam();
    	}
    	examService.saveExam(exam);
   
		return "create";
	}

	//答案导出
	@RequestMapping("/outputAnswer")
	public String answerOutput(Model model) {
		
		if (examService.getAnswer().equals("output_ok")) {
			model.addAttribute("status","success");
		}
		return "create";
	}
    
}
