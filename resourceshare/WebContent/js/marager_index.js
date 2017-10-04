// JavaScript Document

var show = function(curr_id){
	var sub_menu = document.getElementById("manager"+curr_id);
	if(sub_menu.className == "show"){
		sub_menu.className = "hidden";
	}else{
		sub_menu.className = "show";
	}
}