<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<% HttpSession s = request.getSession(); %>
<% List<List<String>> datas = (List<List<String>>)s.getAttribute("shenaData"); %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<!-- <link rel="stylesheet" href="../css/mod-datas.css"> -->
<style>

table{

border-collapse:collapse;//表格单元格间距样式

border:1px solid #000;

}

tr{border:1px solid #000;}

td{

border:0px;}

tr:nth-child(2n){

background-color: #fbc9a7

}
th {text-align:left}
.table-body{width:100%; height:500px;overflow-y:scroll;}

</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="../js/jquery.tablesorter.js"></script>
</head>
<body>
<div class="table-head" id="zyzs">
      <table width="1350px" class="data-table">
        <thead>
          <tr>
            <th width="70px">代码</th>
            <th width="70px">名称</th>
            <th width="70px">最新价</th>
            <th width="70px">涨跌额</th>
            <th width="70px">涨跌幅</th>
            <th width="70px">成交量(手)</th>
            <th width="95px">成交额(万)</th>
            <th width="70px">昨收</th>
            <th width="70px">今开</th>
            <th width="70px">最高</th>
            <th width="70px">最低</th>
          </tr>
          </thead>
          
          
        </tbody>
      </table>
    </div>
    <div class="table-body">
    <table width="1350px" class="data-table">
    <tbody id="toadd">
    </table>
    </div>
<script type="text/javascript">
var data = new Array(); 
<%for(int j=0;j<datas.size();j++){%>  
   var da=new Array();
   <%for(int t=0;t<11;t++){%>
    da[<%=t%>]='<%=datas.get(j).get(t)%>';
  <%}%>
 data[<%=j%>] = da;
<%}%>

for (var i=0;i<data.length;i++){
		  var newline='<tr><td width="70px"><span>'+data[i][0]+'</span></td><td width="70px"><span>'
		  +data[i][1]+'</span></td><td width="70px"><span >'+data[i][2]+'</span></td><td width="70px"><span >'
		  +data[i][3]+'</span></td><td width="70px"><span >'+data[i][4]+'</span></td><td width="70px"><span>'
		  +data[i][5]+'</span></td><td width="70px"><span>'+data[i][6]+'</span></td><td width="70px"><span>'
		  +data[i][7]+'</span></td><td width="70px"><span>'+data[i][8]+'</span></td><td width="70px"><span>'
		  +data[i][9]+'</span></td><td width="70px"><span>'+data[i][10]+'</span></td></tr>'
		$("#toadd").append(newline);
}
</script>    
<script type="text/javascript">
    $(document).ready(function() {
        $(".data-table").tablesorter(); 
    });
</script>
</body>
</html>
