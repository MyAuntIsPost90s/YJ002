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
		<i class="icon icon-arrow" onclick="javascript:window.history.go(-1);"></i>
		<div>表情商店</div>
	</div>
	<div class="wrapper shop-page">
		<div class="purchased-wrap">
			<ul class="expression-list"  style="margin-top: 0">
	           <!-- 未购买 -->
	        </ul>
		</div>
    </div>
	<jsp:include page="../foot.jsp"></jsp:include>
	
	<script>
		var page=-1;
		$(function(){
			page=1;
			loadData();
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
		
		function loadData(){
			if(page<0)
				return;
			$.post("/YuJian/ExpressionBags/GetExpressionBagsViews"
					,{ page:page,pageSize:16}
					,function(data){
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
		
		function buy(id,name){
			phoneConfirm('是否确定购买 '+name+'？'
					,function(){
						showSpin();
						$.post("/YuJian/UserExpressionBags/BuyExpressionBags"
								,{ expressionbagid:id}
								,function(data){
									closeSpin();
									if(data.status==1){
										$('li[data-id='+id+']').remove();
										javascript:window.history.go(-1);
									}
									tipAlert(data.msg);
									
								},'json')
					});
		}
		
		//设置宽度
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