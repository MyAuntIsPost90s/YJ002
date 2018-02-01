<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/expression.css">
<title>表情包</title>
</head>
<body>
	<div class="header-tip-bar">
		<i class="icon icon-arrow" onclick="javascript:window.history.go(-1);"></i>
		<div id="title">表情内容</div>
	</div>
	<div class="wrapper shop-page">
		<ul class="expression-list" style="margin-top: 0.01rem">
           <!-- 表情包内容 -->
        </ul>
	</div>
	<div class="foot-bar" style="display: none;">
		<button onclick="buy(this)">购买表情包(<span id="ebcost">0</span>币)</button>
	</div>
	<script>
		var expressionbagid='${id}';
		var expressionbag=null;
		var haved=0;
		
		$(function(){
			loadTitle();
			loadExpressions();
			
			if(haved==0){
				$('.foot-bar').show();
			}
		});
		
		function loadTitle(){
			$.post('/YuJian/ExpressionBags/GetExpressionBag'
					,{expressionbagid:expressionbagid}
					,function(data){
						if(data==null)
							return;
						expressionbag=data;
						document.title=data.expressionbagtitle;
						$('#title').html(data.expressionbagtitle);
						$('#ebcost').html(data.ebcost);
					},'json');
		}
		
		function loadExpressions(){
			if(expressionbagid==null||expressionbagid=='')
				return;
			$.post('/YuJian/Expressions/GetExpressions'
					,{expressionbagid:expressionbagid}
					,function(data){
						if(data==null||data.length<1)
							return;
						var html='';
						for(var i=0;i<data.length;i++){
							var temp='<li class="expression-item">'
		                		+'<img src="{0}" alt="">'
		                		+'</li>';
							temp = temp.format(data[i].expressionurl);
			            	html+=temp;
						}
						$('.expression-list').html(html);
					},'json');
		}
		
		function buy(obj){
			if(expressionbag==null||haved==1){
				return;
			}
			phoneConfirm('是否确定购买 '+expressionbag.expressionbagtitle+'？'
					,function(){
						showSpin();
						$.post("/YuJian/UserExpressionBags/BuyExpressionBags"
								,{ expressionbagid:expressionbagid}
								,function(data){
									closeSpin();
									if(data.status==1){
										$(obj).text("已购买");
										$(obj).addClass("disable");
										haved=1;
									}
									tipAlert(data.msg);
									
								},'json')
					});
		}
	</script>
</body>
</html>