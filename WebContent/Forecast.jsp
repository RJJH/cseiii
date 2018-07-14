<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<% HttpSession s = request.getSession(); %>
<% List<List<Double>> shangres = (List<List<Double>>)s.getAttribute("shangres"); %>
<% List<List<Double>> shenres = (List<List<Double>>)s.getAttribute("shenres"); %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>走势预测</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
</head>

<body>

<center>
  <div><img src="images/yuce.png" width="514" height="95" /></div></center>
  <div><a href="#intro"><img src="images/str_intro.png" width="177" height="41" /></a>
    </div>
<table width="1500px" border="0">
  <tr>
    <td><h2>上证指数</h2></td>
  </tr>
  <tr>
    <td>
    
    <table width="1040px" border="2" >
  <tr>
    <th width="240" scope="col">天数（后）/区间</th>
    <th width="200" scope="col">小于3080</th>
    <th width="200" scope="col">3081~3160</th>
    <th width="200" scope="col">3161~3240</th>
    <th width="200" scope="col">大于3241</th>
  </tr>
  <tr id="shang_zhen">
    <th scope="row">一天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">两天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">三天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">四天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">五天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">六天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">七天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">八天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">九天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">十天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th colspan="5" scope="row"><div id="shang" style="height:400px; width:1000"></div></th>
    </tr>
    </table>

    </td>
  </tr>
  
  
  <tr>
    <td><h2>深圳指数</h2></td>
  </tr>
  <tr>
    <td>
    
    <table width="1040px" border="2">
  <tr>
    <th width="240" scope="col">天数（后）/区间</th>
    <th width="200" scope="col">小于3080</th>
    <th width="200" scope="col">3081~3160</th>
    <th width="200" scope="col">3161~3240</th>
    <th width="200" scope="col">大于3241</th>
  </tr>
  <tr id="shen_zhen">
    <th scope="row" >一天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">两天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">三天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">四天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">五天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">六天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">七天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">八天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">九天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">十天</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th colspan="5" scope="row"><div id="shen" style="height:400px; width:1000"></div></th>
    </tr>
</table>

    </td>
  </tr>
</table>
<footer style="border:thick solid #FF0; background-color:#FFC">
  <div>
<h1 style="background-color: #0CF; color:#FFF"><a name="intro" id="intro"></a>走势预测策略介绍</h1>
<div style=" background-color: #FF9">
<p>-马尔科夫过程概述</p>
<p>马尔可夫过程（Markov process）是一类随机过程。它的原始模型马尔可夫链，由俄国数学家A.A.马尔可夫于1907年提出。该过程具有如下特性：在已知目前状态 （现在）的条件下，它未来的演变 （将来）不依赖于它以往的演变 ( 过去 ) 。 例如森林中动物头数的变化构成——马尔可夫过程 。在现实世界中，有很多过程都是马尔可夫过程，如液体中微粒所作的布朗运动、传染病受感染的人数、车站的候车人数等，都可视为马尔可夫过程。关于该过程的研究，1931年A.H.柯尔莫哥洛夫在《概率论的解析方法》一文中首先将微分方程等分析的方法用于这类过程，奠定了马尔可夫过程的理论基础。</p>
<p>设E是{0,1，…，M}或{0,1,2，…},{X,t≥0}是一族取值于E的随机变量，如果在（1）式中，将n1,n2，…，m,n理解为实数，（1）式仍成立，则称{Xt，t≥0}为连续时间马尔可夫链。若还与s≥0无关，记为pij(t），则称链为齐次的。连续时间齐次马尔可夫链也由它的转移矩阵P(t)=(pij(t))(i,j∈E,t>0)所刻画。P(t）满足下述条件：①pij(t）≥0，；②柯尔莫哥洛夫-查普曼方程；通常假定：③标准性 这里δii=1，δij=0(i≠j）。有时直接称满足①、②、③的一族矩阵P(t)=(pij(t）），t≥0为转移矩阵或马尔可夫链。当①中条件放宽为时，称为广转移矩阵，它有很好的解析性质。例如，每个pij(t）在t>0时具有连续的有穷导数P‘（t）；在t=0，右导数P’（0）存在，i≠j时P‘（0）非负有穷，但P’（0）可能为无穷。矩阵Q =(qij）呏（P‘（0））称为链的密度矩阵，又称Q矩阵。对于每个齐次马尔可夫链{X,t≥0},钟开莱找到一个具有较好轨道性质（右下半连续）的修正{X(t)，t≥0}（即对一切t≥0，P(X(t)≠Xt)=0，且对每个轨道对一切t≥0有），而且以概率1，对任意t≥0,s从大于t的一侧趋于t时，X最多只有一个有穷的极限点。
以Q为密度矩阵的广转移矩阵称为Q广转移矩阵或Q过程。在一定条件下，Q广转移矩阵P(t),t≥0满足向后微分方程组或者向前微分方程组。</p>

<p>-股市走势预测的马尔科夫过程模型</p>
<p>通过对股市指数的统计分析，将股指划分为若干区间	，即状态区间。利用股市的历史资料，统计得出在连续两个时段（天、周、旬、月）内，前一个时段股指处于i区，而后一个时段股指处于j区的比率pij（i、j=1,2,3，……，n），表示第一时段股指处于i区，而第二时段股指处于j区的可能性</p>
<p>一般情况下，股指预测的马尔科夫链是有限马尔科夫链，且存在正整数k，使得pij(k)>0(i,j=1,2,……n)，所以，由前述马尔科夫链的有关性质可知，股指预测的马尔科夫链具有遍历性。就是说，无论股市初期股指所处的区间X(0)=(x1(0),x2(0)……，xn(0))如何，经过足够长的时间后，股指最终处于各个区间的概率分布都是相同的。而且，这一概率分布可以通过求解一个关于转移概率的简单线性方程组而唯一求得。</p></div>
</div></footer>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript">

var data = new Array(); 
<%for(int j=0;j<shangres.size();j++){%>  
   var da=new Array();
   <%for(int t=0;t<4;t++){%>
    da[<%=t%>]='<%=shangres.get(j).get(t)%>';
  <%}%>
 data[<%=j%>] = da;
<%}%>

	$(document).ready(function(){
		var present=$("#shang_zhen");
		for(var kk=0;kk<10;kk++){
		   var temp=present.find("th").next();
		   temp.text(data[kk][0]);
		   temp.next().text(data[kk][1]);
		   temp.next().next().text(data[kk][2]);
		   temp.next().next().next().text(data[kk][3]);
		   present=present.next();  
		}
	});
	var data0 = new Array(); 
	<%for(int j=0;j<shenres.size();j++){%>  
	   var da=new Array();
	   <%for(int t=0;t<4;t++){%>
	    da[<%=t%>]='<%=shenres.get(j).get(t)%>';
	  <%}%>
	 data0[<%=j%>] = da;
	<%}%>
		$(document).ready(function(){
			var present=$("#shen_zhen");
			for(var kk=0;kk<10;kk++){
			   var temp=present.find("th").next();
			   temp.text(data0[kk][0]);
			   temp.next().text(data0[kk][1]);
			   temp.next().next().text(data0[kk][2]);
			   temp.next().next().next().text(data0[kk][3]);
			   present=present.next();  
			}
		});	


</script>
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
                var myChart = ec.init(document.getElementById('shang')); 
                
                var option = {
    title : {
        text: '上证指数十日预测',
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['小于3080','3081~3160','3161~3240','大于3241']
    },
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ['一日','二日','三日','四日','五日','六日','七日','八日','九日','十日'],
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'小于3080',
            type:'line',
            data:<%=s.getAttribute("shang1")%>,
        },
      {
            name:'3081~3160',
            type:'line',
            data:<%=s.getAttribute("shang2")%>,
        },
      {
            name:'3161~3240',
            type:'line',
            data:<%=s.getAttribute("shang3")%>,
        },
        {
            name:'大于3241',
            type:'line',
            data:<%=s.getAttribute("shang4")%>,
        }
    ]
};
                          
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
</script>
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
                var myChart = ec.init(document.getElementById('shen')); 
                
                var option = {
    title : {
        text: '深证指数十日预测',
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['小于3080','3081~3160','3161~3240','大于3241']
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ['一日','二日','三日','四日','五日','六日','七日','八日','九日','十日'],
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'小于3080',
            type:'line',
            data:<%=s.getAttribute("shen1")%>,
        },
      {
            name:'3081~3160',
            type:'line',
            data:<%=s.getAttribute("shen2")%>,
        },
      {
            name:'3161~3240',
            type:'line',
            data:<%=s.getAttribute("shen3")%>,
        },
        {
            name:'大于3241',
            type:'line',
            data:<%=s.getAttribute("shen4")%>,
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