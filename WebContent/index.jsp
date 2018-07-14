<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%
    HttpSession s = request.getSession();     
%>
<html class="no-js" lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="keywords" content="股票，股票分析，股票回测，股票市场温度计，股票比较"/>
<title>股票分析系统Quantour</title>
<link rel="alternate icon" type="image/png" href="/favicon.png">
<link rel="stylesheet" href="css/amazeui.min.css">
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/searchstock.js"></script>
<link rel="Stylesheet" type="text/css" href="css/loginDialog.css" />
<script type="text/javascript" src="js/jquery.bigautocomplete.js"></script> 
<link rel="stylesheet" href="css/jquery.bigautocomplete.css" type="text/css" />
</head>


<body style="overflow-x: hidden">
<center>
   <header class="m-hd">
    <section data-am-sticky class="am-show-md-up">
      <div class="am-container"> <a href="index.jsp" class="m-logo" rel="nofollow"><i class="am-icon-spinner"></i> Quantour</a>
        <nav>
          <ul class="m-nav am-nav am-nav-pills am-fr">
<% if (s.getAttribute("email")==null) { %>
      <li><a href="register.jsp" rel="nofollow" style="color:red">注册</a></li>
      <li><a href="login.jsp" rel="nofollow" style="color:red"s>登陆</a></li> 
<% } else { %>
      <li style="color:	#DC143C; font-size:24px"><%="当前用户："+s.getAttribute("email")%></li>
      <li><form action="logout.spring">
       <button name="logout" id="logout">注销</button>
      </form>
      </li>
<% } %>
</ul>
        </nav>
      </div>
    </section>
  </header>  
  <div data-am-widget="slider" class="am-slider am-slider-i2" data-am-flexslider="{controlNav:false}">
    <ul class="am-slides">
      <li class="am-slider-images">
        <div class="am-container am-slider-desc">
          <div class="search"><img name="index_logo" src="images/index_logo.png" width="480" height="86" border="0" id="index_logo" alt="" />          
              <form name="toSubmit" action="searchStock.spring"  onSubmit="return check();">
              <table><tr><td>
                <input type="text" id="tt" name="stock_info" class="text" placeholder="请输入您要查找的股票代码或简称" style="width:605px">
                </td>
                <td>
                <input type="submit" value="搜 索" class="submit" id="search_stock_btn">
                </td>
               </table>
              </form>
          </div>
        </div>
      </li>
    </ul>
  </div>
  <div class="m-services m-home-box">
    <section class="am-container">
      <hgroup class="am-animation-slide-bottom am-animation-delay-1" data-am-scrollspy="{animation:'slide-bottom', delay: 100}">
        <h2>更好的股票分析</h2>
        <p>为您实现使用量化交易算法的股票回溯和演算系统，深入分析股票行情，模拟和比较量化模型</p>
      </hgroup>
      <table width="1950px" border="0">
        <tr>
          <td><ul class="am-avg-sm-2 am-avg-md-3 am-avg-lg-6 am-thumbnails">
              <li class="am-animation-slide-top" data-am-scrollspy="{animation:'slide-top', delay: 0}"> <a href="temperature.jsp"><img src="images/areabg1.png" width="180" height="90" /></a> </li>
              <li class="am-animation-slide-top" data-am-scrollspy="{animation:'slide-top', delay: 0}"> <a href="allstock.html"  id="suoyou"><img src="images/areabg2.png" width="180" height="90" /></a> </li>
              <!--<li class="am-animation-slide-top am-animation-delay-1" data-am-scrollspy="{animation:'slide-top', delay: 100}"> <a href="strategy.jsp"><img src="images/areabg3.png" width="180" height="90" /></a> </li>-->
               <li class="am-animation-slide-top am-animation-delay-1" data-am-scrollspy="{animation:'slide-top', delay: 100}"> <a href="stockcompare.jsp"><img src="images/areabg4.png" width="180" height="90" /></a> </li>  
            </ul>
            <ul class="am-avg-sm-2 am-avg-md-3 am-avg-lg-6 am-thumbnails">
              <li class="am-animation-slide-top am-animation-delay-1" data-am-scrollspy="{animation:'slide-top', delay: 100}"> <a href="longhu.jsp"  id="longhubang"><img src="images/areabg5.png" width="180" height="90" /></a> </li>
              <li class="am-animation-slide-top am-animation-delay-1" data-am-scrollspy="{animation:'slide-top', delay: 100}"> <a href="Forecast.jsp"  id="yuce"><img src="images/areabg6.png" width="180" height="90"  /></a> </li>

            </ul>
            
<script type="text/javascript">
function check(){
	var strs= new Array(); 
	var stock=document.getElementById('tt').value;
	strs=stock.split("("); 
	if(isNull(stock)){
		alert("不可为空");
		return false;
	}
	else if(strs.length!=2){
		alert("输入有误");
		return false;
	}
	else if(strs[1].length!=7){
		alert("输入有误");
		return false;
	}
	else if(strs[0].length<2){
		alert("输入有误");
		return false;
	}
	else{
		return true;
	}
}
function isNull(str){
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}
</script>
<script type="text/javascript">
var yuce = document.getElementById('yuce');
var longhubang= document.getElementById('longhubang');
var suoyou= document.getElementById('suoyou');
yuce.onclick = function(e1) {
	<% if (s.getAttribute("email")==null) { %>
		alert('登录后方可查看，谢谢');
		return false;
	<%}%>
}
longhubang.onclick = function(e1) {
	<% if (s.getAttribute("email")==null) { %>
		alert('登录后方可查看，谢谢');
		return false;
	<%}%>
}
suoyou.onclick = function(e1) {
	<% if (s.getAttribute("email")==null) { %>
		alert('登录后方可查看，谢谢');
		return false;
	<%}%>
}
</script>

            </td>
        </tr>
      </table>
    </section>
  </div>
  <!-- <footer class="m-footer" style="display: block;">
    <div class="m-footer-bottom"></div>
  </footer> -->
</center>
<script src="js/echo.min.js"></script> 
<script src="js/amazeui.min.js"></script> 

<script src='js/jquery.qrcode.min.js'></script> 
<script type="text/javascript">

//返回顶部
function imj2(){
	this.init();
}
imj2.prototype = {
	constructor: imj2,
	init: function(){
		this._initBackTop();
	},
	_initBackTop: function(){
		var $backTop = this.$backTop = $('<div class="m-top-cbbfixed">'+
						'<a class="m-top-weixin m-top-cbbtn"">'+
							'<span class="m-top-weixin-icon"></span><div></div>'+
						'</a>'+
						'<a class="m-top-go m-top-cbbtn">'+
							'<span class="m-top-goicon"></span>'+
						'</a>'+
					'</div>');
		$('body').append($backTop);

		$backTop.click(function(){
			$("html, body").animate({
				scrollTop: 0
			}, 120);
		});

		var timmer = null;
		$(window).bind("scroll",function() {
            var d = $(document).scrollTop(),
            e = $(window).height();
            0 < d ? $backTop.css("bottom", "10px") : $backTop.css("bottom", "-90px");
			clearTimeout(timmer);
			timmer = setTimeout(function() {
                clearTimeout(timmer)
            },100);
	   });
	}

}
var imj2 = new imj2();
//end返回顶部
</script>
</body>
</html>