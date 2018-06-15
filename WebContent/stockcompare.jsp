<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%
    HttpSession s = request.getSession();     
%>
<html class="no-js" lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>股票比较</title>
<link rel="stylesheet" href="css/style.css">
<link rel="alternate icon" type="image/png" href="/favicon.png">
<link rel="stylesheet" href="css/amazeui.min.css">
<link rel="stylesheet" href="css/comp.css">
<script language="javascript" type="text/javascript" src="js/WdatePicker.js"></script>
<script src="js/searchstock.js"></script> 
</head>

<body>
<header class="m-hd">
  <section data-am-sticky class="am-show-md-up">
      <div class="am-container"> <a href="index.jsp" class="m-logo" rel="nofollow"><i class="am-icon-spinner"></i> Quantour</a>
        <nav>
          <ul class="m-nav am-nav am-nav-pills am-fr">
<% if (s.getAttribute("email")==null) { %>
      <li><a href="register.jsp" rel="nofollow">注册</a></li>
      <li><a href="login.jsp" rel="nofollow">登陆</a></li> 
<% } else { %>
      <li style="color:#FFF; font-size:24px"><%="当前用户："+s.getAttribute("email")%></li>
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
<div class="m-header-banner m-list-header" >
  <section class="am-container">
    <hgroup data-am-scrollspy="{animation:'slide-bottom', delay: 317}">
      <h2>股票比较</h2>
    </hgroup>
  </section>
  <table width="1872px" border="3" align="center" id="comta">
    <tr>
      <td><table width="1872px" border="0">
          <tr>
            <td width="300"><form id="form1" name="form1" method="get" action="">
                <input type="text" name="firststock" id="firststock" placeholder="股票名称/代码" autocomplete="off"/>
                <!-- <button name="insertstock" id="insertstock" onclick="addstock()">添加股票</button> -->
                <input type="button" name="insertstock" id="insertstock" value="添加股票"  onclick="addstock()"/>
                </form>
            </td>
            <td colspan="2" width="1200px">
            
            <div id="sc_sel_results_div">
            <ol class="sc_sel_results" id="sc_sel_results">		
                    </ol>
               </div> 
<script type="text/javascript">

 function addstock(){
	 var strs= new Array(); 
	 strs=$("#firststock")[0].value.split("(");
	 if($("#sc_sel_results li").length<=4){
		 if($("#firststock")[0].value==""){
			 alert("不可为空");
		 }
		 else if(strs.length==1&&strs[0].length!=6){
			 alert("格式错误");
		 }
		 else if(strs.length==2&&(strs[0].length<=1||strs[1].length!=7)){
			 alert("格式错误");
		 }
		 else{ 
	       var newstock='<li>'+ $("#firststock")[0].value+'<span onClick="delstock($(this))">X</span></li>';
		   var judge=0;
		   $("#sc_sel_results").find("li").each(function(i,n){
              if($(newstock).html()==$(n).html()){
		        judge=1;
                alert("请勿重复添加");}
           });
		   if(judge==0)
	        $("#sc_sel_results").append(newstock);
			var val=$("#comstocks").val();
			$("#comstocks").val(val+","+$("#firststock")[0].value);
	        $("#firststock")[0].value ="";
		 }
	 }
	 else{
		 alert("最多比较五支股票");
	 }
	 
 }
</script>
<script type="text/javascript">
  function delstock(obj){
	  obj.closest('li').remove();
	  $("#comstocks").val("");
	  var val="";
	  $('#sc_sel_results li').each(function(i,n){
           val+=$(n).text();
		   val+=",";
});
      $("#comstocks").val(val);
  }
</script>              
            </td>

            <!--  <td colspan="2" rowspan="2"><form id="form3" name="form3" method="post" >
                <button type="submit" id="search-btn" hidefocus="true" value="COMPARE"></button> -->

            <td rowspan="2"><form id="form3" name="form3" method="post" action="compare.spring">
             开始时间<input class="Wdate" name="begin" type="text" onClick="WdatePicker()">
            结束时间<input class="Wdate" name="end" type="text" onClick="WdatePicker()">
            <input name="comstocks" id="comstocks" type="text" style="display:none" value="">
                <input type="submit" id="search-btn" hidefocus="true" value="COMPARE"></input>

              </form></td>
          </tr>        
          <tr>
            <td colspan="2">&nbsp;</td>
            <td width="21%">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="5"><hr/></td>
          </tr>
          <tr>
            <td colspan="5" bgcolor="#FFFF99">
            <div id="main" style="height:400px"></div>
            </td>
          </tr>
          <tr>
            <td colspan="5">
            <table class="sc_data_table">
				<thead>
				<tr>
					<th>对比项</th>
					<th><a  target="_blank"> <%=(s.getAttribute("title[1]")==null)?"--":String.valueOf(s.getAttribute("title[1]")) %></a></th><td><%=(s.getAttribute("title[2]")==null)?"--":String.valueOf(s.getAttribute("title[2]")) %></td>
					<td><%=(s.getAttribute("title[3]")==null)?"--":String.valueOf(s.getAttribute("title[3]")) %></td><td><%=(s.getAttribute("title[4]")==null)?"--":String.valueOf(s.getAttribute("title[4]")) %></td><td><%=(s.getAttribute("title[5]")==null)?"--":String.valueOf(s.getAttribute("title[5]")) %></td>				</tr>
				</thead>
				<tbody>
				<tr class="odd">
					<th>最低价</th>
					<td class="cRed"><%=(s.getAttribute("title[1]")==null)?"--":String.valueOf(s.getAttribute("min[1]")) %></td><td><%=(s.getAttribute("title[2]")==null)?"--":String.valueOf(s.getAttribute("min[2]")) %></td>
					<td><%=(s.getAttribute("title[3]")==null)?"--":String.valueOf(s.getAttribute("min[3]")) %></td><td><%=(s.getAttribute("title[4]")==null)?"--":String.valueOf(s.getAttribute("min[4]")) %></td><td><%=(s.getAttribute("title[5]")==null)?"--":String.valueOf(s.getAttribute("min[5]")) %></td>				</tr>
				<tr class="even">
					<th>最高价</th>
					<td><%=(s.getAttribute("title[1]")==null)?"--":String.valueOf(s.getAttribute("max[1]")) %></td><td><%=(s.getAttribute("title[2]")==null)?"--":String.valueOf(s.getAttribute("max[2]")) %></td>
					<td><%=(s.getAttribute("title[3]")==null)?"--":String.valueOf(s.getAttribute("max[3]")) %></td><td><%=(s.getAttribute("title[4]")==null)?"--":String.valueOf(s.getAttribute("max[4]")) %></td><td><%=(s.getAttribute("title[5]")==null)?"--":String.valueOf(s.getAttribute("max[5]")) %></td>				</tr>
				<tr class="odd">
					<th>涨幅/跌幅</th>
					<td><%=(s.getAttribute("title[1]")==null)?"--":String.valueOf(s.getAttribute("fudu[1]")) %></td><td><%=(s.getAttribute("title[2]")==null)?"--":String.valueOf(s.getAttribute("fudu[2]")) %></td>
					<td><%=(s.getAttribute("title[3]")==null)?"--":String.valueOf(s.getAttribute("fudu[3]")) %></td><td><%=(s.getAttribute("title[4]")==null)?"--":String.valueOf(s.getAttribute("fudu[4]")) %></td><td><%=(s.getAttribute("title[5]")==null)?"--":String.valueOf(s.getAttribute("fudu[5]")) %></td>				</tr>
				<tr class="even">
					<th>对数收益率</th>
					<td><%=(s.getAttribute("title[1]")==null)?"--":String.valueOf(s.getAttribute("get[1]")) %></td><td><%=(s.getAttribute("title[2]")==null)?"--":String.valueOf(s.getAttribute("get[2]")) %></td>
					<td><%=(s.getAttribute("title[3]")==null)?"--":String.valueOf(s.getAttribute("get[3]")) %></td><td><%=(s.getAttribute("title[4]")==null)?"--":String.valueOf(s.getAttribute("get[4]")) %></td><td><%=(s.getAttribute("title[5]")==null)?"--":String.valueOf(s.getAttribute("get[5]")) %></td>				</tr>
				<!-- <tr class="odd">
					<th>一年涨跌</th>
					<td><%=(s.getAttribute("year[0]")==null)?"--":String.valueOf(s.getAttribute("year[0]")) %></td><td>--</td><td>--</td><td>--</td><td>--</td>				</tr>
				 -->
				</tbody>
			</table>
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table width="100%" border="0">
    <tr>
      <td><img src="images/transparent.png" width="10" height="10"></td>
    </tr>
    <tr>
      <td><footer class="m-footer" style="display: block;">
          <div class="m-footer-bottom"><img src="images/transparent.png" width="10" height="40"></div>
        </footer></td>
    </tr>
  </table>
</div>
<script src="js/echo.min.js"></script> 
<script>
  echo.init({
    offset: 100,
    throttle: 250,
    unload: false,
    callback: function (element, op) {
      console.log(element, 'has been', op + 'ed')
    }
  });
  // 图片赖加载
  </script> 
<!--[if (gte IE 9)|!(IE)]><!--> 
<script src="js/jquery.min.js"></script> 
<script src="js/amazeui.min.js"></script> 
<!--<![endif]--> 
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

<!--[if (gte IE 9)|!(IE)]><!--> 
<script src="js/jquery.min.js"></script> 
<script src="js/amazeui.min.js"></script> 
<!--<![endif]--> 
<script src='js/jquery.qrcode.min.js'></script>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>

<script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:<%=s.getAttribute("code")%>
        
    },

    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : <%=s.getAttribute("date")%>,
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:<%=s.getAttribute("title[1]")%>,
            type:'line',
            stack: '总量',
            data:<%=s.getAttribute("data1")%>
        },
        {
            name:<%=(s.getAttribute("title[2]")=="")?"2":s.getAttribute("title[2]")%>,
            type:'line',
            stack: '总量',
            data:<%=s.getAttribute("data2")%>
        },
        {
            name:<%=(s.getAttribute("title[3]")=="")?"3":s.getAttribute("title[3]")%>,
            type:'line',
            stack: '总量',
            data:<%=s.getAttribute("data3")%>
        },
        {
            name:<%=(s.getAttribute("title[4]")=="")?"4":s.getAttribute("title[4]")%>,
            type:'line',
            stack: '总量',
            data:<%=s.getAttribute("data4")%>
        },
        {
            name:<%=(s.getAttribute("title[5]")=="")?"5":s.getAttribute("title[5]")%>,
            type:'line',
            stack: '总量',
            data:<%=s.getAttribute("data5")%>
        }
    ]
};
                    
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    </script>
</body>
</html>
