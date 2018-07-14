<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%
    HttpSession s = request.getSession();     
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script>
function load()
{   
	var status = "<%= session.getAttribute("status")%>";
	if(status!="null"){
	alert(status);
	}
    <%s.removeAttribute("status");%>
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登陆</title>
<link rel="stylesheet" href="css/log.css">
<!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

</head>
<body onload="load()">      
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
        <td><form action="login.spring" class="login" method="post">
      <h1>登 陆</h1>
      <input type="email" name="email" class="login-input" placeholder="用户账户" autofocus>
      <input type="password" name="password" class="login-input" placeholder="密码输入">
      <input type="submit" value="登陆" class="login-submit">
      <p class="login-help"><a href="index.jsp">返回首页</a></p>
</form></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>

</body>
</html>
