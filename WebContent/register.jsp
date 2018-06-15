<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<% HttpSession s = request.getSession();%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<link rel="stylesheet" href="css/log.css">
<script>
function load()
{   
	var status = "<%= session.getAttribute("regstatus")%>";
	if(status!="null"){
	alert(status);
	}
    <%s.removeAttribute("regstatus");%>
}
</script>
</head>
<body onload=load()>
    <table width="1950px" border="0">
      <tr>
        <td>&nbsp;</td>
        <td colspan="3"><img src="images/transparent.png" width="500" height="200" /></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td width="682.5px">&nbsp;</td>
        <td colspan="3"><img src="images/index_logo.png" width="522" height="93"  /></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td><form action="register.spring" class="login" name=toregister onSubmit="return check();">
      <h1>注 册</h1>
      <input type="email" name="email" class="login-input" placeholder="用户账户" autofocus>
      <input type="password" name="password" class="login-input" placeholder="密码输入">
      <input type="password" name="password2" class="login-input" placeholder="确认密码">
      <input type="submit" value="确  认" class="login-submit">
      <p class="login-help"><a href="index.jsp">返回首页</a></p>
</form></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>
<script>
function check(){
	var email=document.toregister.email.value;
    var password=document.toregister.password.value;
    var password2=document.toregister.password2.value;
    if(isNull(email)||isNull(password)||isNull(password2)){
    	alert("别空着呀");
    	return false;
    }
    else if(password!=password2){
    	alert("密码不一致");
    	return false;
    }
    else if(!checkEmail(email)){
    	return false;
    }
    else if(!isNumberOrLetter(password)){
    	alert("密码由数字和英文组成");
    	return false;
    }
    else{
    	return true;
    }
}
function isNumberOrLetter( s ){//判断是否是数字或字母

	var regu = "^[0-9a-zA-Z]+$";

	var re = new RegExp(regu);

	if (re.test(s)) {

	return true;

	}else{

	return false;

	}

	}

function checkEmail(strEmail) {

	//var emailReg = /^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/;

	var emailReg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;

	if( emailReg.test(strEmail) ){

	return true;

	}else{

	alert("您输入的Email地址格式不正确！");

	return false;

	}
}
function isNull(str){
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}
</script>
</body>
</html>