function getAllTags() {
	var tags = [];
	$.ajax({
		url: "getalltags.do",
		dataType: 'json',
		async: false,
		success: function(data) {
			for(var index=0; index<data.length; index++) {
				tags.push(data[index]);
			}
		},
		error: function(err){
			alert("获取标签信息失败！"+err);
		}
	});
	
	// 返回同步形式获取到的标签信息。
	return tags;
}

$(document).ready(function(){
	// 页面加载完成后即启动标签填充，为写博客这一动作提供标签信息的支持。
	var tags = getAllTags();
	for(var index=0; index<tags.length; index++) {
		var styles = [ 'primary', 'success', 'info', 'warning',
						'danger' ];
		var tag = "<input type='checkbox' name='tag' value='"
			+tags[index].id+"'><span style='margin:3px;' class='label label-"+styles[Math.floor(Math.random()*100)%5]+"'>"+tags[index].name+"</span>&nbsp;&nbsp;&nbsp;";
		$("#tags").append($(tag));
	}
});