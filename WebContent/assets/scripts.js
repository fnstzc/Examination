$(function() {
	  $(".uniform_on").uniform();
	// table
	// checkbox全選
	$('#selectAll').click(function(){
		if($(this).is(':checked')==true){
			$('tbody tr').each(function(){
				$(this).find("td input")[0].checked = true;
			});
			$('#dlt-btn').attr('disabled',false);
		}else{
			$('tbody tr').each(function(){
				$(this).find("td input")[0].checked = false;
			});	
			$('#dlt-btn').attr('disabled',true);					
		}
	});
	// 删除题目
	$('#dlt-btn').click(function(){
		var ids = new Array();
		$('tbody input:checked').each(function(){
			ids.push($(this).attr('id'));
		});
		if (confirm("确定要删除这些题目？")){
			$.ajax( {  
			     url:'/Examination/question_delete',// 跳转到 action            
		         data:{"datas":ids},
			     success:function(data) {  
		             	$('tbody input:checked').parent().parent().addClass('hidden');
			             alert("删除成功！");  
			      },  
			      error : function() {  
			           alert("异常！");  
			      }  
			 });
		}
	});

	$('#knowledge').blur(function(){
		searchCheck();
	});
});
function illegalChar(str){
	var pattern=/[`~!@#\$%\^\&\*\(\)_\+<>\?:"\{\},\.\\;'\[\]]/im; 
	if(pattern.test(str)){ 
		return true; 
	} 
	return false; 
};
function searchCheck(){
	if(illegalChar($('#knowledge').val())){
		alert('知识点请以“/”号隔开');
		$('#knowledge').focus();
		return false;
	}
	return true;
}