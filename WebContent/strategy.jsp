<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%HttpSession s = request.getSession(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>股票回测</title>
<link rel="stylesheet" href="css/style.css">
<link rel="alternate icon" type="image/png" href="/favicon.png">
<link rel="stylesheet" href="css/amazeui.min.css">
<link rel="stylesheet" href="css/comp.css">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/style_menu.css" media="screen" type="text/css" />
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
      <td><table width="1949px" border="0" >
          <tr>
            <td width="292px" class="container_table">
              <div class="container">
                <ul>
                  <li class="dropdown"> <a href="index.jsp" data-toggle="dropdown">首页</a> </li>
                  <li class="dropdown"> <a href="#" data-toggle="dropdown">股票回测<i class="icon-arrow"></i></a>
                    <ul class="dropdown-menu">
                      <li><a href="strategy_1_1.jsp">动量策略</a></li>
                      <li><a href="strategy_1_2.jsp">均值回归</a></li>
                    </ul>
                  </li>
                  <li class="dropdown"> <a href="#" data-toggle="dropdown">周期参考 <i class="icon-arrow"></i></a>
                    <ul class="dropdown-menu">
                      <li><a href="strategy_2_1.jsp">动量策略</a></li>
                      <li><a href="strategy_2_2.jsp">均值回归</a></li>
                    </ul>
                  </li>
                </ul>
              </div></td>
            <td width="1657px">
            <iframe src="intro.html" width="1657px" height="600px" style="background-color:#FFF"></iframe>
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
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
<script src="js/menu.js"></script>
</body>
</html>
