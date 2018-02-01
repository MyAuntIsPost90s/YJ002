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
    		<label class="btn-del">删除</label>
    	</div>
	</div>
	<script>
		var user=null;
		$(function(){
			showSpin();
			loadMe();
			getMyImgs();
			loadSwiper();
		});
		
		$('.btn-del').click(function(){
			var imgid=$('.swiper-slide-active img').attr('data-imgid')
			$('.swiper-slide-active').remove();
			
			var count=parseInt($('#img-count').html());
			var index=parseInt($('#img-index').html());
			
			count=count-1;
			$('#img-count').html(count);
			if(count<index){
				$('#img-index').html(count);
			}
			
			delImg(imgid);
		})
		
		//加载个人信息
		function loadMe(){
			$.post('/YuJian/Users/GetMyInformation'
					,function(data){
						user=data;
						closeSpin();
			},'json');
		}

		//获取我的照片
		function getMyImgs(){
			$.post('/YuJian/UserImgs/GetMyImgs'
					,function(data){
						$('#img-count').html(data.length);
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
		//删除照片
		function delImg(id){
			if(user==null)
				return;
			$.post("/YuJianRoom/Common/DelUserImg?userid="
					+user.userid+"&pwd="
					+user.password+"&userimgid="
					+id);
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