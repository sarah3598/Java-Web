$(document).ready(function(){
	$("#navBar li").removeClass("active");
	$("#selfList li").removeClass("active");
	$("#selfList li#nav-albums").addClass("active");

	$(".thumbnail").each(function(){
		$(this).find("a.pull-left").click(function(){
			var self = $(this);
			var r = confirm("确认删除该相册吗？");
			if (r) {
				//点击确定之后去数据库删除数据

				//返回结果如果删除成功了，删除页面上的记录
				self.closest(".col-md-3").detach();
			}
		});
	});
	$('#myModal').on('shown.bs.modal', function (event) {  
        var button = $(event.relatedTarget) // 触发事件的按钮
        var recipient = button.data('whatever') // 解析出data-whatever内容  
		$(".modal-footer").find("button.pull-left").click(function(){
			$(".modal-body").children("textarea").val("");
		});
		$(".modal-footer").find("button.pull-right").click(function(){
			var name = $('input[name="albumName"]').val();
			console.log(name);
			//ajax

			//如果成功，关闭模态框,并修改相册名
			$(this).attr("data-dismiss","modal");
			$(".thumbnail a[data-whatever="+recipient+"]").closest(".caption").find("h4").text(name);
		});
    })

});