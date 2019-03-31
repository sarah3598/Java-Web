$(document).ready(function(){
	var ol = $(".se_nl").find(".list-group");
	product(ol);
	var search = $(".friends-search"),
	    p = search.find("ul>li>p"),
	    option = $(".se_ce").find("ol li a"),
	    text = "";
	p.click(function(){
		$(this).siblings(".se_ce").removeClass("hidden");
	});
	option.click(function(){
		text = $(this).text();
		$(this).parents(".se_ce").addClass("hidden")
			   .siblings().children("span").text(text);

	});
	setInterval('autoScroll(".maqBox", ".list-group","35")',3000);
	$('#myModal').on('shown.bs.modal', function (event) {  
        var button = $(event.relatedTarget) // 触发事件的按钮
        var recipient = button.data('whatever') // 解析出data-whatever内容  
			$(".modal-footer").find("button.pull-left").click(function(){
				$(".modal-body").children("textarea").val("");
			});
			$(".modal-footer").find("button.pull-right").click(function(){
				//textarea的值
				var text = $('textarea[name="letter"]').val();
				//ajax

				//如果成功关闭模态框
				$(this).attr("data-dismiss","modal");
			});

    })

	$(".f-dynamic-item").each(function(){
		var ul = $(this).find(".f-dynamic-comment>ul");
		var lis = ul.children("li");
		if (lis.length>5) {
			$(this).find(".all-comment").show();
			ul.children("li:gt(4)").hide();
		}
		$(this).find(".all-comment").click(function(){
			if ($(this).hasClass("lookall")) {
				ul.children("li:gt(4)").hide(1000);
				$(this).removeClass("lookall")
					   .text("查看全部评论");
			}else{
				ul.children("li:gt(4)").show(1000);
				$(this).addClass("lookall")
					   .text("收起");
			}
		})
	});

})

function product(ol){
	var startAge = 18,
		endAge = 58
		html="";
	for (var i = startAge; i <= endAge; i++) {
		html += '<li><a fid='+i+' name="agefrom" class="selLi">'+i+'岁</a></li>';
	}
	ol.html(html);

}
function autoScroll(obj, ul,liHeight){
	$(obj).find(ul).animate({
			marginTop: '-=35'
		},1500,function(){
				var s = Math.abs(parseInt($(this).css("margin-top")));
				if(s >= liHeight){
					$(this).find("li:first").appendTo($(this));
					$(this).css("margin-top", 0);
				}
		});
}

function toggle(obj,className1,className2,text1,text2){
	className2 = className2 ? className2 : "";
	text1 = text1 ? text1 : "";
	text2 = text2 ? text2 : "";
	obj.click(function(){
		if($(this).hasClass(className1)){
			$(this).removeClass(className1)
				   .addClass(className2)
				   .find("span").text(text1);
		}else{
			$(this).removeClass(className2)
				   .addClass(className1)
				   .find("span").text(text2);
		}
	});

}