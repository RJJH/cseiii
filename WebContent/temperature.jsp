<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%
    HttpSession s = request.getSession();     
%>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>市场温度计</title>
<link rel="stylesheet" href="css/style.css">
<link rel="alternate icon" type="image/png" href="/favicon.png">
<link rel="stylesheet" href="css/amazeui.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/comp.css">
<link rel="stylesheet" href="css/singlestock_style.css">
<script language="javascript" type="text/javascript" src="js/WdatePicker.js"></script>



</head>
<body style="overflow-x: hidden">
<header class="m-hd">
  <section data-am-sticky class="am-show-md-up">
      <div class="am-container"> <a href="index.jsp" class="m-logo" rel="nofollow"><i class="am-icon-spinner"></i> Quantour</a>
        <nav>
          <ul class="m-nav am-nav am-nav-pills am-fr">
<% if (s.getAttribute("email")==null) { %>
      <li><a href="register.jsp" rel="nofollow" style="color:red">注册</a></li>
      <li><a href="login.jsp" rel="nofollow" style="color:red">登陆</a></li> 
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
<div class="m-header-banner m-list-header" >
  <section class="am-container">
    <hgroup data-am-scrollspy="{animation:'slide-bottom', delay: 317}">
      <h2 style="color:black">市场温度计</h2>
    </hgroup>
  </section>
  <div>
    <table width="1950px" align="center" id="comta">
      <tr>
        <td><table width="1949px" border="0">
            <tr>
              <td><form id="form1" name="form1" method="post" action="temperature.spring"  onSubmit="return check();" >     
                  <input class="Wdate" name="date" type="text" onClick="WdatePicker()">
                  <input type="submit" name="query" id="query" value="查询" />
                </form></td>
            </tr>
            <tr>
              <td><hr /></td>
            </tr>
            <tr>
              <td><div class="stock-info" data-spm="2">
                  <div class="stock-bets">
                    <div class="bets-content">
                      <div class="line1">
                        <dl>
                          <dt>交易总量</dt>
                          <dd class="s-up"><%=(s.getAttribute("sum")==null)?"":String.valueOf(s.getAttribute("sum")) %></dd>
                        </dl>
                        <dl>
                          <dt>涨停股票数</dt>
                          <dd class="s-up"><%=(s.getAttribute("upTop")==null)?"":String.valueOf(s.getAttribute("upTop")) %></dd>
                        </dl>
                        <dl>
                          <dt>涨幅超5%</dt>
                          <dd class="s-up"><%=(s.getAttribute("upAbove")==null)?"":String.valueOf(s.getAttribute("upAbove")) %></dd>
                        </dl>
                        <dl>
                          <dt>开盘-收盘大于5%</dt>
                          <dd><%=(s.getAttribute("upBetween")==null)?"":String.valueOf(s.getAttribute("upBetween")) %></dd>
                        </dl>
                        <div class="clear"></div>
                      </div>
                      <div class="line2" >
                        <dl>
                          <dt>&nbsp;</dt>
                          <dd></dd>
                        </dl>
                        <dl>
                          <dt>跌停股票数</dt>
                          <dd class="s-down"><%=(s.getAttribute("lowDown")==null)?"":String.valueOf(s.getAttribute("lowDown")) %></dd>
                        </dl>
                        <dl>
                          <dt>跌幅超5%</dt>
                          <dd class="s-down"><%=(s.getAttribute("lowBelow")==null)?"":String.valueOf(s.getAttribute("lowBelow")) %></dd>
                        </dl>
                        <dl>
                          <dt>开盘-收盘小于-5%</dt>
                          <dd class="s-down"><%=(s.getAttribute("downBetween")==null)?"":String.valueOf(s.getAttribute("downBetween")) %></dd>
                        </dl>
                        <dl>
                          <dt></dt>
                          <dd></dd>
                        </dl>
                      </div>
                      <div class="clear"></div>
                    </div>
                  </div>
                </div></td>
            </tr>
            <tr>
              <td><div id="main" style="height:400px"></div></td>
            </tr>
            <tr>
              <td></td>
            </tr>
          </table></td>
      </tr>
    </table>
    <!-- <table width="100%" border="0">
      <tr>
        <td><img src="images/transparent.png" width="10" height="10"></td>
      </tr>
      <tr>
        <td><footer class="m-footer" style="display: block;">
            <div class="m-footer-bottom"><img src="images/transparent.png" width="10" height="70"></div>
          </footer></td>
      </tr>
    </table> -->
  </div>
</div>
<script>
function check(){
	var d = new Date();
    var year = d.getFullYear();
    var month = d.getMonth() + 1; // 记得当前月是要+1的
    var dt = d.getDate();
    var today = year + "-" + month + "-" + dt;
    var late="2005-1-1";
    var date=document.form1.date.value;
    if(isNull(date)){
    	alert("别空着呀");
    	return false;
    }
    else if(my(date,today)>-14){
    	alert("亲，暂无数据");
    	return false;
    }
    else if(!checkTwoDate(late,date)){
    	alert("年代太久远啦");
        return false; 
    }
    else{
    	return true;
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
	function checkTwoDate(startDate,endDate ) {

	   if( startDate > endDate ) {
		return false;
		}
		return true;

		}

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
              'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
          ],
          function (ec) {
              // 基于准备好的dom，初始化echarts图表
              var myChart = ec.init(document.getElementById('main')); 
			  
              var option = {
  title : {
      text: '股票数量',
  },
  tooltip : {
      trigger: 'axis'
  },
  legend: {
      data:['股票数量']
  },

  calculable : true,
  xAxis : [
      {
          type : 'category',
          data : ['涨停股票数','跌停股票数','涨幅超5%','跌幅超5%','开盘-收盘大于5%','开盘-收盘小于5%']
      }
  ],
  yAxis : [
      {
          type : 'value'
      }
  ],
  series : [
      {
          name:'股票数量',
          type:'bar',
          data:<%=s.getAttribute("listData") %>
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