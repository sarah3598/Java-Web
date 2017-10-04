$(document).ready(function(){
	$("#navBar li").removeClass("active");
	$("#selfList li").removeClass("active");
	$("#selfList li#nav-letter").addClass("active");
	$(".nav-tabs>li").click(function(){
		var className = $(this).find("a").attr("value");
		$(this).addClass("active")
		       .siblings().removeClass("active");
		$("."+className).show("normal")
				   .siblings("div").hide("normal");
	});
});