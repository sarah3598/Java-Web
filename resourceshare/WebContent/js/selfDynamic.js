$(document).ready(function(){
	$(".f-dynamic-item").each(function(){
		$(this).find(".article-bottom a.pull-left").click(function(){
			var self = $(this);
			var r=confirm("确定要删除该动态吗？");
			if (r) {
				//点击确定之后去数据库删除数据


				//返回结果如果删除成功了，删除页面上的记录
				self.closest(".f-dynamic-item").detach();
			}
		});
	});
});