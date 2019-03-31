// JavaScript Document


//为字符串增加trim方法，使用正则表达式截取空格
String.prototype.trim = function(){							
	return this.replace(/^\s*/,"").replace(/\s*$/,"");
}

var get = function(id){                           
	return document.getElementById(id);            
}

//用户名判断
var userName = function(){
	var username = get("username").value;				//用户名
	var exreg = /(^[A-Za-z0-9]{6,16}$)|(^[\u4E00-\u9FA5]{2,8}$)/;
	if(username == null || username.trim() == ""){			
		get("usernamemsg").innerHTML = '<font color="red" size="2px">请输入用户名</font>';
		return false;					
	}else if(!exreg.test(username)){
		get("usernamemsg").innerHTML = '<font color="red" size="2px">6-16个字符或2-8个中文</font>';			
		return false;
	}else{
		get("usernamemsg").innerHTML = '<font color="green" size="2px">√</font>';
		return true;
	}
}

//用户名判断
var Admin = function(){
	var username = get("admin").value;				//用户名
	var exreg = /(^[A-Za-z0-9]{6,16}$)|(^[\u4E00-\u9FA5]{2,8}$)/;
	if(username == null || username.trim() == ""){			
		get("usernamemsg").innerHTML = '<font color="red" size="2px">请输入管理员用户名</font>';
		return false;					
	}else if(!exreg.test(username)){
		get("usernamemsg").innerHTML = '<font color="red" size="2px">6-16个字符或2-8个中文</font>';			
		return false;
	}else{
		get("usernamemsg").innerHTML = '<font color="green" size="2px">√</font>';
		return true;
	}
}

//原密码判断
var oldPwd = function(){
	var oldPwd = get("oldpwd").value;						//密码
	if(oldPwd == null || oldPwd.trim() == ""){			
		get("oldpwdmsg").innerHTML = '<font color="red" size="2px">请输入原密码</font>';	
		return false;
	}else{
		get("oldpwdmsg").innerHTML = '<font color="green" size="2px">√</font>';
		return true;
	}
}


//密码判断
var Pwd = function(){
	var pwd = get("pwd").value;							//密码
	var exreg = /^[\x21-\x7E]{6,20}$/;					//正则表达式
	if(pwd == null || pwd.trim() == ""){			
		get("pwdmsg").innerHTML = '<font color="red" size="2px">请输入密码</font>';	
		return false;
	}else if(!exreg.test(pwd)){
		get("pwdmsg").innerHTML = '<font color="red" size="2px">6-20个字符，建议由数字和符合两种以上组合</font>';	
		return false;
	}else{
		get("pwdmsg").innerHTML = '<font color="green" size="2px">√</font>';
		return true;
	}
}

//再次确认密码
var rPwd = function(){     
	var pwd2 = get("rpwd").value;
	var pwd = get("pwd").value;	
	if(pwd2 == null || pwd2.trim() == ""){			
		get("rpwdmsg").innerHTML = '<font color="red" size="2px">请再次输入密码</font>';
		return false;
	}else if(pwd != pwd2 || pwd2 == ""){
		get("rpwdmsg").innerHTML = '<font color="red" size="2px">您输入的两次密码不一致，请再次确认</font>';
		return false;
	}else{
		get("rpwdmsg").innerHTML = '<font color="green" size="2px">√</font>';
		return true;
	}
}

function All(){
	return userName() && Pwd() && rPwd();
}

function All2(){
	return Admin() && Pwd() && rPwd();
}

function All3(){
	return oldPwd() && Pwd() && rPwd();
}

get("submit").onclick = function(){
	window.history.go(-1);
}

function loadfile(obj){
	obj.select();//选中
	document.getElementById("filename").value=document.selection.createRange().text;//获取选中的文本
}