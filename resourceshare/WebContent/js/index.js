$(document).ready(function(){
	$(".media-body").find("button").click(function(){
		var button = $(this);
		if (button.hasClass("care")) {
			button.removeClass("care")
				  .addClass("delete")
				  .text("取关");
		}else{
			button.removeClass("delete")
				  .addClass("care")
				  .text("关注");

		}
	});
	$("form .btns button").click(function(){
		
	})
});