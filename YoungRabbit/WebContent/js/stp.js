/**
	必须引用配套的 stp.css，不然提示上没效果
 */

/**
 var isArray = function (o) {
 return Object.prototype.toString.call(o) === '[object Array]';
 }
 */

/**
 * 统一提示显示 emsg: 提示的文本 cb: 提示的回调函数或执行的语句，在很多情况下使用，比如ajax执行成功后，显示提示后在执行的后续动作
 * 可以修改提示的停留的时间
 */
window.__warn_period = 3000;
ims_show_warn = function(emsg, cb) {
	var obj = document.getElementById("stpWarn");
	var txt = (typeof emsg == "string") ? emsg : emsg.emsg;
	if (!!!obj) {
		obj = document.createElement("div");
		obj.id = "stpWarn";
		//obj.innerHTML = "<div  style='margin-left:300px;margin-right:50px;width:auto;' class='modal-content'><i class='fa fa-exclamation-sign'></i><p class='warn_toast_content' id='stpWarn_text'>" + txt + "</p></div>";
		obj.innerHTML = "<div style='margin-left:300px;margin-right:50px;width:auto;' class='modal-content'><div class='modal-header' style='padding: 5px;'><h3 class='modal-title' style='color:red;'>提示</h3></div><div class='modal-body smart-form'><label class='label' ><i id='stpWarn_text' class='fa-fw fa fa-exclamation-circle fa-3x' style='color:red;'></i></label></div></div>";
		document.body.appendChild(obj);
		document.getElementById("stpWarn_text").innerHTML = txt;
	} else
		document.getElementById("stpWarn_text").innerHTML = txt;
	obj.style.display = "";

	setTimeout(function() {
		obj.style.display = "none";
		switch (typeof cb) {
		case "function":
			cb();
			break;
		case "string":
			(new Function(cb))();
			break;
		}
	}, __warn_period);
};

window.__warn_period = 3000;
show_msg_warn = function(id,emsg) {
	var obj = document.getElementById("stpWarn_text");
	var txt = (typeof emsg == "string") ? emsg : emsg.emsg;
	if (!!!obj) {
		document.getElementById(id).innerHTML="";
		obj = document.createElement("div");
		obj.id = "myAlert";		
		obj.innerHTML = "<div class='alert alert-danger'><a class='close' data-dismiss='alert'>&times;</a>	<strong id='stpWarn_text'></strong></div>";
		var o=document.getElementById(id);
		document.getElementById(id).appendChild(obj);
		document.getElementById("stpWarn_text").innerHTML = txt;
	} else
		document.getElementById("stpWarn_text").innerHTML = txt;
	
	setTimeout(function() {
		document.getElementById(id).innerHTML="";
	}, __warn_period);
};

/**
 * 同步加载的优化 opt.period opt.dto disable timeout
 */
ims_show_mask = function(opt) {
	var obj = document.getElementById('stpMask');
	if (!!!opt)
		opt = {
			period : 8000,
			dto : false
		};
	if (!!!obj) {
		obj = document.createElement("div");
		obj.id = "stpMask";
		obj.innerHTML = "<div class='loadmore'><i class='loading'></i></div>";
		document.body.appendChild(obj);
	}
	obj.style.display = "block";

	if (!opt.dto)
		window.__mask = setTimeout(function() {
			if (!!window.__mask) {
				clearTimeout(window.__mask);
				obj.style.display = "none";
				ims_show_warn("没有联网或网络故障！");
			}
		}, opt.period || 8000);
	else
		window.__mask = null;
	return true;
}
ims_hide_mask = function() {
	var obj = document.getElementById('stpMask');
	if (!!obj)
		obj.style.display = "none";
	if (!!window.__mask) {
		clearTimeout(window.__mask);
		window.__mask = null;
	}
}

/**
 * 异步提交 params is a=1&b=2&c=3 or null or form-object cb is callback function
 * when success fcb is callback function when fail {result:true, data:'...'/[]}
 * or {result:false, emsg:...}
 */
ims_submit_ajax = function(url, params, cb, fcb) {
	var req = window.XMLHttpRequest ? (new XMLHttpRequest())
			: (new ActiveXObject("Microsoft.XMLHTTP"));
	var done = function(req) {
		// var robj = JSON.parse(req.responseText);
		if (req.responseText.length > 0) {
			var robj = eval('(' + req.responseText + ')');
			if (typeof (robj) != "object") {
				return ims_show_warn("系统返回值格式不对！", fcb);
			}
			if (robj.result) { // 成功执行后
				switch (typeof cb) {
				case "function":
					//修改by lxp,兼容原来的功能
					if (robj.msg) {
						cb(robj.data,robj.msg);
					} else {
						cb(robj.data);
					}
					break;
				case "string":
					(new Function(cb))(robj.data);
					break;
				default:
					ims_show_warn("系统提交后台成功执行！");
					break;
				}
			} else//修改by lxp,<div id="emsg_id"></div>
				//ims_show_warn((!!robj.emsg) ? robj.emsg : robj.ecode, fcb);
				show_msg_warn("emsg_id", robj.emsg);
		} else
			/** else responseText == "" ?????? */
			//ims_show_warn("responseText为空，网络错误或执行错误！", fcb);
			show_msg_warn("emsg_id","responseText为空，网络错误或执行错误！");
	};

	req.onreadystatechange = function() {
		switch (req.readyState) {
		case 4:
			ims_hide_mask();
			if (req.status == 200)
				done(req);
			else {
				var text = req.statusText;
				if (req.status < 1)
					text = "网络错误！";
				ims_show_warn(text, fcb);
			}
			break;
		}
	};

	ims_show_mask({
		period : 8000,
		dto : true
	});
	switch (typeof (params)) {
	case "string":
		req.open('POST', url, true);
		req.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		req.send(params);
		break;

	case "object": // params 是form对象
		req.open('POST', url, true);
		// req.setRequestHeader("Content-Type", "multipart/form-data");
		req.send(params);
		break;

	default:
		req.open('GET', url, true);
		req.send();
		break;
	}

	return false;
}
