package com.fnst.examination.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fnst.examination.entity.Param;
import com.fnst.examination.service.BaseService;
import com.fnst.examination.service.BaseServiceImpl;

public class CommonUtil {
	
	private static Param param = getCurrentExamParam();
	
	public static List<Integer> getExamListId() {
		List<Integer> questList = new ArrayList<Integer>();
		return questList;
	}
	
	public static String getImageUrl(MultipartFile imgFile,HttpServletRequest request) throws IllegalStateException, IOException {
		String filePath = "";
		 //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //定义上传路径  
                        filePath = "\\image"+ "\\" + myFileName;  
                        File localFile = new File(filePath);
                        if(!localFile.exists()){
                            localFile.mkdirs();
                            try {
                                localFile.createNewFile();
                            } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            }
                            }
                        System.out.println(localFile.exists());
                        file.transferTo(localFile);  
                    }  
                }  
            }  
              
        }  
        
        return filePath;
	}
	
	public static Param getCurrentExamParam() {
		Param param = new BaseServiceImpl().getParam();
		return param;
	}
	private int questionType[] ;//选择填空简答  每种多少题
	private String knowledgeType[] ;// 知识点数组  输入有多少知识点
	private double knowledgeScale[] ;// 知识点分布 知识点比率
	
	public static int[] getQuestionType() {
		int choiceNum = Integer.parseInt(param.getChoiceNum());
		int vacantNum = Integer.parseInt(param.getVacantNum());
		int questNum = Integer.parseInt(param.getQuestNum());
		int questionType[] = {questNum,vacantNum,choiceNum};
		
		return questionType;
	}
	
	public static String[] getPointType() {
		List<String> pointList = new ArrayList<String>();
		if (param.getJavaRate() != null) {
			pointList.add("java");
		}
		if (param.getJavascriptRate() != null) {
			pointList.add("javascript");
		}
		if (param.getHtmlRate() != null) {
			pointList.add("html");
		}
		if (param.getCssRate() != null) {
			pointList.add("css");
		}
		if (param.getPythonRate() != null) {
			pointList.add("python");
		}
		if (param.getRubyRate() != null) {
			pointList.add("ruby");
		}
		
		String [] temp = new String[pointList.size()];
		String[] pointType = pointList.toArray(temp);
		
		return pointType;
	}
	
	public static int[] getPointRate() {
		List<Integer> pointList = new ArrayList<Integer>();
		
		if (param.getJavaRate() != null) {
			pointList.add(Integer.parseInt(param.getJavaRate()));
		}
		if (param.getJavascriptRate() != null) {
			pointList.add(Integer.parseInt(param.getJavascriptRate()));
		}
		if (param.getHtmlRate() != null) {
			pointList.add(Integer.parseInt(param.getHtmlRate()));
		}
		if (param.getCssRate() != null) {
			pointList.add(Integer.parseInt(param.getCssRate()));
		}
		if (param.getPythonRate() != null) {
			pointList.add(Integer.parseInt(param.getPythonRate()));
		}
		if (param.getRubyRate() != null) {
			pointList.add(Integer.parseInt(param.getRubyRate()));
		}
		
		int [] pointRate = new int[pointList.size()];
		
		for (int i = 0; i < pointList.size(); i++) {
			pointRate[i] = pointList.get(i);
			System.out.println(pointRate[i]);
		}
		
		return pointRate;
	}
}
