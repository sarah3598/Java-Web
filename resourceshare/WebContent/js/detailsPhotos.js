$(document).ready(function(){
	$("#navBar li").removeClass("active");
	$(".glyphicon-chevron-right").click(function(){
		var currentImg = $(this).siblings("ul").find(" li.active img");
		next(currentImg);
	});
	$(".glyphicon-chevron-left").click(function(){
		var currentImg = $(this).siblings("ul").find(" li.active img");
		prev(currentImg);
	});
	$(".glyphicon-arrow-right").click(function(){
		var bigImg = $(this).siblings("img");
		var src = bigImg.attr("src");
		var alt = bigImg.attr("alt");
		var currentImg = $(".imgs>li>img[alt="+alt+"]");
		next(currentImg);
	});
	$(".glyphicon-arrow-left").click(function(){
		var bigImg = $(this).siblings("img");
		var src = bigImg.attr("src");
		var alt = bigImg.attr("alt");
		var currentImg = $(".imgs>li>img[alt="+alt+"]");
		prev(currentImg);
	});
	$(".imgs li").click(function(){
		var img = $(this).find("img");
	});
	$(".big-img p").click(function(){
		if ($(this).hasClass("active")) {
			$(this).removeClass("active")
				   .find("span").text("点赞");
		}else{
			$(this).addClass("active")
				   .find("span").text("取消");
		}
	});

});

function next(obj){
	var ul = $(".imgs");
	var count = $(".imgs li").length;
	var alt = obj.attr("alt");
	var bigImg = $(".big-img img");
	var nextLi = obj.parent().next("li");
	var nextImg = nextLi.find("img");
	var src = nextImg.attr("src");
	if (alt >= count ) {
		alert("已是最后一张！");
	}else{
		alt++;
		obj.parent().removeClass("active");
		nextLi.addClass("active");
		bigImg.attr({src:src,alt:alt});
		if(alt>7){
			ul.css("margin-left",(7-alt)*140+"px");
		}

	}
}
function prev(obj){
	var ul = $(".imgs");
	var count = $(".imgs li").length;
	var alt = obj.attr("alt");
	var bigImg = $(".big-img img");
	var prevLi = obj.parent().prev("li");
	var prevImg = prevLi.find("img");
	var src = prevImg.attr("src");
	if (alt <= 1 ) {
		alert("已是第一张！");
	}else{
		alt--;
		obj.parent().removeClass("active");
		prevLi.addClass("active");
		bigImg.attr({src:src,alt:alt});
		if (alt%7 == 0) {
			ul.css("margin-left",-(alt/7-1)*980+"px");
		};
	}

}