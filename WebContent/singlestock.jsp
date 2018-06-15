<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    HttpSession s = request.getSession();     
    List<List<Double>> kline = (List<List<Double>>)s.getAttribute("Kline");
	List<List<String>> history=(List<List<String>>)s.getAttribute("History");
	List<List<String>> ownStock=(List<List<String>>)s.getAttribute("Person");
	List<List<String>> gsxw = (List<List<String>>)s.getAttribute("gsxw");
	List<List<String>> gsgg = (List<List<String>>)s.getAttribute("gsgg");
%>
<html class="no-js" lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>个股查询</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp"/>
<link rel="alternate icon" type="image/png" href="/favicon.png">
<link rel="stylesheet" href="css/amazeui.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/singlestock_style.css">
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>

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
      <h2>个股查询</h2>
    </hgroup>
  </section>
  <div>
    <div class="stock-info" data-spm="2">
      <div class="stock-bets">
        <table width="1950px" border="0" >
          <tr>
            <td width="292.5px"><h1><%=s.getAttribute("stock_name")%><span>(<%=s.getAttribute("stock_id")%>)</span></h1></td>
            <td width="1657.5px"><form name="form1" method="post" action="addFavorite.spring">
                <input name="stockidname" type="text" style="display:none" value="<%=s.getAttribute("stock_id")%>">
                <input type="submit" name="addstock" id="addstock" value="加 入 自 选" onClick="addnewStock()">
                
              </form></td>
          </tr>
        </table>
     
 <div class="price s-up "> <strong class="_close"><%=String.valueOf(s.getAttribute("price"))%></strong> 
          
        </div>

        <div class="bets-content">
          <div class="line1">
            <dl>
              <dt>开盘</dt>
              <dd><%=String.valueOf(s.getAttribute("open"))%></dd>
            </dl>
            <dl>
              <dt>涨跌额</dt>
              <dd id="cjl"><%=String.valueOf(s.getAttribute("change"))%></dd>
            </dl>
            <dl>
              <dt>最高</dt>
              <dd class="s-up"><%=String.valueOf(s.getAttribute("max_price"))%></dd>
            </dl>
            <dl>
              <dt>成交量</dt>
              <dd><%=String.valueOf(s.getAttribute("cjl"))%></dd>
            </dl>
            <dl>
              <dt>振幅</dt>
              <dd><%=s.getAttribute("amplitude")%></dd>
            </dl>
            
            <div class="clear"></div>
          </div>
          <div class="line2">
            <dl>
              <dt>昨收</dt>
              <dd><%=String.valueOf(s.getAttribute("finish"))%></dd>
            </dl>
            <dl>
              <dt>涨跌幅</dt>
              <dd><%=s.getAttribute("range")%></dd>
            </dl>
            <dl>
              <dt>最低</dt>
              <dd class="s-down"><%=String.valueOf(s.getAttribute("min_price"))%></dd>
            </dl>
            <dl>
              <dt>成交额</dt>
              <dd> <%=String.valueOf(s.getAttribute("volume"))%></dd>
            </dl>
            
          </div>
          <div class="clear"></div>
        </div>
      </div>
      <hr>
    </div>
  </div>
  <script>
</script>
  <table width="1850px" border="0" align="center" id="imtable">
    <tr>
      <td colspan="2" bgcolor="#FFFFCC"><div id="junxian" style="height:400px"></div></td>
      <td width="298" rowspan="2" bgcolor="#FFFFCC">
        
        <div class="righttable"> 
          <div class="histitle">历 史 记 录</div>
          <hr>
          <div class="needlogin">
            <a href="login.jsp">请 先 登 陆</a>
          </div>
          <ul class="col_name">
            <li class="clearfix">
              <h3 style="color:#000">名称</h3>
              <h3>股票id</h3>
              <h3>浏览时间</h3>
            </li>
          </ul>
          <ul id="recentstock">           
          </ul>
          
        </div>   
      </td>
      <td width="298" rowspan="2" bgcolor="#FFFFCC">
      
      <div class="righttable">
        <div class="owntitle">自 选 股 票</div>
        <hr>
        <div class="needlogin">
           <a href="login.jsp">请 先 登 陆</a>
        </div>
        <div class="ownstockshow">
      <ul id="ownstock">
         <li class="clearfix">
              <h3 style="color:#000">名称</h3>
              <h3>股票id</h3>
              <h3>&nbsp;</h3>
            </li>
      </ul>
      </div>
      </div>  
      <script type="text/javascript">
         var data = new Array();
		 var data2=new Array();
         <% if (s.getAttribute("email")==null) { %>
           $("#ownstock").hide();
		   $(".col_name").hide();
        <% }else{%>

           $(".needlogin").hide();
           
       <%for(int j=0;j<history.size();j++){%>  
            var da=new Array();
            <%for(int t=0;t<3;t++){%>
	         da[<%=t%>]='<%=history.get(j).get(t)%>';
	       <%}%>
          data[<%=j%>] = da;
      <%}%>
      if(data.length==0){
    	  var note='<li><p style="font-size:20px;">暂无数据</p></li>';
    	  $("#recentstock").append(note);
      }
      else{
    	  for (var i=0;i<data.length;i++){
    		   var newstock='<li class="clearfix"><h4>'+ data[i][0]+'</h4><h3><span>'+data[i][1]+'</span></h3><h3><span>'+data[i][2]+'</span></h3></li>';
    	   $("#recentstock").append(newstock);
    	   }
      }
    
   
   <% for(int j=0;j<ownStock.size();j++){%>
       var da=new Array();
       <%for(int t=0;t<2;t++){%>
        da[<%=t%>]='<%=ownStock.get(j).get(t)%>';
   <%}%>
   data2[<%=j%>] = da;
<%}%>
if(data2.length==0){
	  var note='<li><p style="font-size:20px;">暂无数据</p></li>';
	  $("#ownstock").append(note);
}
else{
	for (var i=0;i<data2.length;i++){
		  var newstock='<form name="delownStock" method="get" action="delown.spring"><li class="clearfix"><h4>'+ data2[i][0]+'</h4><h3><span>'+data2[i][1]+'</span></h3><h3><span><input type="submit" name="delstock" value="X" onClick="delownstock()"></span></h3></li><input name="tobedel" id="comstocks" type="text" style="display:none" value="'+data2[i][1]+'"></form>';
		$("#ownstock").append(newstock);
		}
}


<%}%>
  
</script>
      </td>
    </tr>
    <tr>
      <td colspan="2" bgcolor="#FFFFCC"><div id="main" style="height:400px;width:1000px"></div></td>
    </tr>
    <tr>
      <td width="455" bgcolor="#FFFFCC">
      <table width="600" border="1">
  <tr>
    <th scope="col">
    <div class="sub_cont_5 m_s_l hSty3" stat="gegugp_gsxw" id="xwgg">
					<div class="m_title_4" style="line-height: 37px;background-color:#F99;overflow:hidden;">
						<h2 class="fl" style="font-size:20px">公司新闻</h2>
						<ul class="mt_list fr" style="overflow:hidden;">
						</ul>
					</div>
					<ul class="news_list stat" stat="f10_spqk_gsxw" style="background-color:#FFF; height:290" id="xinwen">										
					</ul>
				</div>
    </th>
  </tr>
</table>

<script type="text/javascript">
      var data=new Array(); 
       <%for(int j=0;j<gsxw.size()&&j<8;j++){%>  
            var da=new Array();
            <%for(int t=0;t<3;t++){%>
	         da[<%=t%>]='<%=gsxw.get(j).get(t)%>';
	       <%}%>
          data[<%=j%>] = da;
      <%}%>
      if(data.length==0){
    	  var note='<li><p style="font-size:20px;">暂无数据</p></li>';
    	  $("#xinwen").append(note);
      }
      else{
    	  for (var i=0;i<data.length;i++){
    		   var news='<li class="clearfix"><span class="news_title fl"><a href="'+data[i][0]+'" target="_blank">'+data[i][1]+'</a></span><span class="news_date fr"><em>'+data[i][2]+'</em></span></li>';
    	   $("#xinwen").append(news);
    	   }
      }
</script>  
      
      </td>
      <td colspan="2" bgcolor="#FFFFCC">
      <table width="600" border="1">
  <tr>
    <th scope="col">
    <div class="sub_cont_5 m_s_l hSty3" stat="gegugp_gsxw" id="xwgg">
          <div class="m_title_4" style="line-height: 37px; background-color:#F99;overflow: hidden;">
            <h2 class="fl" style="font-size:20px">公司公告</h2>
            <ul class="mt_list fr">
              </ul>
            </div>
          <ul class="news_list stat" stat="f10_spqk_gsxw" style="background-color:#FFF;height:290" id="gonggao">
            </ul>
          </div>
    
    </th>
  </tr>
</table>

        
        
<script type="text/javascript">
      var data=new Array(); 
       <%for(int j=0;j<gsgg.size()&&j<8;j++){%>  
            var da=new Array();
            <%for(int t=0;t<3;t++){%>
	         da[<%=t%>]='<%=gsgg.get(j).get(t)%>';
	       <%}%>
          data[<%=j%>] = da;
      <%}%>
      if(data.length==0){
    	  var note='<li><p style="font-size:20px;">暂无数据</p></li>';
    	  $("#gonggao").append(note);
      }
      else{
    	  for (var i=0;i<data.length;i++){
    		   var news='<li class="clearfix"><span class="news_title fl"><a href="'+data[i][0]+' "target="_blank">'+data[i][1]+'</a></span><span class="news_date fr"><em>'+data[i][2]+'</em></span></li>';
    	   $("#gonggao").append(news);
    	   }
      }
</script>           
          
                </td>
      <td bgcolor="#FFFFCC"></td>
    </tr>
    <tr>
      <td colspan="4" bgcolor="#FFFFCC">
         
      </td>
    </tr>
  </table>
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
$(function(){
	var str = "http://www.imj2.com";
		$("#code").qrcode({
			render: "table",
			width: 100,
			height:100,
			text: str
		});
})
// 二维码生成
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
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script> 
<script type="text/javascript">

        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
		
		 require(
            [
                'echarts',
				'echarts/theme/macarons',
                'echarts/chart/k' 
            ],
            function (ec,theme) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main'),theme); 
                
              var  option = {
    title : {
        text: '股票指数'
    },
    tooltip : {
        trigger: 'axis',
        formatter: function (params) {
            var res = '<%=s.getAttribute("stock_name")%>' + ' ' + params[0].name;
            res += '<br/>  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
            res += '<br/>  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
            return res;
        }
    },
    legend: {
        data:['<%=s.getAttribute("stock_name")%>']
    },

    dataZoom : {
        show : true,
        realtime: true,
        start : 50,
        end : 100
    },
    xAxis : [
        {
            type : 'category',
            boundaryGap : true,
            axisTick: {onGap:false},
            splitLine: {show:false},
            data : <%=s.getAttribute("info_list")%>
        }
    ],
    yAxis : [
        {
            type : 'value',
            scale:true,
            boundaryGap: [0.01, 0.01]
        }
    ],
    series : [
        {
            name:'',
            type:'k',
            data: <%=s.getAttribute("Kline")%>
        }
    ]
};
myChart.setOption(option); 
            }
        );
    </script> 
<script type="text/javascript"> //均线图
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
                'echarts/chart/line'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('junxian')); 
                
                var option = {
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['日K','5日','10日','30日']
    },
dataZoom : {
        show : true,
        realtime: true,
        start : 0,
        end : 100
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : true,
            axisTick: {onGap:false},
            splitLine: {show:false},
            data : <%=s.getAttribute("info_list")%>
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'日K',
            type:'line',
            stack: '总量',
			symbol:'none',
            data:<%=s.getAttribute("AverageLine")%>
        },
        {
            name:'5日',
            type:'line',
            stack: '总量',
			symbol:'none',
            data:<%=s.getAttribute("AverageLine5")%>
        },
        {
            name:'10日',
            type:'line',
            stack: '总量',
			symbol:'none',
            data:<%=s.getAttribute("AverageLine10")%>
        },
        {
            name:'30日',
            type:'line',
            stack: '总量',
			symbol:'none',
            data:<%=s.getAttribute("AverageLine30")%>
        },
    ]
};
                    
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    </script> 
<!--  <script type="text/javascript">
function addnewStock(){
	var name="<%=s.getAttribute("stock_name")%>";
	var id="<%=s.getAttribute("stock_id")%>";
	
	var newstockadd='<form name="delownStock" method="get" action=""><li class="clearfix"><h4>'+ name+'</h4><h3><span>'+
	  id+'</span></h3><h3><span><input type="submit" name="delstock" value="X" onClick="delownstock()"></span></h3></li></form>';
    var data=new Array();
  <%for(int j=0;j<ownStock.size();j++){%>  
      data[<%=j%>] ='<%=ownStock.get(j).get(0)%>';
<%}%>
	if(data.length==0){
	   $("#ownstock").find("li").remove(); 
	}
	$("#ownstock").append(newstockadd);
}
</script>  -->
</body>
</html>