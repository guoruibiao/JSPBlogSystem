function checkUsername() {
	
	var username = $("#username").val();
	if(username=="") {
		alert("用户名不能为空！");
	}
}

function checkPassword() {
	var password = $("#password").val();
	if(password=="") {
		alert("密码不能为空！");
	}
	
}
/**
 * 在文档加载完毕的时候对输入框进行监听。
 */
$(document).ready(function(){
	$("#username").blur(checkUsername);
//	$("#password").focus(checkUsername);
	
	$("#password").blur(checkPassword);
});