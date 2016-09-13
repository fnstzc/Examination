$(function() {
	  $(".uniform_on").uniform();
	  $('.question').focus(function(){
			$('.question').next().addClass('hidden');
			$('.question').parent().parent().removeClass('error');	  	
	  });
	  $('.answer').focus(function(){
			$('.answer').next().addClass('hidden');
			$('.answer').parent().parent().removeClass('error');	  	
	  });
	  $('.knowledge input[type="checkbox"]').each(function(){
	  	$(this).click(function(){
			if($(this).is(':checked')==true){
				$('.knowledge-warn').addClass('hidden');
			}			
		});

	  });

});
	function addModalCheck(){
		var bool = true;
		if($('#myModal #question').val()==''||$('#myModal #question').val()==null){
			$('#myModal #question').next().removeClass('hidden');
			$('#myModal #question').parent().parent().addClass('error');
			$('#myModal #question').next().html('题目内容不能为空').addClass('error');
			bool = false;
		}
		if($('#myModal #answer').val()==''||$('#myModal #answer').val()==null){
			$('#myModal #answer').next().removeClass('hidden');
			$('#myModal #answer').parent().parent().addClass('error');
			$('#myModal #answer').next().html('答案不能为空').addClass('error');
			bool = false;
		}
		if($('#myModal input:checked').size()<1){
			$('#myModal .knowledge-warn').addClass('error').removeClass('hidden');
			$('#myModal .knowledge-warn').children().children().html('请至少选择一个知识点');
			bool = false;
		}
		return bool;
	};
	
	function editModalCheck(){
		var bool = true;
		if($('#editModal #question').val()==''||$('#editModal #question').val()==null){
			$('#editModal #question').next().removeClass('hidden');
			$('#editModal #question').parent().parent().addClass('error');
			$('#editModal #question').next().html('题目内容不能为空').addClass('error');
			bool = false;
		}
		if($('#editModal #answer').val()==''||$('#editModal #answer').val()==null){
			$('#editModal #answer').next().removeClass('hidden');
			$('#editModal #answer').parent().parent().addClass('error');
			$('#editModal #answer').next().html('答案不能为空').addClass('error');
			bool = false;
		}
		if($('#editModal input:checked').size()<1){
			$('#editModal .knowledge-warn').addClass('error').removeClass('hidden');
			$('#editModal .knowledge-warn').children().children().html('请至少选择一个知识点');
			bool = false;
		}
		return bool;
	};
	function editFunc(obj){
		$('#editModal .modal-body').find('.control-group').removeClass('error');
		$('#editModal .modal-body').find('textarea').val('');
		$('#editModal .modal-body').find('.help-inline').html('');
		$('#editModal .modal-body').find('input[type="file"]').val('');
		$('#editModal .modal-body').find('input[type="checkbox"]').click().click().checked = false;
		$('#editModal .modal-body').find('button[type="reset"]').click();
		var id = obj.parentNode.parentNode.childNodes.item(0).childNodes.item(0).id;
		console.log(id);
		$.ajax( {  
		     url:'/Examination/question_findone',// 跳转到 action               
	         data:{"id":id},
		     success:function(data) { 
		    	 console.log(data);
		         $('#editModal #question').val(data.context);
		         $('#editModal #eidt_id').val(data.id);
		         $('#editModal #eidt_id').attr("readonly",true);
		         $('#editModal #answer').val(data.answer);
		         $('#editModal #type').val(data.type);
		         $('#editModal #difficulty').val(data.level);
		         var point = data.point.split('/');
		         for (var i = point.length - 1; i >= 0; i--) {
		         	switch(point[i]){
						case 'java':
						  $('#editModal #java').attr('checked',true);
						  break;
						case 'javascript':
						  $('#editModal #javascript').attr('checked',true);
						  break;
						case 'css':
						  $('#editModal #css').attr('checked',true);
						  break;
						case 'html':
						  $('#editModal #html').attr('checked',true);
						  break;
						case 'python':
						  $('#editModal #python').attr('checked',true);
						  break;
						case 'ruby':
						  $('#editModal #ruby').attr('checked',true);
						  break;
					}
		         }
		         $('#editModal img').attr('src',data.image==null?"":data.image);
		      },  
		      error : function() {  
		           alert("异常！");  
		      }  
		 });

		};