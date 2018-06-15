$(function(){
	//头部导航效果
	headShow();
	
	//	显示微信，微博二维码
	hoverCode();
	
	//关闭弹窗
	closeBox();
});
//头部导航效果
function headShow(){
	var bfH = "#FFFFFF"; //hover 前颜色
	var afH = "#FFFFFF"; //hover 颜色
	$('.nav a').css('color', bfH);
	$('.nav').hover(function(){
		$('.nav a').css('color', afH);
		$(this).parent('.head').addClass('onbg');
		$('.nav_list').show();
	},function(){
		$('.nav a').css('color', bfH);
		$(this).parent('.head').removeClass('onbg');
		$('.nav_list').hide();
	});
	$('.nav li').hover(function(){
		$(this).children('.nav_list').parents('li').addClass('onbg');
	},function(){
		$(this).children('.nav_list').parents('li').removeClass('onbg');
	});
}

//	显示微信，微博二维码
function hoverCode(){
	$('.w_code').hover(function(){
		$(this).children('img').stop().show(500);
	},function(){
		$(this).children('img').stop().hide(500);
	});
}

//更改当前所在位置
function setNewsCurrent(item, current){
	$(item).click(function(){
		var val = $(this).html();
		$(current).html(val);
	});
}

//click显示/隐藏
function changeOn(title,con){
	$(title).click(function(){
		var $newList = $(con);
		var index1 = $(this).data('index');
		var index2;
		$(this).addClass('on').siblings().removeClass("on");
		for(var i = 0; i < $newList.length; i ++){
			index2 = $newList.eq(i).data('index');
			if(index1 == index2){
				$newList.eq(i).show().siblings().hide();
			}
		}
	})
}

//关闭窗口
function closeBox(){
	$('.close').click(function(){
		$(this).parent().hide();
		$('.mask').hide();
	})
}

//显示窗口
function showBox(a){
	$('.mask').show();
	$(a).show();
}