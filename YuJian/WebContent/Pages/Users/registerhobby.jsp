<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<script type="text/javascript" src = "/YuJian/Contents/js/ajaxFileUpload.js"></script>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/register.css?version=0.1">
<title>兴趣爱好</title>
</head>
<body>
	<div class="header-tip-bar">
		<div>兴趣爱好</div>
	</div>
	<div class="wrapper registerbobby">
		<div class="hobby">
			<div class="hobby-item enable" data-val="运动">
				<i class="icon-hobby icon-sport"></i>
				<label>运动</label>
			</div>
			<div class="hobby-item enable" data-val="音乐">
				<i class="icon-hobby icon-music"></i>
				<label>音乐</label>
			</div>
			<div class="hobby-item enable" data-val="影视">
				<i class="icon-hobby icon-movie"></i>
				<label>影视</label>
			</div>
			<div class="hobby-item enable" data-val="美食">
				<i class="icon-hobby icon-food"></i>
				<label>美食</label>
			</div>
			<div class="hobby-item enable" data-val="游戏">
				<i class="icon-hobby icon-game"></i>
				<label>游戏</label>
			</div>
			<div class="hobby-item enable" data-val="户外">
				<i class="icon-hobby icon-out"></i>
				<label>户外</label>
			</div>
			<div class="hobby-item enable" data-val="文学">
				<i class="icon-hobby icon-read"></i>
				<label>文学</label>
			</div>
			<div class="hobby-item enable" data-val="艺术">
				<i class="icon-hobby icon-dance"></i>
				<label>艺术</label>
			</div>
			<div class="hobby-item enable" data-val="动漫">
				<i class="icon-hobby icon-animation"></i>
				<label>动漫</label>
			</div>
		</div>
		<button class="register-btn-next enable">开始寻爱</button>
	</div>
	
	<script type="text/javascript">
		var user=null;
		$(function(){
			loadMe();
		});
		
		$('.hobby-item').click(function(){
			var obj = $(this).find('i');
			if($(this).hasClass('enable')){
				$(this).removeClass('enable');
				var str =obj.attr("class").trim();
				obj.attr("class",str+"-active");
				$('.register-btn-next').removeClass('enable');
			}else{
				$(this).addClass('enable');
				var str =obj.attr("class").trim();
				obj.attr("class",str.replace('-active',''));
				
				if($('.hobby .enable').length==9)
					$('.register-btn-next').addClass('enable');
			}
		});
		
		$('.register-btn-next').click(function(){
			if($(this).hasClass('enable')){
				return;
			}
			if(user==null){
				tipAlert('发生错误！用户不存在');
				return;
			}
			var hobby='';
			$('.hobby-item').each(function(){
				if($(this).hasClass('enable'))
					return;
				hobby+=$(this).attr('data-val')+' ';
			});
			hobby=hobby.trim(' ');
			
			showSpin();
			//提交
			$.post("/YuJian/Users/UpdateHobby"
	        		,{hobby:hobby}
	        		,function(data){
						closeSpin();
	        			if(data==null||data=='null'){
	        				tipAlert('修改失败T^T,请稍后后重试。')
	        				return;
	        			}
	        			//重新加载
	        			$.get("/YuJian/Users/ReloadUser");	//重载user
	        			window.location.href='/YuJian/CommonUsers';
	        		},'json');
		});
		
		//加载自己的信息
		function loadMe(){
			$.post('/YuJian/Users/GetMyInformation'
					,function(data){
				user=data;
				if(user!=null&&user.hobby!=null&&user.hobby!=""){
					var arr=user.hobby.split(' ');
					if(arr==null)
						return;
					for(var i=0;i<arr.length;i++){
						var obj = $('div[data-val='+arr[i]+']').find('i');
						
						$('div[data-val='+arr[i]+']').removeClass('enable');
						var str = obj.attr('class');
						obj.attr('class',str+"-active");
					}
					if(arr.length>0)
						$('.register-btn-next').removeClass('enable');
				}
			},'json');
		}
		
	</script>
</body>
</html>