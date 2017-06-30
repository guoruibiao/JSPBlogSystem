/**
 * 检查用户名字段是否为空。当username输入框失去焦点的时候触发该检查事件。
 */
function checkRegUsername() {
	$("#username").blur(function() {
		var username = $("#username").val();
		if (username == null) {
			alert("用户名不能为空！");
			return;
		}
		if (username.length < 6 || username.length > 12) {
			alert("用户名长度应为6~12位！");
			return;
		}
		if (!username.match(/[a-zA-Z0-9]+/ig)) {
			alert("用户名只能为字母数字的组合！");
			return;
		}
		// 使用ajax检测用户名是否合法。这里用到了对于闭包的实现。
		(function(username) {
			$.ajax({
				url : "validateusername.do?username=" + username,
				dataType : 'json',
				success : function(data) {
					if (data.result) {
						alert("用户名：  " + username + "  可用:)");
					} else {
						alert("用户名： " + username + "  已存在:(  您可以换个更好听的名字哟！");
					}
				},
				error : function(err) {
					alert("服务器繁忙，请稍后重试！");
					console.log(err);
				}
			});
		})(username);
	});
}

/**
 * 对密码字段进行探查。 首先检测长度，然后是两次密码输入的一致性检测。 触发时机应该是confirm字段失去焦点的时候。
 */
function checkRegPasswordAndConfirm() {
	var password = $("#password").val();
	var confirm = $("#confirm").val();
	// 长度检验
	if (password.length < 6 || password.length > 12) {
		alert("密码长度应保持在6~12位！");
		return;
	}
	// 检测两次输入的密码的一致性检测
	if (password != confirm) {
		alert("两次输入的密码不一致！");
		return;
	}
}

/**
 * 对邮箱字段进行检测。首先是非空检测，然后是格式检测。触发时机仍旧是该字段失去鼠标焦点。
 */
function checkRegEmail() {
	var email = $("#email").val();
	if(email=="" || email==null||email.length<=0) {
		alert("邮箱不能为空！");
		return;
	}
	if(!email.match(/^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/i)){
		alert("邮箱格式不合法，请仔细检查一下您输入的邮箱格式！");
		return;
	}

}

/**
 * 全局的检测实现，在文档加载完毕的时候即可触发。
 */
$(document).ready(function() {
	// 用户名字段检测
	checkRegUsername();
	// 密码字段检测
	$("#confirm").blur(checkRegPasswordAndConfirm);
	// 对邮箱格式进行检测
	$("#email").blur(checkRegEmail);

});