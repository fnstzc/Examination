<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head> 
  <title>参数设置</title> 
  <meta charset="UTF-8" /> 
  <!-- Bootstrap --> 
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen" /> 
  <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen" /> 
  <link href="assets/styles.css" rel="stylesheet" media="screen" /> 
  <link href="assets/DT_bootstrap.css" rel="stylesheet" media="screen" /> 
</head> 
<body> 
   <!-- top -->
   <div class="navbar navbar-fixed-top"> 
     <div class="navbar-inner"> 
        <div class="container-fluid"> 
           <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> 
           <a class="brand" href="#">Fujitsu试卷生成系统</a> 
           <div class="nav-collapse collapse"> 
              <ul class="nav pull-right"> 
                 <li class="dropdown"> <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> Admin <i class="caret"></i> </a> 
                    <ul class="dropdown-menu"> 
                       <li> <a tabindex="-1" href="/Examination/">Logout</a> </li> 
                   </ul> </li> 
               </ul> 
           </div> 
           <!--/.nav-collapse --> 
       </div> 
   </div> 
</div> 
<div class="container-fluid"> 
   <div class="row-fluid"> 
       <!-- sidebar -->
       <div class="span3" id="sidebar"> 
         <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse"> 
          <li> <a href="/Examination/tables"><i class="icon-chevron-right"></i> 题库管理</a> </li> 
          <li class="active"> <a href="/Examination/setting"><i class="icon-chevron-right"></i> 参数设置</a> </li> 
          <li> <a href="/Examination/create"><i class="icon-chevron-right"></i> 生成试卷</a> </li> 
      </ul> 
  </div> 
  <!--content--> 
  <div class="span9" id="content"> 
     <div class="row-fluid"> 
      <!-- block --> 
      <div class="block"> 
           <div class="navbar navbar-inner block-header"> 
              <div class="muted pull-left">参数设置</div> 
           </div>
           <div class="block-content collapse in">
              <div class="span12">
              <form id="setting_form" class="form-horizontal setting-form" action="parameter_save" onsubmit="return settingCheck();">
                <fieldset>
                <legend></legend>
                  <div class="control-group">
                    <label class="control-label" for="coincide">试&nbsp;卷&nbsp;重&nbsp;合&nbsp;率<span class="required">*</span></label>
                    <div class="controls">
                      <input class="span6 m-wrap focused" id="coincide" name="repeatRate" type="number" value="${Examparam.repeatRate}">%
                      <span class="help-inline">不能大于40%</span>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="difficulty">试卷难易程度&nbsp;<span class="required">*</span></label>
                    <div class="controls">
                        <select id="difficulty" class="span6 m-wrap" name="level" value="${Examparam.level}"> 
                          <option value="0">非常简单</option> 
                          <option value="1">简单</option> 
                          <option value="2">一般</option> 
                          <option value="3">困难</option> 
                          <option value="4">非常困难</option> 
                        </select> 
                    </div>
                  </div>
                  <div class="control-group knowledge">
                    <label class="control-label" >${Examparam.id }知&nbsp;识&nbsp;点&nbsp;比&nbsp;率<span class="required">*</span></label>
                    <div class="controls">
                        <div class="span3">
                        	<c:if test="${empty Examparam.javaRate}">
	                            <div class="span6"><input type="checkbox"   id="java" /> java</div>
	                            <div class="span6"><input class="span10" disabled id="java-percent" name="javaRate" type="number" value="${Examparam.javaRate}" />%</div> 
                        	</c:if>
                        	<c:if test="${not empty Examparam.javaRate}">
	                            <div class="span6"><input type="checkbox"   id="java" checked /> java</div>
	                            <div class="span6"><input class="span10" id="java-percent" name="javaRate" type="number" value="${Examparam.javaRate}" />%</div> 
                        	</c:if>
                        </div>
                        <div class="span3">
                        	<c:if test="${empty Examparam.javascriptRate}">
	                            <div class="span6"><input type="checkbox"   id="javascript" /> javascript</div> 
	                            <div class="span6"><input class="span10" disabled id="javascript-percent" name="javascriptRate" type="number" value="${Examparam.javascriptRate}"  />%</div> 
                        	</c:if>
                        	<c:if test="${not empty Examparam.javascriptRate}">
	                            <div class="span6"><input type="checkbox"   id="javascript" checked /> javascript</div> 
	                            <div class="span6"><input class="span10" id="javascript-percent" name="javascriptRate" type="number" value="${Examparam.javascriptRate}"  />%</div> 
                        	</c:if>                        
                        </div>
                    </div>
                  </div>
                  <div class="control-group knowledge">
                    <div class="controls">
                        <div class="span3">
                        	<c:if test="${empty Examparam.htmlRate}">
                            <div class="span6"><input type="checkbox"   id="html" /> html</div> 
                            <div class="span6"><input class="span10" disabled id="html-percent" name="htmlRate" type="number" value="${Examparam.htmlRate}"  />%</div> 
                        	</c:if>
                        	<c:if test="${not empty Examparam.htmlRate}">
                            <div class="span6"><input type="checkbox"   id="html" checked /> html</div> 
                            <div class="span6"><input class="span10"  id="html-percent" name="htmlRate" type="number" value="${Examparam.htmlRate}"  />%</div> 
                        	</c:if>                        
                  
                        </div>
                        <div class="span3">
                        	<c:if test="${empty Examparam.cssRate}">
                            <div class="span6"><input type="checkbox"   id="css" /> css</div> 
                            <div class="span6"><input class="span10" disabled id="css-percent" name="cssRate" type="number" value="${Examparam.cssRate}"  />%</div> 
                        	</c:if>
                        	<c:if test="${not empty Examparam.cssRate}">
                            <div class="span6"><input type="checkbox"   id="css" checked /> css</div> 
                            <div class="span6"><input class="span10"  id="css-percent" name="cssRate" type="number" value="${Examparam.cssRate}"  />%</div> 
                        	</c:if>                        
                        
                        </div>
                    </div>
                  </div>
                  <div class="control-group knowledge">
                    <div class="controls">
                        <div class="span3">
                        	<c:if test="${empty Examparam.pythonRate}">
                            <div class="span6"><input type="checkbox"   id="python" /> python</div> 
                            <div class="span6"><input class="span10" disabled id="python-percent" name="pythonRate" type="number" value="${Examparam.pythonRate}" />%</div> 
                        	</c:if>
                        	<c:if test="${not empty Examparam.pythonRate}">
                            <div class="span6"><input type="checkbox"   id="python"  checked /> python</div> 
                            <div class="span6"><input class="span10"  id="python-percent" name="pythonRate" type="number" value="${Examparam.pythonRate}" />%</div> 
                        	</c:if>                        
                        </div>
                        <div class="span3">
                        	<c:if test="${empty Examparam.rubyRate}">
                            <div class="span6"><input type="checkbox"   id="ruby" /> ruby </div> 
                            <div class="span6"><input class="span10" disabled id="ruby-percent" name="rubyRate" type="number" value="${Examparam.rubyRate}"   />%</div> 
                        	</c:if>
                        	<c:if test="${not empty Examparam.rubyRate}">
                            <div class="span6"><input type="checkbox"   id="ruby" checked /> ruby </div> 
                            <div class="span6"><input class="span10" id="ruby-percent" name="rubyRate" type="number" value="${Examparam.rubyRate}"   />%</div> 
                        	</c:if>                        
                        </div>
                    </div>
                  </div>  
                  <div class="control-group knowledge-warn error hidden"> 
                    <div class="controls"><span class="help-inline">请选择至少一个知识点</span></div>
                  </div>               
                  <div class="control-group">
                    <label class="control-label" >题型数量&分值<span class="required">*</span></label>
                    <div class="controls">
                        <div class="span3"><label>数量<small>（总10题）</small></label></div> 
                        <div class="span3"><label>分值</label></div>
                    </div>
                  </div> 
                  <div class="control-group question">
                    <label class="control-label">填空题</label>
                    <div class="controls">
                        <div class="span3"><input class="span10" id="tian_number" name="vacantNum" type="number" value="${Examparam.vacantNum}" /></div> 
                        <div class="span3"><input class="span10" id="tian_score" name="vacantScore" type="number" value="${Examparam.vacantScore}" /></div>
                    </div>
                  </div>
                  <div class="control-group question">
                    <label class="control-label">选择题</label>
                    <div class="controls">
                        <div class="span3"><input class="span10" id="xuan_number" name="choiceNum" type="number" value="${Examparam.choiceNum}" /></div> 
                        <div class="span3"><input class="span10" id="xuan_score" name="choiceScore" type="number" value="${Examparam.choiceScore}" /></div>
                    </div>
                  </div>
                  <div class="control-group question">
                    <label class="control-label">问答题</label>
                    <div class="controls">
                        <div class="span3"><input class="span10" id="wen_number" name="questNum" type="number" value="${Examparam.questNum}" /></div> 
                        <div class="span3"><input class="span10" id="wen_score" name="questScore" type="number" value="${Examparam.questScore}" /></div>
                    </div>
                  </div>                  
                   <div class="control-group question-warn error hidden"> 
                    <div class="controls"><span class="help-inline">请保证题目总数为10题</span></div>
                  </div>               
                 <div class="form-actions">
                    <div class="span2"><button type="submit" class="btn btn-primary" id="setting-submit">Submit</button></div>
                    <div class="span2"><button type="reset" class="btn" id="setting-default">default</button></div>
                  </div>

                </fieldset>
              </form>
              </div>
           </div> 
  </div> 
</div> 
</div> 

</div>   
<hr /> 
<footer> 
 <p>&copy; Fujitsu试卷生成系统 2016</p> 
</footer>  
<!--/.fluid-container--> 
<script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script src="vendors/jquery-1.9.1.js"></script> 
<script src="bootstrap/js/bootstrap.min.js"></script> 
<script src="vendors/datatables/js/jquery.dataTables.min.js"></script> 
<script src="assets/setting_scripts.js"></script> 
<script src="assets/DT_bootstrap.js"></script> 
<script>
$(function() {

});
</script>  
</body>
</html>