<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>

<% HttpSession s = request.getSession();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>股票回测_均值回归</title>
<link rel="stylesheet" href="css/style.css">
<link rel="alternate icon" type="image/png" href="/favicon.png">
<link rel="stylesheet" href="css/amazeui.min.css">
<link rel="stylesheet" href="css/comp.css">
<link rel="stylesheet" href="css/menu.css">
<link rel="stylesheet" href="css/singlestock_style.css">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/style_menu.css" media="screen" type="text/css" />
<script language="javascript" type="text/javascript" src="js/WdatePicker.js"></script>
<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
<script src="/follow.js" type="text/javascript"></script>
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
    <h2>股票回测</h2>
  </hgroup>
</section>
<table width="1950px" border="3" align="center" bgcolor="#FFFF99" id="comta">
    <tr>
  
    <td>
  <table width="1949px" border="0" >
      <tr>
    
    <td width="204px" class="container_table">


  <div class="container">
  
  <ul>
    <li class="dropdown">
      <a href="index.jsp" data-toggle="dropdown">首页</a>  
    </li>
    <li class="dropdown">
      <a href="#" data-toggle="dropdown">股票回测<i class="icon-arrow"></i></a>
      <ul class="dropdown-menu">
        <li><a href="strategy_1_1.jsp">动量策略</a></li>
        <li><a href="#">均值回归</a></li>
      </ul>
    </li>
    <li class="dropdown">
      <a href="#" data-toggle="dropdown">周期参考<i class="icon-arrow"></i></a>
      <ul class="dropdown-menu">
        <li><a href="strategy_2_1.jsp">动量策略</a></li>
        <li><a href="strategy_2_2.jsp">均值回归</a></li>
      </ul>
    </li>
  </ul>
</div></td>
      <td>
    
    <div class="stra_title">
    <table width="1600" border="0">
  <tr>
    <th scope="col"><h3 style="color:#000">均值回归</h3></th>
    <th scope="col"></th>
  </tr>
</table> 
    </div>
    <table width="100%" border="0">
      <tr>
        <td colspan="2"><form action="strategy_1_2.spring" method="get" name="toSubmit" class="strategy_form" onSubmit="return check();">
            开始时间
            <input class="Wdate" name="start" type="text" onClick="WdatePicker()">
            结束时间
            <input class="Wdate" name="end" type="text" onClick="WdatePicker()">
            股票池选择
            <select name="stockpool">
              <option value="沪股">沪股</option>
              <option value="深股">深股</option>
              <option value="创业板">创业板</option>
              <option value="自选股">自选股</option>
            </select>
            均线日期
            <select name="line">
              <option value="5">5</option>
              <option value="10">10</option>
              <option value="30">30</option>
            </select>
            参数
            <input class="textinput" name="chiyou" type="text" id="search_input" placeholder="持仓周期" autocomplete="off">
            <input class="textinput" name="shuliang" type="text" id="search_input" placeholder="持仓数量" autocomplete="off">
            <input type="submit" class="button2" value="START" hidefocus="true" />
          </form>
      </tr>
      <tr>
<script>

function check(){
	var d = new Date();
    var year = d.getFullYear();
    var month = d.getMonth() + 1; // 记得当前月是要+1的
    var dt = d.getDate();
    var today = year + "-" + month + "-" + dt;
    var late="2005-1-1";
    var pool=document.toSubmit.stockpool.value;
    var zi="自选股";
    if(pool==zi){
    	<% if (s.getAttribute("email")==null) { %>
    	    alert("请先登录");
    	    return false;
    	<%}%>
    }
    else{
    	<% List<List<String>> own=(List<List<String>>)s.getAttribute("Person");%>
    	var nums='<%=own.size()%>';

	var chiyou=document.toSubmit.chiyou.value;
	var shuliang=document.toSubmit.shuliang.value;
	var end=document.toSubmit.end.value;
	var start=document.toSubmit.start.value;
	var day=getDays(start,end);
    if (isNull(start)||isNull(end)||isNull(chiyou)||isNull(shuliang)){        
        alert("不能为空");        
        return false;    
        }
    else if(!isNumber(chiyou)||!isNumber(shuliang)){
    	alert("请输入正确");        
        return false;  
    }
    else if(shuliang>20||shuliang>nums){
    	alert("持仓数太多啦");        
        return false; 
    }
    else if(day<8){
    	alert("时间太短啦");
        return false; 
    }
    else if(day/2<chiyou){
    	alert("时间太短或周期太长");
        return false; 
    }
    else if(!checkTwoDate(start,end)){
    	alert("起始日期不能大于终止日期!");
        return false; 
    }
    else if(!checkTwoDate(late,start)){
    	alert("年代太久远啦");
        return false; 
    }
    else if(my(end,today)>-14){
    	alert("亲，暂无数据");
        return false;
    }
    else{
        return true;
        }
    }
}
function my(strDateStart,strDateEnd){
	var strSeparator = "-"; //日期分隔符
	   var oDate1;
	   var oDate2;
	   var iDays;
	   oDate1= strDateStart.split(strSeparator);
	   oDate2= strDateEnd.split(strSeparator);
	   var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
	   var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
	   iDays=parseInt((strDateS - strDateE ) / 1000 / 60 / 60 /24)
	   return iDays;
}
function getDays(strDateStart,strDateEnd){
	   var strSeparator = "-"; //日期分隔符
	   var oDate1;
	   var oDate2;
	   var iDays;
	   oDate1= strDateStart.split(strSeparator);
	   oDate2= strDateEnd.split(strSeparator);
	   var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
	   var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
	   iDays = parseInt(Math.abs(strDateS - strDateE ) / 1000 / 60 / 60 /24)//把相差的毫秒数转换为天数 
	   return iDays ;
}
function isNumber( s ){
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1) {
	return true;
    } else {
	return false;
	}
	}
function isNull(str){
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}
function isDate( date, fmt ) {
	if (fmt==null) fmt="yyyy-MM-dd";
	var yIndex = fmt.indexOf("yyyy");
	if(yIndex==-1) return false;
	var year = date.substring(yIndex,yIndex+4);
	var mIndex = fmt.indexOf("MM");
	if(mIndex==-1) return false;
	var month = date.substring(mIndex,mIndex+2);
	var dIndex = fmt.indexOf("dd");
	if(dIndex==-1) return false;
	var day = date.substring(dIndex,dIndex+2);
	if(!isNumber(year)||year>"2100" || year< "1900") return false;
	if(!isNumber(month)||month>"12" || month< "01") return false;
	if(day>getMaxDay(year,month) || day< "01") return false;
	return true;
	}
	function getMaxDay(year,month) {
	if(month==4||month==6||month==9||month==11)
	return "30";
	if(month==2)
	if(year%4==0&&year%100!=0 || year%400==0)
	return "29";
	else
	return "28";
	return "31";
	}
	function checkTwoDate( startDate,endDate ) {

	   if( startDate > endDate ) {
		return false;
		}
		return true;

		}
</script> 


        <td colspan="2">
        <div class="stock-info" data-spm="2">
      <div class="stock-bets">
        <div class="price">
          <div class="bets-content">
            <div class="line1">
              <dl>
                                <dt>年化收益率</dt>
                                <dd><%=(s.getAttribute("AnnualReturn")==null)?"":s.getAttribute("AnnualReturn")%></dd>
                              </dl>
                              <dl>
                                <dt>基准年化收益率</dt>
                                <dd><%=(s.getAttribute("BaseReturn")==null)?"":s.getAttribute("BaseReturn")%></dd>
                              </dl>
                              <dl>
                                <dt>阿尔法系数</dt>
                                <dd ><%=(s.getAttribute("Alpha")==null)?"":s.getAttribute("Alpha")%></dd>
                              </dl>
                              <dl>
                                <dt>贝塔系数</dt>
                                <dd><%=(s.getAttribute("Beta")==null)?"":s.getAttribute("Beta")%></dd>
                              </dl>
                              <dl>
                                <dt>夏普比率</dt>
                                <dd><%=(s.getAttribute("SharpeRatio")==null)?"":s.getAttribute("SharpeRatio")%></dd>
                              </dl>
                              <dl>
                                <dt>最大回撤</dt>
                                <dd><%=(s.getAttribute("MaxDrawDown")==null)?"":s.getAttribute("MaxDrawDown")%></dd>
                              </dl>
            </div>  
          </div>
        </div>
      </div></div>
        </td>
      
        </tr>
      <tr>
        <td colspan="2">
        <div id="Yield_comparison" style="height:400px"></div>
        </td>
      </tr>
      <tr>
        <td colspan="2">
        <div id="Yield_distribution" style="height:400px"></div>
        </td>
      </tr>
    </table>
      </td>
    
      </tr>
    
  </table>
    </td>
  
    </tr>
  
</table>
<p>&nbsp;</p>
<table width="100%" border="0">
  <tr>
    <td><img src="images/transparent.png" width="10" height="10"></td>
  </tr>
  <tr>
    <td><footer class="m-footer" style="display: block;">
        <div class="m-footer-bottom"><img src="images/transparent.png" width="10" height="70"></div>
      </footer></td>
  </tr>
</table>
</div>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script> 
<script type="text/javascript">
	$(function(){
	var thisTime;
	$('.nav-ul li').mouseleave(function(even){
			thisTime	=	setTimeout(thisMouseOut,1000);
	})

	$('.nav-ul li').mouseenter(function(){
		clearTimeout(thisTime);
		var thisUB	=	$('.nav-ul li').index($(this));
		if($.trim($('.nav-slide-o').eq(thisUB).html()) != "")
		{
			$('.nav-slide').addClass('hover');
			$('.nav-slide-o').hide();
			$('.nav-slide-o').eq(thisUB).show();
		}
		else{
			$('.nav-slide').removeClass('hover');
		}
		
	})
	
	function thisMouseOut(){
		$('.nav-slide').removeClass('hover');
	}
	 
	$('.nav-slide').mouseenter(function(){
		clearTimeout(thisTime);
		$('.nav-slide').addClass('hover');
	})
	$('.nav-slide').mouseleave(function(){
		$('.nav-slide').removeClass('hover');
	})

})
</script> 
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

  </script> 
<!--[if (gte IE 9)|!(IE)]><!--> 
<script src="js/jquery.min.js"></script> 
<script src="js/amazeui.min.js"></script> 
<!--<![endif]--> 
<script src='js/jquery.qrcode.min.js'></script> 
<script type="text/javascript">

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
</script>

<script type="text/javascript">
        
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
       
        require(
            [
                'echarts',
                'echarts/chart/line' 
            ],
            function (ec) {
               
                var myChart = ec.init(document.getElementById('Yield_comparison')); 
                
                var option = {
    title : {
        text: '收益率比较图'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['基准收益率','策略收益率']
    },
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : <%=s.getAttribute("dates")%>
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
            name:'基准收益率',
            type:'line',
            data:<%=s.getAttribute("base")%>,   
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'策略收益率',
            type:'line',
            data:<%=s.getAttribute("strategy")%>,
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        }
    ]
};
                      
                myChart.setOption(option); 
            }
        );
    </script>
    
    <script type="text/javascript">

        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });

        require(
            [
                'echarts',
                'echarts/chart/bar' 
            ],
            function (ec) {
             
                var myChart = ec.init(document.getElementById('Yield_distribution'));           
				
                var option = {
                		title : {
                	        text: '收益分布图'
                	    },
                    tooltip: {
                        show: true
                    },
                    legend: {
                        data:['数量']
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : ["-0.9以下","-0.9~-0.8","-0.8~-0.7","-0.7~-0.6","-0.6~-0.5","-0.5~-0.4","-0.4~-0.3","-0.3~-0,2","-0.2~-0.1","-0.1~0","0~0.1","0.1~0.2","0.2~0.3","0.3~0.4","0.4~0.5","0.5~0.6","0.6~0.7","0.7~0.8","0.8~0.9","0.9以上"]
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"数量",
                            "type":"bar",
                            "data":<%=s.getAttribute("fenbu")%>
                        }
                    ]
                };
                    
                    myChart.setOption(option);
                
            }
        );
        
    </script>
<script src="js/menu.js"></script>
</body>
</html>