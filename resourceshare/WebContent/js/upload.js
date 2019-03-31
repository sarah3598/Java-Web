var imgLength=0,
	deleteArr = [],//弹出框里面删除的照片合集
	tempFiles = {length:0};
$(document).ready(function(){
	$(".modal-footer .upload").hide();
	var input = $(".uploadBtn input");
	var html;
	if (typeof FileReader === 'undefined') {
		alert("抱歉，你的浏览器不支持预览！");
		input.attr("disabled","disabled");
	}else{
		input.change(readFile);
	}
	$(".modal-footer .upload").click(function(){
		//ajax
		
		//如果成功弹出上传成功，且弹出层消失
		alert("上传成功");
		$(this).attr("data-dismiss","modal");
	})
});
function readFile(){
	var files = this.files;
	for (var i = 0; i<files.length; i++) {
		tempFiles[tempFiles.length+i] = files[i];
	}
	for(var i=tempFiles.length;i<tempFiles.length+files.length;i++){
		if (!tempFiles[i].name.match(/.jpg|.gif|.png|.bmp/i)) {
			alert("上传的图片格式不正确，请重新选择");
		}else{

				path=URL.createObjectURL(tempFiles[i]);
				html = '<div class="col-xs-6 col-md-3" id="pic'+i+'"><div class="photosItem"><img src="'
				+path+'" alt=""><p><span class="glyphicon glyphicon-remove-circle" onclick="removeImg('+i+')"></span></p></div></div>';
				$(".uploadPhotos").append(html);
			
		}
	}
	tempFiles.length += files.length;
	// imgLength = this.files.length;
	if (this.files.length>0) {
		$(".modal-footer .upload").show();
	}
}
function removeImg(i){
	deleteArr.push(i);
	 $("#pic"+i).detach();
}

