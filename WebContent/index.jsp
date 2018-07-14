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
<style>

td{
border:none;/*这个是单元格，不给他要边框*/
}
</style>
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
  <div  style="position:absolute;left:500px;">
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
	<div style="position:absolute;left:200px;top:450px">
	<p style="color:red" align="left"><font size="5">新人必看</p>
	<table width="400px" class="table-body">
	<tr><td><font size="4"><a href="https://jingyan.baidu.com/article/148a1921830fa84d70c3b14b.html"  target="blank">新手怎么学炒股</a></td></tr>
	<tr><td><font size="4"><a href="https://jingyan.baidu.com/article/3a2f7c2e7fa77326afd6110d.html" target="blank">股票新手操作指南</a></td></tr>
    <tr><td><font size="4"><a href="http://zhengu.88889829.top/?gpmc-gpdm&gp=600519" target="blank">大数据诊股</a></td></tr>
    <tr><td><font size="4"><a href="http://bj.neofelis.wang/" target="blank">解密炒股</a></td></tr>
    <tr><td><font size="4"><a href="http://niugu.sxzctec027.cn/gp/208" target="blank">股票走势精准预测</a></td></tr>
    <tr><td><font size="4"><a href="http://gp.sxzctec060.cn/jzx/" target="blank">股票大盘走势图</a></td></tr>
    <tr><td><font size="4"><a href="http://stock.eastmoney.com/" target="blank">股票频道</a></td></tr>
    <tr><td><font size="4"><a href="http://stock.xinhua08.com/" target="blank">中国金融</a></td></tr>
    <tr><td><font size="4"><a href="http://stock.10jqka.com.cn/" target="blank">股票必读</a></td></tr>
    <tr><td><font size="4"><a href="http://finance.sina.com.cn/stock/" target="blank">新浪财经</a></td></tr>
	</table>
	</div>
	
	<div style="position:absolute;left:800px;top:450px">
	<p style="color:red" align="left"><font size="5">新人必看</p>
	<table width="400px" class="table-body">
	<tr><td><font size="4"><a href="http://stock.hexun.com/" target="blank">新闻速递</a></td></tr>
	<tr><td><font size="4"><a href="http://stock.stockstar.com/" target="blank">证券之星</a></td></tr>
    <tr><td><font size="4"><a href="http://qy.compass.cn/fenfen2.php?from=360_101_4039292066_3620390365_5384713275_1135771252" target="blank">中国股票信息网</a></td></tr>
    <tr><td><font size="4"><a href="http://www.qn43.cn/" target="blank">今日股票</a></td></tr>
    <tr><td><font size="4"><a href="http://qk.sxzctec006.cn/" target="blank">从入门到精通</a></td></tr>
    <tr><td><font size="4"><a href="http://zhengu.88889829.top/?gpmc-gpdm&gp=600519" target="blank">解读股票近期行情</a></td></tr>
    <tr><td><font size="4"> <a href="http://cl.sxzctec047.cn/" target="blank">新手推荐入股</a></td></tr>
    <tr><td><font size="4"> <a href="http://www.syb678.com/" target="blank">投资技术大讲堂</a></td></tr>
    <tr><td><font size="4"><a href="http://qk.sxzctec006.cn/" target="blank">龙头股推荐</a></td></tr>
    <tr><td><font size="4"><a href="http://vip.9111726.cn/" target="blank">散户必看</a></td></tr>
	</table>
	</div>
  
  <div style="position:absolute;left:200px;top:350px">
      <table width="1300px" border="0">
        <tr>
          <td><ul class="am-avg-sm-2 am-avg-md-3 am-avg-lg-6 am-thumbnails">
              <li class="am-animation-slide-top" data-am-scrollspy="{animation:'slide-top', delay: 0}"> <a href="temperature.jsp"><img src="images/areabg1.png" width="180" height="90" /></a> </li>
              <li class="am-animation-slide-top" data-am-scrollspy="{animation:'slide-top', delay: 0}"> <a href="allstock.html"  id="suoyou"><img src="images/areabg2.png" width="180" height="90" /></a> </li>
              <!--<li class="am-animation-slide-top am-animation-delay-1" data-am-scrollspy="{animation:'slide-top', delay: 100}"> <a href="strategy.jsp"><img src="images/areabg3.png" width="180" height="90" /></a> </li>-->
               <li class="am-animation-slide-top am-animation-delay-1" data-am-scrollspy="{animation:'slide-top', delay: 100}"> <a href="longhu.jsp"  id="longhubang"><img src="images/areabg5.png" width="180" height="90" /></a> </li>
              <li class="am-animation-slide-top am-animation-delay-1" data-am-scrollspy="{animation:'slide-top', delay: 100}"> <a href="Forecast.jsp"  id="yuce"><img src="images/areabg6.png" width="180" height="90"  /></a> </li> 
               <li class="am-animation-slide-top am-animation-delay-1" data-am-scrollspy="{animation:'slide-top', delay: 100}"> <a href="stockcompare.jsp"><img src="images/areabg4.png" width="180" height="90" /></a> </li>  
            </ul>
   </div>
   
   
            
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