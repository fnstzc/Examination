$(function() {
	$('#coincide').focus(function(){
		$('#coincide').next().html('不能大于40%');
		$('#coincide').parent().parent().removeClass('error');			
	});
	$('.knowledge input[type="checkbox"]').each(function(){
		$(this).click(function(){
			if($(this).is(':checked')==true){
				$(this).parent().next().children().attr('disabled',false);
				$('.knowledge-warn').addClass('hidden');
			}else{
				$(this).parent().next().children().attr('disabled',true);
			}			
		});
	});
	$('.knowledge input[type="number"]').each(function(){
		$(this).focus(function(){
			$('.knowledge-warn').addClass('hidden');		
		});
	});
	$('#setting_form').submit(function(){
		if(settingCheck()){
			  $.ajax({
			         type: "POST",
			         url:'/Examination/parameter_save',
			         data:$(this).serialize(),// 要提交的表单 
			         success: function(msg) {alert('保存成功！');}
			     });			
		}
		  return false;
	  });

	$('#setting-default').click(function(){
		$.ajax( {  
		     url:'/Examination/setting_default',// 跳转到 action  
		     dataType : "json", 
		     success:function(data) {  
		    		$('#difficulty').val(data.level);
		    		$('#coincide').val(data.repeatRate);
		    		if(data.javaRate==null||data.javaRate==""||data.javaRate=="0"||data.javaRate==0){
		    			$('#java-percent').attr('disabled',true);
		    			$('#java').attr('checked',false);
		    		}else{
		    			$('#java-percent').attr('disabled',false);
		    			$('#java-percent').val(data.javaRate);
		    			$('#java').attr('checked',true);
		    		}
		    		if(data.javascriptRate==null||data.javascriptRate==""||data.javascriptRate=="0"||data.javascriptRate==0){
		    			$('#javascript-percent').attr('disabled',true);
		    			$('#javascript').attr('checked',false);
		    		}else{
		    			$('#javascript-percent').attr('disabled',false);
		    			$('#javascript-percent').val(data.javascriptRate);
		    			$('#javascript').attr('checked',true);
		    		}
		    		if(data.htmlRate==null||data.htmlRate==""||data.htmlRate=="0"||data.htmlRate==0){
		    			$('#html-percent').attr('disabled',true);
		    			$('#html').attr('checked',false);
		    		}else{
		    			$('#html-percent').attr('disabled',false);
		    			$('#html-percent').val(data.htmlRate);
		    			$('#html').attr('checked',true);
		    		}
		    		if(data.cssRate==null||data.cssRate==""||data.cssRate=="0"||data.cssRate==0){
		    			$('#css-percent').attr('disabled',true);
		    			$('#css').attr('checked',false);
		    		}else{
		    			$('#css-percent').attr('disabled',false);
		    			$('#css-percent').val(data.cssRate);
		    			$('#css').attr('checked',true);
		    		}
		    		if(data.pythonRate==null||data.pythonRate==""||data.pythonRate=="0"||data.pythonRate==0){
		    			$('#python-percent').attr('disabled',true);
		    			$('#python').attr('checked',false);
		    		}else{
		    			$('#python-percent').attr('disabled',false);
		    			$('#python-percent').val(data.pythonRate);
		    			$('#python').attr('checked',true);
		    		}
		    		if(data.rubyRate==null||data.rubyRate==""||data.rubyRate=="0"||data.rubyRate==0){
		    			$('#ruby-percent').attr('disabled',true);
		    			$('#ruby').attr('checked',false);
		    		}else{
		    			$('#ruby-percent').attr('disabled',false);
		    			$('#ruby-percent').val(data.rubyRate);
		    			$('#ruby').attr('checked',true);
		    		}
		    		$('#tian_number').val(data.vacantNum);
		    		$('#tian_score').val(data.vacantScore);
		    		$('#xuan_number').val(data.choiceNum);
		    		$('#xuan_score').val(data.choiceScore);
		    		$('#wen_number').val(data.questNum);
		    		$('#wen_score').val(data.questScore);
		      },  
		      error : function() {  
		           alert("异常！");  
		      }  
		 });
	});
	$('#setting-default').click();

});
// setting
	function coincideCheck(){
		if ($('#coincide').val()==''||$('#coincide').val()==null) {
			$('#coincide').next().html('不能为空');
			$('#coincide').parent().parent().addClass('error');
			return false;
		}else if(Number($('#coincide').val())<0){
			$('#coincide').next().html('不能小于0');
			$('#coincide').parent().parent().addClass('error');
			return false;			
		}else if($('#coincide').val()>=40){
			$('#coincide').next().html('不能大于40%');
			$('#coincide').parent().parent().addClass('error');		
			return false;	
		}else{
			return true;
		}
	};
	function knowledgeCheck(){
		var bool = true;
		if($(':checked').size()<2){
			$('.knowledge-warn span').html('请选择至少一个知识点');
			$('.knowledge-warn').removeClass('hidden');
			return false;
		}
		$(':checked').each(function(){
			if(Number($(this).parent().next().children().val())<0){
				$('.knowledge-warn span').html('知识点比率不能小于0');
				$('.knowledge-warn').removeClass('hidden');
				bool = false;
			}else if(Number($(this).parent().next().children().val())>100){
				$('.knowledge-warn span').html('知识点比率不能大于100');
				$('.knowledge-warn').removeClass('hidden');
				bool = false;
			}
		});
		return bool;	
	}
	function questionCheck(){
		var bool = true;
		var count = 0;
		$('.question input').each(function(){
			if($(this).val()==''||$(this).val()==null){
				bool=false;
				$('.question-warn span').html('数量/分数不能为空');
				$('.question-warn').removeClass('hidden');
			}else if(Number($(this).val())<0){
				bool=false;
				$('.question-warn span').html('数量/分数不能小于0');
				$('.question-warn').removeClass('hidden');				
			}else{
				var question_id = $(this).attr('id');
				if(question_id.split('_')[1]=='number'){
					count = count +Number($(this).val());
				}
			}
		});
		if(bool==true){
			if(count!=10){
				bool=false;
				$('.question-warn span').html('请保证题目总数为10题');
				$('.question-warn').removeClass('hidden');			
			}else{
				$('.question-warn').addClass('hidden');			
			}
		}
		return bool;
	};
	function settingCheck(){
		var coincideBool =  coincideCheck();
		var knowledgeBool = knowledgeCheck();
		var questionBool = questionCheck();
		return coincideBool&&knowledgeBool&&questionBool;
	};
