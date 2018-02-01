<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/collection.css?version=0。0。0">
<title>我的收藏</title>
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="javascript:window.history.go(-1);" class="icon icon-arrow"></i>
		<div>我的收藏</div>
	</div>
	<div class="wrapper collectPage">
		<div id="eplist-tip">暂无收藏哦~</div>
		<ul class="collect-list" style="padding: 0.3rem;display: none;background-color: #fff">
			<!-- 收藏用户 -->
		</ul>
	</div>
	
	<script type="text/javascript">
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
	    if($(document).height()*0.5 <= totalheight){
	        if(stop==false){ 
	        	stop=true;
	        	page++;
	        	loadData();
	        }
	    }
	});
	
	function loadData(){
		$.post('/YuJian/UserCollections/GetMyCollectUser'
				,{page:page,pageSize:16}
				,function(data){
					if(data==null||data.length<1)
						return;
					stop=false;
					
					var html='';
					for(var i=0;i<data.length;i++){
						var temp='<li>'
								+'<img onclick="collect(this)" data-id="{0}" src="/YuJian/Contents/images/icon-collect.png" class="icon-collect">'
								+'<a href="/YuJian/UserDetail?id={0}"><img src="{1}" class="picture"></a>'
								+'<p><span>{2}</span><i>{3} 岁</i></p>'
								+'</li>';
						html+=temp.format(data[i].usercollectionid,data[i].headimgurl,data[i].realname,getAge(data[i].birthday));
						
						$('#eplist-tip').hide();
						$('.collect-list').show();
						
					}
					
					$('.collect-list').append(html);
				},'json');
	}
	
	//收藏
	function collect(obj){
		if($(obj).attr("src")=="/YuJian/Contents/images/icon-collect.png"){
			$.post("/YuJian/UserCollections/UnCollect",{usercollectionid:$(obj).attr('data-id')});	
			$(obj).attr("src","/YuJian/Contents/images/icon-nocollect.png");
		}
		else{
			$.post("/YuJian/UserCollections/Collect",{usercollectionid:$(obj).attr('data-id')});
			$(obj).attr("src","/YuJian/Contents/images/icon-collect.png");
		}
		$(obj).parent().remove();
		
		if($('.collect-list li').length<1){
			$('#eplist-tip').show();
			$('.collect-list').hide();
		}
	}
	</script>
</body>
</html>