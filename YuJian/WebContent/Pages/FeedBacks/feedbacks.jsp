<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/link.css?version=0.0.1">
<title>联系</title>
</head>
<body>
	<div class="header-tip-bar">
		<i class="icon icon-arrow" onclick="javascript:window.history.go(-1);"></i>
		<div>联系</div>
	</div>
	<div class="wrapper assistPage">
		<div class="optionbox">
			<ul>
				<li data-val="0">告白</li>
				<li data-val="1">求婚</li>
				<li data-val="2">其他</li>
				<li data-val="3">反馈</li>
			</ul>
		</div>
		<div class="form">
			<textarea id="feedbackcontent" rows="4" placeholder="输入你需要的内容" ></textarea>
			<button id="btn-sub" type="button">提交</button>
		</div>
	</div>
	
	<script>
	//#b3553e
		var feedbacktype=0; 
		
		$(function(){
			$('.optionbox ul li').get(0).click();
		})
		
		$('.optionbox ul li').click(function(){
			$('.optionbox ul li').css('color','#848383');
			$(this).css('color','red');
			
			feedbacktype=$(this).attr('data-val');
		})
		$('#feedbackcontent').keyup(function(){
			var val=$(this).val().trim();
			if(val.length<1){
				$('#btn-sub').css('background-color','#727172');
				return;
			}
			if(val.length>0){
				$('#btn-sub').css('background-color','#E3A54E');
			}
			
			if(val.length>200){
				$(this).val(val.substring(0,200));
			}
		})
		$('#btn-sub').click(function(){
			var val=$('#feedbackcontent').val().trim();
			if(val.length<1){
				return;
			}
			
			$.post('/YuJian/FeedBacks/Add'
					,{feedbacktype:feedbacktype,feedbackcontent:val}
					,function(data){
						if(data.status==1){
							$('#feedbackcontent').val('');
							$('#feedbackcontent').keyup();
							
							//跳回
							window.location.href="/YuJian/Mine";
						}
						tipAlert(data.msg);
					},'json');
		})
		
	</script>
</body>
</html>