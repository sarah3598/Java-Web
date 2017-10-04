$(function(){
	$(".avator input").change(function(){
		if (!this.files[0].name.match(/.jpg|.gif|.png|.bmp/i)) {
			alert("上传的图片格式不正确，请重新选择");
		}else{
				path=URL.createObjectURL(this.files[0]);
				$(".avator img").attr("src",path);
			
		}
	});
})