<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/seekintroducts.css">
<title>求撮合</title>
</head>
<body>
	<div class="header-tip-bar">
		<i class="icon icon-arrow" onclick="window.location.href='/YuJian/Mine';"></i>
		<div>求撮合</div>
	</div>
	<div class="wrapper seekintroductPage" style="padding-bottom: 0">
		<div class="topbox" style="margin-top: -15px;">
			<div class="toptab">
				<a href="/YuJian/MySeekIntroduct">求撮合</a>
				<a href="#" class="red">求介绍</a>
			</div>
		</div>
		<div class="seekintroduct-list">
			<!-- 求撮合列表 -->
			<div id="eplist-tip">暂无数据哦~</div>
		</div>
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
		$.post('/YuJian/SeekIntroducts/GetMySingleSeekIntroduct'
				,{page:page,pageSize:16}
				,function(data){
					if(data==null||data.length<1)
						return;
					stop=false;
					
					var html='';
					for(var i=0;i<data.length;i++){
						var temp='<div class="seekintroduct-item">'
								+'<label onclick="del(\'{0}\',this)" class="close-btn">×</label>'
								+'<div class="seekintroduct"><div class="single-user">'
								+'<a href="/YuJian/UserDetail?id={1}"><img src="{2}" /></a>'
								+'<div class="msg">'
								+'<label>姓名：{3}</label>'
								+'<label>交友ID：{4}</label>'
								+'<label>性别：{5}</label></div></div></div></div>';
						html+=temp.format(data[i].seekintroductid,data[i].fromuserid
								,data[i].fromheadimgurl,data[i].fromrealname
								,idFormat(data[i].fromuserid)
								,data[i].fromsex==1?'男':'女');
					}
					$('#eplist-tip').hide();
					$('.seekintroduct-list').append(html);
				},'json');
	}
	
	function del(id,obj){
		$.post('/YuJian/SeekIntroducts/Del',{seekintroductid:id});
		$(obj).parent().remove();
		
		if($('.seekintroduct-list .seekintroduct-item').length<1)
			$('#eplist-tip').show();
	}
	</script>
</body>
</html>