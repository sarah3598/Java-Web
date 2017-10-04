var	photosArr = [];//照片显示页面删除的照片合集
$(document).ready(function(){
	$("#navBar li").removeClass("active");
	$(".delete-btn").click(function(){
		if ($(this).val()==0) {
			$(this).siblings("label").show();
			$(".thumbnail input").show();
			$(this).val("1");
			$(this).text("完成");
		}else{
			deletePhotos();
			$(this).siblings("label").hide();
			$(".thumbnail input").hide();
			$(this).val("0");		
			$(this).text("删除照片");
		}
	});
	$("#checkall").click(function(){
		$(".thumbnail input").prop('checked',$(this).prop('checked'));

	});
	$(".thumbnail input").click(function(){
		if ($(".thumbnail input").length == $(".thumbnail input:checked").length) {
			$("#checkall").prop("checked",true);
		}else{
			$("#checkall").prop("checked",false);
		}
	});
});
function deletePhotos(){
	$(".thumbnail").each(function(){
		var checkedBox = $(this).find("input:checked");
		if (checkedBox.length != 0) {
			photosArr.push(checkedBox.val());
		}
	});
}
