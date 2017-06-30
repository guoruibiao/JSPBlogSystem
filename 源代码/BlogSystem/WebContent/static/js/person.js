/**
 * 根据博客编号获取博客的标题。
 * 
 * @param post_id
 *            博客编号
 */
function getTitleByPostID(post_id) {
	var title = '';
	$.ajax({
		url : 'getposttitle.do?post_id=' + post_id,
		async : false,
		success : function(data) {
			title = data;
		},
		error : function(err) {
			console.log("根据博客编号获取博客标题失败！" + err);
			title = "未命名";
		}
	});
	return title;
}

/**
 * 使用ajax技术获取topN的博主。
 * 
 * @param n
 */
function topNAuthors(n) {
	if (n <= 0) {
		alert(n + "应该为大于0的正整数！");
		return;
	} else {
		var source = [];
		var topnpath = $("#topnpath").val();
//		console.log(topnpath + 'notice.do?flag=post&topn=' + n);
		$.ajax({
			url : 'notice.do?flag=author&topn=' + n,
			dataType : 'json',
			// async: false,
			success : function(data) {
				for (var index = 0; index < data.length; index++) {
					var obj = {
						// name: data[index].post_id,
						// value: data[index].watches
						name : data[index].name,
						value : data[index].post_number
					}
					source.push(obj);
					// postids.push(data[index].post_id);
					// watches.push(data[index].watches);
					// percents.push(data[index].percent);
				}
				// 开始准备填充数据
				var mychart = echarts.init(document
						.getElementById("topnauthors"));
				var option = {
					backgroundColor : '#fff8f8',

					title : {
						text : '发表文章数Top' + n + "博主",
						left : 'center',
						top : 20,
						textStyle : {
							color : 'red'
						}
					},

					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					},

					visualMap : {
						show : false,
						min : 80,
						max : 600,
						inRange : {
							colorLightness : [ 0, 1 ]
						}
					},
					series : [ {
						name : '访问来源',
						type : 'pie',
						radius : '55%',
						center : [ '50%', '50%' ],
						data :
						// [
						// {value:335, name:'直接访问'},
						// {value:310, name:'邮件营销'},
						// {value:274, name:'联盟广告'},
						// {value:235, name:'视频广告'},
						// {value:400, name:'搜索引擎'}
						// ]
						source.sort(function(a, b) {
							return a.value - b.value;
						}),
						roseType : 'radius',
						label : {
							normal : {
								textStyle : {
									color : 'rgba(255, 255, 255, 0.3)'
								}
							}
						},
						labelLine : {
							normal : {
								lineStyle : {
									color : 'rgba(255, 255, 255, 0.3)'
								},
								smooth : 0.2,
								length : 10,
								length2 : 20
							}
						},
						itemStyle : {
							normal : {
								color : '#321234',
								shadowBlur : 200,
								shadowColor : 'rgba(0, 255, 0, 0.2)'
							}
						},

						animationType : 'scale',
						animationEasing : 'elasticOut',
						animationDelay : function(idx) {
							return Math.random() * 5000;
						}
					} ]
				};

				mychart.setOption(option);
			},
			error : function(err) {
				alert(err);
				console.log(err);
			}
		});
	}

}

function topNPosts(n) {
	if (n <= 0) {
		alert(n + "应该为大于0的正整数！");
		return;
	} else {
		var source = [];
//		var topnpath = $("#topnpath").val();
//		alert(topnpath + 'notice.do?flag=post&topn=' + n);
		$.ajax({
			url : 'notice.do?flag=post&topn=' + n,

			dataType : 'json',
			// async: false,
			success : function(data) {
				for (var index = 0; index < data.length; index++) {
					var obj = {
						name : getTitleByPostID(data[index].post_id),
						value : data[index].watches
					}
					source.push(obj);
					// postids.push(data[index].post_id);
					// watches.push(data[index].watches);
					// percents.push(data[index].percent);
				}
				// 开始准备填充数据
				var mychart2 = echarts.init(document
						.getElementById("topnposts"));
				var option = {
					backgroundColor : '#2c343c',

					title : {
						text : '博客浏览量Top' + n + "文章",
						left : 'center',
						top : 20,
						textStyle : {
							color : '#ccc'
						}
					},

					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					},

					visualMap : {
						show : false,
						min : 80,
						max : 600,
						inRange : {
							colorLightness : [ 0, 1 ]
						}
					},
					series : [ {
						name : '访问来源',
						type : 'pie',
						radius : '55%',
						center : [ '50%', '50%' ],
						data :
						// [
						// {value:335, name:'直接访问'},
						// {value:310, name:'邮件营销'},
						// {value:274, name:'联盟广告'},
						// {value:235, name:'视频广告'},
						// {value:400, name:'搜索引擎'}
						// ]
						source.sort(function(a, b) {
							return a.value - b.value;
						}),
						roseType : 'radius',
						label : {
							normal : {
								textStyle : {
									color : 'rgba(255, 255, 255, 0.3)'
								}
							}
						},
						labelLine : {
							normal : {
								lineStyle : {
									color : 'rgba(255, 255, 255, 0.3)'
								},
								smooth : 0.2,
								length : 10,
								length2 : 20
							}
						},
						itemStyle : {
							normal : {
								color : '#321234',
								shadowBlur : 200,
								shadowColor : 'rgba(0, 255, 0, 0.2)'
							}
						},

						animationType : 'scale',
						animationEasing : 'elasticOut',
						animationDelay : function(idx) {
							return Math.random() * 5000;
						}
					} ]
				};

				mychart2.setOption(option);
			},
			error : function(err) {
				alert(err);
				console.log(err);
			}
		});
	}

}

/**
 * 文档加载完毕即可渲染图形。
 */
$(document).ready(function() {
	topNAuthors(3);
	topNPosts(10);
});