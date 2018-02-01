<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/gift.css">
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/leaveword.css?version=0.0.2">
<title>TA的礼物</title>
</head>
<body>
	<div class="header-tip-bar">
		<i class="icon icon-arrow" onclick="javascript:window.history.go(-1);"></i>
		<div id="title">礼物</div>
	</div>
	<!-- 和留言共用模板 -->
	<div class="wrapper leavewordPage usergiftPage" style="padding-bottom: 0;">
		<div class="userleavewords-board">
			<!-- 礼物 -->
		</div>
	</div>
	<a href="/YuJian/GiftShop?id=${id}"><img class="send-gift" src="/YuJian/Contents/images/giftto.png" /></a>
	<script>
	var userid='${id}';
	var page=-1;
	
	$(function(){
		page=1;
		loadLeaveWords();	//加载留言
		
		setTitle();	//设置标题
		$('.leavewordPage').css('min-height', document.body.clientHeight+"px");	//设置高度
	});
	
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
	        	loadLeaveWords();
	        }
	    }
	});
	
	//加载用户礼物
	function loadLeaveWords(){
		$.post('/YuJian/UserGifts/GetUserGifts'
				,{userid :userid,page:page,pageSize:10}
				,function(data){
					if(data==null||data.length<1)
						return;
					stop=false;
					
					var html='';
					for(var i=0;i<data.length;i++){
						if(data[i].gifts==null||data[i].gifts.length<1){
							continue;
						}
						var temp='<div class="leavewords-item"><div class="header">'
								+'<a href="/YuJian/UserDetail?id={0}"><img src="{1}" /></a>'
								+'<div class="leaveword-user">'
								+'<label class="realname">{2}</label>'
								+'<label class="leavedate">{3}</label>'
								+'</div></div>'
								+'{4}</div>'
						var imgs='';
						for(var j=0;j<data[i].gifts.length;j++){
							imgs+='<div class="leaveword-imgs"><img class="leaveword-body" src="'+data[i].gifts[j].gifturl+'" />'
									+'<span class="leavewordcontent">'+data[i].gifts[j].giftcontent+'</span>'
									+'<span class="leavewordcount">×'+data[i].gifts[j].giftcount+'<span></div>'
						}
						temp=temp.format(data[i].fromuserid
								,data[i].headimgurl==''?"/YuJian/Contents/images/dfthead.png":data[i].headimgurl
								,data[i].realname,isToday(data[i].usergifttime)
								,imgs);
						html+=temp;
					}
					$('.userleavewords-board').append(html);
				},'json');
	}
	
	//设置标题
	function setTitle(){
		$.post("/YuJian/Users/GetOtherUserInfo"
				,{userid:userid}
				,function(data){
			if(data==null)
				return;
			document.title=data.realname+"的礼物";
			$('#title').html(document.title);
		},'json');
	}
	
	//判断是否为今天
	function isToday(str) {
		var date=new Date(Date.parse(str.replace(/-/g,  "/")));
	    if (date.toDateString() === new Date().toDateString()) {
	    	var hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
	    	var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
	    	var second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
	        return "今天 "+hour+":"+minute+":"+second;
	    } else {
	        return str;
	    }
	}
	</script>
</body>
</html>