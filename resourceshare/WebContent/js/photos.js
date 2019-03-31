$(document).ready(function(){
	$("#navBar li").removeClass("active");
	$("#navBar li#nav-photos").addClass("active");

	$(window).scroll(function(){  
	    if ($(document).height() - $(this).scrollTop() - $(this).height() == 0){
			loadMore();
		}
	});
	var obj = $(".photos-describe p");
	toggle(obj,"zan",null,"点赞","取消");
});

function loadMore(){
	$.ajax({
		url:"",
		dataType:"json",
		success:function(data){
			if (typeof data == object) {
/*			<div class="photos-item">
				<div class="photos-img">
					<img src="./images/1.jpg" alt="">
					<p>
						<span>彭和平</span>
						<span>女</span>
						<span>28岁</span>
					</p>
				</div>
				<div class="photos-describe">
					<p><i class="glyphicon glyphicon-thumbs-up"></i><span>点赞</span></p>
					<a><i class="
glyphicon glyphicon-envelope"></i>私信</a>
					<a href="info.html"><i class="glyphicon glyphicon-home"></i>个人主页</a>
				</div>
			</div>
*/
			}
		}
	});
}