<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/expression.css?version=0.0.0">
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/swiper-3.4.2.min.css?version=0.1">
<script type="text/javascript" src = "/YuJian/Contents/js/swiper-3.4.2.jquery.min.js"></script>
<title>表情商城</title>
<style type="text/css">
	.swiper-container{margin: 0.1rem 0;}
	.swiper-container img{height: 100%;width: 100%;}
</style>
</head>
<body>
	<div class="header-tip-bar">
		<div>商店</div>
	</div>
	<div class="wrapper shop-page">
	 	<!-- 轮播图区域 -->
        <div class="swiper-container canhide" style="width: 20rem;height: 8rem;display: none;">
	        <div class="swiper-wrapper">
	          
	        </div>
	        <!-- 如果需要分页器 -->
	        <div class="swiper-pagination"></div>
    	</div>
        <div class="purchased-wrap" style="display: none">
           <div class="title">已购买</div>
           <div style="min-height: 9.0rem;width: 100% ;overflow-x: scroll;height: 100%;-webkit-overflow-scrolling: touch;">
           <ul class="purchased-list">           
               <!-- 已购买 -->
           </ul>
           </div>
        </div>
		<div class="purchased-wrap">
			<div class="title">表情商店</div>
			<ul class="expression-list">
	           <!-- 未购买 -->
	        </ul>
		</div>
    </div>
	<jsp:include page="../foot.jsp"></jsp:include>
	
	<script>
		var page=-1;
		$(function(){
			page=1;
			showSpin();
			loadData();
			loadMyData();
			loadBanner();
		})
		
		//滚动加载事件
		var stop=false;
		var end=false;
		$(window).scroll(function() {
			if(stop==true||end==true||page<0)
				return;
			var totalheight = parseFloat($(window).scrollTop()+$(window).height()/2); 
		    if($(document).height() <= totalheight){
		        if(stop==false){ 
		        	stop=true;
		        	page++;
		        	loadData();
		        }
		    }
		});
		
		function loadMyData(){
			$.post("/YuJian/UserExpressionBags/GetMyExpressionBags"
					,{}
					,function(data){
						if(data==null||data.length<1){
							return;
						}
						$('.purchased-wrap').show();
						var html='';
						for(var i=0;i<data.length;i++){
							var temp='<li class="expression-item">'
			                		+'<a href="/YuJian/Expressions?id={2}"><img src="{0}" alt=""></a>'
			                		+'<div>{1}</div>'
			                		+'</li>';
			            	
			            	temp = temp.format(data[i].expressionbagurl,data[i].expressionbagtitle,data[i].expressionbagid);
			            	html+=temp;
						}
						$('.purchased-list').html(html);
						
						setWidth();
					},'json')
		}

		function loadData(){
			if(page<0)
				return;
			$.post("/YuJian/ExpressionBags/GetExpressionBagsViews"
					,{ page:page,pageSize:16}
					,function(data){
						closeSpin();
						if(data==null||data.length<1){
							page=-1;
							return;
						}
						
						var html='';
						for(var i=0;i<data.length;i++){
							var temp='<li data-id="{2}" class="expression-item">'
			                		+'<a href="/YuJian/Expressions?id={2}"><img src="{0}" alt=""></a>'
			                		+'<div>{1}</div>'
			                		+'<span onclick="buy(\'{2}\',\'{1}\')">购买({3}币)</span></li>';
			            	
			            	temp = temp.format(data[i].expressionbagurl
			            			,data[i].expressionbagtitle,data[i].expressionbagid,data[i].ebcost);
			            	html+=temp;
						}
						$('.expression-list').append(html);
					},'json');
		}
		
		//加载轮播图
		function loadBanner(){
			 $.post('/YuJian/Banners/GetList?bannertype=1'
					 ,function(data){
				 if(data==null||data.length<1){
					 return;
				 }
				 var html='';
				 for(var i=0;i<data.length;i++){
					 var temp='<div class="swiper-slide"><a href="'+data[i].bannerlink+'"><img src="'+data[i].bannerimgurl+'"/></a></div>';
					 html+=temp;
				 }
				 $('.swiper-wrapper').html(html);
				 
				 $('.swiper-container').show();
				 var swiper = new Swiper ('.swiper-container', {
				        // 轮播图的方向，也可以是vertical方向
				        direction:'horizontal',        
				        //循环播放
				        loop: true,
				        // 自动播放时间
				        autoplay:5000,
				        // 播放的速度
				        speed:1000,
				        // 如果需要分页器，即下面的小圆点
				        pagination: '.swiper-pagination', 
				        //前进后退按钮
				        //nextButton: '.swiper-button-next',
				        //prevButton: '.swiper-button-prev',
				　　　　// 滑动之后， 定时器不会被清除
				　　　　  autoplayDisableOnInteraction : false,
				 });
			 },'json');
			 
		}
		
		function buy(id,name){
			phoneConfirm('是否确定购买 '+name+'？'
					,function(){
						showSpin();
						$.post("/YuJian/UserExpressionBags/BuyExpressionBags"
								,{ expressionbagid:id}
								,function(data){
									closeSpin();
									if(data.status==1){
										loadMyData();
										$('li[data-id='+id+']').remove();
									}
									tipAlert(data.msg);
									
								},'json')
					});
		}
		
		//设置高度
		function setWidth(){
			var width=0;
			$('.purchased-list li').each(function(){
				width+=parseInt($(this).find('img').css("height").replace('px',''));
			});
			$('.purchased-list').css('width',width+'px');
		}
	</script>
</body>
</html>