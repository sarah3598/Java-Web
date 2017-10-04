$(document).ready(function(){
	$("#navBar li").removeClass("active");
	$("#navBar li#nav-friends").addClass("active");
	$(".publish button:first").click(function(){
		$(this).siblings("textarea").val("");
	});
	var obj = $(".article-bottom p");
	toggle(obj,"heart",null,"点赞","取消");
})