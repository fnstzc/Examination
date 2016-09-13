<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head> 
  <title>生成试卷</title> 
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
                  <li> <a href="/Examination/setting"><i class="icon-chevron-right"></i> 参数设置</a> </li> 
                  <li class="active"> <a href="/Examination/create"><i class="icon-chevron-right"></i> 生成试卷</a> </li> 
              </ul> 
        </div> 
  <!--content--> 
  <div class="span9" id="content"> 
     <!-- wizard -->
    <div class="row-fluid section">
         <!-- block -->
        <div class="block">
            <div class="navbar navbar-inner block-header">
                <div class="muted pull-left">生成试卷</div>
            </div>
            <div class="block-content collapse in">
                <div class="span12">
                    <div id="rootwizard">
                        <div class="navbar">
                          <div class="navbar-inner">
                            <div class="container">
                        <ul>
                            <li><a href="#tab1" data-toggle="tab">参数检查</a></li>
                            <li><a href="#tab2" data-toggle="tab">预览试卷</a></li>
                        </ul>
                         </div>
                          </div>
                        </div>
                        <div class="tab-content">
                            <div class="tab-pane" id="tab1">
                               <form class="form-horizontal">
                                  <fieldset>
                <legend></legend>
                  <div class="control-group">
                    <label class="control-label" for="coincide">试&nbsp;卷&nbsp;重&nbsp;合&nbsp;率:</label>
                    <div class="controls">
                      <label id="coincide">${Examparam.repeatRate}%</label>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="difficulty">试卷难易程度&nbsp;:</label>
                    <div class="controls">
                      <label><c:if test="${Examparam.level==0}">非常简单</c:if>
				              <c:if test="${Examparam.level==1}">简单</c:if>
				              <c:if test="${Examparam.level==2}">一般</c:if>
				              <c:if test="${Examparam.level==3}">困难</c:if>
				              <c:if test="${Examparam.level==4}">非常困难</c:if></label> 
                    </div>
                  </div>
                  <div class="control-group knowledge">
                    <label class="control-label" >知&nbsp;识&nbsp;点&nbsp;比&nbsp;率:</label>
                    <div class="controls">
                        <div class="span3">
                            <div class="span6"><label id="java">java</label></div>
                            <div class="span6"><label id="java-percent">${Examparam.javaRate}%</label></div> 
                        </div>
                        <div class="span3">
                            <div class="span6"><label id="javascript"> javascript</label></div> 
                            <div class="span6"><label id="javascript-percent">${Examparam.javascriptRate}%</label></div> 
                        </div>
                    </div>
                  </div>
                  <div class="control-group knowledge">
                    <div class="controls">
                        <div class="span3">
                            <div class="span6"><label id="html" > html</label></div> 
                            <div class="span6"><label id="html-percent">${Examparam.htmlRate}%</label></div> 
                        </div>
                        <div class="span3">
                            <div class="span6"><label id="css" > css</label></div> 
                            <div class="span6"><label id="css-percent">${Examparam.cssRate}%</label></div> 
                        </div>
                    </div>
                  </div>
                  <div class="control-group knowledge">
                    <div class="controls">
                        <div class="span3">
                            <div class="span6"><label id="python" > python</label></div> 
                            <div class="span6"><label id="python-percent">${Examparam.pythonRate}%</label></div> 
                        </div>
                        <div class="span3">
                            <div class="span6"><label id="ruby" > ruby </label></div> 
                            <div class="span6"><label id="c-percent" >${Examparam.rubyRate}%</label></div> 
                        </div>
                    </div>
                  </div>               
                  <div class="control-group">
                    <label class="control-label" >题型数量&分值:</label>
                    <div class="controls">
                        <div class="span3"><label>数量<small>（总10题）</small></label></div> 
                        <div class="span3"><label>分值</label></div>
                    </div>
                  </div> 
                  <div class="control-group question">
                    <label class="control-label">填空题</label>
                    <div class="controls">
                        <div class="span3"><lable id="tian_number">${Examparam.vacantNum}</lable></div> 
                        <div class="span3"><lable id="tian_score">${Examparam.vacantScore}</lable></div>
                    </div>
                  </div>
                  <div class="control-group question">
                    <label class="control-label">选择题</label>
                    <div class="controls">
                        <div class="span3"><lable id="xuan_number">${Examparam.choiceNum}</lable></div> 
                        <div class="span3"><lable id="xuan_score">${Examparam.choiceScore}</lable></div>
                    </div>
                  </div>
                  <div class="control-group question">
                    <label class="control-label">问答题</label>
                    <div class="controls">
                        <div class="span3"><lable id="wen_number">${Examparam.questNum}</lable></div> 
                        <div class="span3"><lable id="wen_score">${Examparam.questScore}</lable></div>
                    </div>
                  </div>     
                                  </fieldset>
                                </form>
                            </div>
                            <div class="tab-pane" id="tab2">
                                <h2 class="center">试卷重合率为22.5%</h2>
                                <h4 class="center">试卷预览</h4>
                               <embed id="exampdf" width="980" height="350" src="images/word.pdf"></embed>
                                <h4 class="center">答案预览</h4>
                               <embed id="answerpdf" width="980" height="250" src="images/word.pdf"></embed>
                            </div>
                            <ul class="pager wizard">
                                <li class="previous"><a href="javascript:void(0);">上一页</a></li>
                                <li class="next hidden"><a href="javascript:void(0);">下一页</a></li>
                                <li class="next create"><a href="javascript:void(0);">生成试卷</a></li>
                                <li class="next finish"  style="display:none"><a href="javascript:;">导出答案</a></li>
                                <li class="next finish exam"  style="display:none"><a href="javascript:;">导出试卷</a></li>
                            </ul>
                        </div>  
                    </div>
                </div>
            </div>
        </div>
        <!-- /block -->
    </div>
  <!-- /wizard -->


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
<script src="assets/create_scripts.js"></script> 
<script src="assets/DT_bootstrap.js"></script>
<script src="vendors/wizard/jquery.bootstrap.wizard.js"></script> 
</body>
</html>