$(function() {

  $('#rootwizard').bootstrapWizard({onTabShow: function(tab, navigation, index) {
      var $total = navigation.find('li').length;
      var $current = index+1;
      // If it's the last tab then hide the last button and show the finish instead
      if($current >= $total) {
          $('#rootwizard').find('.pager .next').hide();
          $('#rootwizard').find('.pager .finish').show();
          $('#rootwizard').find('.pager .finish').removeClass('disabled');
      } else {
          $('#rootwizard').find('.pager .next').show();
          $('#rootwizard').find('.pager .finish').hide();
      }
  }});
  $('.next.create').children().click(function(){
	  $('.previous').children().click(function(){
		    $('.next.hidden').removeClass('hidden');
		    $('.next.create').children().html('生成新试卷');
		  });
		$.ajax( {  
		     url:'/Examination/outputExam',// 跳转到 action
		     beforeSend:function(){
		    	$('body').append('<div id="mask" style="height:100%; width:100%; position:fixed; _position:absolute; top:0; z-index:1000;opacity:0.5; filter: alpha(opacity=50); background-color:#000;color:#fff;text-align:center;"><h1><br /><br /><br /><br />正在生成试卷......</h1></div>  ');
		    	},
		    	complete:function(){
		    		$('#mask').remove();
		    	},
		     success:function(data) {  
		             alert("生成试卷！");  
		             $("#exampdf").attr('src','words/0722102352.pdf');
		             $(".next.finish.exam").children().attr('href','words/0722102352.pdf');
		      },  
		      error : function() {  
		           alert("异常！");  
		      }  
		 });

  });
});