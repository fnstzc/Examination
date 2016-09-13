$(function() {
	$('#username').focus(function(){
			$('#username').next().addClass('hidden');
			$('#username').parent().parent().removeClass('error');	  	
	  });
		$('#password').focus(function(){
			$('#password').next().addClass('hidden');
			$('#password').parent().parent().removeClass('error');	  	
	  });
});
	function usernameCheck(){
		if ($('#username').val()==''||$('#username').val()==null) {
			$('#username').next().html('用户名不能为空');
			$('#username').parent().parent().addClass('error');
			return false;
		}
		return true;
	};
	function passwordCheck(){
		if ($('#password').val()==''||$('#password').val()==null) {
			$('#password').next().html('密码不能为空');
			$('#password').parent().parent().addClass('error');
			return false;
		}
		return true;
	};
	function loginCheck(){
		var usernameBool =  usernameCheck();
		var passwordBool = passwordCheck();
		return usernameBool&&passwordBool;
	};