<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head> 
  <title>题库管理</title> 
  <meta charset="UTF-8" /> 
  <!-- Bootstrap --> 
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen" /> 
  <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen" /> 
  <link href="assets/styles.css" rel="stylesheet" media="screen" /> 
  <link href="assets/DT_bootstrap.css" rel="stylesheet" media="screen" /> 
  <link href="vendors/uniform.default.css" rel="stylesheet" media="screen" >
  <!-- <link href="bootstrap/js/bootstrap.min.js" rel="stylesheet" media="screen" />  -->
  <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]--> 
  <!-- HTML5 shim, for IE6-8 support of HTML5 elements --> 
  <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
            <![endif]--> 
        </head> 
        <body> 
        <iframe id='hiddenIFrame' name='hiddenIFrame' style="display:none;"></iframe>
        
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
                  <li class="active"> <a href="/Examination/tables"><i class="icon-chevron-right"></i> 题库管理</a> </li> 
                  <li> <a href="/Examination/setting"><i class="icon-chevron-right"></i> 参数设置</a> </li> 
                  <li> <a href="/Examination/create"><i class="icon-chevron-right"></i> 生成试卷</a> </li> 
              </ul> 
          </div> 
          <!--content--> 
          <div class="span9" id="content"> 
           <div class="row-fluid"> 
              <!-- block --> 
              <div class="block"> 
                 <div class="navbar navbar-inner block-header"> 
                    <div class="muted pull-left">题库管理</div> 
                </div> 
                <div class="block-content collapse in"> 
                    <!-- tools --> 
                    <div class="row-fluid tool-line"> 
                       <div class="span2"> 
                          <button class="btn btn-success" data-toggle="modal" data-target="#myModal" data-backdrop="static" data-keyboard="false"><i class="icon-plus icon-white"></i> Add</button>
                      </div> 
                      <div class="span2"> 
                          <button id="dlt-btn" class="btn btn-danger" disabled><i class="icon-remove icon-white"></i> Delete</button> 
                      </div> 
                  </div>
                  <!-- search --> 
                  <div class="row-fluid search-line" id="search_form">
                   <div class="span1">
                      <input class="focused" type="number" placeholder="NO…" id="number" name="number"/> 
                  </div> 
                  <div class="span3">
                      <input class="input-xlarge focused" type="text" placeholder="请输入关键字…" id="keywords" name="keywords"/>
                  </div> 
                  <div class="span3"> 
                      <div class="span6"> 
                         <select class="m-wrap" id="type" name="type"> 
                            <option value="">题目类型</option> 
                            <option value="0">填空题</option> 
                            <option value="1">选择题</option> 
                            <option value="2">简答题</option> 
                        </select> 
                    </div> 
                    <div class="span6"> 
                     <select class="m-wrap" id="difficulty" name="level"> 
                        <option value="">题目难度</option> 
                        <option value="0">非常简单</option> 
                        <option value="1">简单</option> 
                        <option value="2">一般</option> 
                        <option value="3">困难</option> 
                        <option value="4">非常困难</option> 
                    </select> 
                </div> 
            </div>
            <div class="span2">
              <input class="focused" type="text" placeholder="知识点(请以“/”号隔开)" id="knowledge" name="point"/>
          </div> 
          <div class="span2"> 
              <select class="m-wrap" id="pic" name="image"> 
                <option value="">图片…</option> 
                <option value="1">有</option> 
                <option value="0">无</option> 
            </select> 
        </div> 
        <div class="span1">
          <button class="btn btn-primary" id="search_btn">查询</button>
      </div> 
      
  </div> 

  <!-- table --> 
  <div class="row-fluid"> 
   <div class="span12"> 
      <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example"> 
         <thead> 
            <tr> 
               <th><input id="selectAll" type="checkbox" /></th> 
               <th>NO</th> 
               <th>题目内容</th> 
               <th>答案</th> 
               <th>题目类型</th> 
               <th>题目难度</th> 
               <th>知识点</th> 
               <th>图片</th> 
               <th>编辑</th> 
           </tr> 
       </thead> 
       <tbody>  
        <c:forEach var="question" items="${questionList}" varStatus="status"> 
          <tr class="odd"> 
           <td><input type="checkbox" id="${question.id}"/></td> 
           <td>${question.id}</td> 
           <td>${question.context}</td> 
           <td>${question.answer}</td> 
           <td><c:if test="${question.type=='0'}">填空题</c:if>
              <c:if test="${question.type=='1'}">选择题</c:if>
              <c:if test="${question.type=='2'}">问答题</c:if> </td>
           <td><c:if test="${question.level=='0'}">非常简单</c:if>
              <c:if test="${question.level=='1'}">简单</c:if>
              <c:if test="${question.level=='2'}">一般</c:if>
              <c:if test="${question.level=='3'}">困难</c:if>
              <c:if test="${question.level=='4'}">非常困难</c:if></td> 
           <td>${question.point}</td> 
           <td>${question.image==""||question.image==null?"无":"有"}</td> 
           <td><button class="btn btn-primary edit-btn" onclick="editFunc(this)" data-toggle="modal" data-target="#editModal"   data-backdrop="static" data-keyboard="false"><i class="icon-pencil icon-white"></i></button></td> 
          </tr> 
        </c:forEach> 
   </tbody> 
</table> 
</div> 
</div> 
</div> 
</div> 
<!-- /block --> 
</div> 
</div> 
</div> 
<!-- /block --> 
</div>   
<hr /> 
<footer> 
 <p>&copy; Fujitsu试卷生成系统 2016</p> 
</footer>  
<!--/.fluid-container--> 
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
   <div class="modal-dialog"> 
      <div class="modal-content"> 
         <div class="modal-header"> 
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times; </button> 
            <h4 class="modal-title" id="myModalLabel"> 新增题目 </h4> 
        </div>


     <div class="row-fluid"> 
        <div class="modal-body">
           <div class="block-content collapse in">
              <div class="span12 form-horizontal">
              <form ></form>
              <form id="add_modal_form"  action="question_add" method="post" enctype="multipart/form-data" onsubmit="return addModalCheck();"  target="hiddenIFrame"  class="form-horizontal">
                <fieldset>
                  <div class="control-group">
                    <label class="control-label">题&nbsp;目&nbsp;内&nbsp;容<span class="required">*</span></label>           
                    <div class="controls">
                      <textarea class="input-xlarge focused question" id="question" rows="2" name="context"></textarea>
                      <span class="help-inline hidden"></span>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label">答&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案<span class="required">*</span></label>           
                    <div class="controls"> 
                      <textarea class="input-xlarge focused answer" id="answer" rows="2" name="answer"></textarea>
                      <span class="help-inline hidden"></span>
                    </div>
                  </div>

                  <div class="control-group">
                    <label class="control-label">题&nbsp;目&nbsp;类&nbsp;型<span class="required">*</span></label>           
                    <div class="controls">
                      <select id="type" name="type"> 
                        <option  value="0">填空题</option> 
                        <option  value="1">选择题</option> 
                        <option  value="2">问答题</option> 
                    </select> 
                </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label">题&nbsp;目&nbsp;难&nbsp;度<span class="required">*</span></label>
                    <div class="controls ">
                        <select id="difficulty" name="level"> 
                          <option value="0">非常简单</option> 
                          <option value="1">简单</option> 
                          <option value="2">一般</option> 
                          <option value="3">困难</option> 
                          <option value="4">非常困难</option> 
                        </select> 
                    </div>
                  </div>
                  <div class="control-group knowledge">
                    <label class="control-label" >知&nbsp;识&nbsp;点<span class="required">*</span></label>
                    <div class="controls">
                      <div class="span3"><input type="checkbox" id="java" name="java"/>java</div>
                      <div class="span3"><input type="checkbox" id="javascript" name="javascript"/>javascript</div> 
                      <div class="span3"><input type="checkbox" id="css" name="css"/>css</div>
                    </div>
                  </div>
                  <div class="control-group knowledge">
                    <div class="controls">
                      <div class="span3"><input type="checkbox" id="html" name="html" />html</div>
                      <div class="span3"><input type="checkbox" id="python" name="python" />python</div> 
                      <div class="span3"><input type="checkbox" id="ruby" name="ruby" />ruby</div>
                    </div>
                  </div>
                   <div class="control-group knowledge-warn error hidden"> 
                    <div class="controls"><span class="help-inline"></span></div>
                  </div>                                 
                  <div class="control-group">
                    <label class="control-label" for="fileInput">图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片&nbsp;</label>
                    <div class="controls">
                        <input class="input-file uniform_on" id="fileInput" name="imgFile" type="file" accept="image/*">
                    </div>
                </div>                  
                  
                  <div class="form-actions">
                    <div class="span3"><button type="submit" class="btn btn-primary">Submit</button></div>
                    <div class="span3"><button type="reset" class="btn">Clear</button></div>
                  </div>

                </fieldset>
              </form>
          </div>
      </div>
  </div>
</div>

</div>
<!-- /.modal-content --> 
</div> 
</div>
<!-- 编辑 -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
   <div class="modal-dialog"> 
      <div class="modal-content"> 
         <div class="modal-header"> 
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times; </button> 
            <h4 class="modal-title" id="editModalLabel"> 编辑题目 </h4> 
        </div>


     <div class="row-fluid"> 
        <div class="modal-body">
           <div class="block-content collapse in">
              <div class="span12">
              <form id="edit_modal_form" action="question_update" method="post" class="form-horizontal" onsubmit="return editModalCheck();"   target="hiddenIFrame">
                <fieldset>
                	<div class="control-group">
                    <label class="control-label">题&nbsp;目&nbsp;编&nbsp;号<span class="required">*</span></label>           
                    <div class="controls">
                      <input type="text" class="input-xlarge" id="eidt_id" name="id"  />
                      <span class="help-inline hidden"></span>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label">题&nbsp;目&nbsp;内&nbsp;容<span class="required">*</span></label>           
                    <div class="controls">
                      <textarea class="input-xlarge focused question" id="question" rows="2" name="context"></textarea>
                      <span class="help-inline hidden"></span>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label">答&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案<span class="required">*</span></label>           
                    <div class="controls"> 
                      <textarea class="input-xlarge focused answer" id="answer" rows="2" name="answer"></textarea>
                      <span class="help-inline hidden"></span>
                    </div>
                  </div>

                  <div class="control-group">
                    <label class="control-label">题&nbsp;目&nbsp;类&nbsp;型<span class="required">*</span></label>           
                    <div class="controls">
                      <select id="type" name="type"> 
                        <option  value="0">填空题</option> 
                        <option  value="1">选择题</option> 
                        <option  value="2">问答题</option> 
                    </select> 
                </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label">题&nbsp;目&nbsp;难&nbsp;度<span class="required">*</span></label>
                    <div class="controls ">
                        <select id="difficulty" name="level"> 
                          <option value="0">非常简单</option> 
                          <option value="1">简单</option> 
                          <option value="2">一般</option> 
                          <option value="3">困难</option> 
                          <option value="4">非常困难</option> 
                        </select> 
                    </div>
                  </div>
                  <div class="control-group knowledge">
                    <label class="control-label" >知&nbsp;识&nbsp;点<span class="required">*</span></label>
                    <div class="controls">
                      <div class="span3"><input type="checkbox" id="java" name="java"/>java</div>
                      <div class="span3"><input type="checkbox" id="javascript" name="javascript"/>javascript</div> 
                      <div class="span3"><input type="checkbox" id="css" name="css"/>css</div>
                    </div>
                  </div>
                  <div class="control-group knowledge">
                    <div class="controls">
                      <div class="span3"><input type="checkbox" id="html" name="html" />html</div>
                      <div class="span3"><input type="checkbox" id="python" name="python" />python</div> 
                      <div class="span3"><input type="checkbox" id="ruby" name="ruby" />ruby</div>
                    </div>
                  </div>
                   <div class="control-group knowledge-warn error hidden"> 
                    <div class="controls"><span class="help-inline"></span></div>
                  </div>                                 
                  <div class="control-group">
                    <label class="control-label" for="fileInput">图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片&nbsp;</label>
                    <div class="controls">
                        <input class="input-file uniform_on" id="fileInput" name="imgFile" type="file" accept="image/*">
                    </div>
                </div>                  
                  <div class="control-group">
                  <div class="controls"><img src="" /></div>
                  </div>
                  <div class="form-actions">
                    <div class="span3"><button type="submit" class="btn btn-primary">Submit</button></div>
                    <div class="span3"><button type="reset" class="btn">Clear</button></div>
                  </div>
                </fieldset>
              </form>
          </div>
      </div>
  </div>
</div>

</div>
<!-- /.modal-content --> 
</div> 
</div>

<script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script src="vendors/jquery-1.9.1.js"></script> 
<script src="bootstrap/js/bootstrap.min.js"></script> 
<script src="vendors/datatables/js/jquery.dataTables.min.js"></script> 
<script src="assets/scripts.js"></script> 
<script src="assets/modal_scripts.js"></script> 
<script src="assets/DT_bootstrap.js"></script> 
<script src="vendors/jquery.uniform.min.js"></script>
</body>
</html>