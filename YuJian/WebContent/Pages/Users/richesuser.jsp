<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/rank.css">
<title>财富排行</title>
</head>
<body>
	<div class="header-tip-bar">
		<div>排行</div>
	</div>
	<div class="wrapper wealthPage">
		<div class="topbox" style="margin-top: -15px;">
			<div class="toptab">
				<a href="/YuJian/SortUsers">热度</a>
				<a href="#"  class="orange">财富</a>
			</div>
			<div class="mineshow rankline">
				<!-- 我的 -->
			</div>
		</div>
		<div class="rank">
			<!-- 排行 -->
		</div>
	</div>
	<jsp:include page="../foot.jsp"></jsp:include>
	
	<script>
	var page=-1;
	$(function(){
		page=1;
		showSpin();
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
		$.post("/YuJian/Users/GetMyRichesIndex"
				,{}
				,function(data){
					var temp='<span><a href="/YuJian/UserDetail?id={3}"><img src="{0}" alt="" class="avatar"></a><label><a href="/YuJian/UserDetail?id={3}">我的</a>'
							+'<b><span class="icon-sort icon-rich"></span><i>{1}</i></b>'
							+'</label></span>'
							+'<label>No.{2}</label>';
					temp=temp.format(data.headimgurl==''?"/YuJian/Contents/images/dfthead.png":data.headimgurl,data.riches,data.usersortid,data.userid);
					$('.mineshow').html(temp);
				},"json")
	}
	
	function loadData(){
		if(page<0)
			return;
		$.post("/YuJian/Users/GetUsersByRiches"
				,{ page:page,pageSize:16,usertype:1}
				,function(data){
					closeSpin();
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
								+'<b><span class="icon-sort icon-rich"></span><i>{2}</i></b>'
								+'</label></span>'
								+'<label>No.{3}</label></div>';
						var tempPrimary='<div class="primary rankline"><span><span><img src="/YuJian/Contents/images/huangguan.png" alt="" class="crowne">'
								+'<a href="{4}"><img src="{0}" alt="" class="avatar"></a></span><label>'
								+'<a href="{4}">{1}</a>'
								+'<b><span class="icon-sort icon-rich"></span><i>{2}</i></b>'
								+'</label></span><label>No.{3}</label></div>'
		            	if(data[i].usersortid==1){
		            		tempPrimary=tempPrimary.format(data[i].headimgurl==''?"/YuJian/Contents/images/dfthead.png":data[i].headimgurl
		            				,data[i].realname,data[i].riches,data[i].usersortid,'/YuJian/UserDetail?id='+data[i].userid);
		            		html+=tempPrimary;
		            	}
		            	else{
		            		temp=temp.format(data[i].headimgurl==''?"/YuJian/Contents/images/dfthead.png":data[i].headimgurl
		            				,data[i].realname,data[i].riches,data[i].usersortid,'/YuJian/UserDetail?id='+data[i].userid);
		            		html+=temp;
		            	}
					}
					$('.rank').append(html);
				},'json')
	}
	</script>
</body>
</html>