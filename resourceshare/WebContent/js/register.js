// JavaScript Document
// 注册js


var code ;					//在全局定义验证码	
var codes;					//转化成大写的验证码

function checkLogin1(){
	var check = document.getElementById("register1").style.display = "block";
	var check = document.getElementById("register2").style.display = "none";
	document.getElementById("aphoneregister").style.color = "#000";
	document.getElementById("phoneregister").style.borderBottom = "2px solid";
	document.getElementById("phoneregister").style.borderBottomColor = "#09F";
	document.getElementById("aemailregister").style.color = "";
	document.getElementById("emailregister").style.borderBottom = "";
	document.getElementById("emailregister").style.borderBottomColor = "";
}
function checkLogin2(){
	var check = document.getElementById("register1").style.display = "none";
	var check = document.getElementById("register2").style.display = "block";
	createCode2();
	document.getElementById("aemailregister").style.color = "#000";
	document.getElementById("emailregister").style.borderBottom = "2px solid";
	document.getElementById("emailregister").style.borderBottomColor = "#09F";
	document.getElementById("aphoneregister").style.color = "";
	document.getElementById("phoneregister").style.borderBottom = "";
	document.getElementById("phoneregister").style.borderBottomColor = "";
}

//为字符串增加trim方法，使用正则表达式截取空格
String.prototype.trim = function(){							
	return this.replace(/^\s*/,"").replace(/\s*$/,"");
}

/*------表单1------*/	


//验证码的函数
function createCode(){ 
	code = new Array();
	var codeLength = 4;			//验证码的长度
	var checkCode = document.getElementById("checkCode");
	checkCode.value = "";
	var selectChar = new Array(2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');
	for(var i=0; i<codeLength; i++){   
		var charIndex = Math.floor(Math.random()*58);   
		code += selectChar[charIndex];
	}
	if(code.length != codeLength){   
		createCode();
	}
	checkCode.value = code;
	codes = code.toUpperCase(); 						//将验证码转化成大写
}		

//手机用户名判断
function checkPhone(){
	var phonename = document.form1.phone.value;			//手机用户名
	var exreg = /^[1][0-9]{10}$/;
	if(phonename == null || phonename.trim() == ""){			
		document.getElementById("phonemsg").innerHTML = '<br/>&nbsp;请输入手机号码';
		document.form1.phone.style.borderColor = "red";
		document.getElementById("phonemsg").style.background = "#FFE1E1";
		document.getElementById("phonemsg").style.height = "38px";
		document.getElementById("phonemsg").style.border = "1px solid";
		document.getElementById("phonemsg").style.borderColor = "pink";	
		return false;					
	}else if(!exreg.test(phonename)){
		document.getElementById("phonemsg").innerHTML = '<br/>手机号码格式错误，请输入正确的手机号码';
		document.form1.phone.style.borderColor = "red";
		document.getElementById("phonemsg").style.background = "#FFE1E1";
		document.getElementById("phonemsg").style.height = "55px"
		document.getElementById("phonemsg").style.border = "1px solid";
		document.getElementById("phonemsg").style.borderColor = "pink";			
		return false;
	}else{
		document.getElementById("phonemsg").innerHTML = '<font color="green" size="2px"><br/>&nbsp;√</font>';
		document.form1.phone.style.borderColor = "";
		document.getElementById("phonemsg").style.background = "";
		document.getElementById("phonemsg").style.border = "";
		return true;
	}
}

//密码判断
function checkPwd(){
	var pwd = document.form1.pwd.value;					//密码
	var exreg = /^[\x21-\x7E]{6,20}$/;					//正则表达式
	if(pwd == null || pwd.trim() == ""){			
		document.getElementById("pwdmsg").innerHTML = '<br/>&nbsp;请输入密码';
		document.form1.pwd.style.borderColor = "red";
		document.getElementById("pwdmsg").style.background = "#FFE1E1";
		document.getElementById("pwdmsg").style.height = "38px";	
		document.getElementById("pwdmsg").style.border = "1px solid";
		document.getElementById("pwdmsg").style.borderColor = "pink";		
		return false;
	}else if(!exreg.test(pwd)){
		document.getElementById("pwdmsg").innerHTML = '<br/>6-20个字符，建议由数字和符合两种以上组合';
		document.form1.pwd.style.borderColor = "red";
		document.getElementById("pwdmsg").style.background = "#FFE1E1";
		document.getElementById("pwdmsg").style.height = "55px"
		document.getElementById("pwdmsg").style.border = "1px solid";
		document.getElementById("pwdmsg").style.borderColor = "pink";		
		return false;
	}else{
		document.getElementById("pwdmsg").innerHTML = '<font color="green" size="2px"><br/>&nbsp;√</font>';
		document.form1.pwd.style.borderColor = "";
		document.getElementById("pwdmsg").style.background = "";
		document.getElementById("pwdmsg").style.border = "";
		return true;
	}
}

//再次确认密码
function checkpwd2(){         
	var pwd2 = document.form1.pwd2.value;
	var pwd = document.form1.pwd.value;
	if(pwd2 == null || pwd2.trim() == ""){			
		document.getElementById("pwd2msg").innerHTML = '<br/>&nbsp;请再次输入密码';
		document.form1.pwd2.style.borderColor = "red";
		document.getElementById("pwd2msg").style.background = "#FFE1E1";
		document.getElementById("pwd2msg").style.height = "38px";
		document.getElementById("pwd2msg").style.borderColor = "red";
		document.getElementById("pwd2msg").style.border = "1px solid";
		document.getElementById("pwd2msg").style.borderColor = "pink";	
		return false;
	}else if(pwd != pwd2 || pwd2 == ""){
		document.getElementById("pwd2msg").innerHTML = '<br/>您输入的两次密码不一致，请再次确认';
		document.form1.pwd2.style.borderColor = "red";
		document.getElementById("pwd2msg").style.background = "#FFE1E1";
		document.getElementById("pwd2msg").style.borderColor = "red";	
		document.getElementById("pwd2msg").style.height = "55px";
		document.getElementById("pwd2msg").style.border = "1px solid";
		document.getElementById("pwd2msg").style.borderColor = "pink";		
		return false;
	}else{
		document.getElementById("pwd2msg").innerHTML = '<font color="green" size="2px"><br/>&nbsp;√</font>';
		document.form1.pwd2.style.borderColor = "";
		document.getElementById("pwd2msg").style.background = "";
		document.getElementById("pwd2msg").style.border = "";
		return true;
	}
}

//判断验证码
function validateCode(){
	var inputCode = document.getElementById("yzm").value.toUpperCase();	//将输入的验证码转换成大写。
	if(inputCode.length <= 0){ 								//判断验证码等于空时输出下面语句
		document.getElementById("yzmmsg").innerHTML = '<br/>&nbsp;请输入验证码'; 
		document.form1.yzm.style.borderColor = "red";
		document.getElementById("yzmmsg").style.background = "#FFE1E1";
		document.getElementById("yzmmsg").style.height = "38px"; 
		document.getElementById("yzmmsg").style.border = "1px solid";
		document.getElementById("yzmmsg").style.borderColor = "pink";	
		return false;
	}else if(inputCode != codes){ 							//判断输入的验证码不等于验证码时输出下面语句
		document.getElementById("yzmmsg").innerHTML = '<br/>&nbsp;您输入验证码有误，请重新输入';  
		document.form1.yzm.style.borderColor = "red";
		document.getElementById("yzmmsg").style.background = "#FFE1E1";     
		createCode();  
		document.getElementById("yzmmsg").style.height = "38px";
		document.getElementById("yzmmsg").style.border = "1px solid";
		document.getElementById("yzmmsg").style.borderColor = "pink";	
		return false;
	}else{													
		document.getElementById("yzmmsg").innerHTML = "";
		document.form1.yzm.style.borderColor = "";
		document.getElementById("yzmmsg").style.background = ""; 
		document.getElementById("yzmmsg").style.border = "";
		return true;
	}
}

//协议复选框
function checkbox(){
	var checkbox = document.getElementById("protocol").checked; 	//同意协议
	if(!checkbox){
		document.getElementById("protocolmsg").innerHTML = '<br/>&nbsp;请您勾选同意协议'; 
		document.getElementById("protocolmsg").style.background = "#FFE1E1"; 
		document.getElementById("submit").style.background = "gray";
		document.getElementById("protocolmsg").style.border = "1px solid";
		document.getElementById("protocolmsg").style.borderColor = "pink";	 
		return false;
	}else{
		document.getElementById("protocolmsg").innerHTML = "";
		document.getElementById("protocolmsg").style.background = ""; 
		document.getElementById("submit").style.background = "#09F";
		document.getElementById("protocolmsg").style.border = ""; 
		return true;
	}
}

//获取焦点改变手机注册的样式
function getsFocus(){
	document.getElementById("aphoneregister").style.color = "#000";
	document.getElementById("phoneregister").style.borderBottom = "2px solid";
	document.getElementById("phoneregister").style.borderBottomColor = "#09F";
}

//集合验证码和改变手机注册样式
function twoMethod(){
	createCode() || getsFocus();
}

//集合所有函数的方法
function form1All(){
	return checkPhone() && checkPwd() && checkpwd2() && validateCode() && checkbox();
	document.form1.submit();
}

//集合所有函数的方法
function form1All2(){
	return checkPhone() || checkPwd() || checkpwd2() || validateCode() || checkbox() && getsFocus();
	//document.form1.submit();		//作用不详
}

/*------表单2------*/


//验证码的函数
function createCode2(){ 
	code = new Array();
	var codeLength = 4;			//验证码的长度
	var checkCode = document.getElementById("checkCode2");
	checkCode.value = "";
	var selectChar = new Array(2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');
	for(var i=0; i<codeLength; i++){   
		var charIndex = Math.floor(Math.random()*58);   
		code += selectChar[charIndex];
	}
	if(code.length != codeLength){   
		createCode();
	}
	checkCode.value = code;
	codes = code.toUpperCase(); 						//将验证码转化成大写
}				

//邮箱用户名判断
function checkEMail(){
	var emailname = document.form2.email.value;			
	var exreg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
	if(emailname == null || emailname.trim() == ""){			
		document.getElementById("emailmsg").innerHTML = '<font color="red" size="2px"><br/>&nbsp;请输入您的邮箱</font>';
		document.form2.email.style.borderColor = "red";
		document.getElementById("emailmsg").style.background = "#FFE1E1";
		document.getElementById("emailmsg").style.height = "38px";
		document.getElementById("emailmsg").style.border = "1px solid";
		document.getElementById("emailmsg").style.borderColor = "pink";	
		return false;					
	}else if(!exreg.test(emailname)){
		document.getElementById("emailmsg").innerHTML = '<font color="red" size="2px"><br/>邮箱格式错误，请输入您正确的邮箱</font>';
		document.form2.email.style.borderColor = "red";
		document.getElementById("emailmsg").style.background = "#FFE1E1";
		document.getElementById("emailmsg").style.height = "55px"
		document.getElementById("emailmsg").style.border = "1px solid";
		document.getElementById("emailmsg").style.borderColor = "pink";			
		return false;
	}else{
		document.getElementById("emailmsg").innerHTML = '<font color=green size=2px><br/>&nbsp;√</font>';
		document.form2.email.style.borderColor = "";
		document.getElementById("emailmsg").style.background = "";
		document.getElementById("emailmsg").style.border = "";
		return true;
	}
}

//密码判断
function checkEMailPwd(){
	var pwd = document.form2.pwd.value;					//密码
	var exreg = /^[\x21-\x7E]{6,20}$/;					//正则表达式
	if(pwd == null || pwd.trim() == ""){			
		document.getElementById("pwdmsg2").innerHTML = '<font color="red" size="2px"><br/>&nbsp;请输入密码</font>';
		document.form2.pwd.style.borderColor = "red";
		document.getElementById("pwdmsg2").style.background = "#FFE1E1";
		document.getElementById("pwdmsg2").style.height = "38px";	
		document.getElementById("pwdmsg2").style.border = "1px solid";
		document.getElementById("pwdmsg2").style.borderColor = "pink";		
		return false;
	}else if(!exreg.test(pwd)){
		document.getElementById("pwdmsg2").innerHTML = '<font color="red" size="2px"><br/>6-20个字符，建议由数字和符合两种以上组合</font>';
		document.form2.pwd.style.borderColor = "red";
		document.getElementById("pwdmsg2").style.background = "#FFE1E1";
		document.getElementById("pwdmsg2").style.height = "55px"
		document.getElementById("pwdmsg2").style.border = "1px solid";
		document.getElementById("pwdmsg2").style.borderColor = "pink";		
		return false;
	}else{
		document.getElementById("pwdmsg2").innerHTML = '<font color="green" size="2px"><br/>&nbsp;√</font>';
		document.form2.pwd.style.borderColor = "";
		document.getElementById("pwdmsg2").style.background = "";
		document.getElementById("pwdmsg2").style.border = "";
		return true;
	}
}

//再次确认密码
function checkEMailpwd2(){         
	var pwd2 = document.form2.pwd2.value;
	var pwd = document.form2.pwd.value;
	if(pwd2 == null || pwd2.trim() == ""){			
		document.getElementById("pwd2msg2").innerHTML = '<font color="red" size="2px"><br/>&nbsp;请再次输入密码</font>';
		document.form2.pwd2.style.borderColor = "red";
		document.getElementById("pwd2msg2").style.background = "#FFE1E1";
		document.getElementById("pwd2msg2").style.height = "38px";
		document.getElementById("pwd2msg2").style.borderColor = "red";
		document.getElementById("pwd2msg2").style.border = "1px solid";
		document.getElementById("pwd2msg2").style.borderColor = "pink";	
		return false;
	}else if(pwd != pwd2 || pwd2 == ""){
		document.getElementById("pwd2msg2").innerHTML = '<font color="red" size="2px"><br/>您输入的两次密码不一致，请再次确认</font>';
		document.form2.pwd2.style.borderColor = "red";
		document.getElementById("pwd2msg2").style.background = "#FFE1E1";
		document.getElementById("pwd2msg2").style.borderColor = "red";	
		document.getElementById("pwd2msg2").style.height = "55px";
		document.getElementById("pwd2msg2").style.border = "1px solid";
		document.getElementById("pwd2msg2").style.borderColor = "pink";		
		return false;
	}else{
		document.getElementById("pwd2msg2").innerHTML = '<font color="green" size="2px"><br/>&nbsp;√</font>';
		document.form2.pwd2.style.borderColor = "";
		document.getElementById("pwd2msg2").style.background = "";
		document.getElementById("pwd2msg2").style.border = "";
		return true;
	}
}

//判断验证码
function ckeckEMailCode(){
	var inputCode = document.getElementById("yzm2").value.toUpperCase();	//将输入的验证码转换成大写。
	if(inputCode.length <= 0){ 								//判断验证码等于空时输出下面语句
		document.getElementById("yzmmsg2").innerHTML = '<font color="red" size="2px"><br/>&nbsp;请输入验证码</font>'; 
		document.form2.yzm2.style.borderColor = "red";
		document.getElementById("yzmmsg2").style.background = "#FFE1E1";
		document.getElementById("yzmmsg2").style.height = "38px"; 
		document.getElementById("yzmmsg2").style.border = "1px solid";
		document.getElementById("yzmmsg2").style.borderColor = "pink";	
		return false;
	}else if(inputCode != codes){ 							//判断输入的验证码不等于验证码时输出下面语句
		document.getElementById("yzmmsg2").innerHTML = '<font color="red" size="2px"><br/>&nbsp;您输入验证码有误，请重新输入</font>';  
		document.form2.yzm2.style.borderColor = "red";
		document.getElementById("yzmmsg2").style.background = "#FFE1E1";     
		document.getElementById("yzmmsg2").style.height = "38px";
		document.getElementById("yzmmsg2").style.border = "1px solid";
		document.getElementById("yzmmsg2").style.borderColor = "pink";	
		createCode();  
		return false;
	}else{													
		document.getElementById("yzmmsg2").innerHTML = "";
		document.form2.yzm2.style.borderColor = "";
		document.getElementById("yzmmsg2").style.background = ""; 
		document.getElementById("yzmmsg2").style.border = "";
		return true;
	}
}

//协议复选框
function checkboxEMail(){
	var checkbox = document.getElementById("protoco2").checked; 	//同意协议
	if(!checkbox){
		document.getElementById("protocolmsg2").innerHTML = '<font color="red" size="2px"><br/>&nbsp;请您勾选同意协议</font>'; 
		document.getElementById("protocolmsg2").style.background = "#FFE1E1"; 
		document.getElementById("submit2").style.background = "gray";
		document.getElementById("protocolmsg2").style.border = "1px solid";
		document.getElementById("protocolmsg2").style.borderColor = "pink";	 
		return false;
	}else{
		document.getElementById("protocolmsg2").innerHTML = "";
		document.getElementById("protocolmsg2").style.background = ""; 
		document.getElementById("submit2").style.background = "#09F";
		document.getElementById("protocolmsg2").style.border = ""; 
		return true;
	}
}

//集合所有函数的方法
function form2All(){
	return checkEMail() && checkEMailPwd() && checkEMailpwd2() && ckeckEMailCode() && checkboxEMail();
	document.form2.submit();
}

//集合所有函数的方法
function form2All2(){
	return checkEMail() || checkEMailPwd() || checkEMailpwd2() || ckeckEMailCode() || checkboxEMail();
	//document.form2.submit();
}