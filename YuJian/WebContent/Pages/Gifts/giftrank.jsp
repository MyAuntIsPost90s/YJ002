<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/rank.css">
<title>TA的礼物榜</title>
</head>
<body>
	<div class="header-tip-bar">
		<i class="icon icon-arrow" onclick="javascript:window.history.go(-1);"></i>
		<div>礼物榜</div>
	</div>
	<div class="wrapper giftrankPage">
		<div class="topbox" style="display: none">
			<div class="mineshow rankline">
				<!-- 我的排行 -->
			</div>
		</div>
		
		<div class="rank">
			<!-- 排行 -->
		</div>
	</div>
	
	<script>
	var page=-1;
	var userid='${id}';
	
	$(function(){
		page=1;
		loadData();
		loadMyRank();
	})
	
	//滚动加载事件
	var stop=false;
	var end=false;
	$(window).scroll(function() {
		if(stop==true||end==true||page<0)
			return;
		var totalheight = parseFloat($(window).scrollTop()+$(window).height()/2); 
	    if($(document).height()*0.5 <= totalheight){
	        if(stop==false){ 
	        	stop=true;
	        	page++;
	        	loadData();
	        }
	    }
	});
	
	function loadMyRank(){
		$.post("/YuJian/UserGifts/GetMyRank"
				,{userid:userid}
				,function(data){
					if(data==null)
						return;
					var temp='<span><a href="/YuJian/UserDetail?id={3}"><img src="{0}" alt="" class="avatar"></a><label><a href="/YuJian/UserDetail?id={3}">我的</a>'
							+'<b><img src="/YuJian/Contents/images/gifticon.png" alt="">价值<img src="/YuJian/Contents/images/dollar.png" alt="" class="dollar"><i>{1}</i></b>'
							+'</label></span>'
							+'<label>No.{2}</label>';
					temp=temp.format(data.headimgurl==''?"/YuJian/Contents/images/dfthead.png":data.headimgurl
							,data.usersortcount,data.usersortid,data.userid);
					$('.mineshow').html(temp);
					
					$('.topbox').show();
				},"json")
	}
	
	function loadData(){
		if(page<0)
			return;
		$.post("/YuJian/UserGifts/GetUserRank"
				,{ page:page,pageSize:16,userid:userid}
				,function(data){
					if(data==null||data.length<1){
						page=-1;
						return;
					}
					stop=false;
					var html='';
					for(var i=0;i<data.length;i++){
						var temp='<div class="rankline"><span>'
								+'<a href="{4}"><img src="{0}" alt="" class="avatar"></a><label>'
								+'<a href="{4}">{1}</a>'
								+'<b><img src="/YuJian/Contents/images/gifticon.png" alt="">价值<img src="/YuJian/Contents/images/dollar.png" alt="" class="dollar"><i>{1}</i></b>'
								+'</label></span>'
								+'<label>No.{3}</label></div>';
						var tempPrimary='<div class="primary rankline"><span><span><img src="/YuJian/Contents/images/huangguan.png" alt="" class="crowne">'
								+'<a href="{4}"><img src="{0}" alt="" class="avatar"></a></span><label>'
								+'<a href="{4}">{1}</a>'
								+'<b><img src="/YuJian/Contents/images/gifticon.png" alt="">价值<img src="/YuJian/Contents/images/dollar.png" alt="" class="dollar"><i>{2}</i></b>'
								+'</label></span><label>No.{3}</label></div>'
		            	if(data[i].usersortid==1){
		            		tempPrimary=tempPrimary.format(data[i].headimgurl==''?"/YuJian/Contents/images/dfthead.png":data[i].headimgurl
		            				,data[i].realname,data[i].usersortcount,data[i].usersortid,'/YuJian/UserDetail?id='+data[i].userid);
		            		html+=tempPrimary;
		            	}
		            	else{
		            		temp=temp.format(data[i].headimgurl==''?"/YuJian/Contents/images/dfthead.png":data[i].headimgurl
		            				,data[i].realname,data[i].usersortcount,data[i].usersortid,'/YuJian/UserDetail?id='+data[i].userid);
		            		html+=temp;
		            	}
					}
					$('.rank').append(html);
				},'json')
	}
	</script>
</body>
</html>