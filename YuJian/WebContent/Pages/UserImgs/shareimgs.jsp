<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/userimgs.css?version=0.0.3">
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/swiper-3.4.2.min.css?version=0.1">
<script type="text/javascript" src = "/YuJian/Contents/js/swiper-3.4.2.jquery.min.js"></script>
<title>照片浏览</title>
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="javascript:window.history.go(-1);" class="icon icon-arrow"></i>
		<div>照片浏览</div>
	</div>
	<div class="wrapper userimgs">
		 <div class="swiper-container" style="width: 20rem;height:calc(100% - 2rem);padding: 0">
	        <div class="swiper-wrapper">
	        
	        </div>
    	</div>
    	<div class="userimg-foot">
    		<div class="img-page"><span id="img-index">1</span>/<span id="img-count">1</span></div>
    	</div>
	</div>
	
	<script>
		var userid=${id};
		var user=null;
		$.ajaxSetup({async : false });
		$(function(){
			showSpin();
			loadUser();
			getUserImgs();
			loadSwiper();
		});
		
		//加载用户的信息
		function loadUser(){
			$.post('/YuJian/Users/GetOtherUserInfo'
					,{userid:userid}
					,function(data){
						user=data;
						if(user==null)
							return;
						var temp='<div class="swiper-slide"><img src="'+data.headimgurl+'"/></div>';
						$('.swiper-wrapper').append(temp);
					},'json');
		}

		//获取用户的照片
		function getUserImgs(){
			$.post('/YuJian/UserImgs/GetUserImgs'
					,{userid:userid}
					,function(data){
						closeSpin();
						$('#img-count').html(data.length+1);
						if(data==null||data.length<1){
							return;
						}
						var html='';
						for(var i=0;i<data.length;i++){
							var temp='<div class="swiper-slide"><img data-imgid="{0}" src="{1}"/></div>'
							temp=temp.format(data[i].userimgid,data[i].userimgurl);
							html+=temp;
						}
						$('.swiper-wrapper').append(html);
			},'json');
		}
		
		function loadSwiper(){
			var swiper = new Swiper ('.swiper-container', {
		        // 轮播图的方向，也可以是vertical方向
		        direction:'horizontal',        
		        //循环播放
		        loop: false,
		        // 播放的速度
		        speed:500,
		        autoplay:false,
		        observer:true,
				onSlideChangeEnd:function(swiper){
				   $('#img-index').html(swiper.activeIndex+1);
			    },
		 });
		}
	</script>
</body>
</html>