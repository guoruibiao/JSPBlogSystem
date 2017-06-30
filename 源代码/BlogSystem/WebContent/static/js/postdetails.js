$(document).ready(function() {
	/*document.getElementById("mdjs").src="";
	if($("#content-raw").css("display")=="none"){
		$("#content-raw").css("display", "block");
	}
	$("#btn-md").click(function(){
		document.getElementById("mdjs").src="http://strapdownjs.com/v/0.2/strapdown.js";
		$("#content-raw").css("display", "none");
	});*/
	/*$("#btn-md").toggle(function() {
		
		$("#content-raw").css("display", "block");
		$("#content-md").css("display", "none");
		document.getElementById("mdjs").src="";
	}, function() {
		document.getElementById("mdjs").src="http://strapdownjs.com/v/0.2/strapdown.js";
		$("#content-raw").css("display", "none");
		$("#content-md").css("display", "block");
		
	});*/
});

function getUsername(user_id) {
	var username = "";
	$.ajax({
		url : "getuser.do?user_id=" + user_id,
		type : "get",
		async : false,
		success : function(result) {
			console.log("获取到了用户名为：" + result);
			username = result;
		},
		error : function(err) {
			alert(err);
		}
	});
	return username;
}

function showWatches(post_id, ele) {
	$.ajax({
		url : 'getwatches.do?post_id=' + post_id,
		dataType : 'json',
		success : function(data) {
			console.log("浏览量信息：------");
			console.log(data);
			ele.html("浏览次数：" + data.watches);
		},
		error : function(err) {
			alert(err);
		}
	});
}

function updateWatches(post_id) {
	$.ajax({
		url : 'increwatches.do?post_id=' + post_id,
		success : function(data) {
			console.log(data);
		},
		error : function(err) {
			console.log("更新浏览量失败！");
			console.log(err);
			alert("更新浏览量失败！");
		}
	});
}

function getTags(post_id) {
	var tags = [];
	$.ajax({
		url : "",
		success : function(data) {
			for (var index = 0; index < data.length; index++) {
				tags.push(data[index].name);
			}
		},
		error : function(err) {
			alert(err);
		}
	});
	return tags;
}

$(document).ready(
		function() {

			$.ajax({
				url : "gettags.do?post_id=" + $("#post_id").val(),
				type : 'get',
				dataType : 'json',
				success : function(data) {
					for (var index = 0; index < data.length; index++) {
						var styles = [ 'primary', 'success', 'info', 'warning',
								'danger' ];
						var temp = " <span class='label label-"
								+ styles[Math.floor(Math.random() * 100) % 6]
								+ "'> " + data[index].name
								+ " </span>&nbsp;&nbsp;";
						// console.log("文章编号：" + $("#post_id").val());
						// console.log(data[index].name);
						console.log("标签相关：", temp);
						$("#tags").append($(temp));
					}
				},
				error : function(err) {
					alert(err);
				}
			});
		});

$(document).ready(
		function() {
			$.ajax({
				url : "getcomments.do?post_id=" + $("#post_id").val(),
				dataType : 'json',
				success : function(data) {
					for (var index = 0; index < data.length; index++) {
						// 更新评论信息
						var author = getUsername(data[index].user_id);
						// 更新页面上的作者名称
						$("#author").html(author);
						console.log("Author: " + author);

						var temp = "<dl><dt>" + author + "</dt>" + "<dd>"
								+ new Date(data[index].commenttime) + "</dd>"
								+ "<br/ >" + "<p class='text-left green lead'>"
								+ data[index].content + "</p></dl><hr>";
						var child = $(temp);
						$("#comments").append(child);

					}
				},
				error : function(err) {
					console.log("获取评论信息失败！" + err);
					alert("拉取评论失败。");
					console.log(err);
				}
			});
		});

// 在文档成功加载之后即触发博客浏览量增加事件。
$(document).ready(function() {
	updateWatches($("#post_id").val());
});

$(document).ready(function() {
	var post_id = $("#post_id").val();
	var watchesEle = $("#watches");
	// 显示博客的浏览量信息。
	showWatches(post_id, watchesEle);
});
